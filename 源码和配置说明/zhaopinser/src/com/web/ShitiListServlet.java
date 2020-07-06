package com.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.dao.ShitiDao;
import com.model.PageBean;
import com.model.Shiti;
import com.util.DbUtil;
import com.util.JsonUtil;
import com.util.ResponseUtil;

public class ShitiListServlet extends HttpServlet {
	
	DbUtil dbUtil=new DbUtil();
	ShitiDao shitiDao=new ShitiDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		String shitiName=request.getParameter("shitiName");
		//²âÊÔÃû³ÆËÑË÷
		//System.out.println("shitiName = " + shitiName);
		
		if(shitiName==null){
			shitiName="";
		}
		Shiti shiti=new Shiti();
		shiti.setShitiName(shitiName);
		
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(shitiDao.shitiList(con, pageBean,shiti));
			int total=shitiDao.shitiCount(con,shiti);
			result.put("rows", jsonArray);
			result.put("total", total);
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
