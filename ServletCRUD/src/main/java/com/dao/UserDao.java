package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.User;

public class UserDao {
	private String jdbcURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=test";
	private String jdbcUsername = "sa";
	private String jdbcPassword = "Gg56997951";

	private static final String INSERT_USERS_SQL = "INSERT INTO user_table" + "  (id,name,phone,address,description) VALUES " + " (?, ?, ?, ?, ?);";
	private static final String SELECT_USER_BY_ID = "select id,name,phone,address,description from user_table where id =?";
	private static final String SELECT_ALL_USERS = "select * from user_table";
	private static final String DELETE_USERS_SQL = "delete from user_table where id = ?;";
	private static final String UPDATE_USERS_SQL = "update user_table set name = ?,phone= ?, address =?, description =? where id = ?;";

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}
		return connection;
	}
	
	
	//增加
	public void addUser(User user) throws SQLException {
		System.out.println("執行DAOaddUser");
		
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getId());
			preparedStatement.setString(2, user.getName());
			preparedStatement.setInt(3, user.getPhone());
			preparedStatement.setString(4, user.getAddress());
			preparedStatement.setString(5, user.getDescription());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	//根據ID查詢
	public User selectUser(String id) {
		System.out.println("執行DaoselectUser");
		
		User user = null;		
		try (Connection connection = getConnection();			
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setString(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();			
			while (rs.next()) {
				String name = rs.getString("name");
				int phone = rs.getInt("phone");
				String address = rs.getString("address");
				String description=rs.getString("description");
				user = new User(id, name,phone,address,description);
			}
		} catch (SQLException e) {
			printSQLException(e);
			System.out.println("查詢錯誤");
		}
		System.out.println("查玩了");
		return user;		
	}	
	//全部數據
	public List<User> selectAllUsers() {
		System.out.println("執行DaolistUser");
				
		List<User> users = new ArrayList<>();		
		try (Connection connection = getConnection();			
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {			
			ResultSet rs = preparedStatement.executeQuery();			
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				int phone = rs.getInt("phone");
				String address = rs.getString("address");
				String description=rs.getString("description");
				users.add(new User(id, name,phone, address,description));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		System.out.println("結束DaoServletlistUser");
		return users;
	}
	//刪除
	public boolean deleteUser(String id) throws SQLException {
		System.out.println("執行DAOdeleteUser");
		
		boolean rowDeleted;
		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setString(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	//改
	public boolean updateUser(User user) throws SQLException {
		System.out.println("執行DaoupdateUser");
		
		boolean rowUpdated;
		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, user.getName());
			statement.setInt(2, user.getPhone());
			statement.setString(3, user.getAddress());
			statement.setString(4, user.getDescription());
			statement.setString(5, user.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
