package com.luepro.starter.character.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 캐릭터 정보창
 **/
public class Character {
	
	private long characterNo;
	private String cName;

	private Status status;
	private int statusPoint;
	
	private int healthPoint;
	private int skillPoint;
	
	
	private String sex;
	private int lv;
	private int exp;
	private long money;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date creDate;
	
	private String job;


	public long getCharacterNo() {
		return characterNo;
	}
	public void setCharacterNo(long characterNo) {
		this.characterNo = characterNo;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public int getStatusPoint() {
		return statusPoint;
	}
	public void setStatusPoint(int statusPoint) {
		this.statusPoint = statusPoint;
	}
	public int getHealthPoint() {
		return healthPoint;
	}
	public void setHealthPoint(int healthPoint) {
		this.healthPoint = healthPoint;
	}
	public int getSkillPoint() {
		return skillPoint;
	}
	public void setSkillPoint(int skillPoint) {
		this.skillPoint = skillPoint;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getLv() {
		return lv;
	}
	public void setLv(int lv) {
		this.lv = lv;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public long getMoney() {
		return money;
	}
	public void setMoney(long money) {
		this.money = money;
	}
	public Date getCreDate() {
		return creDate;
	}
	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}
	
	
	
	
}
