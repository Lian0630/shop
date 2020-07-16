package com.lian.shop.mapper;


import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lian.shop.entity.Money;




@RunWith(SpringRunner.class)
@SpringBootTest
public class MoneyMapperTestCase {
	
	@Autowired
	private MoneyMapper mapper;

	
	@Test
	public void findById(){
		Long id = 3L;
		Money money = mapper.findById(id);
		System.out.println(money);
	}
	
	@Test
	public void findByPriority(){	
		Integer count= 4;		
		List<Money> list = mapper.findByPriority(count);
		System.out.println("begin.");
		for(Money data : list){
			System.out.println(data);
		}
		System.out.println("end.");
	}
	
}
