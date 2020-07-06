package com.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.dao.UserDao;
import com.model.User;
import com.util.DbUtil;
import com.util.ResponseUtil;
import com.util.StringUtil;

public class UserSaveServlet extends HttpServlet {
	DbUtil dbUtil = new DbUtil();
	UserDao userDao = new UserDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String userXingming = request.getParameter("userXingming");
		String userAge = request.getParameter("userAge");
		String userSex = request.getParameter("userSex");
		String roleId = request.getParameter("roleId");
		User user = new User();

		Connection con = null;
		try {

			con = dbUtil.getCon();

			if (StringUtil.isNotEmpty(userId)) {
				user = userDao.findByUserId(con, userId);
			}

			if (StringUtil.isNotEmpty(userName)) {
				user.setUserName(userName);
			}
			if (StringUtil.isNotEmpty(userPassword)) {
				user.setUserPassword(userPassword);
			}
			if (StringUtil.isNotEmpty(userXingming)) {
				user.setUserXingming(userXingming);
			}
			if (StringUtil.isNotEmpty(userAge)) {
				user.setUserAge(userAge);
			}
			if (StringUtil.isNotEmpty(userSex)) {
				user.setUserSex(userSex);
			}
			if (StringUtil.isNotEmpty(roleId)) {
				user.setRoleId(Integer.parseInt(roleId));
			}
			JSONObject result = new JSONObject();
			int saveNums = 0;
			if (StringUtil.isNotEmpty(userId)) {
				saveNums = userDao.userModify(con, user);
			} else {
				saveNums = userDao.userAdd(con, user);
				request.getRequestDispatcher("user.jsp").forward(request, response);
			}

			if (saveNums > 0) {
				result.put("success", "true");
			} else {
				result.put("errorMsg", "±£¥Ê ß∞‹");
			}
			ResponseUtil.write(response, result);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
