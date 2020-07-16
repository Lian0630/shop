package com.lian.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lian.shop.controller.ex.FileEmptyException;
import com.lian.shop.controller.ex.FileSizeOutOfLimitException;
import com.lian.shop.controller.ex.FileTypeNotSupportException;
import com.lian.shop.controller.ex.FileUploadException;
import com.lian.shop.controller.ex.RequestException;
import com.lian.shop.service.ex.AccessDeniedException;
import com.lian.shop.service.ex.AddressNotFoundException;
import com.lian.shop.service.ex.DeleteException;
import com.lian.shop.service.ex.DuplicateKeyException;
import com.lian.shop.service.ex.InsertException;
import com.lian.shop.service.ex.PasswordNotMatchException;
import com.lian.shop.service.ex.ServiceException;
import com.lian.shop.service.ex.UpdateException;
import com.lian.shop.service.ex.UserNotFoundException;
import com.lian.shop.util.ResponseResult;



/**
 * 當前項目中所有控制器類的基類
 */
public abstract class BaseController{
	
	/**
	 * 正確響應代號
	 */
	public static final Integer SUCCESS=200;
	
	@ExceptionHandler({ServiceException.class,RequestException.class})
	@ResponseBody
	public ResponseResult<Void> handleException(Exception e){
		Integer state = null;
		
		if(e instanceof DuplicateKeyException){
			// 400-違反Unique約束異常
	       state = 400;
	    } else if(e instanceof UserNotFoundException){
	    	// 401-用戶未找到
	    	state = 401;
	    } else if(e instanceof PasswordNotMatchException){
	    	// 402-密碼錯誤
	    	state = 402;
	    } else if(e instanceof AddressNotFoundException){
	    	// 403-收貨地址數據不存在
	    	state = 403;
	    } else if(e instanceof AccessDeniedException){
	    	// 404-訪問被拒絕異常
	    	state = 404;
	    } else if (e instanceof InsertException) {
	        // 500-插入数据异常
	       state = 500;
	    } else if(e instanceof UpdateException){
	    	//501-更新數據異常
	    	state = 501;
	    } else if(e instanceof DeleteException){
	    	//502-刪除數據異常
	    	state = 502;
	    } else if(e instanceof FileEmptyException){
	    	//600-上傳文件為空的異常
	    	state = 600;
	    } else if(e instanceof FileSizeOutOfLimitException){
	    	//601-上傳文件超出限制的異常
	    	state = 601;
	    } else if(e instanceof FileTypeNotSupportException){
	    	//602-上傳文件類型不支持的異常
	    	state = 602;
	    } else if(e instanceof FileUploadException){
	    	//610-文件上傳異常
	    	state = 610;
	    }

	    return new ResponseResult<>(state, e); 
	}
	
	/**
	 * 從Session中拿uid
	 * @param session HttpSession對象
	 * @return 當前登入的用戶id
	 */
	protected Integer getUidFromSession(HttpSession session) {
	    return Integer.valueOf(session.getAttribute("uid").toString());
	}
}
