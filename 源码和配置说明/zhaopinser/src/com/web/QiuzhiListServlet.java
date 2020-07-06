package com.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.dao.QiuzhiDao;
import com.dao.UserDao;
import com.model.PageBean;
import com.model.Qiuzhi;
import com.util.DbUtil;
import com.util.JsonUtil;
import com.util.ResponseUtil;
import com.util.StringUtil;

public class QiuzhiListServlet extends HttpServlet {
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
		String zhiweiId = request.getParameter("zhiweiId");
		String userId = request.getParameter("userId");
		String qiuzhiType = request.getParameter("qiuzhiType");
		Connection con = null;
		try {
			JSONObject result = new JSONObject();
			con = dbUtil.getCon();
			Qiuzhi qiuzhi = new Qiuzhi();
			if(StringUtil.isNotEmpty(zhiweiId)){
				qiuzhi.setZhiweiId(Integer.parseInt(zhiweiId));
			}
			if(StringUtil.isNotEmpty(userId)){
				qiuzhi.setUserId(Integer.parseInt(userId));
			}
			if(StringUtil.isNotEmpty(qiuzhiType)){
				qiuzhi.setQiuzhiType(Integer.parseInt(qiuzhiType));
			}
			String page = request.getParameter("page");
			String rows = request.getParameter("rows");
			PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));

			JSONArray jsonArray = JsonUtil
					.formatRsToJsonArray(qiuzhiDao.qiuzhiList(con, pageBean, qiuzhi));
			int total = qiuzhiDao.qiuzhiCount(con, qiuzhi);
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
