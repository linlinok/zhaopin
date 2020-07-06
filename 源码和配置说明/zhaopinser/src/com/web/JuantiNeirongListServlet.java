package com.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.JuantiDao;
import com.dao.ShitiDao;
import com.model.Juanti;
import com.model.Shiti;
import com.util.DbUtil;
import com.util.StringUtil;

public class JuantiNeirongListServlet extends HttpServlet {
	DbUtil dbUtil = new DbUtil();
	JuantiDao juantiDao = new JuantiDao();
	ShitiDao shitiDao = new ShitiDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String shijuanId = request.getParameter("shijuanId");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			Juanti juanti = new Juanti();
			if(StringUtil.isNotEmpty(shijuanId)){
				juanti.setShijuanId(Integer.parseInt(shijuanId));
			}
			List <Shiti> shitis = new ArrayList<Shiti>();
			Shiti shiti = new Shiti();
			ResultSet rs = juantiDao.juantiList(con, null, juanti);
			int total = juantiDao.juantiCount(con, juanti);
			for(int i = 0;i < total;i++){
				rs.next();
				shiti = shitiDao.findById(con, rs.getString("shitiId"));
				shitis.add(shiti);
			}
			
			HttpSession session=request.getSession();
			session.setAttribute("shitis", shitis);
			session.setAttribute("total", total);
			response.sendRedirect("shijuanneirong.jsp");
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
