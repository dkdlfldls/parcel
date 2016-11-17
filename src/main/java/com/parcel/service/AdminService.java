package com.parcel.service;

import java.util.List;

import com.parcel.entity.Log;
import com.parcel.util.Page;

public interface AdminService {
	
	public List<Log> getLogList(Page page);

	public List<Log> getSearchResultOfLogList(Page page);
}
