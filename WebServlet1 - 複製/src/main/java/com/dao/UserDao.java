package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entity.User;
import com.util.DBUtil;

public class UserDao {
	
	/*
	 * 增加
	 */
	public boolean add(User user) {
		String sql = "insert into user_table(id,name,phone,address,description) values('"+user.getId() + "','" + user.getName() + "','" +user.getPhone()+"','" + user.getAddress()+"','" + user.getDescription() + "')";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		boolean f = false;
		int a = 0;
		
		try {
			state = conn.createStatement();
			state.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(state, conn);
		}
		
		if (a > 0) {
			f = true;
		}
		return f;
	}
	
	/**
	 * 刪除
	 */
	public boolean delete (int id) {
		boolean f = false;
		String sql = "delete from user_table where id='" + id + "'";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		int a = 0;		
		try {
			state = conn.createStatement();
			a = state.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(state, conn);
		}
		
		if (a > 0) {
			f = true;
		}
		return f;
	}
	
	/**
	 * 修改
	 */
	public boolean update(User user) {
		String sql = "update user_table set name='" + user.getName() + "', phone='" + user.getPhone() + "', address='" + user.getAddress()+ "', description='" + user.getDescription()
		+ "' where id='" + user.getId() + "'";
		
		Connection conn = DBUtil.getConn();
		Statement state = null;
		boolean f = false;
		int a = 0;
		try {
			state = conn.createStatement();
			a = state.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(state, conn);
		}
		
		if (a > 0) {
			f = true;
		}
		return f;
	}
	
	/**
	 * 驗證身分證是否唯一
	 */
	public boolean id(int id) {
		boolean flag = false;
		String sql = "select id from user_table where id = '" + id + "'";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		ResultSet rs = null;
		
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			while (rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		return flag;
	}
	
	/**
	 * 查詢
	 */
	public List<User> search(Integer id,String name,Integer phone,String address, String description) {
		String sql = "select * from user_table where ";
		if (id != null) {
			sql += "id like '%" + id + "%'";
		}
		if (name != "") {
			sql += "name like '%" + name + "%'";
		}
		if (phone !=null) {
			sql += "phone like '%" + phone + "%'";
		}
		if (address != "") {
			sql += "address like '%" + address + "%'";
		}
		if (description != "") {
			sql += "description like '%" + description + "%'";
		}
		List<User> list = new ArrayList<>();
		Connection conn = DBUtil.getConn();
		Statement state = null;
		ResultSet rs = null;

		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			User bean = null;
			while (rs.next()) {
				int id2 = rs.getInt("id");
				String name2 = rs.getString("name");
				int phone2 = rs.getInt("phone");
				String address2 = rs.getString("address");
				String description2 = rs.getString("description");
				bean = new User(id2, name2,phone2,address2,description2);
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		
		return list;
	}
	
	/**
	 * 全部資料
	 * @param name
	 * @param teacher
	 * @param classroom
	 * @return
	 */
	public List<User> list() {
		String sql = "select * from user_table";
		List<User> list = new ArrayList<>();
		Connection conn = DBUtil.getConn();
		Statement state = null;
		ResultSet rs = null;

		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			User bean = null;
			while (rs.next()) {
				int id2 = rs.getInt("id");
				String name2 = rs.getString("name");
				int phone2 = rs.getInt("phone");
				String address2 = rs.getString("address");
				String description2 = rs.getString("description");
				bean = new User(id2, name2,phone2,address2,description2);
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		
		return list;
	}

	
	
}
