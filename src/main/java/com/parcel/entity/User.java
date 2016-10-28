package com.parcel.entity;


public class User {
	
	private int idx;
	private String id;
	private String pw;
	private String phone;
	private String email;
	private int web_authority;
	private int state;
	private String name;
	private int productIdx;
	private boolean hasGroup;
	private String productName;

	public User(int idx, String id, String pw, String phone, String email, int web_authority, int state) {
		super();
		this.idx = idx;
		this.id = id;
		this.pw = pw;
		this.phone = phone;
		this.email = email;
		this.web_authority = web_authority;
		this.state = state;
	}
	
	
	
	public User(int idx, String id, String pw, String phone, String email, int web_authority, int state, String name,
			int productIdx) {
		super();
		this.idx = idx;
		this.id = id;
		this.pw = pw;
		this.phone = phone;
		this.email = email;
		this.web_authority = web_authority;
		this.state = state;
		this.name = name;
		this.productIdx = productIdx;
	}

	


	public User(int idx, String id, String pw, String phone, String email, int web_authority, int state, String name,
			int productIdx, boolean hasGroup) {
		super();
		this.idx = idx;
		this.id = id;
		this.pw = pw;
		this.phone = phone;
		this.email = email;
		this.web_authority = web_authority;
		this.state = state;
		this.name = name;
		this.productIdx = productIdx;
		this.hasGroup = hasGroup;
	}
	
	



	public User(int idx, String id, String pw, String phone, String email, int web_authority, int state, String name,
			int productIdx, boolean hasGroup, String productName) {
		super();
		this.idx = idx;
		this.id = id;
		this.pw = pw;
		this.phone = phone;
		this.email = email;
		this.web_authority = web_authority;
		this.state = state;
		this.name = name;
		this.productIdx = productIdx;
		this.hasGroup = hasGroup;
		this.productName = productName;
	}



	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(int idx) {
		// TODO Auto-generated constructor stub
		this.idx = idx;
	}
	
	



	public int getIdx() {
		return idx;
	}



	public void setIdx(int idx) {
		this.idx = idx;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getPw() {
		return pw;
	}



	public void setPw(String pw) {
		this.pw = pw;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public int getWeb_authority() {
		return web_authority;
	}



	public void setWeb_authority(int web_authority) {
		this.web_authority = web_authority;
	}



	public int getState() {
		return state;
	}



	public void setState(int state) {
		this.state = state;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getProductIdx() {
		return productIdx;
	}



	public void setProductIdx(int productIdx) {
		this.productIdx = productIdx;
	}



	public boolean isHasGroup() {
		return hasGroup;
	}



	public void setHasGroup(boolean hasGroup) {
		this.hasGroup = hasGroup;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	@Override
	public String toString() {
		return "User [idx=" + idx + ", id=" + id + ", pw=" + pw + ", phone=" + phone + ", email=" + email
				+ ", web_authority=" + web_authority + ", state=" + state + ", name=" + name + ", productIdx="
				+ productIdx + ", hasGroup=" + hasGroup + ", productName=" + productName + "]";
	}



	



	
	
	
	
}
