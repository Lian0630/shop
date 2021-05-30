package com.service.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.dao.I_User;
import com.dao.imp.I_UserImp;
import com.domain.User;
import com.service.I_UserService;

public class IUserServiceImp implements I_UserService{
	public Connection conn1 = null;
	public ResultSet rs = null;
	public PreparedStatement ps = null;
	boolean b=false;
	//这里的 service层要想與dao层(BAL层逻辑层)建立联系那么必须要 创建  dao层的对象  
	I_User myiTb_User=new I_UserImp();//创建了 dao层的ITb_UserImp对象 
	 
	//查询所有数据
	     @Override 
	     public List<User> searchAllData() {
	          
	         return myiTb_User.searchAllData();
	     }
	     
	     //新增  
	     @Override
	     public boolean addData(User t) {
	         if (t!=null) {  myiTb_User.addData(t);
	                  b=true; 
	 }
	          return b;
	     }
	     
	     //修改
	     @Override
	     public boolean update(User t) {
	         if (t!=null) { 
	              myiTb_User.update(t);
	                  b=true; 
	 }
	          return b;
	     }
	     //删除
	     @Override
	     public boolean delete(int id) {
	         if (id!=0) {  
	                  myiTb_User.delete(id);
	                  b=true;  
	                         }
	          return b;
	     }
	         //查询一条数据
	     @Override
	     public User getDataById(int id) {
	          if (id!=0) {
	         return myiTb_User.getDataById(id);
	         }
	         else {
	            return null;
	        }
	     }
}
