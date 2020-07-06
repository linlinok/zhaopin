package com.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DatiDao;
import com.dao.UserDao;
import com.model.Dati;
import com.model.Shiti;
import com.util.DbUtil;

public class DatiSaveServlet extends HttpServlet {
	DbUtil dbUtil = new DbUtil();
	DatiDao datiDao = new DatiDao();
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
		HttpSession session=request.getSession();
		List shitis = (List) session.getAttribute("shitis");
		
		Connection con = null;
		try {
			con = dbUtil.getCon();
			Dati dati = new Dati();
			for(int i=0;i<shitis.size();i++){
				Shiti shiti =(Shiti)shitis.get(i);
				dati.setUserId(Integer.parseInt(userId));
				String userDaan = request.getParameter("userDaan[" + i + "]");
				dati.setUserDaan(userDaan);
				dati.setShitiId(shiti.getShitiId());
				datiDao.datiAdd(con, dati);
			}
			response.sendRedirect("datijieshu.html");
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
