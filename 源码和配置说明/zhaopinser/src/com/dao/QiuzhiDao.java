package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.PageBean;
import com.model.Qiuzhi;

public class QiuzhiDao {

	public ResultSet qiuzhiList(Connection con, PageBean pageBean, Qiuzhi qiuzhi) throws Exception {
		StringBuffer sb = new StringBuffer(
				"select * from t_qiuzhi q,t_user u,t_zhiwei z where q.userId = u.userId and q.zhiweiId = z.zhiweiId ");
		if (qiuzhi != null) {
			if (qiuzhi.getUserId() != null) {
				sb.append(" and q.userId = '" + qiuzhi.getUserId() + "'");
			}
			if (qiuzhi.getZhiweiId() != null) {
				sb.append(" and q.zhiweiId = '" + qiuzhi.getZhiweiId() + "'");
			}
			if (qiuzhi.getQiuzhiType() != null) {
				sb.append(" and q.qiuzhiType = '" + qiuzhi.getQiuzhiType() + "'");
			}
		}
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
		}
		System.out.println(sb);
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}

	public int qiuzhiCount(Connection con, Qiuzhi qiuzhi) throws Exception {
		StringBuffer sb = new StringBuffer(
				"select count(*) as total from t_qiuzhi q,t_user u,t_zhiwei z where q.userId = u.userId and q.zhiweiId = z.zhiweiId ");
		if (qiuzhi != null) {
			if (qiuzhi.getUserId() != null) {
				sb.append(" and q.userId = '" + qiuzhi.getUserId() + "'");
			}
			if (qiuzhi.getZhiweiId() != null) {
				sb.append(" and q.zhiweiId = '" + qiuzhi.getZhiweiId() + "'");
			}
			if (qiuzhi.getQiuzhiType() != null) {
				sb.append(" and q.qiuzhiType = '" + qiuzhi.getQiuzhiType() + "'");
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
	public int qiuzhiDelete(Connection con, String delIds) throws Exception {
		String sql = "delete from t_qiuzhi where qiuzhiId in(" + delIds + ")";
		System.out.println(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}

	public int qiuzhiAdd(Connection con, Qiuzhi qiuzhi) throws Exception {
		String sql = "insert into t_qiuzhi values(null,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, qiuzhi.getZhiweiId());
		pstmt.setInt(2, qiuzhi.getUserId());
		pstmt.setString(3, qiuzhi.getQiuzhiMark());
		pstmt.setInt(4, qiuzhi.getQiuzhiType());
		return pstmt.executeUpdate();
	}

	public int qiuzhiModify(Connection con, Qiuzhi qiuzhi) throws Exception {
		String sql = "update t_qiuzhi set zhiweiId=?,userId=?,qiuzhiMark=?,qiuzhiType=? where qiuzhiId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, qiuzhi.getZhiweiId());
		pstmt.setInt(2, qiuzhi.getUserId());
		pstmt.setString(3, qiuzhi.getQiuzhiMark());
		pstmt.setInt(4, qiuzhi.getQiuzhiType());
		pstmt.setInt(5, qiuzhi.getQiuzhiId());
		return pstmt.executeUpdate();
	}

	// ByQiuzhiId
	public Qiuzhi findById(Connection con, String qiuzhiId) throws Exception {
		Qiuzhi resultQiuzhi = null;
		String sql = "select * from t_qiuzhi where qiuzhiId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, qiuzhiId);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			resultQiuzhi = new Qiuzhi();
			resultQiuzhi.setQiuzhiId(rs.getInt("qiuzhiId"));
			resultQiuzhi.setZhiweiId(rs.getInt("zhiweiId"));
			resultQiuzhi.setUserId(rs.getInt("userId"));
			resultQiuzhi.setQiuzhiMark(rs.getString("qiuzhiMark"));
			resultQiuzhi.setQiuzhiType(rs.getInt("qiuzhiType"));
			return resultQiuzhi;
		} else {
			return null;
		}
	}

}