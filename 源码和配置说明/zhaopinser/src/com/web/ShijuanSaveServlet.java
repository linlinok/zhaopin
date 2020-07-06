package com.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.dao.ShijuanDao;
import com.model.Shijuan;
import com.util.DbUtil;
import com.util.ResponseUtil;
import com.util.StringUtil;

public class ShijuanSaveServlet extends HttpServlet {
	
	DbUtil dbUtil=new DbUtil();
	ShijuanDao shijuanDao=new ShijuanDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String shijuanName=request.getParameter("shijuanName");
		String shijuanMark=request.getParameter("shijuanMark");
		String zhiweiId=request.getParameter("zhiweiId");
		String shijuanId=request.getParameter("shijuanId");
		Shijuan shijuan=new Shijuan();
		Connection con=null;
		try{
			con=dbUtil.getCon();
			
			if(StringUtil.isNotEmpty(shijuanId)){
				shijuan = shijuanDao.findById(con, shijuanId);
			}
			if(StringUtil.isNotEmpty(shijuanName)){
				shijuan.setShijuanName(shijuanName);
			}
			if(StringUtil.isNotEmpty(shijuanMark)){
				shijuan.setShijuanMark(shijuanMark);
			}
			if(StringUtil.isNotEmpty(zhiweiId)){
				shijuan.setZhiweiId(Integer.parseInt(zhiweiId));
			}
			if(StringUtil.isNotEmpty(shijuanId)){
				shijuan.setShijuanId(Integer.parseInt(shijuanId));
			}
			
			int saveNums=0;
			JSONObject result=new JSONObject();
			if(StringUtil.isNotEmpty(shijuanId)){
				saveNums=shijuanDao.shijuanModify(con, shijuan);
			}else{
				saveNums=shijuanDao.shijuanAdd(con, shijuan);
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