package com.lian.shop.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.lian.shop.entity.User;
import com.lian.shop.mapper.UserMapper;
import com.lian.shop.service.IUserService;
import com.lian.shop.service.ex.DuplicateKeyException;
import com.lian.shop.service.ex.InsertException;
import com.lian.shop.service.ex.PasswordNotMatchException;
import com.lian.shop.service.ex.UpdateException;
import com.lian.shop.service.ex.UserNotFoundException;






/**
 * 處理用戶數據的業務層實現類
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User reg(User user) throws DuplicateKeyException, InsertException {
		 	// 根據嘗試註冊的用戶名查詢用戶數據
	    	User data = findByUsername(user.getUsername());
	    	// 判断查询到的數據是否为null
	    	if (data == null) {
	    		// 是：用户名不存在，允許註冊，则處理密碼加密
	    		
	    		//補充非用戶提交的數據
	    	    // 是否已經刪除：否
	    		user.setIsDelete(0); 
	    		// 4項日誌
	    		Date now = new Date();
	    		user.setCreatedUser(user.getUsername());
	    		user.setCreatedTime(now);
	    		user.setModifiedUser(user.getUsername());
	    		user.setModifiedTime(now);
	    		//----------------------
	    		//處理密碼加密
	    		// 加密-1：獲取隨機的UUID作為鹽值
	    		String salt = UUID.randomUUID().toString();
	    		// 加密-2：獲取用戶提交的原始密碼
	    		String srcPassword = user.getPassword();
	    		// 加密-3：基於原始密码和鹽值执行加密，獲取通過MD5加密的密码
	    		String md5Password = getMd5Password(srcPassword, salt);
	    		// 加密-4：將加密後的密码封装在user對象中
	    		user.setPassword(md5Password);
	    		// 加密-5：将鹽值封裝在user對象中
	    		user.setSalt(salt);
	    		// 執行註冊
	    		addnew(user);
	    		//返回註冊的用戶對象
	    		return user;
	    } else {
	        // 否：用戶名已被占用，抛出DuplicateKeyException異常
	        throw new DuplicateKeyException("註冊失败！註冊的用户名(" + user.getUsername() + ")被占用！");
	    }
	}
	
	
	@Override
	public User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException {
		// 根據參數username查詢用戶數據
		User data = findByUsername(username);
	    // 判斷用戶數據是否為null
		if(data == null){
			// 是：為null，用户名不存在，则抛出UserNotFoundException
			throw new UserNotFoundException("登入失敗，用戶名不存在!");
		}
	    // 否：非null，根據用戶名找到數據，取出鹽值
		String salt = data.getSalt();
	    //  对參數password執行加密
		String md5Password = getMd5Password(password, salt);
	    //  判斷密碼是否匹配
		if(data.getPassword().equals(md5Password)){
			//  是：匹配，密碼正確，則判斷是否被删除
			if(data.getIsDelete() == 1 ){
				//是：已被删除，则抛出UserNotFoundException
				throw new UserNotFoundException("登入失敗，用戶名被刪除!");
			}
			// 否：沒被刪除，則登入成功，將第一步查询的用戶數據中的鹽值和密碼設置為null
			data.setPassword(null);
			data.setSalt(null);
			// 返回用戶數據
			return data;
		}else{
			//  否：不匹配，密碼錯誤，则抛出PasswordNotMatchException
			throw new PasswordNotMatchException("登入失敗，密碼錯誤!");
		}
	}
	
	
	@Override
	public void changePassword(Integer uid, String oldPassword, String newPassword)
			throws UserNotFoundException, PasswordNotMatchException, UpdateException {
		// 根據uid查詢用戶數據
		User data = findById(uid);
	    // 判斷查詢結果是否為null
		if(data == null){
			// 是：拋出異常：UserNotFoundException
			throw new UserNotFoundException("用戶數據不存在!");
		}
	    // 判断查詢结果中的isDelete是否為1
		if(data.getIsDelete() == 1){
			// 是：拋出異常：UserNotFoundException
			throw new UserNotFoundException("用戶數據不存在!");
		}
	    // 取出查询结果中的鹽值
		String salt = data.getSalt();
	    // 對參數oldPassword執行MD5加密
		String oldMd5Password = getMd5Password(oldPassword, salt);
	    // 将加密结果與查詢结果中的password對比是否匹配
		if(data.getPassword().equals(oldMd5Password)){
		    // 是：原密碼正确，對參數newPassword執行MD5加密
			String newMd5Password = getMd5Password(newPassword, salt);
		    // 獲取當前時間
			Date now = new Date();
		    // 更新密碼
			updatePassword(uid, newMd5Password, data.getUsername(),now);
		}else{
			// 否：原密碼錯誤，拋出異常：PasswordNotMatchException
			throw new PasswordNotMatchException("原密碼錯誤");
		}
	}
	
	@Override
	public void changeInfo(User user) throws UserNotFoundException, UpdateException {
		// 根據user.getId()查詢用戶數據
		User data = findById(user.getId());
		// 判斷數據是否為null
		if(data == null){
			// 是：拋出：UserNotFoundException
			throw new UserNotFoundException("用戶數據不存在!");
		}
		// 判斷is_delete是否為1
		if(data.getIsDelete() == 1){
			// 是：抛出：UserNotFoundException
			throw new UserNotFoundException("用戶數據不存在!");
		}
		// 向參數對象中封裝：
		// - modified_user > data.getUsername()
		// - modified_time > new Date()
		user.setModifiedUser(data.getUsername());
		user.setModifiedTime(new Date());
		// 執行修改：gender,phone,email,modified_user,modified_time
		updateInfo(user);
		
	}	
	
	@Override
	public void changeAvatar(Integer uid, String avatar) throws UserNotFoundException, UpdateException {
		// 根据user.getId()查询用户數據
		User data = findById(uid);
		// 判斷數據是否是null
		if(data == null){
			// 是：抛出：UserNotFoundException
			throw new UserNotFoundException("改頭像失敗，用戶數據不存在!");
		}
		// 判斷is_delete是否為1
		if(data.getIsDelete() == 1){
			// 是：抛出：UserNotFoundException
			throw new UserNotFoundException("改頭像失敗，用戶數據被刪除!");
		}
		//執行更新頭像
		String modifiedUser = data.getUsername();
		Date modifiedTime = new Date();
		updateAvatar(uid, avatar, modifiedUser, modifiedTime);
	}
	
	@Override
	public User getById(Integer id){
		User data = findById(id);
		data.setPassword(null);
		data.setSalt(null);
		data.setIsDelete(null);
		return data;
	}
	
	/**
	 * 獲取根據Md5加密的密碼
	 * @param srcPassword
	 * @param salt
	 * @return
	 */
	private String getMd5Password(String srcPassword,String salt){
	    // 鹽值 拼接 原密碼 拼接 鹽值
	    String str = salt + srcPassword + salt;
	    // 循環執行10次運算
	    for (int i = 0; i < 10; i++) {
	        str = DigestUtils.md5DigestAsHex(str.getBytes());
	    }
	    // 返回摘要结果
	    return str;
	}
	
	/**
	 * 插入用戶數據
	 * @param user 用戶數據
	 * @throws InsertException
	 */
	private void addnew(User user){
		Integer rows = userMapper.addnew(user);
		if(rows != 1 ){
			throw new InsertException("增加用戶時出現未知錯誤");
		}
	}
	
	/**
	 * 更新密碼
	 * @param uid 用戶的id
	 * @param password 新密碼
	 * @param modifiedUser 修改人
	 * @param modifiedTime 修改時間
	 * @return 受影響行數
	 */
	private void updatePassword(Integer uid, String password, String modifiedUser, Date modifiedTime){
		Integer rows =userMapper.updatePassword(uid, password, modifiedUser, modifiedTime);
		if(rows != 1){
			throw new UpdateException("更新密碼失敗!出現未知錯誤!");
		}
	}
	
	/**
	 * 更新個人資料
	 * @param user 用戶數據
	 */
	private void updateInfo(User user){
		//執行更新，獲取返回值
		Integer rows = userMapper.updateInfo(user);
		//判斷返回值，出錯時拋出"更新時的未知錯誤"
		if(rows != 1){
			throw new UpdateException("更新用戶數據出現未知錯誤");
		}
	}
	
	/**
	 * 更新頭像
	 * @param uid 用戶的id
	 * @param avatart 新頭像路徑
	 * @param modifiedUser
	 * @param modifiedTime
	 */
	private void updateAvatar(Integer uid, String avatar, String modifiedUser, Date modifiedTime) {
		    Integer rows =userMapper.updateAvatar(uid, avatar, modifiedUser, modifiedTime);
		    if (rows != 1) {
		        throw new UpdateException("更新頭像出現未知錯誤");
		    }
		}
	
	/**
	 * 根据用户名查询用户數據
	 * @param username 用户名
	 * @return 匹配的用戶數據，如果沒有則返回null
	 */
	private User findByUsername(String username){
		return userMapper.findByUsername(username);
	}
	
	/**
	 * 根据用户名id查用戶數據
	 * @param id 用戶id
	 * @return 匹配的用戶數據，如果沒有則返回null
	 */
	private User findById(Integer id){
		return userMapper.findById(id);
	}
	







	
	











	
}
