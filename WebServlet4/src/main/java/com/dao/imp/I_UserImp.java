package com.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.I_User;
import com.domain.User;
import com.unit.UnitMssql;

public class I_UserImp implements I_User{
	  public Connection conn1 = null;
	  public ResultSet rs = null;
	  public PreparedStatement ps = null;
	   
	       // 查询所有的数据
	       @Override
	       public List<User> searchAllData() {
	           conn1 = UnitMssql.getConnection();// 链接数据库
	           List<User> list = new ArrayList<User>();
	           try {
	               String sqlSelect = "select * from users"; // 查询多条数据
	               ps = conn1.prepareStatement(sqlSelect);
	               rs = ps.executeQuery();
	               User user = null;
	               while (rs.next()) {
	                   user = new User();
	                   user.setId(rs.getInt("id"));
	                   user.setName(rs.getString("name"));
	                   user.setPhone(rs.getInt("phone"));
	                   user.setAddress(rs.getString("address"));
	                   user.setRemark(rs.getString("remark"));	                  
	                   list.add(user);
	               }
	           } catch (SQLException e) {
	               e.printStackTrace();
	           } finally {
	               UnitMssql.Close(rs, ps, conn1);
	           }
	           return list;
	       }
	   
	       // 新增
	       @Override
	       public int addData(User t) {
	           conn1 = UnitMssql.getConnection();
	           int i = 0;
	           try {
	               String sqlInsert = "insert into users(id,name,phone,address,remark) values(?,?,?,?,?) ;";
	               ps = conn1.prepareStatement(sqlInsert);
	               // 这里的1,2..必须要按上面的新增的顺序来定义
	               ps.setInt(1, t.getId());
	               ps.setString(2, t.getName());
	               ps.setInt(3, t.getPhone());
	               ps.setString(4, t.getAddress());
	               ps.setString(5, t.getRemark());
	               ps.executeUpdate();         
	               
	               //rs = getDataById(t.getId());// 得到 最新的 ID
	               if (rs.next()) {// 是否存在
	                   i = rs.getInt(1);
	               }
	           } catch (SQLException e) {
	               e.printStackTrace();
	           } finally {
	               UnitMssql.Close(rs, ps, conn1);
	           }
	    
	           return i;
	       }
	   
	       // 修改
	       @Override
	       public int update(User t) {
	           conn1 = UnitMssql.getConnection();
	           int i = 0;
	           try {
	               String sqlUpdate = "update users set name=?, phone =? ,address=?,remark=?  where id=?";
	               ps = conn1.prepareStatement(sqlUpdate);
	               ps.setString(1, t.getName());
	               ps.setInt(2, t.getPhone());
	               ps.setString(3, t.getAddress());
	               ps.setString(4, t.getRemark());
	               ps.setInt(5, t.getId());
	               i = ps.executeUpdate();
	           } catch (SQLException e) {
	               e.printStackTrace();
	           } finally {
	               UnitMssql.Close(null, ps, conn1);
	           }
	           return i; 
	      }
	  
	      // 删除
	      @Override
	      public int delete(int id) {
	          conn1 = UnitMssql.getConnection();
	          int i = 0;
	          try {
	              String sqlDelete = "delete from users where id=?";
	              ps = conn1.prepareStatement(sqlDelete);
	              ps.setInt(1, id);
	              i = ps.executeUpdate();
	              if (i == 1) {
	                  return i;
	              }
	          } catch (SQLException e) {
	              e.printStackTrace();
	          } finally {
	              UnitMssql.Close(null, ps, conn1);
	          }
	          return i;
	      }
	  
	      // 查询一条数据通过ID
	      	@Override
	      public User getDataById(int id) {
	          conn1 = UnitMssql.getConnection();
	          String sql = "select * from users where id=?";
	          User user = null;
	          if (id > 0) {
	              try {
	                  ps = conn1.prepareStatement(sql);
	                  ps.setInt(1, id);
	                 rs = ps.executeQuery();
	                  if (rs.next()) {
	                      user = new User();
	                      user.setId(rs.getInt("id"));
	                      user.setName(rs.getString("name"));
	                      user.setPhone(rs.getInt("phone"));
	                      user.setAddress(rs.getString("address"));
	                      user.setRemark(rs.getString("remark"));
	                  }
	              } catch (SQLException e) {
	                  e.printStackTrace();
	              } finally {
	                 UnitMssql.Close(null, ps, conn1);
	              }
	          }
	          return user;
	      }	 
}
