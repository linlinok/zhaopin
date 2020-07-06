package com.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.dao.DatiDao;
import com.dao.UserDao;
import com.model.Dati;
import com.model.PageBean;
import com.util.DbUtil;
import com.util.JsonUtil;
import com.util.ResponseUtil;
import com.util.StringUtil;

public class DatiListServlet extends HttpServlet {
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
		String shitiId = request.getParameter("shitiId");
		String userId = request.getParameter("userId");
		Connection con = null;
		try {
			JSONObject result = new JSONObject();
			con = dbUtil.getCon();
			Dati dati = new Dati();
			if(StringUtil.isNotEmpty(shitiId)){
				dati.setShitiId(Integer.parseInt(shitiId));
			}
			if(StringUtil.isNotEmpty(userId)){
				dati.setUserId(Integer.parseInt(userId));
			}
			String page = request.getParameter("page");
			String rows = request.getParameter("rows");
			PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));

			JSONArray jsonArray = JsonUtil
					.formatRsToJsonArray(datiDao.datiList(con, pageBean, dati));
			int total = datiDao.datiCount(con, dati);
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
