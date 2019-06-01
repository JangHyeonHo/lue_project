package com.luepro.starter.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luepro.starter.member.model.Member;
import com.luepro.starter.member.repository.MemberRepository;

@Service
public class SignUpService implements SignUpInterface {
	
	@Autowired
	private MemberRepository repository;
	
	@Override
	public Integer SignUp(Member member) {
		return repository.SignUp(member);
	}

	@Override
	public boolean emailCheck(String email) {
		// TODO Auto-generated method stub
		String checkEmail = repository.emailCheck(email);
		
		return checkEmail==null;
	}
}
