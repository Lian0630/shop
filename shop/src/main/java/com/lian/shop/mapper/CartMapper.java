package com.lian.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lian.shop.entity.Cart;
import com.lian.shop.vo.CartVO;



/**
 * 購物車數據持久層接口
 */
public interface CartMapper {
	/**
	 * 根據用戶id和商品id查詢購物車數據
	 * @param uid 用戶id
	 * @param goodsId 商品id
	 * @return 匹配的購物車數據，如果沒有匹配的數據則返回null
	 */
	Cart findByUidAndGid(@Param("uid") Integer uid, @Param("moneyId") Long moneyId);
	
	/**
	 * 新增購物車數據
	 * @param cart 購物車數據
	 * @return 受影響的行數
	 */
	Integer addnew(Cart cart);
	
	
	
	/**
	 * 更新購物車中的商品數量
	 * @param id 購物車數據id
	 * @param count 新的數量
	 * @return 受影響行數
	 */
	Integer updateCount(@Param("id") Integer id, @Param("count") Integer count);
	
	
	/**
	 * 根據id獲取購物車數據
	 * @param id 購物車數據id
	 * @return 匹配的購物車數據，如果沒有匹配的數據則返回null
	 */
	Cart findById(Integer id);
	
	/**
	 * 根據id刪除購物車數據
	 * @param id 購物車數據id
	 * @return 受影響行數
	 */
	Integer deleteById(Integer id);
	
	/**
	 * 根據用戶id查詢該用戶的購物車數據列表
	 * @param uid 用戶id
	 * @return 該用戶的購物車數據列表
	 */
	List<CartVO> findByUid(Integer uid);
	/**
	 * 根據無數個id查詢匹配的購物車數據集合
	 * @param ids 無數個id
	 * @return 匹配的購物車數據集合
	 */
	List<CartVO> findByIds(Integer[] ids);
	
	/**
	 * 根據uid刪除購物車數據
	 * @param uid 用戶uid
	 * @return 受影響行數
	 */
	Integer deleteByUid(Integer uid);
	
	
}
