package com.lian.shop.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lian.shop.entity.Cart;
import com.lian.shop.service.ex.ServiceException;
import com.lian.shop.vo.CartVO;




@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTestCase {
	@Autowired
	private ICartService service;
	
	@Test
	public void addToCart(){
		try{
		String username = "lian";
		Cart cart = new Cart();
		cart.setUid(3);
		cart.setGid(9527L);
		cart.setCount(2);
		cart.setPrice(800L);
		service.addToCart(username, cart);
		System.out.println("OK!");
		}catch(ServiceException e){
			System.out.println("錯誤類型:"+e.getClass().getName());
			System.out.println("錯誤描述:"+e.getMessage());
		}
	}
	
	@Test 
	public void getByUid(){
		Integer uid = 3;
		List<CartVO> list = service.getByUid(uid);
		System.out.println("begin.");
		for(CartVO cartVO: list){
			System.out.println(cartVO);
		}
		System.out.println("end.");
	}
	
	@Test
	public void addCount(){
		try{
			Integer id = 8;
			Integer uid = 3;
			service.addCount(id, uid);
			System.out.println("OK!");
		}catch(ServiceException e){
			System.out.println("錯誤類型:"+e.getClass().getName());
			System.out.println("錯誤描述:"+e.getMessage());
		}
	}
	
	@Test
	public void reduceCount(){
		try{
			Integer id = 8;
			Integer uid = 3;
			service.reduceCount(id, uid);
			System.out.println("OK!");
		}catch(ServiceException e){
			System.out.println("錯誤類型:"+e.getClass().getName());
			System.out.println("錯誤描述:"+e.getMessage());
		}
	}
	@Test 
	public void getByIds(){
		Integer[] ids = {5,9};
		List<CartVO> list = service.getByIds(ids);
		System.out.println("begin.");
		for(CartVO cartVO: list){
			System.out.println(cartVO);
		}
		System.out.println("end.");
	}
	
	@Test
	public void delete(){
		try{
			Integer id = 11;
			Integer uid = 3;
			service.delete(uid, id);
		}catch(ServiceException e){
			System.out.println("錯誤類型:"+e.getClass().getName());
			System.out.println("錯誤描述:"+e.getMessage());
		}
	}
	
	@Test
	public void deleteByUid(){
		try{
			
			Integer uid = 3;
			service.deleteByUid(uid);
		}catch(ServiceException e){
			System.out.println("錯誤類型:"+e.getClass().getName());
			System.out.println("錯誤描述:"+e.getMessage());
		}
	}
	
}
