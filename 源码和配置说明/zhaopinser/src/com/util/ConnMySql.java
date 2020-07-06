package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class ConnMySql {
 
    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        Class.forName("com.mysql.jdbc.Driver");
         
       // Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sheji_db?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true","root","lin5201314");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zhaopin_db?serverTimezone=UTC&characterEncoding=utf8&amp", "root", "123");
        
        Statement stmt =  conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from t_admin");
         
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "\t"
                    +rs.getString(2) + "\t"
                    +rs.getString(3) );
            }
         
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();   
        }
        if (conn != null) {
            conn.close();   
        }
    }
 
}