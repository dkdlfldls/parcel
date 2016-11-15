package com.parcel.entity;

import java.util.HashMap;

import com.google.gson.Gson;

public class ChattingVO {

	private String message;
	private String group;
	private String type; //type 1=접속 2=채팅 3=탈퇴
	private String name;
	private static Gson gson = new Gson();
	
	public ChattingVO() {
		
	}
	
	public static ChattingVO convertMessage(String source) {
		
		/*
		ChattingVO vo = new ChattingVO();
		//{"message":"hjhj","group":"aaa"} 이 형식에 대해서만
		String temp = source;
		temp = temp.replaceAll("[{}\"]", "");
		
		HashMap<String, String> map = new HashMap<String, String>();
		String[] parsedStr = temp.split(",");
		String[] parsedStrTemp;
		for (String str : parsedStr) {
			parsedStrTemp = str.split(":");
			map.put(parsedStrTemp[0], parsedStrTemp[1]);
		}
		vo.setMessage(map.get("message"));
		vo.setGroup(map.get("group"));
		*/
		
		return gson.fromJson(source, ChattingVO.class);
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
