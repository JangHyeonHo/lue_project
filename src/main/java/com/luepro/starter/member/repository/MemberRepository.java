package com.luepro.starter.member.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luepro.starter.member.model.Member;

@Repository
public class MemberRepository implements MemberRepositoryInterface {
	
	@Autowired
	private SqlSessionTemplate template;
	
	private String namespace = "members";

	@Override
	public Integer SignUp(Member member) {
		// TODO Auto-generated method stub
		return template.insert(namespace+".insert", member);
	}

	@Override
	public Member Login(Member member) {
		// TODO Auto-generated method stub
		return template.selectOne(namespace+".login", member);
	}

	@Override
	public Integer passwordChange(int mno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member autoLogIn(String ipaddr) {
		// TODO Auto-generated method stub
		return template.selectOne(namespace+".autoLogin", ipaddr);
	}

	@Override
	public String emailCheck(String email) {
		// TODO Auto-generated method stub
		return template.selectOne(namespace+".isExistEmail",email);
	}

	@Override
	public Integer IpaddrSaver(Member member) {
		// TODO Auto-generated method stub
		return template.update(namespace+".ipUpdate", member);
	}

	@Override
	public Integer noAutoLogin(int mno) {
		// TODO Auto-generated method stub
		return template.update(namespace+".autoCancel", mno);
	}

	
	
}
