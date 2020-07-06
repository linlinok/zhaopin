package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.Juanti;
import com.model.PageBean;

public class JuantiDao {

	public ResultSet juantiList(Connection con, PageBean pageBean, Juanti juanti) throws Exception {
		StringBuffer sb = new StringBuffer(
				"select * from t_juanti j,t_shiti s,t_shijuan h where j.shitiId = s.shitiId and j.shijuanId = h.shijuanId ");
		if (juanti != null) {
			if (juanti.getShitiId() != null) {
				sb.append(" and j.shitiId = '" + juanti.getShitiId() + "'");
			}
			if (juanti.getShijuanId() != null) {
				sb.append(" and j.shijuanId = '" + juanti.getShijuanId() + "'");
			}
		}
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
		}
		System.out.println(sb);
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}

	public int juantiCount(Connection con, Juanti juanti) throws Exception {
		StringBuffer sb = new StringBuffer(
				"select count(*) as total from t_juanti j,t_shiti s,t_shijuan h where j.shitiId = s.shitiId and j.shijuanId = h.shijuanId ");
		if (juanti != null) {
			if (juanti.getShitiId() != null) {
				sb.append(" and j.shitiId = '" + juanti.getShitiId() + "'");
			}
			if (juanti.getShijuanId() != null) {
				sb.append(" and j.shijuanId = '" + juanti.getShijuanId() + "'");
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
	public int juantiDelete(Connection con, String delIds) throws Exception {
		String sql = "delete from t_juanti where juantiId in(" + delIds + ")";
		System.out.println(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}

	public int juantiAdd(Connection con, Juanti juanti) throws Exception {
		String sql = "insert into t_juanti values(null,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, juanti.getShijuanId());
		pstmt.setInt(2, juanti.getShitiId());
		return pstmt.executeUpdate();
	}

	public int juantiModify(Connection con, Juanti juanti) throws Exception {
		String sql = "update t_juanti set shijuanId=?,shitiId=? where juantiId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, juanti.getShijuanId());
		pstmt.setInt(2, juanti.getShitiId());
		pstmt.setInt(3, juanti.getJuantiId());
		return pstmt.executeUpdate();
	}

	// ByJuantiId
	public Juanti findById(Connection con, String juantiId) throws Exception {
		Juanti resultJuanti = null;
		String sql = "select * from t_juanti where juantiId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, juantiId);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			resultJuanti = new Juanti();
			resultJuanti.setJuantiId(Integer.parseInt(rs.getString("juantiId")));
			resultJuanti.setShijuanId(Integer.parseInt(rs.getString("shijuanId")));
			resultJuanti.setShitiId(Integer.parseInt(rs.getString("shitiId")));
			return resultJuanti;
		} else {
			return null;
		}
	}

}