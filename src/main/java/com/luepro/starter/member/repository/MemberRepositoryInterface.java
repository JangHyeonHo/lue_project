package com.luepro.starter.member.repository;

import com.luepro.starter.member.model.Member;

public interface MemberRepositoryInterface {
	public Integer SignUp(Member member);
	public Member Login(Member member);
	public Integer passwordChange(int mno);
	public Member autoLogIn(String ipaddr);
	public String emailCheck(String email);
	public Integer IpaddrSaver(Member member);
	public Integer noAutoLogin(int mno);
}
