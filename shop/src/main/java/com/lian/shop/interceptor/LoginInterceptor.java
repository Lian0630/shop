package com.lian.shop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * 登入攔截器
 */
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		//獲取session對象
		HttpSession session = request.getSession();
		//判斷Session中是否存在uid
		if(session.getAttribute("uid") == null){
			//為null，即沒有uid，即沒有登入
			response.sendRedirect("../web/login.html");
			//攔截
		    return false;
		}else{
			//非null，即存在uid，即已經登入
			return true;
		}	
		
	}
	
}



