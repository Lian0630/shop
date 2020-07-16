package com.lian.shop.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lian.shop.entity.Order;
import com.lian.shop.service.ex.ServiceException;
import com.lian.shop.vo.OrderVO;




@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTestCase {
	@Autowired
	private IOrderService service;
	
	@Test
	public void createOrder(){
		try{
			Integer uid = 9;
			String username = "lian";
			Integer addressId = 4;
			Integer[] cartIds = {1,2,3};
			Order order = service.createOrder(uid, username, addressId, cartIds);
			System.out.println(order);
		}catch(ServiceException e){
			System.out.println("錯誤類型:"+e.getClass().getName());
			System.out.println("錯誤描述:"+e.getMessage());
		}
	}
	
	@Test
	public void getById(){
		Integer id = 3;
		OrderVO data = service.getById(id);
		System.out.println(data);
	}
	
}
