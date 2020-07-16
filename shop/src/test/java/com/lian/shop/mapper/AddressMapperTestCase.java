package com.lian.shop.mapper;


import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lian.shop.entity.Address;




@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressMapperTestCase {
	
	@Autowired
	private AddressMapper addressMapper;
	@Test
	public void addnew(){
		Address address = new Address();
		address.setUid(2);
		address.setName("水");
		Integer rows = addressMapper.addnew(address);
		System.out.println("rows:"+rows);
		System.out.println(address);
	}
	@Test
	public void getCountByUid(){
		Integer uid = 2;
		Integer count = addressMapper.getCountByUid(uid);
		System.out.println("count:"+count);
	}
	@Test
	public void findByUid(){
		Integer uid = 9;
		List<Address> list = addressMapper.findByUid(uid);
		System.out.println("begin.");
		for(Address address : list){
			System.out.println(address);
		}
		System.out.println("end.");
	}
	
	@Test
	public void updateNonDefault(){
		Integer uid = 9;
		Integer rows = addressMapper.updateNonDefault(uid);
		System.out.println("rows"+rows);
	}
	
	@Test
	public void updateDefault(){
		Integer id = 5;
		Integer rows = addressMapper.updateDefault(id);
		System.out.println("rows"+rows);
	}
	
	@Test
	public void findById(){
		Integer id=1;
		Address address = addressMapper.findById(id);
		System.out.println("address"+address);
	}
	@Test
	public void findLastModified(){
		Integer uid = 9;
		Address address = addressMapper.findLastModified(uid);
		System.out.println("address"+address);
	}
	@Test
	public void deleteById(){
		Integer id = 3;
		Integer rows = addressMapper.deleteById(id);
		System.out.println("rows"+rows);
	}
	
}
