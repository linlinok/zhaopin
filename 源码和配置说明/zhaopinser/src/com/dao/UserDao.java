package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.PageBean;
import com.model.User;
import com.util.StringUtil;

public class UserDao {

	// ²éÕÒ
	public ResultSet userList(Connection con, PageBean pageBean, User user) throws Exception {
		StringBuffer sb = new StringBuffer("select * from t_user u,t_role r where u.roleId=r.roleId ");

		// System.out.println("I am here : UserDao.userList");

		if (user != null) {
			// ÐÕÃû
			if (StringUtil.isNotEmpty(user.getUserName())) {
				sb.append(" and u.userName like '%" + user.getUserName() + "%'");

			}
			if (user.getUserId() != null) {
				sb.append(" and u.userId ='" + user.getUserId() + "'");
			}
			if (user.getRoleId() != null) {
				sb.append(" and u.roleId =" + user.getRoleId());
			}
			
		}
		// Ò³Âë
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
		}

		// ²âÊÔ²éÕÒsql
		System.out.println("userList : " + sb);

		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}

	// ËÑË÷¼ÆÊý
	public int userCount(Connection con, User user) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_user u,t_role r where u.roleId=r.roleId ");

		// System.out.println("I am here : UserDao.userList");

		if (user != null) {
			// ÐÕÃû
			if (StringUtil.isNotEmpty(user.getUserName())) {
				sb.append(" and u.userName like '%" + user.getUserName() + "%'");

			}
			if (user.getUserId() != null) {
				sb.append(" and u.userId ='" + user.getUserId() + "'");
			}
			if (user.getRoleId() != null) {
				sb.append(" and u.roleId =" + user.getRoleId());
			}
			
		}

		// ²âÊÔËÑË÷¼ÆÊýsql
		// System.out.println(sb);

		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}

	// É¾³ý
	public int userDelete(Connection con, String delIds) throws Exception {
		String sb = "delete from t_user where userId in(" + delIds + ")";

		// ²âÊÔÉ¾³ýsql
		// System.out.println(sb);

		PreparedStatement pstmt = con.prepareStatement(sb);
		return pstmt.executeUpdate();
	}

	// Ìí¼Ó
	public int userAdd(Connection con, User user) throws Exception {
		String sql = "insert into t_user values(null,?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getUserPassword());
		pstmt.setString(3, user.getUserXingming());
		pstmt.setString(4, user.getUserAge());
		pstmt.setString(5, user.getUserSex());
		pstmt.setInt(6, user.getRoleId());
		return pstmt.executeUpdate();
	}

	// ÐÞ¸Ä
	public int userModify(Connection con, User user) throws Exception {
		String sql = "update t_user set userName=?,userPassword=?,userXingming=?,userAge=?,userSex=?,roleId=? where userId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getUserPassword());
		pstmt.setString(3, user.getUserXingming());
		pstmt.setString(4, user.getUserAge());
		pstmt.setString(5, user.getUserSex());
		pstmt.setInt(6, user.getRoleId());
		pstmt.setInt(7, user.getUserId());
		return pstmt.executeUpdate();
	}

	// µÇÂ¼
	public User login(Connection con, User user) throws Exception {
		User resultUser = null;
		String sql = "select * from t_user where userName=? and userPassword=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getUserPassword());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			resultUser = new User();
			resultUser.setUserId(Integer.parseInt(rs.getString("userId")));
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setUserPassword(rs.getString("userPassword"));
			resultUser.setUserXingming(rs.getString("userXingming"));
			resultUser.setUserAge(rs.getString("userAge"));
			resultUser.setUserSex(rs.getString("userSex"));
			resultUser.setRoleId(rs.getInt("roleId"));
			return resultUser;
		} else {
			return null;
		}
	}

	// ÅÐ¶ÏÓÃ»§Ãû
	public boolean zhuce(Connection con, User user) throws Exception {
		String sql = "select * from t_user where userName=? ";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return false;
		}else{
			return true ;
		}
	}

	// ByUserId
	public User findByUserId(Connection con, String userId) throws Exception {
		User resultUser = null;
		String sql = "select * from t_user where userId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userId);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			resultUser = new User();
			resultUser.setUserId(Integer.parseInt(rs.getString("userId")));
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setUserPassword(rs.getString("userPassword"));
			resultUser.setUserXingming(rs.getString("userXingming"));
			resultUser.setUserAge(rs.getString("userAge"));
			resultUser.setUserSex(rs.getString("userSex"));
			resultUser.setRoleId(rs.getInt("roleId"));
			return resultUser;
		} else {
			return null;
		}
	}
}
