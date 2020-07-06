package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.PageBean;
import com.model.Shiti;
import com.util.StringUtil;

public class ShitiDao {

	public ResultSet shitiList(Connection con, PageBean pageBean, Shiti shiti) throws Exception {
		StringBuffer sb = new StringBuffer("select * from t_shiti");
		if (shiti != null && StringUtil.isNotEmpty(shiti.getShitiName())) {
			sb.append(" and shitiName like '%" + shiti.getShitiName() + "%'");
		}
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}

	public int shitiCount(Connection con, Shiti shiti) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_shiti");
		if (StringUtil.isNotEmpty(shiti.getShitiName())) {
			sb.append(" and shitiName like '%" + shiti.getShitiName() + "%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
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
	public int shitiDelete(Connection con, String delIds) throws Exception {
		String sql = "delete from t_shiti where shitiId in(" + delIds + ")";
		System.out.println(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}

	public int shitiAdd(Connection con, Shiti shiti) throws Exception {
		String sql = "insert into t_shiti values(null,?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, shiti.getShitiName());
		pstmt.setString(2, shiti.getShitiD1());
		pstmt.setString(3, shiti.getShitiD2());
		pstmt.setString(4, shiti.getShitiD3());
		pstmt.setString(5, shiti.getShitiD4());
		pstmt.setString(6, shiti.getShitiDaan());
		return pstmt.executeUpdate();
	}

	public int shitiModify(Connection con, Shiti shiti) throws Exception {
		String sql = "update t_shiti set shitiName=?,shitiD1=?,shitiD2=?,shitiD3=?,shitiD4=?,shitiDaan=? where shitiId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, shiti.getShitiName());
		pstmt.setString(2, shiti.getShitiD1());
		pstmt.setString(3, shiti.getShitiD2());
		pstmt.setString(4, shiti.getShitiD3());
		pstmt.setString(5, shiti.getShitiD4());
		pstmt.setString(6, shiti.getShitiDaan());
		pstmt.setInt(7, shiti.getShitiId());
		return pstmt.executeUpdate();
	}

	// ByShitiId
	public Shiti findById(Connection con, String shitiId) throws Exception {
		Shiti resultShiti = null;
		String sql = "select * from t_shiti where shitiId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, shitiId);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			resultShiti = new Shiti();
			resultShiti.setShitiId(Integer.parseInt(rs.getString("shitiId")));
			resultShiti.setShitiName(rs.getString("shitiName"));
			resultShiti.setShitiD1(rs.getString("shitiD1"));
			resultShiti.setShitiD2(rs.getString("shitiD2"));
			resultShiti.setShitiD3(rs.getString("shitiD3"));
			resultShiti.setShitiD4(rs.getString("shitiD4"));
			resultShiti.setShitiDaan(rs.getString("shitiDaan"));
			return resultShiti;
		} else {
			return null;
		}
	}

}