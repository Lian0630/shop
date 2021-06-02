package com.servlet;

import java.io.IOException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.User;
import com.service.UserService;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
      
	UserService service = new UserService();
    	
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("執行service");
		req.setCharacterEncoding("utf-8");
		String method = req.getParameter("method");
		if ("add".equals(method)) {
			add(req, resp);
		} else if ("delete".equals(method)) {
			delete(req, resp);
		} else if ("update".equals(method)) {
			update(req, resp);
		} else if ("search".equals(method)) {
			search(req, resp);
		} else if ("list".equals(method)) {
			list(req, resp);
		}
	}
	
	/**
	 * 新增
	 */
	private void add(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("執行add");
		
		req.setCharacterEncoding("utf-8");						
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		int phone = Integer.parseInt(req.getParameter("phone"));
		String address = req.getParameter("address");
		String description = req.getParameter("description");
		User user = new User(id,name,phone,address,description);
		
		//新增後訊息顯示
		if(service.add(user)) {
			req.setAttribute("message", "新增成功");
			req.getRequestDispatcher("index.jsp").forward(req,resp);			
		} else {
			req.setAttribute("message", "使用者重複");
			req.getRequestDispatcher("index.jsp").forward(req,resp);
		}
		
	}
	
	/**
	 * 刪除
	 */
	private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		req.setCharacterEncoding("utf-8");
		String id =req.getParameter("id");
		service.delete(id);
		req.setAttribute("message", "刪除成功！");
		req.getRequestDispatcher("index.jsp").forward(req,resp);
	}
	
	/**
	 * 修改
	 */
	private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		int phone = Integer.parseInt(req.getParameter("phone"));
		String address = req.getParameter("address");
		String description = req.getParameter("description");
		User user = new User(id, name, phone, address,description);
		
		service.update(user);
		req.setAttribute("message", "修改成功");
		req.getRequestDispatcher("UserServlet?method=list").forward(req,resp);
	}
	
	/**
	 * 查詢
	 */
	private void search(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		int phone = Integer.parseInt(req.getParameter("phone"));
		String address = req.getParameter("address");
		String description = req.getParameter("description");
		List<User> users = service.search(id,name,phone,address,description);
		req.setAttribute("users", users);
		req.getRequestDispatcher("index.jsp").forward(req,resp);
	}
	
	/**
	 * 全部
	 */
	private void list(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{

		System.out.println("執行list");
		req.setCharacterEncoding("utf-8");
		List<User> users = service.list();
		req.setAttribute("users",users);
		req.getRequestDispatcher("index.jsp").forward(req,resp);
	}
	
	
	
	

}
