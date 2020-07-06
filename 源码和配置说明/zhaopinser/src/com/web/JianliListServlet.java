package com.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.JianliDao;
import com.model.Jianli;
import com.util.DbUtil;
import com.util.StringUtil;

public class JianliListServlet extends HttpServlet {
	
	DbUtil dbUtil=new DbUtil();
	JianliDao jianliDao=new JianliDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId=request.getParameter("userId");
		Jianli jianli=new Jianli();
		if(StringUtil.isNotEmpty(userId)){
			jianli.setUserId(Integer.parseInt(userId));
		}
		
		Connection con=null;
		try{
			con=dbUtil.getCon();
			jianli = jianliDao.findById(con, userId);
			HttpSession session=request.getSession();
			session.setAttribute("jianli", jianli);
			// ¿Í»§¶ËÌø×ª
			response.sendRedirect("jianli.jsp");
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
