package com.luepro.starter.character.model;

public class Status {
		
	private int str;
	private int dex;
	private int intel;
	private int luc;
	
	private int fullHealthPoint;
	private int fullSkillPoint;
	
	private int atkRate;
	private int defRate;
	private int magRate;
	private int magdefRate;
	private double hitRate;
	private double evasiveRate;
	private double criticalRate;
	
	public int getStr() {
		return str;
	}
	public Status setStr(int str) {
		this.str = str;
		return this;
	}
	public int getDex() {
		return dex;
	}
	public Status setDex(int dex) {
		this.dex = dex;
		return this;
	}
	public int getIntel() {
		return intel;
	}
	public Status setIntel(int intel) {
		this.intel = intel;
		return this;
	}
	public int getLuc() {
		return luc;
	}
	public Status setLuc(int luc) {
		this.luc = luc;
		return this;
	}
	public int getFullHealthPoint() {
		return fullHealthPoint;
	}
	public Status setFullHealthPoint(int fullHealthPoint) {
		this.fullHealthPoint = fullHealthPoint;
		return this;
	}
	public int getFullSkillPoint() {
		return fullSkillPoint;
	}
	public Status setFullSkillPoint(int fullSkillPoint) {
		this.fullSkillPoint = fullSkillPoint;
		return this;
	}
	public int getAtkRate() {
		return atkRate;
	}
	public Status setAtkRate(int atkRate) {
		this.atkRate = atkRate;
		return this;
	}
	public int getDefRate() {
		return defRate;
	}
	public Status setDefRate(int defRate) {
		this.defRate = defRate;
		return this;
	}
	public int getMagRate() {
		return magRate;
	}
	public Status setMagRate(int magRate) {
		this.magRate = magRate;
		return this;
	}
	public int getMagdefRate() {
		return magdefRate;
	}
	public Status setMagdefRate(int magdefRate) {
		this.magdefRate = magdefRate;
		return this;
	}
	public double getHitRate() {
		return hitRate;
	}
	public Status setHitRate(double hitRate) {
		this.hitRate = hitRate;
		return this;
	}
	public double getEvasiveRate() {
		return evasiveRate;
	}
	public Status setEvasiveRate(double evasiveRate) {
		this.evasiveRate = evasiveRate;
		return this;
	}
	public double getCriticalRate() {
		return criticalRate;
	}
	public Status setCriticalRate(double criticalRate) {
		this.criticalRate = criticalRate;
		return this;
	}
	public int getAllStatusPoint() {
		return str+dex+intel+luc;
	}
	
}
