package com.parcel.entity;

import java.sql.Timestamp;

public class Product {
	private int idx;
	private int machine;
	private String machine_code;
	private Timestamp registration_date;
	private int registrant;
	private int state;
	private String public_name;
	private int is_open;
	private String machine_name;
	private String registrant_name;
	
	
	
	
	
	
	@Override
	public String toString() {
		return "Product [idx=" + idx + ", machine=" + machine + ", machine_code=" + machine_code
				+ ", registration_date=" + registration_date + ", registrant=" + registrant + ", state=" + state
				+ ", public_name=" + public_name + ", is_open=" + is_open + ", machine_name=" + machine_name
				+ ", registrant_name=" + registrant_name + "]";
	}
	public Product(int idx, int machine, String machine_code, Timestamp registration_date, int registrant, int state,
			String public_name, int is_open, String machine_name) {
		super();
		this.idx = idx;
		this.machine = machine;
		this.machine_code = machine_code;
		this.registration_date = registration_date;
		this.registrant = registrant;
		this.state = state;
		this.public_name = public_name;
		this.is_open = is_open;
		this.machine_name = machine_name;
	}
	public Product(int idx, int machine, String machine_code, Timestamp registration_date, int registrant, int state,
			String public_name, int is_open) {
		super();
		this.idx = idx;
		this.machine = machine;
		this.machine_code = machine_code;
		this.registration_date = registration_date;
		this.registrant = registrant;
		this.state = state;
		this.public_name = public_name;
		this.is_open = is_open;
	}
	

	public Product(int idx, int machine, String machine_code, Timestamp registration_date, int registrant, int state,
			String public_name, int is_open, String machine_name, String registrant_name) {
		super();
		this.idx = idx;
		this.machine = machine;
		this.machine_code = machine_code;
		this.registration_date = registration_date;
		this.registrant = registrant;
		this.state = state;
		this.public_name = public_name;
		this.is_open = is_open;
		this.machine_name = machine_name;
		this.registrant_name = registrant_name;
	}
	
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	public String getMachine_name() {
		return machine_name;
	}


	public void setMachine_name(String machine_name) {
		this.machine_name = machine_name;
	}


	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getMachine() {
		return machine;
	}
	public void setMachine(int machine) {
		this.machine = machine;
	}
	public String getMachine_code() {
		return machine_code;
	}
	public void setMachine_code(String machine_code) {
		this.machine_code = machine_code;
	}
	public Timestamp getRegistration_date() {
		return registration_date;
	}
	public void setRegistration_date(Timestamp registration_date) {
		this.registration_date = registration_date;
	}
	public int getRegistrant() {
		return registrant;
	}
	public void setRegistrant(int registrant) {
		this.registrant = registrant;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getPublic_name() {
		return public_name;
	}
	public void setPublic_name(String public_name) {
		this.public_name = public_name;
	}
	public int getIs_open() {
		return is_open;
	}
	public void setIs_open(int is_open) {
		this.is_open = is_open;
	}
	public String getRegistrant_name() {
		return registrant_name;
	}
	public void setRegistrant_name(String registrant_name) {
		this.registrant_name = registrant_name;
	}
	
	
	
}
