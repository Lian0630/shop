package com.lian.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lian.shop.entity.Money;
import com.lian.shop.service.IMoneyService;
import com.lian.shop.util.ResponseResult;



@RestController
@RequestMapping("/money")
public class MoneyController extends BaseController{
	@Autowired
	private IMoneyService moneyService;
	

	
	@GetMapping("/details/{id}")
	public ResponseResult<Money> getById(@PathVariable("id") Long id){
		Money money = moneyService.getById(id);
		return new ResponseResult<Money>(SUCCESS,money);
	}
	
	@GetMapping("/hot")
	public ResponseResult<List<Money>> getHotMoney() {
		List<Money> list = moneyService.getByPriority(4);
	    return new ResponseResult<List<Money>>(SUCCESS, list);
	}
}
