package com.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.entity.User;

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

private UserDao userDao;

public void init() {
	userDao = new UserDao();
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);
}

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("執行ServletupdateUser");
	
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	int phone = Integer.parseInt(request.getParameter("phone"));
	String address = request.getParameter("address");
	String description = request.getParameter("description");
	User book = new User(id, name,phone,address,description);
	try {
		userDao.updateUser(book);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	request.getRequestDispatcher("index.jsp").forward(request, response);
}

}
