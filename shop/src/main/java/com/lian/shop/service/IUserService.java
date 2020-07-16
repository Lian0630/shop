package com.lian.shop.service;

import com.lian.shop.entity.User;
import com.lian.shop.service.ex.DuplicateKeyException;
import com.lian.shop.service.ex.InsertException;
import com.lian.shop.service.ex.PasswordNotMatchException;
import com.lian.shop.service.ex.UpdateException;
import com.lian.shop.service.ex.UserNotFoundException;




/***
 * 處理用戶數據的業務層街口
 */
public interface IUserService {
	
	/**
	 * 用戶註冊
	 * @param user 用戶註冊信息
	 * @return 成功註冊的用戶數據
	 * @throws DuplicateKeyException
	 * @throws InsertException
	 */
	User reg(User user) throws DuplicateKeyException, InsertException;
	
	/**
	 * 用戶註冊
	 * @param username 用戶名
	 * @param password 密碼
	 * @return 成功登入的用戶數據
	 * @throws UserNotFoundException
	 * @throws PasswordNotMatchException
	 */
	User login(String username, String password) throws UserNotFoundException,PasswordNotMatchException;
	
	/**
	 * 修改密碼
	 * @param uid 用戶id
	 * @param oldPassword 原密碼
	 * @param newPassword 新密碼
	 * @throws UserNotFoundException
	 * @throws PasswordNotMatchException
	 * @throws UpdateException
	 */
	void changePassword(Integer uid,String oldPassword,String newPassword) throws UserNotFoundException,PasswordNotMatchException,UpdateException;
	
	/**
	 * 修改用戶個人資料
	 * @param user 用戶數據
	 * @throws UserNotFoundException
	 * @throws UpdateException
	 */
	void changeInfo(User user)throws UserNotFoundException,UpdateException;
	
	/**
	 * 修改頭像
	 * @param uid 用戶id
	 * @param avatar 頭像路徑
	 * @throws UserNotFoundException
	 * @throws UpdateException
	 */
	void changeAvatar(Integer uid, String avatar)throws UserNotFoundException,UpdateException;
	
	/**
	 * 根據id獲取用戶數據
	 * @param id 用戶id
	 * @return 匹配的用户數據，如果没有匹配的數據，則返回null
	 */
	User getById(Integer id);
	
	
	
}
