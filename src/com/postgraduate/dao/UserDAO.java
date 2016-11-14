package com.postgraduate.dao;

import com.postgraduate.entity.Student;
import com.postgraduate.entity.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by zhao on 2016/11/15.
 */
public class UserDAO {
    private static DBConnection dbConnection = new DBConnection();

    public Teacher loginTeacher(int userId, String password) {
        Teacher teacher = new Teacher();
        String sql = "SELECT * FROM user WHERE user_id=? AND password=? AND user.type=1";
        Connection con = dbConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                teacher.setName(rs.getString("user_name"));
                teacher.setTeaId(rs.getInt("user_id"));
                return teacher;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Student loginStudent(int userId, String password) {
        Student student = new Student();
        String sql = "SELECT * FROM user WHERE user_id=? AND password=? AND user.type=0";
        Connection con = dbConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                student.setName(rs.getString("user_name"));
                student.setStuId(rs.getInt("user_id"));
                return student;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
