package com.test.bnna.member.board.blackboard;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 블랙리스트 신고게시판 관련 업무를 담당하는 Controller
 * @author 김주혁
 *
 */
@Controller
public class BlackBoardController {

	@Autowired
	private IBlackBoardDAO dao;
	
	@Autowired
	private IMemberDAO mdao;
	
	@Autowired
	private IBlackBoardImgDAO idao;
	
	@Autowired
	private IBlackBoardCmtDAO cdao;
	
	/**
	 * 신고대상회원 검색 메서드
	 * JSON 형식의 데이터 반환
	 * @param req
	 * @param resp
	 * @param session
	 * @param condition 검색조건
	 * @param keyword 검색어
	 */
	@RequestMapping(value="/member/board/blackboard/searchmember.action", method={RequestMethod.GET})
	public void searchmember(HttpServletRequest req, HttpServletResponse resp, HttpSession session, String condition, String keyword) {
		
		resp.setCharacterEncoding("UTF-8"); //인코딩
		resp.setContentType("application/json"); //*****JSON
		
		//검색 결과 받아오기
		List<MemberDTO> mlist = mdao.list(condition, keyword);
		
		try {
			PrintWriter writer = resp.getWriter();
			
			writer.print("[");
			
			for (int i=0; i<mlist.size(); i++) {
				writer.print("{");
				writer.print(String.format("\"seq\":\"%s\",", mlist.get(i).getSeq()));
				writer.print(String.format("\"name\":\"%s\",", mlist.get(i).getName()));
				writer.print(String.format("\"id\":\"%s\"", mlist.get(i).getId()));
				writer.print("}");
				
				//마지막 , 빼기
				if (i < mlist.size() - 1) {
					writer.print(",");
				}
			}
			
			writer.print("]");
			
			writer.close();
		} catch (Exception e) {
			System.out.println("BlackBoardController.searchmember()");
			e.printStackTrace();
		}
		
	} //searchmember
	
	/**
	 * 게시글 작성 화면 호출 메서드
	 * @param req
	 * @param resp
	 * @param session
	 * @param page 페이지번호
	 * @param reply 답글여부
	 * @param thread 답글작성시 부모글 thread
	 * @param depth 답글작성시 부모글 depth
	 * @param seqParent 부모글번호
	 * @return member/board/blackboard/add.jsp
	 */
	@RequestMapping(value="/member/board/blackboard/add.action", method={RequestMethod.GET})
	public String member_add(HttpServletRequest req, HttpServletResponse resp, HttpSession session, String page, String reply, String thread, String depth, String seqParent) {
		
		//데이터 전달
		req.setAttribute("reply", reply);
		req.setAttribute("thread", thread);
		req.setAttribute("depth", depth);
		req.setAttribute("nowPage", page);
		req.setAttribute("seqParent", seqParent);
		
		return "member.board.blackboard.add";
	} //member_add
	
	/**
	 * 게시글 작성 메서드
	 * @param req
	 * @param resp
	 * @param session
	 * @param dto 추가할 정보를 담은 dto
	 * @param page 페이지번호
	 * @param reply 답글여부
	 */
	@RequestMapping(value="/member/board/blackboard/addok.action", method={RequestMethod.POST})
	public void member_addok(HttpServletRequest req, HttpServletResponse resp, HttpSession session, BlackBoardDTO dto, String page, String reply) {
		
		//회원번호
		dto.setSeqMember((String)session.getAttribute("seqMember"));
		
		int parentThread = 0;
		int parentDepth = 0;
		
		if (reply.equals("n")) {
			//새글쓰기
			
			dto.setThread(dao.getThread() + "");
			dto.setDepth("0");
			
		} else {
			parentThread = Integer.parseInt(dto.getThread());
			parentDepth = Integer.parseInt(dto.getDepth());
			//답글쓰기
			
			//이전 새글의 thread
			int previousThread = (int)Math.floor((parentThread - 1) / 1000) * 1000;
			
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			
			map.put("parentThread", parentThread);
			map.put("previousThread", previousThread);
			
			dao.updateThread(map);
			
			dto.setThread((parentThread - 1) + "");
			dto.setDepth((parentDepth + 1) + "");
			
		}
		
		//DB 위임 -> add
		int result = dao.addok(dto);
		
		try {
			
			if (result == 1) {
				//add 성공
				
				//1. 게시판 번호 가져오기
				String addSeqBlackBoard = dao.getAddSeq();
				
				//2. 파일 저장 + 테이블에 추가할 글번호, 파일명, 원본파일명 저장
				MultipartHttpServletRequest mreq = (MultipartHttpServletRequest)req;
				
				List<MultipartFile> flist = mreq.getFiles("image");
				
				//이미지 추가 안한 경우도 flist.size()가 1이다...
				//실제 파일 이름을 가져와 빈문자열이면 이미지 첨부X
				//flist.get(0).getOriginalFilename()
				
				if (flist.get(0).getOriginalFilename() != "") {
					//이미지 추가한 경우만
					
					List<BlackBoardImgDTO> ilist = new ArrayList<BlackBoardImgDTO>();
					
					//path : D:\class\spring\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\AirBnNa\resources\image\board\blackboard
					String path = req.getRealPath("resources/image/board/blackboard");
					
					for (MultipartFile mf : flist) {
						BlackBoardImgDTO idto = new BlackBoardImgDTO();
						
						String orgimage = mf.getOriginalFilename(); //원본파일명
						String image = getFileName(path, orgimage); //중복 검사 후 파일명 저장
						
						String saveImage = path + "\\" + image; //저장될 경로 + 파일명
						
						mf.transferTo(new File(saveImage)); //파일 저장

						idto.setSeqBlackBoard(addSeqBlackBoard);
						idto.setImage(image);
						idto.setOrgimage(orgimage);
						
						ilist.add(idto); //글번호, 파일명, 원본파일명 저장
						
					}

					//3. 이미지 테이블에 추가하기
					int result2 = idao.add(ilist);
					
					if (result2 == ilist.size()) {
						//add 성공
						
						resp.setCharacterEncoding("UTF-8");
						
						PrintWriter writer = resp.getWriter();
						
						writer.print("<html><head><meta charset='utf-8' /></head><body>");
						writer.print("<script>");
						writer.print("alert('글쓰기 성공!!\\n목록으로 이동합니다.');");
						writer.print("location.href='/bnna/member/board/blackboard/list.action?page="+ page +"';");
						writer.print("</script>");
						writer.print("</body></html>");
						
						writer.close();
								
					} else {
						//image add 실패 -> 이미 추가된 글 삭제 후 뒤로가기
						int result3 = dao.del(addSeqBlackBoard);
						
						if (result3 == 1) {
							//추가된 글 삭제 후 돌아가기
							resp.setCharacterEncoding("UTF-8");
							
							PrintWriter writer = resp.getWriter();
							
							writer.print("<html><head><meta charset='utf-8' /></head><body>");
							writer.print("<script>");
							writer.print("alert('글쓰기 실패..\\n이전 페이지로 이동합니다.');");
							writer.print("history.back();");
							writer.print("</script>");
							writer.print("</body></html>");
							
							writer.close();
							
						} else {
							//추가된 글 삭제 안 됨...
							throw new Exception();
						}
						
					}
				} else {
					//이미지 추가 안한 경우
					resp.setCharacterEncoding("UTF-8");
					
					PrintWriter writer = resp.getWriter();
					
					writer.print("<html><head><meta charset='utf-8' /></head><body>");
					writer.print("<script>");
					writer.print("alert('글쓰기 성공!!\\n목록으로 이동합니다.');");
					writer.print("location.href='/bnna/member/board/blackboard/list.action?page="+ page +"';");
					writer.print("</script>");
					writer.print("</body></html>");
					
					writer.close();
				}

				
					
					

				
			} else {
				//add 실패
				
				resp.setCharacterEncoding("UTF-8");
				
				PrintWriter writer = resp.getWriter();
				
				writer.print("<html><head><meta charset='utf-8' /></head><body>");
				writer.print("<script>");
				writer.print("alert('글쓰기 실패..\\n이전 페이지로 이동합니다.');");
				writer.print("history.back();");
				writer.print("</script>");
				writer.print("</body></html>");
				
				writer.close();
				
			}			
			
		} catch (Exception e) {
			System.out.println("BlackBoardController.member_addok()");
			e.printStackTrace();
		}
		
	} //member_addok
	
	/**
	 * 게시글 수정 화면 호출 메서드
	 * @param req
	 * @param resp
	 * @param session
	 * @param seq
	 * @param page
	 * @param reply
	 * @return member/board/blackboard/edit.jsp
	 */
	@RequestMapping(value="/member/board/blackboard/edit.action", method={RequestMethod.GET})
	public String member_owner_edit(HttpServletRequest req, HttpServletResponse resp, HttpSession session, String seq, String page, String reply) {
		
		//DB -> select
		BlackBoardDTO dto = dao.get(seq);
		
		req.setAttribute("dto", dto);
		req.setAttribute("seq", seq);
		req.setAttribute("reply", reply);
		req.setAttribute("nowPage", page);
		
		return "member.board.blackboard.edit";
	} //member_owner_edit
	
	/**
	 * 게시글 수정 메서드
	 * @param req
	 * @param resp
	 * @param session
	 * @param dto 수정할 정보를 담은 dto
	 * @param page 페이지번호
	 * @param reply 답글여부
	 */
	@RequestMapping(value="/member/board/blackboard/editok.action", method={RequestMethod.POST})
	public void member_owner_editok(HttpServletRequest req, HttpServletResponse resp, HttpSession session, BlackBoardDTO dto, String page, String reply) {
		
		//데이터 가져오기 - parameter(dto)
		
		//회원번호
		dto.setSeqMember((String)session.getAttribute("seqMember"));
		
		//DB 위임 -> edit
		int result = dao.editok(dto);
		
		try {
			
			if (result == 1) {
				//edit 성공 > 이미지 수정파일 있는지 확인
				
				//2. 파일 저장 + 테이블에 추가할 글번호, 파일명, 원본파일명 저장
				MultipartHttpServletRequest mreq = (MultipartHttpServletRequest)req;
				
				List<MultipartFile> flist = mreq.getFiles("image");
				
				//이미지 추가 안한 경우도 flist.size()가 1이다...
				//실제 파일 이름을 가져와 빈문자열이면 이미지 첨부X
				//flist.get(0).getOriginalFilename()
				
				if (flist.get(0).getOriginalFilename() != "") {
					//이미지 추가한 경우만
					
					//1. 기존 이미지 삭제
					idao.del(dto.getSeq());
					
					//2. 수정 이미지 추가
					
					List<BlackBoardImgDTO> ilist = new ArrayList<BlackBoardImgDTO>();
					
					//webapp > resources > image > board > blackboard
					String path = req.getRealPath("resources/image/board/blackboard");
					
					for (MultipartFile mf : flist) {
						BlackBoardImgDTO idto = new BlackBoardImgDTO();
						
						String orgimage = mf.getOriginalFilename(); //원본파일명
						String image = getFileName(path, orgimage); //중복 검사 후 파일명 저장
						
						String saveImage = path + "\\" + image; //저장될 경로 + 파일명
						
						mf.transferTo(new File(saveImage)); //파일 저장

						idto.setSeqBlackBoard(dto.getSeq());
						idto.setImage(image);
						idto.setOrgimage(orgimage);
						
						ilist.add(idto); //글번호, 파일명, 원본파일명 저장
						
					}

					//3. 이미지 테이블에 추가하기
					int result2 = idao.add(ilist);
					
					if (result2 == ilist.size()) {
						//add 성공
						
						resp.setCharacterEncoding("UTF-8");
						
						PrintWriter writer = resp.getWriter();
						
						writer.print("<html><head><meta charset='utf-8' /></head><body>");
						writer.print("<script>");
						writer.print("alert('글수정 성공!!\\n상세 페이지로 이동합니다.');");
						writer.print("location.href='/bnna/member/board/blackboard/view.action?seq="+ dto.getSeq() +"&page="+ page +"&reply="+ reply +"';");
						writer.print("</script>");
						writer.print("</body></html>");
						
						writer.close();
								
					}
				} else {
					//이미지 추가 안한 경우
					//안내 후 view로 이동
					resp.setCharacterEncoding("UTF-8");
					
					PrintWriter writer = resp.getWriter();
					
					writer.print("<html><head><meta charset='utf-8' /></head><body>");
					writer.print("<script>");
					writer.print("alert('글수정 성공!!\\n상세 페이지로 이동합니다.');");
					writer.print("location.href='/bnna/member/board/blackboard/view.action?seq="+ dto.getSeq() +"&page="+ page +"&reply="+ reply +"';");
					writer.print("</script>");
					writer.print("</body></html>");
					
					writer.close();
				}
				
			} else {
				//edit 실패
				
				resp.setCharacterEncoding("UTF-8");
				
				PrintWriter writer = resp.getWriter();
				
				writer.print("<html><head><meta charset='utf-8' /></head><body>");
				writer.print("<script>");
				writer.print("alert('글수정 실패..\\n이전 페이지로 이동합니다.');");
				writer.print("history.back();");
				writer.print("</script>");
				writer.print("</body></html>");
				
				writer.close();
				
			}			
			
		} catch (Exception e) {
			System.out.println("BlackBoardController.member_owner_editok()");
			e.printStackTrace();
		}
		
	} //member_owner_editok
	
	/**
	 * 게시글 조회 화면 호출 메서드
	 * @param req
	 * @param resp
	 * @param session
	 * @param seq 게시글번호
	 * @param page 페이지번호
	 * @param reply 답글여부
	 * @return member/board/blackboard/view.jsp
	 */
	@RequestMapping(value="/member/board/blackboard/view.action", method={RequestMethod.GET})
	public String view(HttpServletRequest req, HttpServletResponse resp, HttpSession session, String seq, String page, String reply) {
		
		//1. 데이터 가져오기(seq, reply)
		//2. DB 위임 -> select
		//3. 결과 처리
		
		//조회수 증가하기
		if (session.getAttribute("readBlackBoard") == null || (boolean)session.getAttribute("readBlackBoard") == false) {
			dao.updateReadCnt(seq);
			session.setAttribute("readBlackBoard", true);
		}
		
		//글 정보 가져오기
		BlackBoardDTO dto = dao.get(seq);
		
		//<br>처리
		dto.setContent(dto.getContent().replace("\r\n", "<br>"));
		dto.setContent(dto.getContent().replace("\n", "<br>"));
		
		//이미지 가져오기
		List<BlackBoardImgDTO> ilist = dao.getImages(seq);
		
		//댓글 가져오기
		List<BlackBoardCmtDTO> clist = dao.getComments(seq);
		
		for (BlackBoardCmtDTO cdto : clist) {
			//엔터 -> <br /> 변환 (클라이언트로 전달을 위해)
			cdto.setContent(cdto.getContent().replace("\r\n", "<br />"));			
		}
		
		
		req.setAttribute("seq", seq);
		req.setAttribute("nowPage", page);
		req.setAttribute("reply", reply);
		req.setAttribute("dto", dto);
		req.setAttribute("ilist", ilist);
		req.setAttribute("clist", clist);
		
		return "member.board.blackboard.view";
	} //view
	
	/**
	 * 댓글 추가 메서드
	 * JSON 형식의 데이터 반환
	 * @param req
	 * @param resp
	 * @param session
	 * @param seq 게시글번호
	 * @param content 댓글내용
	 */
	@RequestMapping(value="/member/board/blackboard/addcmt.action", method={RequestMethod.GET})
	public void member_addcmt(HttpServletRequest req, HttpServletResponse resp, HttpSession session, String seq, String content) {
		
		//1. 데이터 가져오기(seq, content)
		//2. DB 위임 -> insert
		//3. 결과 처리
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json"); //*****JSON
		
		//<br /> -> 엔터 변환 (DB 저장을 위해)
		content = content.replace("<br />", "\r\n");
		
		BlackBoardCmtDTO dto = new BlackBoardCmtDTO();
		
		dto.setSeqMember((String)session.getAttribute("seqMember"));
		dto.setSeqBlackBoard(seq);
		dto.setContent(content);
		
		//댓글 추가하기
		int result = cdao.add(dto);
				
		//돌려줄 데이터 가져오기(댓글번호, 회원번호, 회원이름, 회원아이디, 작성내용, 작성일)
		BlackBoardCmtDTO cdto = cdao.getAddCmt();
		
		//엔터 -> <br /> 변환 (클라이언트로 전달을 위해)
		cdto.setContent(cdto.getContent().replace("\r\n", "<br />"));
		
		try {
			
			if (result == 1) {
				//추가 성공				
				PrintWriter writer = resp.getWriter();
				
				writer.print("[");
				writer.print("{");
				writer.print(String.format("\"seq\":\"%s\",", cdto.getSeq()));
				writer.print(String.format("\"seqMember\":\"%s\",", cdto.getSeqMember()));
				writer.print(String.format("\"id\":\"%s\",", cdto.getId()));
				writer.print(String.format("\"name\":\"%s\",", cdto.getName()));
				writer.print(String.format("\"content\":\"%s\",", cdto.getContent()));
				writer.print(String.format("\"regdate\":\"%s\"", cdto.getRegdate()));
				writer.print("}");
				writer.print("]");
				
				writer.close();
				
			} else {
				//추가 실패
				PrintWriter writer = resp.getWriter();
				
				writer.print("<html><head><meta charset='utf-8' /></head><body>");
				writer.print("<script>");
				writer.print("alert('댓글 쓰기 실패..\\n이전 페이지로 이동합니다.');");
				writer.print("history.back();");
				writer.print("</script>");
				writer.print("</body></html>");
				
				writer.close();
			}			
			
		} catch (Exception e) {
			System.out.println("BlackBoardController.member_addcmt()");
			e.printStackTrace();
		}

	} //member_addcmt
	
	/**
	 * 댓글 삭제 메서드
	 * @param req
	 * @param resp
	 * @param session
	 * @param seqBlackBoardCmt 댓글번호
	 * @param seqBlackBoard 게시글번호
	 * @param page 페이지번호
	 * @param reply 답글여부
	 */
	@RequestMapping(value="/member/board/blackboard/delComment.action", method={RequestMethod.GET})
	public void member_cmt_owner_delComment(HttpServletRequest req, HttpServletResponse resp, HttpSession session, String seqBlackBoardCmt, String seqBlackBoard, String page, String reply) {
		
		resp.setCharacterEncoding("UTF-8");
		
		//데이터 받아오기
		
		//DB 위임 -> delete
		int result = cdao.del(seqBlackBoardCmt);

		//결과 처리
		try {
			
			if (result == 1) {
				//삭제 성공
				PrintWriter writer = resp.getWriter();
				
				writer.print("<html><head><meta charset='utf-8' /></head><body>");
				writer.print("<script>");
				writer.print("alert('댓글 삭제 성공!!\\n이전 페이지로 이동합니다.');");
				writer.print("location.href='/bnna/member/board/blackboard/view.action?seq="+ seqBlackBoard +"&page="+ page +"&reply="+ reply +"';");
				writer.print("</script>");
				writer.print("</body></html>");
				
				writer.close();
				
			} else {
				//삭제 실패
				PrintWriter writer = resp.getWriter();
				
				writer.print("<html><head><meta charset='utf-8' /></head><body>");
				writer.print("<script>");
				writer.print("alert('댓글 삭제 실패..\\n이전 페이지로 이동합니다.');");
				writer.print("history.back();");
				writer.print("</script>");
				writer.print("</body></html>");
				
				writer.close();
			}			
			
		} catch (Exception e) {
			System.out.println("BlackBoardController.member_cmt_owner_delComment()");
			e.printStackTrace();
		}

	} //member_cmt_owner_delComment
	
	/**
	 * 게시글 목록 조회 메서드
	 * @param req
	 * @param resp
	 * @param session
	 * @param page 페이지번호
	 * @param condition 검색조건
	 * @param keyword 검색어
	 * @return member/board/blackboard/list.jsp
	 */
	@RequestMapping(value="/member/board/blackboard/list.action", method={RequestMethod.GET})
	public String list(HttpServletRequest req, HttpServletResponse resp, HttpSession session, String page, String condition, String keyword) {

		//view.action > 새로고침 조회수 증가 방지
		session.setAttribute("readBlackBoard", false);
		
		//조건에 맞는 총 게시글 수에 필요한 세팅
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("condition", condition);
		map.put("keyword", keyword);
		
		//조건에 맞는 총 게시글 수 가져오기
		int totalCount = dao.getCount(map);
		
		//페이지바 세팅하기
		Pagination pagination = new Pagination(page, totalCount);
		
		//페이지바 만들기 + 가져오기
		String pagebar = pagination.getPagebar(map);
		
		//페이지 번호 가져오기
		int nowPage = pagination.getNowPage();

		//rownum 세팅하기
		map.put("begin", pagination.getBegin() + "");
		map.put("end", pagination.getEnd() + "");
		
		
		//페이지에 해당하는 게시글 가져오기
		List<BlackBoardDTO> list = dao.list(map);
		
		//사진 있는 게시글 번호 가져오기
		List<String> hasImageSeqList = dao.getSeqHasImage();
		
		//날짜 자르기 및 답글여부 적용, 사진 있는지 확인하기
		for (BlackBoardDTO dto : list) {
			dto.setRegdate(dto.getRegdate().substring(0, 10));
			if (dto.getSeqIssueMember() == null) {
				dto.setReply("y");
			} else {
				dto.setReply("n");
			}
			
			for (String seq : hasImageSeqList) {
				if (dto.getSeq().equals(seq)) {
					dto.setHasImage(true);
					break;
				}
				
			}
			
		}
		
		//결과 처리
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("list", list);
		req.setAttribute("pagebar", pagebar);
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("keyword", keyword);
		
		return "member.board.blackboard.list";
	} //list
	
	/**
	 * 게시글 삭제 메서드
	 * @param req
	 * @param resp
	 * @param session
	 * @param seq 게시글번호
	 * @param seqParent 부모글번호
	 */
	@RequestMapping(value="/member/board/blackboard/del.action", method= {RequestMethod.GET})
	public void member_owner_del(HttpServletRequest req, HttpServletResponse resp, HttpSession session, String seq, String seqParent) {
		
		resp.setCharacterEncoding("UTF-8");
		
		//1. 데이터 가져오기 - 매개변수
		//2. DB -> delete
		//3. 결과
		
		try {
			
			boolean has = false;
			
			//2.
			// - 답글이 있는지(1차)
			has = dao.hasReply(seq);
			
			if (has) {
				//답글 있음 -> 삭제X
				PrintWriter writer = resp.getWriter();
				
				writer.print("<html><head><meta charset='utf-8' /></head><body>");
				writer.print("<script>");
				writer.print("alert('답글 있는 게시글은 삭제할 수 없습니다.\\n이전 페이지로 이동합니다.');");
				writer.print("history.back();");
				writer.print("</script>");
				writer.print("</body></html>");
				
				writer.close();			
			} else {
				//답글 없음 -> 댓글이 있는지(2차)
				has = dao.hasComment(seq);
				
				if (has) {
					//댓글 있음 -> 삭제X
					PrintWriter writer = resp.getWriter();
					
					writer.print("<html><head><meta charset='utf-8' /></head><body>");
					writer.print("<script>");
					writer.print("alert('댓글 있는 게시글은 삭제할 수 없습니다.\\n이전 페이지로 이동합니다.');");
					writer.print("history.back();");
					writer.print("</script>");
					writer.print("</body></html>");
					
					writer.close();
				} else {
					//댓글 없음 -> 이미지 있는지(3차)
					has = idao.hasImage(seq);
					
					if (has) {
						//이미지 있음 -> 이미지 테이블에서 삭제(4차)
						idao.del(seq);
					}
					
					//게시판 테이블에서 삭제(5차)
					int result = dao.del(seq);
					
					if (result == 1) {
						//삭제 성공
						PrintWriter writer = resp.getWriter();
						
						writer.print("<html><head><meta charset='utf-8' /></head><body>");
						writer.print("<script>");
						writer.print("alert('글 삭제 성공!!\\n목록으로 이동합니다.');");
						writer.print("location.href='/bnna/member/board/blackboard/list.action';");
						writer.print("</script>");
						writer.print("</body></html>");
						
						writer.close();
					} else {
						//삭제 실패
						PrintWriter writer = resp.getWriter();
						
						writer.print("<html><head><meta charset='utf-8' /></head><body>");
						writer.print("<script>");
						writer.print("alert('글 삭제 실패..\\n이전 페이지로 이동합니다.');");
						writer.print("history.back();");
						writer.print("</script>");
						writer.print("</body></html>");
						
						writer.close();
					}
					
				}
				
			}			
			
		} catch (Exception e) {
			System.out.println("BlackBoardController.member_owner_del()");
			e.printStackTrace();
		}
		
		
	} //member_owner_del
	
	/**
	 * 임시로 생성한 로그인 메서드
	 * @param req
	 * @param resp
	 * @param session
	 * @param seqMember 회원번호
	 */
	@RequestMapping(value="/member/board/blackboard/login.action", method= {RequestMethod.GET})
	public void login(HttpServletRequest req, HttpServletResponse resp, HttpSession session, String seqMember) {
		
		//1. 데이터 가져오기(seqMember) - 매개변수
		//2. 인증 티켓 발급
		//3. 페이지 이동
		
		//2.
		session.setAttribute("seqMember", seqMember);
		
		//3.
		try {
			resp.sendRedirect("/bnna/member/board/blackboard/list.action");
		} catch (Exception e) {
			System.out.println("BlackBoardController.login()");
			e.printStackTrace();
		}
		
	} //login
	
	/**
	 * 로그아웃 메서드
	 * @param req
	 * @param resp
	 * @param session
	 */
	@RequestMapping(value="/member/board/blackboard/logout.action", method= {RequestMethod.GET})
	public void logout(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
		
		//1. 인증 티켓 폐기
		//2. 페이지 이동
		
		//1.
		session.invalidate();
		
		//2.
		try {
			resp.sendRedirect("/bnna/member/board/blackboard/list.action");
		} catch (Exception e) {
			System.out.println("BlackBoardController.logout()");
			e.printStackTrace();
		}
		
	} //logout
	
	/**
	 * 이미지 파일명 중복 검사 메서드
	 * @param path 경로
	 * @param filename 파일명
	 * @return 중복검사 후 파일명
	 */
	private String getFileName(String path, String filename) {
		
		//return System.currentTimeMillis() + "_" + filename;
		//return System.nanoTime() + "_" + filename;
		
		//dog.png -> dog_1.png -> dog_2.png
		
		//path = "files"
		//filename = "dog.png"
		
		int n = 1; //인덱스
		int index = filename.lastIndexOf(".");
		String tempName = filename.substring(0, index); //"dog"
		String tempExt = filename.substring(index); //".png"
		
		while (true) {
			File file = new File(path + "\\" + filename); //files\dog.png
			if (file.exists()) {
				//있다 -> 중복 -> 파일명 수정
				filename = tempName + "_" + n + tempExt;
				n++;
			} else {
				//없다 -> 반환
				return filename;
			}
		}
		
	} //getFileName	
	
}
