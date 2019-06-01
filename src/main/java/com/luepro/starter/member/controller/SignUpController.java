package com.luepro.starter.member.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luepro.starter.member.model.Member;
import com.luepro.starter.member.service.SignUpService;
import com.luepro.starter.other.ModelingTest;

@Controller
public class SignUpController {
	
	private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);
	
	@Autowired
	private SignUpService service;
	
	@RequestMapping(value = "/signup",method = RequestMethod.GET)
	public String getSignUp(Locale locale) {
		return "Member/SignUp";
	}
	
	@RequestMapping(value = "/signup",method = RequestMethod.POST)
	public String postSignUp(@ModelAttribute Member member) {
		ModelingTest.ModelBlackTest(member);
		service.SignUp(member);
		return "Member/SignUp";
	}
	
	@ResponseBody
	@RequestMapping(value = "/signup/idcheck",method = RequestMethod.POST)
	public String postEmailCheck(@RequestParam("email") String email) {
		System.out.println(email);
		if(service.emailCheck(email)) {
			return "true";
		}
		return "false";
	}
}
