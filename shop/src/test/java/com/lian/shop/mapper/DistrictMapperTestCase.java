package com.lian.shop.mapper;


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
public class DistrictMapperTestCase {
	
	@Autowired
	DistrictMapper districtMapper;
	@Test
	public void findByParent(){
		String parent = "86";
		List<District> result = districtMapper.findByParent(parent);
		System.out.println("begin.");
		for(District district :result){
			System.out.println(district);
		}
		System.out.println("end.");
	}
	
	
	@Test
	public void getCountByUid(){
		String code = "371000";
		District district = districtMapper.findByCode(code);
		System.out.println("district:"+district);
	}
	
	
	
}
