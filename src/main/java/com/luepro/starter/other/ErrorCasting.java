package com.luepro.starter.other;

import org.springframework.ui.Model;

public class ErrorCasting {
	
	public static String alertCasting(Model model, String direct, boolean isRedirect, String alertCode, boolean notCode, String title) {
		String jsName;
		if(isRedirect) {
			jsName = "location.href='"+direct+"'";
		} else {
			jsName = "history.back()";
		}
		String alert;
		if(!notCode) {
			alert = "alert('<spring:message code='" + alertCode + "'/>')";
			model.addAttribute("alertCode", alertCode);
		}
		model.addAttribute("direct", jsName);
		model.addAttribute("title", title);
		return "Error";
	}
	
}
