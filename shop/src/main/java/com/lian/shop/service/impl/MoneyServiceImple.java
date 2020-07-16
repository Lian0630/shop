package com.lian.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lian.shop.entity.Money;
import com.lian.shop.mapper.MoneyMapper;
import com.lian.shop.service.IMoneyService;


/**
 * 商品數據實現類
 */
@Service
public class MoneyServiceImple implements IMoneyService {
	@Autowired
	private MoneyMapper moneyMapper;
	

	
	@Override
	public Money getById(Long id) {
		return findById(id);
	}
	
	@Override
	public List<Money> getByPriority(Integer count) {
		return findByPriority(count);
	}


	/**
	 * 根據id查詢商品詳情
	 * @param id 商品的id
	 * @return 商品詳情，如果沒有匹配的數據，則返回null
	 */
	private Money findById(Long id){
		return moneyMapper.findById(id);
	}
	
	/**
	 * 根據優先級獲取商品數據的列表
	 * @param count 獲取的商品數量
	 * @return 優先級最高的幾個商品數據的列表
	 */
	private List<Money> findByPriority(Integer count){
		return moneyMapper.findByPriority(count);
	}

	
}
