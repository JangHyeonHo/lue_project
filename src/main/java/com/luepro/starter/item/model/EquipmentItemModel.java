package com.luepro.starter.item.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EquipmentItemModel extends ItemModel implements EquipmentItem {
	
	//장착 파트
	private String parts;
	//장착 가능 직업
	private String equipJobs;
	//장착 가능 레벨
	private int equipLevels;
	//장비 강화 수치
	private int enhance;
	//장비 강화할 때 상승치
	private int additionalStatus;
	
	public String getParts() {
		return parts;
	}
	public EquipmentItemModel setParts(String parts) {
		this.parts = parts;
		return this;
	}
	public String getEquipJobs() {
		return equipJobs;
	}
	public EquipmentItemModel setEquipJobs(String equipJobs) {
		this.equipJobs = equipJobs;
		return this;
	}
	public int getEquipLevels() {
		return equipLevels;
	}
	public EquipmentItemModel setEquipLevels(int equipLevels) {
		this.equipLevels = equipLevels;
		return this;
	}
	public int getEnhance() {
		return enhance;
	}
	public EquipmentItemModel setEnhance(int enhance) {
		this.enhance = enhance;
		return this;
	}
	public int getAdditionalStatus() {
		return additionalStatus;
	}
	public EquipmentItemModel setAdditionalStatus(int additionalStatus) {
		this.additionalStatus = additionalStatus;
		return this;
	}
	
	
	@Override
	public String effect() {
		// TODO Auto-generated method stub
		String effect = "";
		for(Method status : this.getClass().getDeclaredMethods()) {
			if(status.getName().startsWith("get")) {
				if(!status.getReturnType().getName().contentEquals(new StringBuffer("int"))) {
					continue;
				}
				try {
					if(Integer.parseInt(String.valueOf(status.invoke(this))) != 0) {
						effect = status.getName().substring(3) + " " + status.invoke(this);
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return effect;
	}
	
	
}
