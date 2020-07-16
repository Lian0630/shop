package com.lian.shop.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lian.shop.entity.Money;



/**
 * 商品數據業務層接口
 */
public interface IMoneyService {
	


	
	/**
	 * 根據id查詢商品詳情
	 * @param id 商品的id
	 * @return 商品詳情，如果沒有匹配的數據，則返回null
	 */
	Money getById(Long id);
	
	/**
	 * 根據優先級獲取商品數據的列表
	 * @param count 獲取的商品數量
	 * @return 優先級最高的幾個商品數據的列表
	 */
	List<Money> getByPriority(Integer count);
}
