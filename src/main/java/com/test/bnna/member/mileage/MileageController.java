package com.test.bnna.member.mileage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MileageController {

	@Autowired
	private IMileageDAO dao;
	
	@RequestMapping(value="/member/mileage/list.action", method={RequestMethod.GET})
	public String list(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
		
		List<MileageDTO> list = dao.list();
		
		req.setAttribute("list", list);
		
		return "member.mypagesidebar.mileage.list";		
	}
	
	
}
