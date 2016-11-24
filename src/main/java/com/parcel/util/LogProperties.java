package com.parcel.util;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class LogProperties {

	@Resource(name="log_detail")
	private Properties log_detail;
	
	public int getInt(String key) {
		try {
			return Integer.parseInt(log_detail.getProperty(key));
		} catch (Exception e) {
			return 0;
		}
		
		
	}

}
