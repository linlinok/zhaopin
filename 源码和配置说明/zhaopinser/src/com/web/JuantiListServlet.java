package com.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.dao.JuantiDao;
import com.dao.ShitiDao;
import com.model.Juanti;
import com.model.PageBean;
import com.util.DbUtil;
import com.util.JsonUtil;
import com.util.ResponseUtil;
import com.util.StringUtil;

public class JuantiListServlet extends HttpServlet {
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
		String shitiId = request.getParameter("shitiId");
		Connection con = null;
		try {
			JSONObject result = new JSONObject();
			con = dbUtil.getCon();
			Juanti juanti = new Juanti();
			if(StringUtil.isNotEmpty(shijuanId)){
				juanti.setShijuanId(Integer.parseInt(shijuanId));
			}
			if(StringUtil.isNotEmpty(shitiId)){
				juanti.setShitiId(Integer.parseInt(shitiId));
			}
			String page = request.getParameter("page");
			String rows = request.getParameter("rows");
			PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));

			JSONArray jsonArray = JsonUtil
					.formatRsToJsonArray(juantiDao.juantiList(con, pageBean, juanti));
			int total = juantiDao.juantiCount(con, juanti);
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
