package com.luepro.starter.member.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.luepro.starter.member.model.Member;
import com.luepro.starter.member.service.LoginService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getLogin(Locale locale, HttpServletRequest request, HttpSession session) {
		Member member = service.autoLogin(request.getRemoteAddr());
		if(member!=null) {
			session.setAttribute("memberInfo", member);
		}
		if(session.getAttribute("memberInfo")!=null) {
			System.out.println("이미 로그인중임");
			return "redirect:./select";
		}
		return "Member/Login";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String postLogin(@ModelAttribute Member member, HttpServletRequest request, HttpSession session) {
		if(member.isAutoCheck()) {
			member.setAutoIpAddress(request.getRemoteAddr());
		}
		Member memberInfo = service.login(member);
		if(memberInfo!=null) {
			session.setAttribute("memberInfo", memberInfo);
			return "redirect:./select";
		}
		return "Member/Login";
	}
}
