package com.postgraduate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by zhao on 2016/11/8.
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        DBConnection dbc = new DBConnection();
        String sql = "SELECT * FROM teacher WHERE tea_id = ?";
        Connection con = dbc.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,1);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("name"));
        }

    }
}