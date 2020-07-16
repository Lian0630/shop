package com.lian.shop.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lian.shop.entity.Address;
import com.lian.shop.service.ex.ServiceException;




@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTestCase {
	@Autowired
	private IAddressService addressService;
	@Test
	public void create(){
		String username = "Admin";
		Address address = new Address();
		address.setUid(2);
		address.setName("小安");
		address.setProvince("440084");
		address.setProvince("440345");
		address.setArea("440544");
		Address result = addressService.creat(username, address);
		System.out.println("result:"+result);
	}
	@Test
	public void setDefault(){
		try{
			Integer id = 4;
			Integer uid = 15;
			addressService.setDefault(uid, id);
		}catch(ServiceException e){
			System.out.println("錯誤類型:"+e.getClass().getName());
			System.out.println("錯誤描述:"+e.getMessage());
		}
	}
	
	@Test
	public void getListByUid(){
		Integer uid = 2;
		List<Address> list = addressService.getListByUid(uid);
		System.out.println("begin.");
		for(Address address : list){
			System.out.println(address);
		}
		System.out.println("end.");
	}
	
	@Test
	public void delete(){
		try{
			Integer id = 11;
			Integer uid = 2;
			addressService.delete(uid, id);
		}catch(ServiceException e){
			System.out.println("錯誤類型:"+e.getClass().getName());
			System.out.println("錯誤描述:"+e.getMessage());
		}
	}
}
