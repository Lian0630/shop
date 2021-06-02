package com.service;

import java.util.List;

import com.dao.UserDao;
import com.entity.User;

public class UserService {
	
	UserDao uDao = new UserDao();

	/**
	 * 新增
	 */
	public boolean add(User user) {
		boolean f = false;
		if(!uDao.id(user.getId())) {
			uDao.add(user);
			f = true;
		}
		return f;
	}
	
	/**
	 * 刪除
	 */
	public void delete(String id) {
		uDao.delete(id);
	}

	
	/**
	 * 修改
	 */
	public void update(User user) {
		uDao.update(user);
	}
	
	
	/**
	 * 查詢
	 * @return 
	 */
	public List<User> search(String id,String name,int phone,String address, String description)  {
		return uDao.search(id,name,phone,address,description) ;
	}
	
	/**
	 * 全部資料
	 * @return 
	 */
	public List<User> list() {
		return uDao.list();
	}
	
	
}
