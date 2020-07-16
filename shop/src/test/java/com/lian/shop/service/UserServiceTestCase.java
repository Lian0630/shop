package com.lian.shop.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lian.shop.entity.User;
import com.lian.shop.service.ex.ServiceException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTestCase {

	
	@Autowired
	IUserService userService;
	@Test
	public void reg(){
		try{
		User user = new User();
		user.setUsername("root1");
		user.setPassword("1234");
		user.setGender(1);
		user.setPhone("1234");
		user.setEmail("123@gmail");
		User result = userService.reg(user);
		System.out.println("result:"+result);
		}catch(ServiceException e){
			System.out.println("錯誤類型:"+e.getClass().getName());
			System.out.println("錯誤描述:"+e.getMessage());
		}
	}
	
	
	@Test
	public void login() {
	    try {
	        String username = "root4";
	        String password= "123";
	        User result = userService.login(username, password);
	        System.err.println("result=" + result);
	    } catch (ServiceException e) {
	        System.err.println("錯誤類型：" + e.getClass().getName());
	        System.err.println("錯誤描述：" + e.getMessage());
	    }
	}
	
	
	
	@Test
	public void changePassword() {
	    try {
	    	Integer uid = 3;
	    	String oldPassword = "1235";
	    	String newPassword = "1234";
	    	userService.changePassword(uid, oldPassword, newPassword);
	        System.err.println("OK!");
	    } catch (ServiceException e) {
	        System.err.println("錯誤類型：" + e.getClass().getName());
	        System.err.println("錯誤描述：" + e.getMessage());
	    }
	}
	
	@Test
	public void changeAvatar() {
	    try {
	    	Integer uid = 3;
	    	String avatar="6";
	    	userService.changeAvatar(uid, avatar);
	        System.err.println("OK!");
	    } catch (ServiceException e) {
	        System.err.println("錯誤類型：" + e.getClass().getName());
	        System.err.println("錯誤描述：" + e.getMessage());
	    }
	}
	
	@Test
	public void changeInfo() {
	    try {
	    	User user = new User();
	    	user.setId(3);
	    	user.setGender(0);
	    	user.setPhone("0909");
	    	user.setEmail("00@gmail");
	    	userService.changeInfo(user);
	        System.err.println("OK!");
	    } catch (ServiceException e) {
	        System.err.println("錯誤類型：" + e.getClass().getName());
	        System.err.println("錯誤描述：" + e.getMessage());
	    }
	}
}
