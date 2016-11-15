package com.parcel.repository;

import com.parcel.entity.Log;

public interface LogRepository {
	
	//로그 입력
	public int insertLog(Log log);
}
