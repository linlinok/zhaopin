package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.Admin;


public class AdminDao {

	/**
	 * µÇÂ¼ÑéÖ¤
	 * @param con
	 * @param admin
	 * @return
	 * @throws Exception
	 */
	public Admin login(Connection con,Admin admin) throws Exception{
		Admin resultUser=null;
		String sql="select * from t_admin where adminName=? and adminPassword=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, admin.getAdminName());
		pstmt.setString(2, admin.getAdminPassword());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			resultUser=new Admin();
			resultUser.setAdminName(rs.getString("adminName"));
			resultUser.setAdminPassword(rs.getString("adminPassword"));
		}
		return resultUser;
	}
}