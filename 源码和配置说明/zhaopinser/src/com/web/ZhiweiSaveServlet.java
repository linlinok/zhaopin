package com.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.dao.ZhiweiDao;
import com.model.Zhiwei;
import com.util.DbUtil;
import com.util.ResponseUtil;
import com.util.StringUtil;

public class ZhiweiSaveServlet extends HttpServlet {
	
	DbUtil dbUtil=new DbUtil();
	ZhiweiDao zhiweiDao=new ZhiweiDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String zhiweiName=request.getParameter("zhiweiName"); //√˚≥∆
		String zhiweiMark=request.getParameter("zhiweiMark"); //±∏◊¢
		String userId=request.getParameter("userId"); //’–∆∏»Àid
		String zhiweiNum=request.getParameter("zhiweiNum");
		String zhiweiId=request.getParameter("zhiweiId");
		Zhiwei zhiwei=new Zhiwei();
		Connection con=null;
		try{
			con=dbUtil.getCon();
			
			if(StringUtil.isNotEmpty(zhiweiId)){
				zhiwei = zhiweiDao.findById(con, zhiweiId);
			}
			if(StringUtil.isNotEmpty(zhiweiName)){
				zhiwei.setZhiweiName(zhiweiName);
			}
			if(StringUtil.isNotEmpty(zhiweiMark)){
				zhiwei.setZhiweiMark(zhiweiMark);
			}
			if(StringUtil.isNotEmpty(userId)){
				zhiwei.setUserId(Integer.parseInt(userId));
			}
			if(StringUtil.isNotEmpty(zhiweiId)){
				zhiwei.setZhiweiId(Integer.parseInt(zhiweiId));
			}
			if(StringUtil.isNotEmpty(zhiweiNum)){
				zhiwei.setZhiweiNum(Integer.parseInt(zhiweiNum));
			}
			
			int saveNums=0;
			JSONObject result=new JSONObject();
			if(StringUtil.isNotEmpty(zhiweiId)){
				saveNums=zhiweiDao.zhiweiModify(con, zhiwei);
			}else{
				saveNums=zhiweiDao.zhiweiAdd(con, zhiwei);
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