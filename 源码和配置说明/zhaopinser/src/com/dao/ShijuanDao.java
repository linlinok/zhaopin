package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.PageBean;
import com.model.Shijuan;
import com.util.StringUtil;

public class ShijuanDao {

	public ResultSet shijuanList(Connection con, PageBean pageBean, Shijuan shijuan) throws Exception {
		StringBuffer sb = new StringBuffer("select * from t_shijuan x,t_zhiwei g where x.zhiweiId = g.zhiweiId ");
		if (shijuan != null) {
			if (StringUtil.isNotEmpty(shijuan.getShijuanName())) {
				sb.append(" and x.shijuanName like '%" + shijuan.getShijuanName() + "%'");
			}
			if (shijuan.getZhiweiId() != null) {
				sb.append(" and x.zhiweiId = '" + shijuan.getZhiweiId() + "'");
			}
		}

		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}

	public int shijuanCount(Connection con, Shijuan shijuan) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_shijuan x,t_zhiwei g where x.zhiweiId = g.zhiweiId ");
		if (shijuan != null) {
			if (StringUtil.isNotEmpty(shijuan.getShijuanName())) {
				sb.append(" and x.shijuanName like '%" + shijuan.getShijuanName() + "%'");
			}
			if (shijuan.getZhiweiId() != null) {
				sb.append(" and x.zhiweiId = '" + shijuan.getZhiweiId() + "'");
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
	public int shijuanDelete(Connection con, String delIds) throws Exception {
		String sql = "delete from t_shijuan where shijuanId in(" + delIds + ")";
		System.out.println(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}

	public int shijuanAdd(Connection con, Shijuan shijuan) throws Exception {
		String sql = "insert into t_shijuan values(null,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, shijuan.getShijuanName());
		pstmt.setString(2, shijuan.getShijuanMark());
		pstmt.setInt(3, shijuan.getZhiweiId());
		return pstmt.executeUpdate();
	}

	public int shijuanModify(Connection con, Shijuan shijuan) throws Exception {
		String sql = "update t_shijuan set shijuanName=?,shijuanMark=?,zhiweiId=? where shijuanId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, shijuan.getShijuanName());
		pstmt.setString(2, shijuan.getShijuanMark());
		pstmt.setInt(3, shijuan.getZhiweiId());
		pstmt.setInt(4, shijuan.getShijuanId());
		return pstmt.executeUpdate();
	}

	// ByShijuanId
	public Shijuan findById(Connection con, String shijuanId) throws Exception {
		Shijuan resultShijuan = null;
		String sql = "select * from t_shijuan where shijuanId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, shijuanId);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			resultShijuan = new Shijuan();
			resultShijuan.setShijuanId(Integer.parseInt(rs.getString("shijuanId")));
			resultShijuan.setShijuanName(rs.getString("shijuanName"));
			resultShijuan.setShijuanMark(rs.getString("shijuanMark"));
			resultShijuan.setZhiweiId(Integer.parseInt(rs.getString("zhiweiId")));
			return resultShijuan;
		} else {
			return null;
		}
	}

}