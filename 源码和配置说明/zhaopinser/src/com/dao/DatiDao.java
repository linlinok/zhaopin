package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.Dati;
import com.model.PageBean;

public class DatiDao {

	public ResultSet datiList(Connection con, PageBean pageBean, Dati dati) throws Exception {
		StringBuffer sb = new StringBuffer(
				"select * from t_dati q,t_user u,t_shiti z where q.userId = u.userId and q.shitiId = z.shitiId ");
		if (dati != null) {
			if (dati.getUserId() != null) {
				sb.append(" and q.userId = '" + dati.getUserId() + "'");
			}
			if (dati.getShitiId() != null) {
				sb.append(" and q.shitiId = '" + dati.getShitiId() + "'");
			}
		}
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
		}
		System.out.println(sb);
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}

	public int datiCount(Connection con, Dati dati) throws Exception {
		StringBuffer sb = new StringBuffer(
				"select count(*) as total from t_dati q,t_user u,t_shiti z where q.userId = u.userId and q.shitiId = z.shitiId ");
		if (dati != null) {
			if (dati.getUserId() != null) {
				sb.append(" and q.userId = '" + dati.getUserId() + "'");
			}
			if (dati.getShitiId() != null) {
				sb.append(" and q.shitiId = '" + dati.getShitiId() + "'");
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
	public int datiDelete(Connection con, String delIds) throws Exception {
		String sql = "delete from t_dati where datiId in(" + delIds + ")";
		System.out.println(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}

	public int datiAdd(Connection con, Dati dati) throws Exception {
		String sql = "insert into t_dati values(null,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, dati.getShitiId());
		pstmt.setInt(2, dati.getUserId());
		pstmt.setString(3, dati.getUserDaan());
		return pstmt.executeUpdate();
	}

	public int datiModify(Connection con, Dati dati) throws Exception {
		String sql = "update t_dati set shitiId=?,userId=?,userDaan=? where datiId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, dati.getShitiId());
		pstmt.setInt(2, dati.getUserId());
		pstmt.setString(3, dati.getUserDaan());
		pstmt.setInt(4, dati.getDatiId());
		return pstmt.executeUpdate();
	}

	// ByDatiId
	public Dati findById(Connection con, String datiId) throws Exception {
		Dati resultDati = null;
		String sql = "select * from t_dati where datiId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, datiId);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			resultDati = new Dati();
			resultDati.setDatiId(rs.getInt("datiId"));
			resultDati.setShitiId(rs.getInt("shitiId"));
			resultDati.setUserId(rs.getInt("userId"));
			resultDati.setUserDaan(rs.getString("userDaan"));
			return resultDati;
		} else {
			return null;
		}
	}

}