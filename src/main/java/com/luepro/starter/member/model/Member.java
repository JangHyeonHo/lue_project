package com.luepro.starter.member.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 회원 정보
 * @author LUE
 *
 */
public class Member implements MemberInterface {
	
	private int memberNo;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private boolean autoCheck;
	private String autoIpAddress;
	
	public int getMemberNo() {
		return memberNo;
	}
	public Member setMemberNo(int memberNo) {
		this.memberNo = memberNo;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public Member setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public Member setPassword(String password) {
		this.password = passwordMD5(password);
		return this;
	}
	public String getFirstName() {
		return firstName;
	}
	public Member setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	public String getLastName() {
		return lastName;
	}
	public Member setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}	
	public boolean isAutoCheck() {
		return autoCheck;
	}
	public Member setAutoCheck(boolean autoCheck) {
		this.autoCheck = autoCheck;
		return this;
	}
	public String getAutoIpAddress() {
		return autoIpAddress;
	}
	public Member setAutoIpAddress(String autoIpAddress) {
		this.autoIpAddress = autoIpAddress;
		return this;
	}
	
	@Override
	public void allCharacterInfomationCall() {
		// TODO Auto-generated method stub

	}
	
	//회원 비밀번호 암호화
	@Override
	public String passwordMD5(String password) {
		// TODO Auto-generated method stub
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			byte[] data = md.digest();
			StringBuffer sbf = new StringBuffer();
			for(byte b : data) {
				sbf.append(Integer.toString((b&0xff) + 0x100, 16).substring(1));
			}
			password = sbf.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.out.println("찾을수 없는 알고리즘 에러");
			e.printStackTrace();
			return null;
		}
		
		return password;
	}

}
