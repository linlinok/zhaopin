package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.PageBean;
import com.model.Zhiwei;
import com.util.StringUtil;

public class ZhiweiDao {

	public ResultSet zhiweiList(Connection con, PageBean pageBean, Zhiwei zhiwei) throws Exception {
		StringBuffer sb = new StringBuffer("select * from t_zhiwei x,t_user g where x.userId = g.userId ");
		if (zhiwei != null) {
			if (StringUtil.isNotEmpty(zhiwei.getZhiweiName())) {
				sb.append(" and x.zhiweiName like '%" + zhiwei.getZhiweiName() + "%'");
			}
			if (zhiwei.getUserId() != null) {
				sb.append(" and x.userId = '" + zhiwei.getUserId() + "'");
			}
		}

		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}

	public int zhiweiCount(Connection con, Zhiwei zhiwei) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_zhiwei x,t_user g where x.userId = g.userId ");
		if (zhiwei != null) {
			if (StringUtil.isNotEmpty(zhiwei.getZhiweiName())) {
				sb.append(" and x.zhiweiName like '%" + zhiwei.getZhiweiName() + "%'");
			}
			if (zhiwei.getUserId() != null) {
				sb.append(" and x.userId = '" + zhiwei.getUserId() + "'");
			}
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}

	/**
	 * delete from tableName where field in (1,3,5)
	 * 
	 * @param con
	 * @param delIds
	 * @return
	 * @throws Exception
	 */
	public int zhiweiDelete(Connection con, String delIds) throws Exception {
		String sql = "delete from t_zhiwei where zhiweiId in(" + delIds + ")";
		System.out.println(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}

	public int zhiweiAdd(Connection con, Zhiwei zhiwei) throws Exception {
		String sql = "insert into t_zhiwei values(null,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, zhiwei.getZhiweiName());
		pstmt.setString(2, zhiwei.getZhiweiMark());
		pstmt.setInt(3, zhiwei.getZhiweiNum());
		pstmt.setInt(4, zhiwei.getUserId());
		return pstmt.executeUpdate();
	}

	public int zhiweiModify(Connection con, Zhiwei zhiwei) throws Exception {
		String sql = "update t_zhiwei set zhiweiName=?,zhiweiMark=?,zhiweiNum=?,userId=? where zhiweiId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, zhiwei.getZhiweiName());
		pstmt.setString(2, zhiwei.getZhiweiMark());
		pstmt.setInt(3, zhiwei.getZhiweiNum());
		pstmt.setInt(4, zhiwei.getUserId());
		pstmt.setInt(5, zhiwei.getZhiweiId());
		return pstmt.executeUpdate();
	}

	// ByZhiweiId
	public Zhiwei findById(Connection con, String zhiweiId) throws Exception {
		Zhiwei resultZhiwei = null;
		String sql = "select * from t_zhiwei where zhiweiId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, zhiweiId);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			resultZhiwei = new Zhiwei();
			resultZhiwei.setZhiweiId(Integer.parseInt(rs.getString("zhiweiId")));
			resultZhiwei.setZhiweiName(rs.getString("zhiweiName"));
			resultZhiwei.setZhiweiMark(rs.getString("zhiweiMark"));
			resultZhiwei.setZhiweiNum(rs.getInt("zhiweiNum"));
			resultZhiwei.setUserId(Integer.parseInt(rs.getString("userId")));
			return resultZhiwei;
		} else {
			return null;
		}
	}

}