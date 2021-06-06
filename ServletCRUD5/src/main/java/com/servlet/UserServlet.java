package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.entity.User;

import net.sf.json.JSONArray;


@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	
	public void init() {
		userDao = new UserDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/add":
				addUser(request, response);
				break;
			case "/delete":
				deleteUser(request, response);
				break;
			case "/update":
				updateUser(request, response);
				break;
			case "/alllist":
				listUser(request, response);
				break;	
			default:
				hi(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void hi(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		System.out.println("開始運行");
		return;
	}
	//增加
	private void addUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		System.out.println("執行ServletaddUser");
			
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		int phone = Integer.parseInt(request.getParameter("phone"));
		String address = request.getParameter("address");
		String description = request.getParameter("description");
		User newUser = new User(id,name,phone,address,description);
		userDao.addUser(newUser);
		response.sendRedirect("list");
	}
	//改
	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		System.out.println("執行ServletupdateUser");
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		int phone = Integer.parseInt(request.getParameter("phone"));
		String address = request.getParameter("address");
		String description = request.getParameter("description");
		User book = new User(id, name,phone,address,description);
		userDao.updateUser(book);
		response.sendRedirect("list");
	}
	//刪除
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		System.out.println("執行ServletdeleteUser");
		
		String id = request.getParameter("id");
		userDao.deleteUser(id);
		response.sendRedirect("list");
	}	
	
	
	//全部數據
		private void listUser(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException, ServletException {
			System.out.println("執行ServletlistUser");
			
			List<User> listUser = userDao.selectAllUsers();		
			JSONArray jsonArray = JSONArray.fromObject(listUser);
			System.out.println(jsonArray);
			response.getWriter().println(jsonArray);
			
			/*List<User> listUser = userDao.selectAllUsers();				
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();			
			out.print(listUser);
			System.out.println(listUser);
			out.flush();*/	
			
			
			/*List<User> listUser = userDao.selectAllUsers();	
			request.setAttribute("listUser", listUser);
			System.out.println(listUser);	
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);*/
				
			/*User users =  userDao.selectAllUsers();
			PrintWriter out=response.getWriter();
			List<User> userList = new ArrayList<User>();
			userList.add(users);
			JSONArray json = JSONArray.fromObject(users);
			String json_str = json.toString();	
			System.out.println(json_str);
			out.print(json_str);		
			out.flush();		
			out.close();*/	
			
			System.out.println("結束ServletlistUser");

		}
	
}
