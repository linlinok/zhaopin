package com.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.dao.ShitiDao;
import com.model.Shiti;
import com.util.DbUtil;
import com.util.ResponseUtil;
import com.util.StringUtil;

public class ShitiSaveServlet extends HttpServlet {
	
	DbUtil dbUtil=new DbUtil();
	ShitiDao shitiDao=new ShitiDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String shitiName=request.getParameter("shitiName");
		String shitiD1=request.getParameter("shitiD1");
		String shitiD2=request.getParameter("shitiD2");
		String shitiD3=request.getParameter("shitiD3");
		String shitiD4=request.getParameter("shitiD4");
		String shitiDaan=request.getParameter("shitiDaan");
		String shitiId=request.getParameter("shitiId");
		Shiti shiti=new Shiti();
		Connection con=null;
		try{
			con=dbUtil.getCon();
			if(StringUtil.isNotEmpty(shitiId)){
				shiti = shitiDao.findById(con, shitiId);
			}
			if(StringUtil.isNotEmpty(shitiName)){
				shiti.setShitiName(shitiName);
			}
			if(StringUtil.isNotEmpty(shitiD1)){
				shiti.setShitiD1(shitiD1);
			}
			if(StringUtil.isNotEmpty(shitiD2)){
				shiti.setShitiD2(shitiD2);
			}
			if(StringUtil.isNotEmpty(shitiD3)){
				shiti.setShitiD3(shitiD3);
			}
			if(StringUtil.isNotEmpty(shitiD4)){
				shiti.setShitiD4(shitiD4);
			}
			if(StringUtil.isNotEmpty(shitiDaan)){
				shiti.setShitiDaan(shitiDaan);
			}
			int saveNums=0;
			JSONObject result=new JSONObject();
			if(StringUtil.isNotEmpty(shitiId)){
				saveNums=shitiDao.shitiModify(con, shiti);
			}else{
				saveNums=shitiDao.shitiAdd(con, shiti);
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