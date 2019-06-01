package com.luepro.starter.item.model;

import com.luepro.starter.character.model.Status;

public class ItemModel extends Status implements Item{
	
	private String itemNo;
	private String itemName;
	private String itemDes;
	private int itemBuyMoney;
	private int itemSellMoney;
	
	public String getItemNo() {
		return itemNo;
	}
	public ItemModel setItemNo(String itemNo) {
		this.itemNo = itemNo;
		return this;
	}
	public String getItemName() {
		return itemName;
	}
	public ItemModel setItemName(String itemName) {
		this.itemName = itemName;
		return this;
	}
	public String getItemDes() {
		return itemDes;
	}
	public ItemModel setItemDes(String itemDes) {
		this.itemDes = itemDes;
		return this;
	}
	public int getItemBuyMoney() {
		return itemBuyMoney;
	}
	public ItemModel setItemBuyMoney(int itemBuyMoney) {
		this.itemBuyMoney = itemBuyMoney;
		return this;
	}
	public int getItemSellMoney() {
		return itemSellMoney;
	}
	public ItemModel setItemSellMoney(int itemSellMoney) {
		this.itemSellMoney = itemSellMoney;
		return this;
	}
	
	
}
