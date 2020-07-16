package com.lian.shop.service;

import java.util.List;

import com.lian.shop.entity.Address;
import com.lian.shop.service.ex.DeleteException;
import com.lian.shop.service.ex.InsertException;



/**
 * 處理收貨地址的業務層接口
 */
public interface IAddressService {
	/**
	 * 創新收貨地址
	 * @param username 當前執行人
	 * @param address 收貨地址
	 * @return 受影響行數
	 * @throws InsertException
	 */
	Address creat(String username,Address address) throws InsertException;
	/**
	 * 獲取某用户的收貨地址列表
	 * @param uid 用戶id
	 * @return 收貨地址列表
	 */
	List<Address> getListByUid(Integer uid);
	/**
	 * 設置默認收貨地址
	 * @param uid 收貨地址歸屬用戶id
	 * @param id 將要設置為默認收貨地址的數據id
	 */
	void setDefault(Integer uid, Integer id);
	/**
	 * 根據id查詢收貨地址
	 * @param id 收貨地址id
	 * @return 匹配的收貨地址數據，如果沒有匹配數據則返回null
	 */
	Address getById(Integer id);
	
	/**
	 * 根據ID刪除收貨地址
	 * @param uid 收貨地址歸屬的用戶id
	 * @param id  收貨地址數據的id
	 * @throws DeleteException
	 */
	void delete(Integer uid, Integer id) throws DeleteException;
	
}
