package com.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.dao.JuantiDao;
import com.dao.ShitiDao;
import com.model.Juanti;
import com.util.DbUtil;
import com.util.ResponseUtil;
import com.util.StringUtil;

public class JuantiSaveServlet extends HttpServlet {
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
		request.setCharacterEncoding("utf-8");
		JSONObject result=new JSONObject();
		String juantiId = request.getParameter("juantiId");
		String shitiId = request.getParameter("shitiId");
		String shijuanId = request.getParameter("shijuanId");
		
		Connection con = null;
		try {
			con = dbUtil.getCon();
			Juanti juanti = new Juanti();
			if (StringUtil.isNotEmpty(juantiId)) {
				juanti = juantiDao.findById(con, juantiId);
			}
			if (StringUtil.isNotEmpty(shitiId)) {
				juanti.setShitiId(Integer.parseInt(shitiId));
			}
			if (StringUtil.isNotEmpty(shijuanId)) {
				juanti.setShijuanId(Integer.parseInt(shijuanId));
			}
			int saveNums = 0;
			if(StringUtil.isNotEmpty(juantiId)){
				juanti.setJuantiId(Integer.parseInt(juantiId));
				saveNums = juantiDao.juantiModify(con, juanti);
			}else{
				saveNums = juantiDao.juantiAdd(con, juanti);
			}
			if(saveNums>0){
				result.put("success", "true");
			}else{
				result.put("success", "true");
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
