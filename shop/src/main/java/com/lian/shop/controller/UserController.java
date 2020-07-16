package com.lian.shop.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.lian.shop.controller.ex.FileEmptyException;
import com.lian.shop.controller.ex.FileSizeOutOfLimitException;
import com.lian.shop.controller.ex.FileTypeNotSupportException;
import com.lian.shop.entity.User;
import com.lian.shop.service.IUserService;
import com.lian.shop.util.ResponseResult;


@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
	
	/**
	 * 上傳文件夾名稱
	 */
	private static final String UPLOAD_DIR_NAME = "upload";
	/**
	 * 上傳文件的最大最小
	 */
	private static final long FILE_MAX_SIZE = 5 * 1024 * 1024;
	/**
	 * 允許上傳的文件類型
	 */
	private static final List<String> FILE_CONTENT_TYPE = new ArrayList<>();
	/**
	 * 初始化允許上傳的文件類型
	 */
	static{
		FILE_CONTENT_TYPE.add("image/jpeg");
		FILE_CONTENT_TYPE.add("image/png");
		FILE_CONTENT_TYPE.add("image/jpg");
	}
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("/reg.do")
	public ResponseResult<Void> handleReg(User user){
		userService.reg(user);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@PostMapping("/login.do")
	public ResponseResult<User> handleLogin(@RequestParam("username") String username,@RequestParam("password")String password,HttpSession session){
		// 執行登入
	    User user= userService.login(username, password);
	    // 將相關信息存入到Session
	    session.setAttribute("uid", user.getId());
	    session.setAttribute("username", user.getUsername());
	    //返回
		return new ResponseResult<>(SUCCESS,user);
	}
	
	@PostMapping("/logout.do")
	public ResponseResult<User> handleLogout(HttpSession session){
		
		session.invalidate();
	    //返回
		return new ResponseResult<>(SUCCESS);
	}
	
	@PostMapping("/password.do")
	public ResponseResult<Void> changePassword(@RequestParam("old_password") String oldPassword,@RequestParam("new_password") String newPassword,
	    HttpSession session) {
		//獲取當前登入的用戶的ID
		Integer uid = getUidFromSession(session);
		//執行修改密碼
		userService.changePassword(uid,oldPassword,newPassword);
		//返回		
		return new ResponseResult<>(SUCCESS);
	}
	
	@PostMapping("/info.do")
	public ResponseResult<User> getInfo(HttpSession session) {
		//獲取當前用戶登入的用戶的id
		Integer id = getUidFromSession(session);
		//執行查詢，獲取用戶數據
		User user = userService.getById(id);
		//返回
		return new ResponseResult<User>(SUCCESS, user);
	}
	
	@PostMapping("/change_info.do")
	public ResponseResult<Void> changeInfo(User user, HttpSession session) {
		//獲取當前用戶登入的用戶的id
		Integer id = getUidFromSession(session);
		//將id封裝到參數user中，因為user是用戶提交的數據，不包含id
		user.setId(id);
		//執行修改
		userService.changeInfo(user);
		//返回
		return new ResponseResult<>(SUCCESS);
	}
	
	@PostMapping("/upload.do")
	public ResponseResult<String> handleUpload(HttpSession session,@RequestParam("file") MultipartFile file) {
	    // 是否存在上傳文件 > file.isEmpty()
		if(file.isEmpty()){
			//拋出異常：文件不允許為空
			throw new FileEmptyException("上傳失敗，文件為空！");
		}	
	    // 檢查文件大小 > file.getSize()
		if(file.getSize() > FILE_MAX_SIZE){
			//拋處異常：文件大小超出限制
			throw new FileSizeOutOfLimitException("上傳失敗，文件過大！");
		}
	    // 檢查文件類型 > file.getContentType()
		if(!FILE_CONTENT_TYPE.contains(file.getContentType())){
			//拋出異常：文件類型限制
			throw new FileTypeNotSupportException("上傳失敗，文件類型不符合！");
		}
		
	    // 確定上傳文件夾路徑 > session.getServletContext.getRealPath(UPLOAD_DIR_NAME) > exists() > mkdirs()
		String parentPath = session.getServletContext().getRealPath("upload");
		File parent = new File(parentPath);
		if(!parent.exists()){
			parent.mkdirs();
		}
	    // 确定文件名 > getOriginalFileName() 
		String originalFileName = file.getOriginalFilename();
		int beginIndex = originalFileName.lastIndexOf(".");
		String suffix = originalFileName.substring(beginIndex);
		String fileName = System.currentTimeMillis() + "" + (new Random().nextInt(900000)+100000) + suffix; 
		
		//確定文件
		File dest = new File(parent,fileName);
	    // 執行保存文件
		try {
			file.transferTo(dest);
			System.out.println("上傳完成！");
		} catch (IllegalStateException e) {
			e.printStackTrace();
			//拋出異常：上傳失敗
		} catch (IOException e) {	
			e.printStackTrace();
			//拋出異常：上傳失敗
		}
	    // 獲取當前用戶id
		Integer uid = getUidFromSession(session);
	    // 更新頭像
		String avatar = "/"+UPLOAD_DIR_NAME+"/"+fileName;
		userService.changeAvatar(uid, avatar);

	    // 返回
		ResponseResult<String> rr = new ResponseResult<>();
		rr.setState(SUCCESS);
		rr.setData(avatar);
		return rr;
	}
}
