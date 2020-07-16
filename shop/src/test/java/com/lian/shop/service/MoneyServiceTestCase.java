package com.lian.shop.service;

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
public class MoneyServiceTestCase {
	@Autowired
	private IMoneyService service;
	
	
	@Test
	public void getById(){
		Long id = 3L;
		Money money = service.getById(id);
		System.out.println(money);
	}
	

	@Test
	public void getByPriority(){	
		Integer count= 4;		
		List<Money> list = service.getByPriority(count);
		System.out.println("begin.");
		for(Money data : list){
			System.out.println(data);
		}
		System.out.println("end.");
	}
}
