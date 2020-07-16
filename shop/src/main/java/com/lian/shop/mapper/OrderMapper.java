package com.lian.shop.mapper;

import com.lian.shop.entity.Order;
import com.lian.shop.entity.OrderItem;
import com.lian.shop.vo.OrderVO;

/**
 * 訂單與訂單商品數據的持久層接口
 */
public interface OrderMapper {
	/**
	 * 插入訂單數據
	 * @param order 訂單數據
	 * @return 受影響行數
	 */
	Integer insertOrder(Order order);
	/**
	 * 插入訂單商品數據
	 * @param orderItem 訂單商品數據
	 * @return 受影響行數
	 */
	Integer insertOrderItem(OrderItem orderItem);
	/**
	 * 根據id查詢訂單詳情
	 * @param id 訂單id
	 * @return 匹配的訂單詳情，如果沒有匹配數據返回null
	 */
	OrderVO findById(Integer id);
}
