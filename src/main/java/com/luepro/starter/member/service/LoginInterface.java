package com.luepro.starter.member.service;

import com.luepro.starter.member.model.Member;

public interface LoginInterface {
	public Member login(Member member);
	public Member autoLogin(String ipaddr);
	
}
