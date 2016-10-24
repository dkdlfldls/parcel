package com.parcel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parcel.repository.TestRepository;

@Service
public class TestService {

	@Autowired
	private TestRepository repository;
	
	public boolean addState(String label) {
		int result = repository.addState(label);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}
}
