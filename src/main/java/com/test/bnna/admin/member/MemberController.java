package com.test.bnna.admin.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {

	@RequestMapping(value="/admin/member/template.action", method={RequestMethod.GET})
	public String template() {
		
		return "admin.member.template";
	}
	
}
