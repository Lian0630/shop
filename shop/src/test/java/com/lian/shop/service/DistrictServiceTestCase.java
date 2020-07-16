package com.lian.shop.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lian.shop.entity.District;




@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictServiceTestCase {
	@Autowired
	private IDistrictService service;
	@Test
	public void getListByParent(){
		String parent = "86";
		List<District> result = service.getListByParent(parent);
		System.out.println("begin.");
		for(District district : result){
			System.out.println(district);
		}
		System.out.println("end.");
	}
	
	@Test
	public void getByCode(){
		String code = "320000";
		District district = service.getByCode(code);
		System.out.println(district);
	}
}
