package com.lian.shop.mapper;


import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lian.shop.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTestCase {
	
	@Autowired
	UserMapper userMapper;
	@Test
	public void addnew(){
		Date now = new Date();
		User user = new User();
		user.setUsername("root3");
		user.setPassword("1234");
		user.setGender(1);
		user.setPhone("1234");
		user.setEmail("123@gmail");
		user.setSalt("hello");
		user.setIsDelete(0);
		user.setCreatedUser("admin");
		user.setModifiedUser("admin");
		user.setCreatedTime(now);
		user.setModifiedTime(now);
		Integer rows = userMapper.addnew(user);
		System.out.println("rows:"+rows);
	}
	
	@Test
	public void updatePassword(){
		Integer uid= 6;
		String password= "1234";
		String modifiedUser= "Lian";
		Date modifiedTime=new Date();
		Integer rows = userMapper.updatePassword(uid, password, modifiedUser, modifiedTime);
		System.out.println("rows"+rows);
	}
	
	
	@Test
	public void updateAvatar(){
		Integer uid= 3;
		String avatar= "1235";
		String modifiedUser= "Lian";
		Date modifiedTime=new Date();
		Integer rows = userMapper.updateAvatar(uid, avatar, modifiedUser, modifiedTime);
		System.out.println("rows"+rows);
	}
	
	@Test
	public void updateInfo(){
		User user = new User();
		user.setId(3);
		user.setGender(1);
		user.setPhone("0970");
		user.setEmail("09@gmail");
		user.setModifiedUser("Lian");
		user.setModifiedTime(new Date());
		Integer rows = userMapper.updateInfo(user);
		System.out.println("rows"+rows);
	}
	
	@Test
	public void findByUsername(){
		String username = "root1";
		User user = userMapper.findByUsername(username);
		System.err.println(user);
	}
	
	@Test
	public void findById(){
		Integer id=6; 
		User user = userMapper.findById(id);
		System.out.println(user);
	}
	
	
}
