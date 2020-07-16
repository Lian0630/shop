package com.lian.shop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lian.shop.entity.Cart;
import com.lian.shop.service.ICartService;
import com.lian.shop.util.ResponseResult;
import com.lian.shop.vo.CartVO;


/**
 * 購物車數據的控制器類
 */
@RestController
@RequestMapping("/cart")
public class CartController extends BaseController {
	
	@Autowired
	private ICartService cartService;
	
	@PostMapping("/add_to_cart")
	public ResponseResult<Void> addToCart(HttpSession session, Cart cart) {
	    // 从session中取username
		String username = session.getAttribute("username").toString();
	    // 从session中取uid
		Integer uid = getUidFromSession(session);
	    // 將uid封裝到cart中
		cart.setUid(uid);
	    // 執行業務方法
		cartService.addToCart(username, cart);
	    // 返回
		return new ResponseResult<>(SUCCESS);
	}
	
	@GetMapping("/list")
	public ResponseResult<List<CartVO>> getByUid(HttpSession session) {
	    // 從session取uid
		Integer uid = getUidFromSession(session);
	    // 執行查询，獲取結果
		List<CartVO> list = cartService.getByUid(uid);
	    // 返回
		return new ResponseResult<>(SUCCESS,list);
	}
	
	@GetMapping("/add_count")
	public ResponseResult<Void> addCount(@RequestParam("id") Integer id,HttpSession session) {
		//獲取uid
		Integer uid = getUidFromSession(session);
		//執行業務
		cartService.addCount(id, uid);
		//返回
		return new ResponseResult<>(SUCCESS);
	
	}
	
	@GetMapping("/reduce_count")
	public ResponseResult<Void> reduceCount(@RequestParam("id") Integer id,HttpSession session) {
		//獲取uid
		Integer uid = getUidFromSession(session);
		//執行業務
		cartService.addCount(id, uid);
		//返回
		return new ResponseResult<>(SUCCESS);
	
	}
	
	@GetMapping("/get_by_ids")
	public ResponseResult<List<CartVO>>getByIds(@RequestParam("cart_id") Integer[] ids) {
		// 執行查询，獲取結果
		List<CartVO> list = cartService.getByIds(ids);
	    //返回
		return new ResponseResult<List<CartVO>>(SUCCESS,list);
	}
	
	@GetMapping("/delete/{id}")
	public ResponseResult<Void> deleteByid(HttpSession session,@PathVariable("id") Integer id) {
	    // 從session中獲取uid
		Integer uid = getUidFromSession(session);
	    // 調用業務層方法設置
		cartService.delete(uid, id);
	    // 返回
		return new ResponseResult<>(SUCCESS);
	}
	
	@GetMapping("/clean")
	public ResponseResult<Void> deleteByUid(HttpSession session) {
		 // 從session中獲取uid
		Integer uid = getUidFromSession(session);
	    // 調用業務層方法
		cartService.deleteByUid(uid);
	    // 返回
		return new ResponseResult<>(SUCCESS);
	}
	
	
}
