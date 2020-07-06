package com.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.dao.ShijuanDao;
import com.model.PageBean;
import com.model.Shijuan;
import com.util.DbUtil;
import com.util.JsonUtil;
import com.util.ResponseUtil;
import com.util.StringUtil;

public class ShijuanListServlet extends HttpServlet {
	
	DbUtil dbUtil=new DbUtil();
	ShijuanDao shijuanDao=new ShijuanDao();
	
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
		String shijuanName=request.getParameter("shijuanName");
		String zhiweiId=request.getParameter("zhiweiId");
		Shijuan shijuan=new Shijuan();
		if(StringUtil.isNotEmpty(shijuanName)){
			shijuan.setShijuanName(shijuanName);
		}
		if(StringUtil.isNotEmpty(zhiweiId)){
			shijuan.setZhiweiId(Integer.parseInt(zhiweiId));
		}
		
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(shijuanDao.shijuanList(con, pageBean,shijuan));
			int total=shijuanDao.shijuanCount(con,shijuan);
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
