package com.postgraduate.dao;


import com.postgraduate.converter.StudentConverter;
import com.postgraduate.converter.TeacherConverter;
import com.postgraduate.entity.Msg;
import com.postgraduate.entity.Student;
import com.postgraduate.entity.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhao on 2016/11/8.
 */
public class TeacherDAO {
    private static DBConnection dbConnection = new DBConnection();;
    private Connection con = null;

    public Teacher getTeacherInf(int id) {
        Teacher teacher = null;
        String sql = "SELECT * FROM teacher WHERE tea_id = ?";
        con = dbConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return TeacherConverter.getTeacher(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            teacher = null;
        }

        return teacher;
    }

    public List<Student> searchStudents(Student student) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student WHERE 1=1";
        if (!student.getProvince().equals("")){
            sql += " AND province='"+student.getProvince()+"'";
        }
        if (!student.getSchool().equals("")) {
            sql += " AND school='"+student.getSchool()+"'";
        }
        if (!student.getMajor().equals("")) {
            sql += " AND major='"+student.getMajor()+"'";
        }
        if (!student.getResearchArea().equals("")) {
            sql += " AND research_area='"+student.getResearchArea()+"'";
        }
        if (!student.getName().equals("")){
            sql += " AND name='"+student.getName()+"'";
        }
        if (!student.getSex().equals("")) {
            sql += " AND sex='"+student.getSex()+"'";
        }
        con = dbConnection.getConnection();
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            students = StudentConverter.getStudents(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            students = null;
        }
        return students;
    }

    public List<Msg> getMsgs() {
        List<Msg> msgs = new ArrayList<>();

        return msgs;
    }

    public boolean updateTeachInf(Teacher teacher) {
        String sql = "UPDATE teacher SET name=?, age=?,professional_title=?," +
                "province=?,school=?,major=?,research_area=?,inf=?,mail=?,sex=?," +
                "pre_num=?,final_num=?,requirement=? WHERE tea_id=?";
        con = dbConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            if(!TeacherConverter.updateTeacher(ps,teacher))
                return false;
            if(ps.executeUpdate() == -1)
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
