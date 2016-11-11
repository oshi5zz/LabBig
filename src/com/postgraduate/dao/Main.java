package com.postgraduate.dao;

import com.postgraduate.entity.Student;
import com.postgraduate.entity.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhao on 2016/11/8.
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        DBConnection dbc = new DBConnection();
        ResultSet rs = dbc.select("select * from Student");
        while (rs.next()) {
            System.out.println(rs.getString("name"));
        }

    }
}