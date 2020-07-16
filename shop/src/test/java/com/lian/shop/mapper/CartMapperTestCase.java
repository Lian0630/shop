package com.lian.shop.mapper;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lian.shop.entity.Cart;
import com.lian.shop.vo.CartVO;



@RunWith(SpringRunner.class)
@SpringBootTest
public class CartMapperTestCase {
	
	@Autowired
	private CartMapper mapper;
	
	@Test
	public void addnew(){
		Cart cart = new Cart();
		cart.setUid(1);
		cart.setGid(100L);
		cart.setPrice(5000L);
		cart.setCount(8);
		Integer rows = mapper.addnew(cart);
		System.out.println("rows="+rows);
	}
	
	@Test
	public void updateCount(){
		Integer id = 1;
		Integer count = 15;
		Integer rows = mapper.updateCount(id, count);
		System.out.println("rows="+rows);	
	}
	
	@Test
	public void findByUidAndGid(){
		Integer uid = 1;
		Long moneyId = 100L;
		Cart cart = mapper.findByUidAndGid(uid, moneyId);
		System.out.println(cart);	
	}
	
	@Test 
	public void findByUid(){
		Integer uid = 3;
		List<CartVO> list = mapper.findByUid(uid);
		System.out.println("begin.");
		for(CartVO cartVO: list){
			System.out.println(cartVO);
		}
		System.out.println("end.");
	}
	
	@Test
	public void findById(){
		Integer id = 5;
		Cart cart = mapper.findById(id);
		System.out.println(cart);	
	}
	
	@Test 
	public void findByIds(){
		Integer[] ids = {5,9};
		List<CartVO> list = mapper.findByIds(ids);
		System.out.println("begin.");
		for(CartVO cartVO: list){
			System.out.println(cartVO);
		}
		System.out.println("end.");
	}
	
	
	@Test
	public void deleteById(){
		Integer id = 8;
		Integer rows = mapper.deleteById(id);
		System.out.println("rows"+rows);
	}
	
	@Test
	public void deleteByUid(){
		Integer uid = 3;
		Integer rows = mapper.deleteByUid(uid);
		System.out.println("rows"+rows);
	}
	
}
