package com.luepro.starter.character.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.luepro.starter.other.ModelingTest;

@Controller
public class CharacterSelectWindowController {
	
	@RequestMapping(value="/select", method=RequestMethod.GET)
	public String getCharacterWindowOpen(HttpSession session) {
		if(session.getAttribute("memberInfo")==null) {
			return "redirect:./login";
		}
		return "Character/CharacterSelectWindow";
	}
	
	@RequestMapping(value="/select/create", method=RequestMethod.GET)
	public String getCharacterCreateWindow(HttpSession session) {
		if(session.getAttribute("memberInfo")==null) {
			return "redirect:./../login";
		}
		return "Character/CharacterCreateWindow";
	}
	
	@RequestMapping(value="/select/create", method=RequestMethod.POST)
	public String postCharacterCreateWindow(HttpSession session, @ModelAttribute com.luepro.starter.character.model.Character character) {
		if(session.getAttribute("memberInfo")==null) {
			return "redirect:./../login";
		}
		ModelingTest.ModelBlackTest(character);
		return "Character/CharacterCreateWindow";
	}
	
}
