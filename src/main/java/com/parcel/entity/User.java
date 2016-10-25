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
	public void setname(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [idx=" + idx + ", id=" + id + ", pw=" + pw + ", phone=" + phone + ", email=" + email
				+ ", web_authority=" + web_authority + ", state=" + state + "]";
	}
	
	
	
}
