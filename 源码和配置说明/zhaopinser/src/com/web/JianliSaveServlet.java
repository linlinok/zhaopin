package com.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.dao.JianliDao;
import com.model.Jianli;
import com.util.DbUtil;
import com.util.ResponseUtil;
import com.util.StringUtil;

public class JianliSaveServlet extends HttpServlet {
	
	DbUtil dbUtil=new DbUtil();
	JianliDao jianliDao=new JianliDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String xuexiao=request.getParameter("xuexiao");
		String zhuanye=request.getParameter("zhuanye");
		String jineng=request.getParameter("jineng");
		String xiangxi=request.getParameter("xiangxi");
		String userId=request.getParameter("userId");
		String jianliId=request.getParameter("jianliId");
		Jianli jianli=new Jianli();
		Connection con=null;
		try{
			con=dbUtil.getCon();
			
			if(StringUtil.isNotEmpty(jianliId)){
				jianli = jianliDao.findById(con, jianliId);
			}
			if(StringUtil.isNotEmpty(xuexiao)){
				jianli.setXuexiao(xuexiao);
			}
			if(StringUtil.isNotEmpty(zhuanye)){
				jianli.setZhuanye(zhuanye);
			}
			if(StringUtil.isNotEmpty(jineng)){
				jianli.setJineng(jineng);
			}
			if(StringUtil.isNotEmpty(xiangxi)){
				jianli.setXiangxi(xiangxi);
			}
			if(StringUtil.isNotEmpty(userId)){
				jianli.setUserId(Integer.parseInt(userId));
			}
			
			int saveNums=0;
			JSONObject result=new JSONObject();
			if(StringUtil.isNotEmpty(jianliId)){
				saveNums=jianliDao.jianliModify(con, jianli);
			}else{
				saveNums=jianliDao.jianliAdd(con, jianli);
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