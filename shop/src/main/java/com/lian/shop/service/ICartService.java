package com.lian.shop.service;

import java.util.List;

import com.lian.shop.entity.Cart;
import com.lian.shop.service.ex.AccessDeniedException;
import com.lian.shop.service.ex.CartNotFoundException;
import com.lian.shop.service.ex.DeleteException;
import com.lian.shop.service.ex.InsertException;
import com.lian.shop.service.ex.UpdateException;
import com.lian.shop.vo.CartVO;


/**
 * 購物車業務層接口
 */
public interface ICartService {
	/**
	 * 將商品添加到購物車
	 * @param username 當前操作執行人
	 * @param cart 購物車數據
	 * @throws InsertException
	 * @throws UpdateException
	 */
	void addToCart(String username,Cart cart) throws InsertException, UpdateException;
	/**
	 * 增加購物車中商品數量
	 * @param id  購物車數據id
	 * @param uid 當前用戶id
	 * @throws CartNotFoundException
	 * @throws AccessDeniedException
	 * @throws UpdateException
	 */
	void addCount(Integer id, Integer uid)throws CartNotFoundException, AccessDeniedException,UpdateException;
	
	/**
	 *  減少購物車商品數量
	 * @param id  購物車數據id
	 * @param uid 當前用戶id
	 * @throws CartNotFoundException
	 * @throws AccessDeniedException
	 * @throws UpdateException
	 */
	void reduceCount (Integer id, Integer uid)throws CartNotFoundException, AccessDeniedException,UpdateException;
	
	/**
	 * 根據用戶id查詢該用戶的購物車數據列表
	 * @param uid 用戶id
	 * @return 該用戶的購物車數據列表
	 */
	List<CartVO> getByUid(Integer uid);
	/**
	 * 根據無數個id查詢匹配的購物車數據集合
	 * @param ids 無數個id
	 * @return 匹配的購物車數據集合
	 */
	List<CartVO> getByIds(Integer[] ids);
	
	/**
	 * 根據ID刪除購物車數據
	 * @param uid 購物車數據歸屬的用戶id
	 * @param id  購物車數據的id
	 * @throws DeleteException
	 */
	void delete(Integer uid, Integer id) throws DeleteException;
	
	/**
	 * 根據UID刪除購物車數據
	 * @param uid 購物車數據歸屬的用戶id
	 * @throws DeleteException
	 */
	void deleteByUid(Integer uid) throws DeleteException;
	
}
