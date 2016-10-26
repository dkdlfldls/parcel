package com.parcel.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.parcel.entity.Machine;
import com.parcel.entity.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	private JdbcTemplate t;
	
	@Autowired
	public ProductRepositoryImpl(DataSource dataSource) {
		t = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Machine> getMachineList() {
		String sql = "SELECT * FROM machine";
		return t.query(sql, (rs,no)->{
			Machine m = new Machine(
					rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getTimestamp(4)
			);
			return m;
		});
	}

	@Override
	public int addProduct(Product product) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateProduct(Product product) {
		// TODO Auto-generated method stub
		String sql = "UPDATE product SET registrant=?, public_name=?, registration_date=now() WHERE machine=? AND machine_code=?";
		
		return t.update(sql, product.getRegistrant(), product.getPublic_name(), product.getMachine(), product.getMachine_code());
	}

	@Override
	public Product checkProduct(Product product) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM product WHERE machine=? AND machine_code=?";
		try {
			return t.queryForObject(sql, (rs, no)->{
				return new Product(rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getTimestamp(4),
						rs.getInt(5),
						rs.getInt(6),
						rs.getString(7),
						rs.getInt(8));
			}, product.getMachine(), product.getMachine_code());
		} catch (Exception e) {
			return null;
		}
	}

}
