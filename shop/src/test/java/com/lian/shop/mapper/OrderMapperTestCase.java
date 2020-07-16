package com.lian.shop.mapper;


import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lian.shop.entity.Order;
import com.lian.shop.entity.OrderItem;
import com.lian.shop.vo.OrderVO;



@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTestCase {
	
	@Autowired
	private OrderMapper mapper;
	
	@Test
	public void insertOrder(){
		Order order = new Order();
		order.setUid(1);
		order.setRecvName("小連");
		Integer rows = mapper.insertOrder(order);
		System.out.println("rows:"+rows);
		
	}
	
	
	@Test
	public void insertOrderItem(){
		OrderItem orderItem = new OrderItem();
		orderItem.setOid(1);
		orderItem.setMoneyTitle("手機");
		Integer rows = mapper.insertOrderItem(orderItem);
		System.out.println("rows:"+rows);	
	}
	
	@Test
	public void findById(){
		Integer id = 3;
		OrderVO data = mapper.findById(id);
		System.out.println(data);
	}
	
}
