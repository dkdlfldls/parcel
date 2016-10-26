package com.parcel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parcel.entity.Machine;
import com.parcel.entity.Product;
import com.parcel.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Machine> getMachineList() {
		System.out.println("=====getMachineList process in ProductServiceImpl=====");
		return productRepository.getMachineList();
	}

	@Override
	public String addProduct(Product product) {
		//1. 등록이 되어있는지 부터 확인한다.
		//2. 등록이 되어있다면 등록된 제품이라고 알려주고 등록이 안되어 있다면 update를 시작한다.
		//3. 업데이트에 실패하면 입력 확인하라고 알려준다.
		//4. 성공하면 성공했다고 알려준다.
		Product temp = productRepository.checkProduct(product);
		if (temp == null) {
			return "입력 정보를 확인하여 주세요";
		} else {
			System.out.println("@@@@" + temp.toString());
			if (temp.getRegistrant() > 0) {
				return "이미 등록된 제품입니다.";
			} else {
				//등록 시작
				int result = productRepository.updateProduct(product);
				if (result > 0 ) {
					return "등록 완료";
				} else {
					return "입력 정보를 확인하여 주세요";
				}
			}
		}
		
	
		
	}

}
