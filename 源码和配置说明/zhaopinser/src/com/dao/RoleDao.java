package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.PageBean;
import com.model.Role;
import com.util.StringUtil;

public class RoleDao {
	
	public ResultSet roleList(Connection con,PageBean pageBean,Role role)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_role where 1=1 ");
		if(role!=null){
			if(StringUtil.isNotEmpty(role.getRoleName())){
				sb.append(" and roleName like '%"+role.getRoleName()+"%'");
			}
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		System.out.println(sb);
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	
	public int roleCount(Connection con,Role role)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_role where 1=1 ");
		if(role!=null){
			if(StringUtil.isNotEmpty(role.getRoleName())){
				sb.append(" and roleName like '%"+role.getRoleName()+"%'");
			}
		}
		System.out.println(sb);
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	/**
	 * delete from tableName where field in (1,3,5)
	 * @param con
	 * @param delIds
	 * @return
	 * @throws Exception
	 */
	public int roleDelete(Connection con,String delIds)throws Exception{
		String sql="delete from t_role where roleId in("+delIds+")";
		System.out.println(sql);
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	
	public int roleAdd(Connection con,Role role)throws Exception{
		String sql="insert into t_role values(null,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, role.getRoleName());
		pstmt.setString(2, role.getRoleMark());
		return pstmt.executeUpdate();
	}
	
	public int roleModify(Connection con,Role role)throws Exception{
		String sql="update t_role set roleName=?,roleMark=? where roleId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, role.getRoleName());
		pstmt.setString(2, role.getRoleMark());
		pstmt.setInt(3, role.getRoleId());
		return pstmt.executeUpdate();
	}
	
}