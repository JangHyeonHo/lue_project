package com.luepro.starter.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luepro.starter.member.model.Member;
import com.luepro.starter.member.repository.MemberRepository;
import com.luepro.starter.other.ModelingTest;

@Service
public class LoginService implements LoginInterface {
	
	@Autowired
	private MemberRepository repository;

	@Override
	public Member login(Member member) {
		// TODO Auto-generated method stub
		Member loginInfo = repository.Login(member);
		ModelingTest.ModelBlackTest(loginInfo);
		if(loginInfo != null && member.isAutoCheck()) {
			repository.IpaddrSaver(loginInfo.setAutoIpAddress(member.getAutoIpAddress()));
		} else if(loginInfo != null && !member.isAutoCheck()) {
			repository.noAutoLogin(loginInfo.getMemberNo());
		}
		return loginInfo;
	}

	@Override
	public Member autoLogin(String ipaddr) {
		// TODO Auto-generated method stub
		Member member = repository.autoLogIn(ipaddr);
		if(member!=null && member.isAutoCheck()==true) {
			return member;
		}
		return null;
	}

}
