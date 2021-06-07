package com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.entity.User;

import net.sf.json.JSONArray;



/**
 * Servlet implementation class AllServlet
 */
@WebServlet("/AllServlet")
public class AllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


private UserDao userDao;

public void init() {
	userDao = new UserDao();
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);
}

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("執行ServletlistUser");
	
	List<User> listUser = userDao.selectAllUsers();
	System.out.println("list:"+listUser);
	JSONArray jsonArray = JSONArray.fromObject(listUser);
	System.out.println("jsonArray:"+jsonArray);
	response.setContentType("text/html;charset=utf-8");
	response.getWriter().println(jsonArray);
	
	System.out.println("結束ServletlistUser");
	
}


}
