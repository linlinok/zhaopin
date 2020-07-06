package com.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.dao.RoleDao;
import com.model.Role;
import com.util.DbUtil;
import com.util.ResponseUtil;
import com.util.StringUtil;

public class RoleSaveServlet extends HttpServlet {
	
	DbUtil dbUtil=new DbUtil();
	RoleDao roleDao=new RoleDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String roleName=request.getParameter("roleName");
		String roleMark=request.getParameter("roleMark");
		String roleId=request.getParameter("roleId");
		Role role=new Role(roleName,roleMark);
		if(StringUtil.isNotEmpty(roleId)){
			role.setRoleId(Integer.parseInt(roleId));
		}
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int saveNums=0;
			JSONObject result=new JSONObject();
			if(StringUtil.isNotEmpty(roleId)){
				saveNums=roleDao.roleModify(con, role);
			}else{
				saveNums=roleDao.roleAdd(con, role);
			}
			if(saveNums>0){
				result.put("success", "true");
			}else{
				result.put("success", "true");
				result.put("errorMsg", "±£¥Ê ß∞‹");
			}
			ResponseUtil.write(response, result);
		}catch(Exception e){
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