package com.test.bnna.admin.board.tripinfo;

import java.io.File;
import java.io.UnsupportedEncodingException;
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
 * 관리자 페이지의 여행정보 게시판 관련 컨트롤러입니다.
 * @author 김다은
 */
@Controller
public class TripInfoController {
	
	@Autowired
	private ITripInfoDAO dao;
	
	@Autowired
	private ITripInfoImgDAO idao;
	
	/**
	 * 
	 * @param req
	 * @param resp
	 * @return 
	 */
	@RequestMapping(value="/admin/board/tripinfo/list.action", method={RequestMethod.GET})
	public String list(HttpServletRequest req, HttpServletResponse resp) {
		
		HashMap<String,String> map = new HashMap<String,String>();
		
		//검색
		String search = req.getParameter("search");
		
		if (!(search == null || search.equals(""))) {
			map.put("search", search);
		}
		
		
		//페이징
		int nowPage = 0;		//현재 페이지 번호
		int totalCount = 0;		//총 게시물 수 
		int pageSize = 10;		//한 페이지당 출력 개수
		int totalPage = 0;		//총 페이지 수
		int begin = 0;			//rnum 시작번호
		int end = 0;			//rnum 끝번호
		int n = 0;				//페이지바 관련 변수
		int loop = 0;			//페이지바 관련 변수
		int blockSize = 10;		//페이지바 관련 변수
		
		String page = req.getParameter("page");
		
		if (page == null || page == "") {
			//기본 -> page = 1
			nowPage = 1;
		} else {
			nowPage = Integer.parseInt(page);
		}
		
		begin = ((nowPage - 1) * pageSize) + 1;
		end = begin + pageSize - 1;
		
		//HashMap에 정보를 넘겨
		map.put("begin", begin + "");
		map.put("end", end + "");		
		
		
		List<TripInfoDTO> list = dao.list(map);
		
		totalCount = dao.getTotalCount(map); //총 게시물 수
		
		totalPage = (int)Math.ceil((double)totalCount / pageSize); //총 페이지 수
		
		
		String pagebar = "";
		
		loop = 1;
		n = ((nowPage - 1) / blockSize) * blockSize + 1;
		
		
		//이전 10페이지로
		if(n == 1) {
			pagebar += String.format("<a class='disabled' href=\"#!\" aria-label=\"Previous\">"
						+ "                &laquo;"
						+ "            </a>");
		} else {
			pagebar += String.format("<a href=\"/bnna/admin/board/tripinfo/list.action?page=%d\" aria-label=\"Previous\">"
						+ "                &laquo;"
						+ "            </a>", n - 1);			
		}
		
		
		
		while (!(loop > blockSize || n > totalPage)) {

			if (nowPage == n) {
				pagebar += "<a class='active' ";
			} else {
				pagebar += "<a ";
			}
			pagebar += String.format("href=\"/bnna/admin/board/tripinfo/list.action?page=%d\">%d</a>", n, n);

			loop++;
			n++;

		}
		
		
		//다음 10페이지로 이동
		if (n > totalPage) {
			pagebar += String.format("<a class='disabled' href=\"#!\" aria-label=\"Next\">"
						+ "                &raquo;"
						+ "            </a>");
			//a href = "#" 본인 페이지 항상 위, "#!" 위로 올라가는 현상 사라짐
		} else {
			pagebar += String.format("<a href=\"/bnna/admin/board/tripinfo/list.action?page=%d\" aria-label=\"Next\">"
					+ "                &raquo;"
					+ "            </a>", n);
		}
		
		
		
		req.setAttribute("list", list);
		req.setAttribute("search", search);
		req.setAttribute("pagebar", pagebar);
		req.setAttribute("nowPage", nowPage);
		
		return "admin.board.tripinfo.list";
	}
	
	
	@RequestMapping(value="/admin/board/tripinfo/view.action", method={RequestMethod.GET})
	public String view(HttpServletRequest req, HttpServletResponse resp, HttpSession session, String seq) {
		
		String search = req.getParameter("search"); //검색어
		String page = req.getParameter("page"); //page - view.jsp 한테 넘기기 위해!
		
		dao.updateReadcount(seq); //조회수 증가. session 처리 안함
		
		TripInfoDTO dto = dao.view(seq);
		
		//dto에서 thread만 빼서 보관하기
		int thread = dto.getThread();
		
		
		// 게시글 번호로 댓글정보 가져오기
		List<TripInfoCmtDTO> cmtlist = dao.cmtlist(seq);
		
		// 게시글 번호로 image 가져오기
		List<TripInfoImgDTO> ilist = idao.list(seq);
		
		// 현재 thread로 관련글 리스트 가져오기
		List<TripInfoDTO> rlist = dao.rlist(thread);
		
		req.setAttribute("dto", dto);
		req.setAttribute("cmtlist", cmtlist);
		req.setAttribute("ilist", ilist);
		req.setAttribute("rlist", rlist);
		
		req.setAttribute("search", search);
		req.setAttribute("page", page);
		
		return "admin.board.tripinfo.view";
	}
	
	
	@RequestMapping(value="/admin/board/tripinfo/write.action", method= {RequestMethod.GET})
	public String write(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
		
		String reply = req.getParameter("reply");
		String thread = req.getParameter("thread");
		String depth = req.getParameter("depth");
		
		req.setAttribute("reply", reply);
		req.setAttribute("thread", thread);
		req.setAttribute("depth", depth);
		
		return "admin.board.tripinfo.write";
	}
	
	@RequestMapping(value="/admin/board/tripinfo/writeok.action", method= {RequestMethod.POST})
	public void writeok(HttpServletRequest req, HttpServletResponse resp, HttpSession session, TripInfoDTO dto) throws UnsupportedEncodingException {

		//1. 
		session = req.getSession();
		
		//1. 
		req.setCharacterEncoding("UTF-8");
		
		
		
		String reply = "";
		int parentThread = 0;
		int parentDepth = 0;
		
		MultipartHttpServletRequest multi = (MultipartHttpServletRequest)req;
		List<MultipartFile> multiList = multi.getFiles("image");
		
		
		// 답글
		try {
			
			reply = multi.getParameter("reply");
			
			//view.do -> 부모글의 thread값 + 부모글의 depth값
			parentThread = Integer.parseInt(multi.getParameter("thread"));
			parentDepth = Integer.parseInt(multi.getParameter("depth"));
			
			
		} catch (Exception e) {
			System.out.println(e);
		}

		int thread = 0;
		int depth = 0;
		
		
		if (reply.equals("n")) {
			
			//1. 새글 쓰기
			//a. 게시물 중 가장 큰 thread를 찾아서 그 값에 +1000 한 값을 새글의 thread 값으로 사용한다.(단, 첫번째 글은 이전 글이 존재하지 않기 때문에 일단 1000을 넣는다.)
			//b. 새글의 depth는 무조건 0을 넣는다.
			
			System.out.println(dao.getThread());
			
			thread = dao.getThread();
			
			
			depth = 0;
					
		} else {
			
			//2. 답변글 쓰기
			//a. 게시물의 모든 thread 값 중 답변글의 부모글 thread 값보다 작고, 이전 새글의 thread 값보다 큰 글들을 모두 찾아서 thread - 1 업데이트 한다.
			//b. 답변글의 thread 값은 부모글의 thread - 1을 넣는다.
			//c. 답변글의 depth 값은 부모글의 depth + 1을 넣는다.
			
			
			//이전 새글의 thread
			int previousThread = (int)Math.floor((parentThread - 1) / 1000) * 1000;
			
			HashMap<String,Integer> map = new HashMap<String,Integer>();
			
			map.put("parentThread", parentThread);
			map.put("previousThread", previousThread);
			
			dao.updateThread(map);
			
			
			thread = parentThread - 1;
			depth = parentDepth + 1;
			
		}
		
		
		dto.setThread(thread);
		dto.setDepth(depth);
		
		
		
		// 파일업로드
		if (multiList.size() == 1 && multiList.get(0).getOriginalFilename().equals("")) {
		
		} else {
			
			String filename="";
			
			try {
				
				for (int i = 0; i < multiList.size(); i++) {
					
					String path = req.getRealPath("/resources/image/board/tripinfo/");
					
					filename = getFileName(path, multiList.get(i).getOriginalFilename());
					
					//이동시킬 최종 경로 + 파일명
					File file = new File(path + "\\" + filename);
					
					//무조건 덮어쓰기 -> 중복 방지 -> 넘버링 직접 구현..
					multiList.get(i).transferTo(file); //renameTo()와 동일
					
				}
				
			} catch (Exception e) {
				System.out.println(e);
			}

		}
		
		
		// DTO에 저장
		ArrayList<TripInfoImgDTO> ilist = new ArrayList<TripInfoImgDTO>();
		
		for (int i = 0; i < multiList.size(); i++) {
			TripInfoImgDTO idto = new TripInfoImgDTO();
			idto.setImage(multiList.get(i).getOriginalFilename());
			idto.setOrgimage(multiList.get(i).getOriginalFilename());
			
			System.out.println("idto : " + multiList.get(i).getOriginalFilename());
			
			ilist.add(idto);
		}
		
		
		try {
			
			System.out.println("controller에서 dto depth: " + dto.getDepth());
			
			int result = dao.write(dto);	//게시글 DB에 데이터 넣기
			
			
			String seq = dao.getTripInfoSeq();	//여행정보 글번호 가져오기
			
			for (int i = 0; i < ilist.size(); i++) {
				ilist.get(i).setSeqTripInfo(seq);
			}
			
			int fileResult = idao.addTripInfoImg(ilist);	//여행정보 사진 DB에 데이터 넣기
			
			
			if (fileResult == 1) {
				resp.sendRedirect("/bnna/admin/board/tripinfo/list.action");
			} else {
				resp.sendRedirect("/bnna/admin/board/tripinfo/write.action");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private String getFileName(String path, String filename) {
		
		int n = 1; //인덱스 숫자
		int index = filename.indexOf(".");
		String tempName = filename.substring(0, index); //"dog"
		String tempExt = filename.substring(index); //".png"
		
		while (true) {
			
			File file = new File(path + "\\" + filename); //files\dog.png
			
			if (file.exists()) {
				//있다 -> 중복 -> 파일명 수정
				filename = tempName + "(" + n + ")" + tempExt;
				n++;
				
			} else {
				//없다 -> 반환
				return filename;
			}
			
		}
	}


	@RequestMapping(value="/admin/board/tripinfo/edit.action", method= {RequestMethod.GET})
	public String edit(HttpServletRequest req, HttpServletResponse resp, HttpSession session, String seq) {
		
		//해당 seq의 여행정보 게시글 가져오기
		TripInfoDTO dto = dao.get(seq);
		
		// 리뷰번호로 이미지가져오기
		List<TripInfoImgDTO> ilist = idao.list(seq);
		
		req.setAttribute("dto", dto);
		req.setAttribute("ilist", ilist);
		
		return "admin.board.tripinfo.edit";
	}
	
	@RequestMapping(value="/admin/board/tripinfo/editok.action", method= {RequestMethod.POST})
	public void editok(HttpServletRequest req, HttpServletResponse resp, HttpSession session, TripInfoDTO dto) {
		
		
		int result = dao.edit(dto);
		
		try {
			if (result == 1) {
				resp.sendRedirect("/bnna/admin/board/tripinfo/view.action?seq=" + dto.getSeq());
			} else {
				resp.sendRedirect("/bnna/admin/board/tripinfo/edit.action?seq=" + dto.getSeq());
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	
	@RequestMapping(value="/admin/board/tripinfo/delok.action", method={RequestMethod.GET})
	public void delok(HttpServletRequest req, HttpServletResponse resp, String seq) { 
		
		String path = req.getRealPath("/resources/image/board/tripinfo/");
		
		int result = dao.del(path, seq);
		
		
		// 3.
		
		try {
			
			if (result == 1) {
				resp.sendRedirect("/bnna/admin/board/tripinfo/list.action");
			} else {
				resp.sendRedirect("/bnna/admin/board/tripinfo/view.action?seq="+seq);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@RequestMapping(value="/member/board/tripinfo/cmtok.action", method={RequestMethod.POST})
	public void cmtok(HttpServletRequest req, HttpServletResponse resp) { 
	
		
	}
	
	
}
