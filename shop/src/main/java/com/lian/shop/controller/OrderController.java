package com.lian.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lian.shop.entity.Money;
import com.lian.shop.entity.Order;
import com.lian.shop.service.IOrderService;
import com.lian.shop.util.ResponseResult;
import com.lian.shop.vo.OrderVO;


@RestController
@RequestMapping("/order")
public class OrderController extends BaseController{
	
	@Autowired
	private IOrderService orderservice;
	
	@RequestMapping("/create")
	public ResponseResult<Order> createOrder(
			HttpSession session, 
			@RequestParam("address") Integer addressId, 
			@RequestParam("cart_id") Integer[] cartIds) {
		Integer uid = getUidFromSession(session);
		String username = session.getAttribute("username").toString();
		Order order = orderservice.createOrder(uid, username, addressId, cartIds);
		return new ResponseResult<Order>(SUCCESS,order);
	}
	
	@GetMapping("/details/{id}")
	public ResponseResult<OrderVO> getById(@PathVariable("id") Integer id){
		OrderVO data = orderservice.getById(id);
		return new ResponseResult<OrderVO>(SUCCESS,data);
	}
	
	
	
}
