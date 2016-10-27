package com.parcel.entity;

import java.util.List;

public class Group {
	private int idx;
	private String group_name;
	private int manager;
	private int product;
	private int state;
	private List<User> groupUserList;
	private String pw;
	private String code;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public int getManager() {
		return manager;
	}
	public void setManager(int manager) {
		this.manager = manager;
	}
	public int getProduct() {
		return product;
	}
	public void setProduct(int product) {
		this.product = product;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	public List<User> getGroupUserList() {
		return groupUserList;
	}
	public void setGroupUserList(List<User> groupUserList) {
		this.groupUserList = groupUserList;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Group() {
		
	}
	public Group(int idx, String group_name, int manager, int product, int state, List<User> groupUserList, String pw,
			String code) {
		super();
		this.idx = idx;
		this.group_name = group_name;
		this.manager = manager;
		this.product = product;
		this.state = state;
		this.groupUserList = groupUserList;
		this.pw = pw;
		this.code = code;
	}
	@Override
	public String toString() {
		return "Group [idx=" + idx + ", group_name=" + group_name + ", manager=" + manager + ", product=" + product
				+ ", state=" + state + ", groupUserList=" + groupUserList + ", pw=" + pw + ", code=" + code + "]";
	}
	
	
	
	
	
	

}
