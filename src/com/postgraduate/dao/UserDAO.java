package com.postgraduate.dao;

import com.postgraduate.converter.StudentConverter;
import com.postgraduate.converter.TeacherConverter;
import com.postgraduate.entity.Student;
import com.postgraduate.entity.Teacher;
import com.postgraduate.entity.User;

import java.sql.*;

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
                sql = "SELECT * FROM teacher WHERE tea_id="+userId;
                Statement stat = con.createStatement();
                rs = stat.executeQuery(sql);
                if (rs.next()) {
                    teacher = TeacherConverter.getTeacher(rs);
                    return teacher;
                } else return null;
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
                sql = "SELECT * FROM student WHERE stu_id="+userId;
                Statement stat = con.createStatement();
                rs = stat.executeQuery(sql);
                if (rs.next()) {
                    student = StudentConverter.getStudent(rs);
                    return student;
                } else return null;
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
