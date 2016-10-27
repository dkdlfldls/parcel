package com.parcel.entity;

public class MainPageEntity {
	private int idx;
	private String name;
	private String pname;
	private int isopen;
	private int countg;
	private int countm;
	private int pidx;
	
	public MainPageEntity() {
		
	}
	
	public MainPageEntity(int idx, String name, String pname, int isopen, int countg, int countm, int pidx) {
		super();
		this.idx = idx;
		this.name = name;
		this.pname = pname;
		this.isopen = isopen;
		this.countg = countg;
		this.countm = countm;
		this.pidx = pidx;
	}
	
	
	public int getPidx() {
		return pidx;
	}

	public void setPidx(int pidx) {
		this.pidx = pidx;
	}

	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getIsopen() {
		return isopen;
	}
	public void setIsopen(int isopen) {
		this.isopen = isopen;
	}
	public int getCountg() {
		return countg;
	}
	public void setCountg(int countg) {
		this.countg = countg;
	}
	public int getCountm() {
		return countm;
	}
	public void setCountm(int countm) {
		this.countm = countm;
	}

	@Override
	public String toString() {
		return "MainPageEntity [idx=" + idx + ", name=" + name + ", pname=" + pname + ", isopen=" + isopen + ", countg="
				+ countg + ", countm=" + countm + ", pidx=" + pidx + "]";
	}

	
	
	
	
}
