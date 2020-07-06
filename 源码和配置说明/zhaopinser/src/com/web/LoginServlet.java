package com.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.AdminDao;
import com.dao.UserDao;
import com.model.Admin;
import com.model.User;
import com.util.DbUtil;
import com.util.StringUtil;

public class LoginServlet extends HttpServlet{

	DbUtil dbUtil=new DbUtil();
	AdminDao adminDao=new AdminDao();
	UserDao userDao=new UserDao();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String adminName=request.getParameter("userName");
		String adminPassword=request.getParameter("password");
		request.setAttribute("userName", adminName);
		if(StringUtil.isEmpty(adminName)||StringUtil.isEmpty(adminPassword)){
			request.setAttribute("error", "用户名或密码为空！");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		if(adminName.equals("admin")){
			Admin admin=new Admin(adminName,adminPassword);
			Connection con=null;
			try {
				con=dbUtil.getCon();
				Admin currentAdmin=adminDao.login(con, admin);
				if(currentAdmin==null){
					request.setAttribute("error", "用户名或密码错误！");
					// 服务器跳转
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}else{
					// 获取Session
					HttpSession session=request.getSession();
					session.setAttribute("admin", currentAdmin);
					System.out.println(currentAdmin.getAdminName());
					// 客户端跳转
					response.sendRedirect("adminMain.jsp");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else { //普通用户登录
			User user = new User();
			user.setUserName(adminName);
			user.setUserPassword(adminPassword);
			Connection con=null;
			try {
				con=dbUtil.getCon();
				User loginUser=userDao.login(con, user);
				if(loginUser==null){
					request.setAttribute("error", "用户名或密码错误！");
					// 服务器跳转
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}else{
					// 获取Session
					HttpSession session=request.getSession();
					session.setAttribute("user", loginUser);
					int roleId = loginUser.getRoleId();
					if(roleId==1){ //招聘人员跳转页面
						response.sendRedirect("zhaopinMain.jsp");
					}else{ //求职人员跳转页面
						response.sendRedirect("qiuzhiMain.jsp");
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
