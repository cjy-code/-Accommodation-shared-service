package com.test.bnna.member.bnbsearch;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 숙소 검색과 관련된 데이터 처리, 비즈니스 로직을 맡는 컨트롤러입니다.
 * @author 조아라
 *
 */
@Controller
public class BnBSearchController {
	
	@Autowired
	private IBnBSearchDAO dao;
	
	@Autowired
	private IBnBPicDAO pdao;
	
	@Autowired
	private IDibsDAO ddao;
	
	@RequestMapping(value="/member/bnbsearch/list.action", method={RequestMethod.GET})
	public String list(HttpServletRequest req, HttpServletResponse resp, SearchConditionDTO dto) {
		
//		req.setAttribute("condition", null);
//		List<BnBSearchResultDTO> list=dao.list(dto);
		
		return "member.nosidebar.bnbsearch.list";
	}
	
	/**
	 * 숙소 검색결과를 가져오는 메서드입니다.
	 * @param req
	 * @param resp
	 * @param dto 검색조건정보를 담고 있는 DTO입니다.
	 * @return member/bnbsearch/result.jsp를 호출합니다.
	 */
	@RequestMapping(value="/member/bnbsearch/result.action", method={RequestMethod.GET})
	public String result(HttpServletRequest req, HttpServletResponse resp, HttpSession session, SearchConditionDTO dto) {
		
		// 회원정보를 가져와서 관심숙소를 찾는다.
		session.setAttribute("seq", 1); // 로그인, 로그아웃 기능 구현 안하고 1번회원 데이터 보여주기로 했다.
		
		String seqMember=session.getAttribute("seq").toString();
		List<DibsDTO> dlist=ddao.list(seqMember);
		
		// 숙소를 검색조건으로 검색한다.
		List<BnBSearchResultDTO> list=dao.list(dto);
		List<BnBPicDTO> plist=pdao.picList(list); // 찾은 숙소정보로 이미지를 가져온다.
		
		// 소개글 너무 길지 않게 잘라준다.
		list=cut(list);
		
		req.setAttribute("condition", dto); // 검색한 조건 결과페이지에도 넣어주기
		session.setAttribute("condition", dto);
		
		// 숙소검색결과
		req.setAttribute("list", list);
		session.setAttribute("searchResultList", list);
		// 관심숙소
		req.setAttribute("dlist", dlist);
		session.setAttribute("dibsList", dlist);
		// 숙소사진리스트
		req.setAttribute("plist", plist);
		return "member.nosidebar.bnbsearch.result";
	}
	
	/**
	 * 검색결과에 필터를 적용한 결과를 가져오는 메서드입니다.
	 * @param req
	 * @param resp
	 * @param dto 적용된 필터정보를 담고 있는 DTO입니다.
	 * @return member/bnbsearch/result.jsp를 호출합니다.
	 */
	@RequestMapping(value="/member/bnbsearch/filterresult.action", method={RequestMethod.GET})
	public String filterresult(HttpServletRequest req, HttpServletResponse resp, HttpSession session, FilterDTO dto) {
		List<BnBSearchResultDTO> list=(List<BnBSearchResultDTO>) session.getAttribute("searchResultList");
		List<DibsDTO> dlist=(List<DibsDTO>)session.getAttribute("dibsList");
		SearchConditionDTO condition=(SearchConditionDTO) session.getAttribute("condition");
		
		List<BnBSearchResultDTO> newList=new ArrayList<BnBSearchResultDTO>();
		
		for(BnBSearchResultDTO rdto:list) {
			// 결과숙소가 필터조건을 충족하는지 검색
			// 문제 발생! 어떤 조건이 걸릴지,
			// 몇 개의 조건이 충족되어야하는지 모르는 상황.
			// 전체 조건을 훑되, 필터값이 1인 조건만 꺼내서 비교
			// 값이 똑같이 1이어야 한다.
			
			if (dto.getBedroom() > rdto.getBedroom()) { // 숙소가 필터조건을 충족하지 못하는 상황
				continue; // 필터조건을 충족하지 못하면 제외시킨다.
			}
			
			if (dto.getBed() > rdto.getBed()) { // 숙소가 필터조건을 충족하지 못하는 상황
				continue; // 필터조건을 충족하지 못하면 제외시킨다.
			}
			
			if (dto.getBathroom() > rdto.getBathroom()) { // 숙소가 필터조건을 충족하지 못하는 상황
				continue; // 필터조건을 충족하지 못하면 제외시킨다.
			}
			
			if (dto.getAmenity()==1 && rdto.getAmenity()!=1) { // 숙소가 필터조건을 충족하지 못하는 상황
				continue; // 필터조건을 충족하지 못하면 제외시킨다.
			}
			
			if (dto.getDryer()==1 && rdto.getDryer()!=1) { // 숙소가 필터조건을 충족하지 못하는 상황
				continue; // 필터조건을 충족하지 못하면 제외시킨다.
			}
			
			if (dto.getKitchen()==1 && rdto.getKitchen()!=1) { // 숙소가 필터조건을 충족하지 못하는 상황
				continue; // 필터조건을 충족하지 못하면 제외시킨다.
			}
			
			if (dto.getHeating()==1 && rdto.getHeating()!=1) {
				continue;
			}
			
			if (dto.getWashing()==1 && rdto.getWashing()!=1) {
				continue;
			}
			
			if (dto.getWifi()==1 && rdto.getWifi()!=1) {
				continue;
			}
			
			if (dto.getWorkroom()==1 && rdto.getWorkroom()!=1) {
				continue;
			}
			
			if (dto.getBabybed()==1 && rdto.getBabybed()!=1) {
				continue;
			}
			
			if (dto.getBreakfast()==1 && rdto.getBreakfast()!=1) {
				continue;
			}
			
			if (dto.getTv()==1 && rdto.getTv()!=1) {
				continue;
			}
			
			if (dto.getTv()==1 && rdto.getTv()!=1) {
				continue;
			}
			
			if (dto.getAc()==1 && rdto.getAc()!=1) {
				continue;
			}
			
			if (dto.getHairdryer()==1 && rdto.getHairdryer()!=1) {
				continue;
			}
			
			if (dto.getParking()==1 && rdto.getParking()!=1) {
				continue;
			}
			
			if (dto.getDisabled()==1 && rdto.getDisabled()!=1) {
				continue;
			}
			
			if (dto.getPet()==1 && rdto.getPet()!=1) {
				continue;
			}
			
			if (dto.getSmoke()==1 && rdto.getSmoke()!=1) {
				continue;
			}
			
			if (dto.getEvt()==1 && rdto.getEvt()!=1) {
				continue;
			}
			
			if (dto.getSelfcheck()==1 && rdto.getSelfcheck()!=1) {
				continue;
			}
			
			newList.add(rdto);
			
		}
		
		// 확정된 리스트에 맞춰 이미지를 가져온다.
		List<BnBPicDTO> plist=pdao.picList(list);
		
		req.setAttribute("condition", condition);
		req.setAttribute("list", newList);
		req.setAttribute("dlist", dlist);
		req.setAttribute("plist", plist);
		
		return "member.nosidebar.bnbsearch.result";
	}
	
	/**
	 * 숙소소개글을 너무 길지 않게 잘라주는 메서드입니다.
	 * @param list 숙소정보를 나타내는 DTO들의 List입니다.
	 * @return 소개글을 자른 후의 List가 반환됩니다.
	 */
	private List<BnBSearchResultDTO> cut(List<BnBSearchResultDTO> list) {
		for (BnBSearchResultDTO dto : list) {
			if (dto.getIntro().length()>150) {
				dto.setIntro(dto.getIntro().substring(0, 150)+"...");
			}
		}
		
		return list;
	}
	
	/**
	 * 검색조건으로 검색한 숙소들을 지도로 볼 수 있게 해주는 메서드입니다.
	 * @param req
	 * @param resp
	 * @param session
	 * @param dto 적용된 필터정보를 담고 있는 DTO입니다.
	 * @return member/bnbsearch/map.jsp을 호출합니다.
	 */
	@RequestMapping(value="/member/bnbsearch/map.action", method={RequestMethod.GET})
	public String map(HttpServletRequest req, HttpServletResponse resp, HttpSession session, SearchConditionDTO dto) {
		
		// 회원정보를 가져와서 관심숙소를 찾는다.
		session.setAttribute("seq", 1); // 로그인, 로그아웃 기능 구현 안하고 1번회원 데이터 보여주기로 했다.
		
		String seqMember=session.getAttribute("seq").toString();
		List<DibsDTO> dlist=ddao.list(seqMember);
		
		// 숙소를 검색조건으로 검색한다.
		List<BnBSearchResultDTO> list=dao.list(dto);
		List<BnBPicDTO> plist=pdao.picList(list); // 찾은 숙소정보로 이미지를 가져온다.
		
		// 소개글 너무 길지 않게 잘라준다.
		list=cut(list);
		
		req.setAttribute("condition", dto); // 검색한 조건 결과페이지에도 넣어주기
		session.setAttribute("condition", dto);
		
		// 숙소검색결과
		req.setAttribute("list", list);
		session.setAttribute("searchResultList", list);
		// 관심숙소
		req.setAttribute("dlist", dlist);
		session.setAttribute("dibsList", dlist);
		// 숙소사진리스트
		req.setAttribute("plist", plist);
		return "member.nosidebar.bnbsearch.map";
	}
	
	/**
	 * 검색결과에 필터를 적용한 결과를 가져오는 메서드입니다.
	 * @param req
	 * @param resp
	 * @param dto 적용된 필터정보를 담고 있는 DTO입니다.
	 * @return member/bnbsearch/result.jsp를 호출합니다.
	 */
	@RequestMapping(value="/member/bnbsearch/filtermap.action", method={RequestMethod.GET})
	public String filterMap(HttpServletRequest req, HttpServletResponse resp, HttpSession session, FilterDTO dto) {
		
		List<BnBSearchResultDTO> list=(List<BnBSearchResultDTO>) session.getAttribute("searchResultList");
		List<DibsDTO> dlist=(List<DibsDTO>)session.getAttribute("dibsList");
		SearchConditionDTO condition=(SearchConditionDTO) session.getAttribute("condition");
		List<BnBSearchResultDTO> newList=new ArrayList<BnBSearchResultDTO>();
		
		for(BnBSearchResultDTO rdto:list) {
			// 결과숙소가 필터조건을 충족하는지 검색
			// 문제 발생! 어떤 조건이 걸릴지,
			// 몇 개의 조건이 충족되어야하는지 모르는 상황.
			// 전체 조건을 훑되, 필터값이 1인 조건만 꺼내서 비교
			// 값이 똑같이 1이어야 한다.
			
			if (dto.getBedroom() > rdto.getBedroom()) { // 숙소가 필터조건을 충족하지 못하는 상황
				continue; // 필터조건을 충족하지 못하면 제외시킨다.
			}
			
			if (dto.getBed() > rdto.getBed()) { // 숙소가 필터조건을 충족하지 못하는 상황
				continue; // 필터조건을 충족하지 못하면 제외시킨다.
			}
			
			if (dto.getBathroom() > rdto.getBathroom()) { // 숙소가 필터조건을 충족하지 못하는 상황
				continue; // 필터조건을 충족하지 못하면 제외시킨다.
			}
			
			if (dto.getAmenity()==1 && rdto.getAmenity()!=1) { // 숙소가 필터조건을 충족하지 못하는 상황
				continue; // 필터조건을 충족하지 못하면 제외시킨다.
			}
			
			if (dto.getDryer()==1 && rdto.getDryer()!=1) { // 숙소가 필터조건을 충족하지 못하는 상황
				continue; // 필터조건을 충족하지 못하면 제외시킨다.
			}
			
			if (dto.getKitchen()==1 && rdto.getKitchen()!=1) { // 숙소가 필터조건을 충족하지 못하는 상황
				continue; // 필터조건을 충족하지 못하면 제외시킨다.
			}
			
			if (dto.getHeating()==1 && rdto.getHeating()!=1) {
				continue;
			}
			
			if (dto.getWashing()==1 && rdto.getWashing()!=1) {
				continue;
			}
			
			if (dto.getWifi()==1 && rdto.getWifi()!=1) {
				continue;
			}
			
			if (dto.getWorkroom()==1 && rdto.getWorkroom()!=1) {
				continue;
			}
			
			if (dto.getBabybed()==1 && rdto.getBabybed()!=1) {
				continue;
			}
			
			if (dto.getBreakfast()==1 && rdto.getBreakfast()!=1) {
				continue;
			}
			
			if (dto.getTv()==1 && rdto.getTv()!=1) {
				continue;
			}
			
			if (dto.getTv()==1 && rdto.getTv()!=1) {
				continue;
			}
			
			if (dto.getAc()==1 && rdto.getAc()!=1) {
				continue;
			}
			
			if (dto.getHairdryer()==1 && rdto.getHairdryer()!=1) {
				continue;
			}
			
			if (dto.getParking()==1 && rdto.getParking()!=1) {
				continue;
			}
			
			if (dto.getDisabled()==1 && rdto.getDisabled()!=1) {
				continue;
			}
			
			if (dto.getPet()==1 && rdto.getPet()!=1) {
				continue;
			}
			
			if (dto.getSmoke()==1 && rdto.getSmoke()!=1) {
				continue;
			}
			
			if (dto.getEvt()==1 && rdto.getEvt()!=1) {
				continue;
			}
			
			if (dto.getSelfcheck()==1 && rdto.getSelfcheck()!=1) {
				continue;
			}
			
			newList.add(rdto);
			
		}
		
		// 확정된 리스트에 맞춰 이미지를 가져온다.
		List<BnBPicDTO> plist=pdao.picList(list);
		
		req.setAttribute("condition", condition);
		req.setAttribute("list", newList);
		req.setAttribute("dlist", dlist);
		req.setAttribute("plist", plist);
			
		return "member.nosidebar.bnbsearch.map";
	}
}
