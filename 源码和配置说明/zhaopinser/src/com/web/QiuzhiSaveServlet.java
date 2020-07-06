package com.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.dao.QiuzhiDao;
import com.dao.UserDao;
import com.model.Qiuzhi;
import com.util.DbUtil;
import com.util.ResponseUtil;
import com.util.StringUtil;

public class QiuzhiSaveServlet extends HttpServlet {
	DbUtil dbUtil = new DbUtil();
	QiuzhiDao qiuzhiDao = new QiuzhiDao();
	UserDao userDao = new UserDao();

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
		String qiuzhiId = request.getParameter("qiuzhiId");
		String userId = request.getParameter("userId");
		String zhiweiId = request.getParameter("zhiweiId");
		String qiuzhiMark = request.getParameter("qiuzhiMark");
		String qiuzhiType = request.getParameter("qiuzhiType");
		
		Connection con = null;
		try {
			con = dbUtil.getCon();
			Qiuzhi qiuzhi = new Qiuzhi();
			if (StringUtil.isNotEmpty(qiuzhiId)) {
				qiuzhi = qiuzhiDao.findById(con, qiuzhiId);
			}
			if (StringUtil.isNotEmpty(userId)) {
				qiuzhi.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(zhiweiId)) {
				qiuzhi.setZhiweiId(Integer.parseInt(zhiweiId));
			}
			if (StringUtil.isNotEmpty(qiuzhiMark)) {
				qiuzhi.setQiuzhiMark(qiuzhiMark);
			}
			if (StringUtil.isNotEmpty(qiuzhiType)) {
				qiuzhi.setQiuzhiType(Integer.parseInt(qiuzhiType));
			}
			int saveNums = 0;
			if(StringUtil.isNotEmpty(qiuzhiId)){
				qiuzhi.setQiuzhiId(Integer.parseInt(qiuzhiId));
				saveNums = qiuzhiDao.qiuzhiModify(con, qiuzhi);
			}else{
				qiuzhi.setQiuzhiType(0);
				saveNums = qiuzhiDao.qiuzhiAdd(con, qiuzhi);
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
