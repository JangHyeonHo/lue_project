package com.luepro.starter.member.service;

import com.luepro.starter.member.model.Member;

public interface SignUpInterface {
	public Integer SignUp(Member member);
	public boolean emailCheck(String email);
}
