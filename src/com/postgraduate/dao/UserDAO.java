package com.postgraduate.dao;

import com.postgraduate.entity.Student;
import com.postgraduate.entity.Teacher;
import com.postgraduate.entity.User;

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

    public boolean logupTeacher(User user) {
        Teacher teacher = new Teacher();
        String sql = "INSERT INTO user(user_id, password, user_name, type) VALUES (?,?,?,?)";
        Connection con = dbConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(user.getUserId()));
            ps.setString(2, user.getPassword());
            ps.setString(3,user.getUserName());
            ps.setInt(4,user.getType());
            boolean result = ps.executeUpdate() == 1;
            if (result) {
                sql = "INSERT INTO teacher(tea_id,name) VALUES (?,?)";
                ps = con.prepareStatement(sql);
                ps.setInt(1,Integer.parseInt(user.getUserId()));
                ps.setString(2,user.getUserName());
                return ps.executeUpdate() == 1;
            } else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
