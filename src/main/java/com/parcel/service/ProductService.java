package com.parcel.service;

import java.util.List;

import com.parcel.entity.Machine;
import com.parcel.entity.Product;

public interface ProductService {
	//public
	
	public List<Machine> getMachineList();
	public String addProduct(Product product);
}
