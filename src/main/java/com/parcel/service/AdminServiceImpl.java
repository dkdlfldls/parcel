package com.parcel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parcel.entity.Log;
import com.parcel.repository.LogRepository;
import com.parcel.util.Page;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private LogRepository logRepository;
	
	@Override
	public List<Log> getLogList(Page page) {
		// TODO Auto-generated method stub
		if (page.getCategory() == 0) {
			page.setPageInfo(logRepository.findCountAllLog());
			return logRepository.findLogByPage(page);
		} else {
			page.setPageInfo(logRepository.findCountLog(page));
			return logRepository.findLogByPageAndCategory(page);
		}
		
	}

	@Override
	public List<Log> getSearchResultOfLogList(Page page) {
		// TODO Auto-generated method stub
		if (page.getCategory() == 0 ) {
			page.setPageInfo(logRepository.findCountLogBySearch(page));		
			return logRepository.findLogByPageAndSearch(page);
		} else {
			page.setPageInfo(logRepository.findCountLogBySearchAndCategory(page));
			return logRepository.findLogByPageAndSearchAndCategory(page);
		}
		
		
	}

}
