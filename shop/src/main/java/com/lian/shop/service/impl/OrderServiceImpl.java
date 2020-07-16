package com.lian.shop.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lian.shop.entity.Address;
import com.lian.shop.entity.Order;
import com.lian.shop.entity.OrderItem;
import com.lian.shop.mapper.OrderMapper;
import com.lian.shop.service.IAddressService;
import com.lian.shop.service.ICartService;
import com.lian.shop.service.IOrderService;
import com.lian.shop.service.ex.AddressNotFoundException;
import com.lian.shop.service.ex.InsertException;
import com.lian.shop.vo.CartVO;
import com.lian.shop.vo.OrderVO;


/**
 * 訂單與訂單商品的業務層實現類
 */
@Service
public class OrderServiceImpl implements IOrderService{
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private IAddressService addressService;
	@Autowired
	private ICartService cartService;
	
	
	@Override
	@Transactional
	public Order createOrder(Integer uid, String username, Integer addressId, Integer[] cartIds)throws InsertException {
		// 创建Date对象
		Date now = new Date();
	    // 声明pay变量
		Long pay = 0L;
	    // List<CartVO> cartService.getByIds(ids)
		List<CartVO> carts = cartService.getByIds(cartIds);
		// 创建List<OrderItem> orderItems
		List<OrderItem> orderItems = new ArrayList<>();
	    // 遍历集合
		for(CartVO cartVO : carts){
			//计算总价pay
			pay += cartVO.getNewPrice() * cartVO.getCount();
			// -- 创建OrderItem
			OrderItem item = new OrderItem();
		    // -- item属性：money_5，OK
			item.setMoneyId(cartVO.getGid());
			item.setMoneyTitle(cartVO.getTitle());
			item.setMoneyImage(cartVO.getImage());
			item.setMoneyPrice(cartVO.getNewPrice());
			item.setMoneyCount(cartVO.getCount());
		    // -- item属性：4个日志，OK
			item.setCreatedUser(username);
			item.setCreatedTime(now);
			item.setModifiedUser(username);
			item.setModifiedTime(now);
			//將item添加到集合中
			orderItems.add(item);
		}
	    
	   

	    // 创建Order对象
		Order order = new Order();
	    // order属性：uid，OK
		order.setUid(uid);
	    // order属性：pay，OK
		order.setPay(pay);
		
	    // 通过addressService.getById()得到收货地址数据
		Address address = addressService.getById(addressId);
		
		//判斷是否查詢到address數據
		if(address == null){
			throw new AddressNotFoundException("創建訂單失敗!收貨地址數據有誤!請重整!");
		}
		
	    // order属性：recv_4，OK
		order.setRecvName(address.getName());
		order.setRecvPhone(address.getPhone());
		order.setRecvDistrict(address.getDistrict());
		order.setRecvAddress(address.getAddress());
	    // order属性：order_time，OK
		order.setOrderTime(now);
	    // order属性：status，OK，值为0
		order.setStatus(0);
	    // order属性：4个日志，OK
		order.setCreatedUser(username);
		order.setCreatedTime(now);
		order.setModifiedUser(username);
		order.setModifiedTime(now);
	    // 插入订单数据并获取oid：insertOrder(order)
		insertOrder(order);
	    // 遍历orderItems
		for(OrderItem orderItem : orderItems){
			// item属性：oid
			orderItem.setOid(order.getId());
			// 插入订单商品数据
			insertOrderItem(orderItem);
		}		
		
		//刪除購物車中對應的數據
		//cartService.deleteByIds(cartIds);
		
		
		//返回
		return order;
	}
	
	@Override
	public OrderVO getById(Integer id) {
		return findById(id);
	}
	
	
	/**
	 * 插入订单数据
	 * @param order 訂單數據
	 */
	private void insertOrder(Order order){
		Integer rows = orderMapper.insertOrder(order);
		if(rows != 1){
			throw new InsertException("插入訂單數據發生未知錯誤");
		}
	}
	/**
	 * 插入订单商品数据
	 * @param orderItem 订单商品数据
	 */
	private void insertOrderItem(OrderItem insertOrderItem){
		Integer rows = orderMapper.insertOrderItem(insertOrderItem);
		if(rows != 1){
			throw new InsertException("插入訂單商品數據發生未知錯誤");
		}
	}
	
	/**
	 * 根據id查詢訂單詳情
	 * @param id 訂單id
	 * @return 匹配的訂單詳情，如果沒有匹配數據返回null
	 */
	private OrderVO findById(Integer id){
		return orderMapper.findById(id);
	}

	
	
	
}