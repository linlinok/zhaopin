package com.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.dao.ZhiweiDao;
import com.model.PageBean;
import com.model.Zhiwei;
import com.util.DbUtil;
import com.util.JsonUtil;
import com.util.ResponseUtil;
import com.util.StringUtil;

public class ZhiweiListServlet extends HttpServlet {
	
	DbUtil dbUtil=new DbUtil();
	ZhiweiDao zhiweiDao=new ZhiweiDao();
	
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
		String zhiweiName=request.getParameter("zhiweiName");
		String userId=request.getParameter("userId");
		Zhiwei zhiwei=new Zhiwei();
		if(StringUtil.isNotEmpty(zhiweiName)){
			zhiwei.setZhiweiName(zhiweiName);
		}
		if(StringUtil.isNotEmpty(userId)){
			zhiwei.setUserId(Integer.parseInt(userId));
		}
		
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(zhiweiDao.zhiweiList(con, pageBean,zhiwei));
			int total=zhiweiDao.zhiweiCount(con,zhiwei);
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
