package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.Jianli;
import com.model.PageBean;

public class JianliDao {

	public ResultSet jianliList(Connection con, PageBean pageBean, Jianli jianli) throws Exception {
		StringBuffer sb = new StringBuffer("select * from t_jianli x,t_user g where x.userId = g.userId ");
		if (jianli != null) {
			if (jianli.getUserId() != null) {
				sb.append(" and x.userId = '" + jianli.getUserId() + "'");
			}
		}

		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}

	public int jianliCount(Connection con, Jianli jianli) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_jianli x,t_user g where x.userId = g.userId ");
		if (jianli != null) {
			if (jianli.getUserId() != null) {
				sb.append(" and x.userId = '" + jianli.getUserId() + "'");
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
	public int jianliDelete(Connection con, String delIds) throws Exception {
		String sql = "delete from t_jianli where jianliId in(" + delIds + ")";
		System.out.println(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}

	public int jianliAdd(Connection con, Jianli jianli) throws Exception {
		String sql = "insert into t_jianli values(null,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, jianli.getXuexiao());
		pstmt.setString(2, jianli.getZhuanye());
		pstmt.setString(3, jianli.getJineng());
		pstmt.setString(4, jianli.getXiangxi());
		pstmt.setInt(5, jianli.getUserId());
		return pstmt.executeUpdate();
	}

	public int jianliModify(Connection con, Jianli jianli) throws Exception {
		String sql = "update t_jianli set xuexiao=?,zhuanye=?,jineng=?,xiangxi=?,userId=? where jianliId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, jianli.getXuexiao());
		pstmt.setString(2, jianli.getZhuanye());
		pstmt.setString(3, jianli.getJineng());
		pstmt.setString(4, jianli.getXiangxi());
		pstmt.setInt(5, jianli.getUserId());
		pstmt.setInt(6, jianli.getJianliId());
		return pstmt.executeUpdate();
	}

	// ByuserId
	public Jianli findById(Connection con, String userId) throws Exception {
		Jianli resultJianli = null;
		String sql = "select * from t_jianli where userId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userId);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			resultJianli = new Jianli();
			resultJianli.setJianliId(rs.getInt("jianliId"));
			resultJianli.setXuexiao(rs.getString("xuexiao"));
			resultJianli.setZhuanye(rs.getString("zhuanye"));
			resultJianli.setJineng(rs.getString("jineng"));
			resultJianli.setXiangxi(rs.getString("xiangxi"));
			resultJianli.setUserId(rs.getInt("userId"));
			return resultJianli;
		} else {
			return null;
		}
	}

}