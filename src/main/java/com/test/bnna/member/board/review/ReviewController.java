package com.test.bnna.member.board.review;

import java.io.File;
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
 * 회원 리뷰게시판 관련 비즈니스로직을 처리하는 컨트롤러입니다.
 * @author 조아라
 *
 */
@Controller
public class ReviewController {
	
	@Autowired
	private IReviewDAO dao;
	
	@Autowired
	private IReviewPicDAO pdao;
	
	@Autowired
	private IBookForMemberDAO bdao;
	
	/**
	 * 회원 개인의 리뷰목록을 가져오는 메서드입니다.
	 * @param req
	 * @param resp
	 * @param session
	 * @param page
	 * @return member/board/review/list.jsp를 호출합니다.
	 */
	@RequestMapping(value="/member/board/review/list.action", method={RequestMethod.GET})
	public String listForMember(HttpServletRequest req, HttpServletResponse resp, HttpSession session, String page) {
		
		// 1번회원 데이터를 보여주기로 했으므로 회원번호 1번으로 설정
		session.setAttribute("seq", 1);
		
		// 회원번호 가져와서
		String seqMember=session.getAttribute("seq").toString();
		
		// 그 회원의 리뷰목록 찾아오기(pagination 추가)
		
		HashMap<String,String> map = new HashMap<String,String>();
		
		//페이징
		int nowPage = 0;		//현재 페이지 번호
		int totalCount = 0;		//총 게시물 수
		int pageSize = 6;		//한페이지 당 출력 개수
		int totalPage = 0;		//총 페이지 수
		int begin = 0;			//rnum 시작 번호
		int end = 0;			//rnum 끝 번호
		int n = 0;				//페이지바 관련 변수
		int loop = 0;			//페이지바 관련 변수
		int blockSize = 10;		//페이지바 관련 변수
		
		if (page == null || page == "") {
			//기본 -> page = 1
			nowPage = 1;
		} else {
			nowPage = Integer.parseInt(page);
		}
		
		begin = ((nowPage - 1) * pageSize) + 1;
		end = begin + pageSize - 1;
		
		map.put("begin", begin + "");
		map.put("end", end + "");
		map.put("seq", seqMember); // 회원번호도 넣어준다.
		
		
		// 그 회원의 리뷰목록 찾아오기
		List<ReviewForMemberDTO> list=dao.listForMember(map);
		
		totalCount = dao.getTotalCount(seqMember); //총 게시물 수
		
		totalPage = (int)Math.ceil((double)totalCount / pageSize); //총 페이지 수
		
		String pagebar = "";
		loop=1;
		n = ((nowPage - 1) / blockSize) * blockSize + 1;
		
		if (n == 1) {
			pagebar += String.format("<div class='pagearea'>\n" + 
					"		    <div class=\"pagination\">\n" + 
					"		        <a>&laquo;</a>");
		} else {
			pagebar += String.format("<div class=pagearea>\n" + 
					"		    <div class=\"pagination\">\n" + 
					"		        <a href=\"/bnna/member/board/review/list.action?page=%d\">&laquo;</a>", n - 1);
		}
		
		while (!(loop > blockSize || n > totalPage)) {
			
			if (nowPage == n) {
				pagebar += String.format("<a href=\'/bnna/member/board/review/list.action?page=%d\' class=\'active\'>%d</a>", n , n);
			} else {
				pagebar += String.format("<a href=\'/bnna/member/board/review/list.action?page=%d\'>%d</a>", n, n);
			}
			
			loop++;
			n++;
		}
		
		//다음 10페이지로 이동
		if (n > totalPage) {
			pagebar += String.format("<a>&raquo;</a>\n" + 
					"		    </div>\n" + 
					"		</div>");
		} else {
			pagebar += String.format("<a href=\'/bnna/member/board/review/list.action?page=%d\'>&raquo;</a>\n" + 
					"		    </div>\n" + 
					"		</div>", n);
		}
		

		req.setAttribute("list", list);
		req.setAttribute("pagebar", pagebar);
		req.setAttribute("nowPage", nowPage);
		
		return "member.board.review.list";
	}
	
	/**
	 * 리뷰 추가하기 화면을 호출하는 메서드입니다.
	 * @param req
	 * @param resp
	 * @param session
	 * @return member/board/review/add.jsp를 호출합니다.
	 */
	@RequestMapping(value="/member/board/review/add.action", method={RequestMethod.GET})
	public String addForMember(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
		
		// 1번회원 데이터 보여주기로 했으므로.
		session.setAttribute("seq", 1);
		
		// 리뷰 작성시 예약번호를 선택할 수 있도록
		// 예약했지만 아직 리뷰가 작성되지 않은 예약번호들을 가져온다.
		String seqMember=session.getAttribute("seq").toString();
		List<BookForMemberDTO> blist=bdao.list(seqMember);
		
		req.setAttribute("blist", blist);
		
		return "member.board.review.add";
	}
	
	/**
	 * 추가할 리뷰정보를 받아와서 DB추가와 파일업로드 작업을 하는 메서드입니다.
	 * @param req
	 * @param resp
	 * @param session
	 * @param dto 추가할 리뷰정보를 담고 있는 DTO입니다.
	 */
	@RequestMapping(value="/member/board/review/addok.action", method={RequestMethod.POST})
	public void addokForMember(HttpServletRequest req, HttpServletResponse resp, HttpSession session, AddReviewDTO dto) {
		
		MultipartHttpServletRequest multi=(MultipartHttpServletRequest)req;
		List<MultipartFile> multiList = multi.getFiles("reviewpic");
		ArrayList<ReviewPicDTO> plist=new ArrayList<ReviewPicDTO>();
		
		// 파일업로드
		if (multiList.size() == 1 && multiList.get(0).getOriginalFilename().equals("")) {

        } else {

        	String filename="";

        	try {

	            for (int i = 0; i < multiList.size(); i++) {

	            	String path=req.getRealPath("/resources/image/board/review/");
	    			filename=getFileName(req, path, multiList.get(i).getOriginalFilename());

	    			File file;

	    			// 맥 OS일 때는 경로를 다르게 입력해줘야 한다.
	    			if (req.getHeader("user-agent").indexOf("Mac OS")!=0) {
	    				// 이동시킬 최종 경로 + 파일명
	    				file=new File(path + "/" + filename);
	    			} else {
	    				// 이동시킬 최종 경로 + 파일명
	    				file=new File(path + "\\" + filename);
	    			}

	    			// 이동
	    			multiList.get(i).transferTo(file); // renameTo()와 동일

	    			// 리뷰이미지DTO를 만들어서 파일이름을 넣고 리스트에 담는다.
	    			ReviewPicDTO pdto=new ReviewPicDTO();
	    			pdto.setImage(filename);
	    			pdto.setOrgimage(multiList.get(i).getOriginalFilename());

	    			plist.add(pdto);

	            }

        	}catch (Exception e) {
            	System.out.println("ReviewController.addok()");
    			e.printStackTrace();
            }
        }

		try {

			// DB에 데이터 넣어주기
			int result=dao.add(dto);

			if (plist.size()!=0) {
				// 방금 넣은 리뷰 글번호가져와서 리뷰사진도 DB에 넣기
				int seq=dao.getCurrentReviewSeq();
	
				for (int i = 0; i < plist.size(); i++) {
					plist.get(i).setSeqreview(seq);
				}
	
				int fileResult=pdao.addReviewPic(plist);
	
				if (fileResult>=1) {
					resp.sendRedirect("/bnna/member/board/review/list.action");
				} else {
					resp.sendRedirect("/bnna/member/board/review/add.action");
				}
			}
			
			if (result>=1) {
				resp.sendRedirect("/bnna/member/board/review/list.action");
			} else {
				resp.sendRedirect("/bnna/member/board/review/add.action");
			}

		} catch (Exception e) {
			System.out.println("ReviewController.addok()");
			e.printStackTrace();
		}
	}
	
	/**
	 * 중복 파일명이 있는지 확인하고 중복된 파일명이면 '_'+숫자 를 추가하는 메서드입니다.
	 * @param request
	 * @param path
	 * @param filename
	 * @return 새로운 파일명을 반환합니다.
	 */
	private String getFileName(HttpServletRequest request, String path, String filename) {
		
		int n=1; // 인덱스
		int index=filename.indexOf(".");
		String tempName=filename.substring(0, index); // "dog"
		String tempExt=filename.substring(index); // ".png"
		
		while (true) {
			
			File file;
			
			// 맥 OS일 때는 경로를 다르게 입력해줘야 한다.
			if (request.getHeader("user-agent").indexOf("Mac OS")!=0) {
				// 이동시킬 최종 경로 + 파일명
				file=new File(path + "/" + filename);
			} else {
				// 이동시킬 최종 경로 + 파일명
				file=new File(path + "\\" + filename); // files\dog.png
			}
			
			if (file.exists()) {
				// 있다 -> 중복 -> 파일명 수정
				filename=tempName+"_"+n+tempExt;
				n++;
			} else {
				// 없다 -> 반환
				return filename;
			}
		}
	}
	
	/**
	 * 리뷰 수정하기 화면을 호출하는 메서드입니다.
	 * @param req
	 * @param resp
	 * @param session
	 * @param seq
	 * @return
	 */
	@RequestMapping(value="/member/board/review/edit.action", method={RequestMethod.GET})
	public String editForSpecificMember(HttpServletRequest req, HttpServletResponse resp, HttpSession session, String seq) {
		
		// 리뷰번호로 전체정보가져오기
		ReviewForMemberDTO dto=dao.info(seq);
		
		// 리뷰번호로 이미지가져오기
		List<ReviewPicDTO> plist=pdao.list(seq);
		
		req.setAttribute("dto", dto);
		req.setAttribute("plist", plist);
		
		return "member.board.review.edit";
	}
	
	/**
	 * 리뷰를 수정하는 메서드입니다.
	 * @param req
	 * @param resp
	 * @param session
	 * @param dto 수정할 리뷰정보를 담고 있는 DTO입니다.
	 */
	@RequestMapping(value="/member/board/review/editok.action", method={RequestMethod.POST})
	public void editokForSpecificMember(HttpServletRequest req, HttpServletResponse resp, HttpSession session, EditReviewDTO dto) { // 1.

		// 1. 데이터 가져오기
		// 2. DB 위임 -> edit
		// 3. 결과 처리
		
		int result=dao.edit(dto);
		
		try {
			
			if (result==1) {
				resp.sendRedirect("/bnna/member/board/review/view.action?seq="+dto.getSeq());
			} else {
				resp.sendRedirect("/bnna/member/board/review/edit.action?seq="+dto.getSeq());
			}
			
		} catch (Exception e) {
			System.out.println("ReviewController.delok()");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 리뷰를 조회하는 메서드입니다.
	 * @param req
	 * @param resp
	 * @param session
	 * @param seq 리뷰번호입니다.
	 * @return member/board/review/view.jsp를 호출합니다.
	 */
	@RequestMapping(value="/member/board/review/view.action", method={RequestMethod.GET})
	public String viewForSpecificMember(HttpServletRequest req, HttpServletResponse resp, HttpSession session, String seq) {
		
		// 리뷰번호로 전체정보가져오기
		ReviewForMemberDTO dto=dao.info(seq);
		
		// 엔터처리
		dto.setContent(dto.getContent().replace("\r\n", "<br>"));
		
		// 리뷰번호로 이미지가져오기
		List<ReviewPicDTO> plist=pdao.list(seq);
		
		req.setAttribute("dto", dto);
		req.setAttribute("plist", plist);
		
		return "member.board.review.view";
	}
	
	/**
	 * 리뷰를 삭제하는 메서드입니다.
	 * @param req
	 * @param resp
	 * @param session
	 * @param seq 삭제할 리뷰번호입니다.
	 */
	@RequestMapping(value="/member/board/review/delok.action", method={RequestMethod.GET})
	public void delokForSpecificMember(HttpServletRequest req, HttpServletResponse resp, HttpSession session, String seq) { // 1.

		// 1. 데이터 가져오기
		// 2. DB 위임 -> delete
		// 3. 결과 처리
		
		// 2.
		
		String path=req.getRealPath("/resources/image/board/review/");
		
		// dao 내부에서 원본이미지파일삭제, DB의 이미지파일정보 삭제, DB의 리뷰글 삭제가 모두 일어난다.
		int result=dao.del(path, seq);
		
		// 3.
		
		try {
			
			if (result==1) {
				resp.sendRedirect("/bnna/member/board/review/list.action");
			} else {
				resp.sendRedirect("/bnna/member/board/review/view.action?seq="+seq);
			}
			
		} catch (Exception e) {
			System.out.println("ReviewController.delok()");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 리뷰 수정하기 화면에서 ajax로 호출되는, 개별 리뷰이미지를 삭제하는 메서드입니다.
	 * @param req
	 * @param resp
	 * @param session
	 * @param seq 삭제할 리뷰이미지 번호입니다.
	 * @return 삭제 후 member/board/review/edit.jsp를 호출합니다.
	 */
	@RequestMapping(value="/member/board/review/delfile.action", method={RequestMethod.GET})
	public String delFileForSpecificMember(HttpServletRequest req, HttpServletResponse resp, HttpSession session, String seq) { // 1.

		// 1. 데이터 가져오기
		// 2. DB 위임 -> delete
		// 3. 결과 처리
		
		String path=req.getRealPath("/resources/image/board/review/");
		
		// 2.
		pdao.del(path, seq);
		
		return "member.board.review.edit";
		
	}
	
}
