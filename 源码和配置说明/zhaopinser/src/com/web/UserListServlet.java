package com.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.dao.UserDao;
import com.model.PageBean;
import com.model.User;
import com.util.DbUtil;
import com.util.JsonUtil;
import com.util.ResponseUtil;
import com.util.StringUtil;

public class UserListServlet extends HttpServlet {
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
		String userName = request.getParameter("userName");
		String userId = request.getParameter("userId");
		String roleId = request.getParameter("roleId");

		User user = new User();
		if (StringUtil.isNotEmpty(userName)) {
			user.setUserName(userName);
		}
		if (StringUtil.isNotEmpty(userId)) {
			user.setUserId(Integer.parseInt(userId));
		}
		if (StringUtil.isNotEmpty(roleId)) {
			user.setRoleId(Integer.parseInt(roleId));
		}

		String page = request.getParameter("page");
		String rows = request.getParameter("rows");

		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Connection con = null;
		try {
			con = dbUtil.getCon();
			JSONObject result = new JSONObject();
			JSONArray jsonArray = JsonUtil.formatRsToJsonArray(userDao.userList(con, pageBean, user));
			int total = userDao.userCount(con, user);
			result.put("rows", jsonArray);
			result.put("total", total);
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
