package com.postgraduate.converter;

import com.postgraduate.entity.Student;
import sun.font.ScriptRun;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhao on 2016/11/12.
 */
public class StudentConverter {

    public static Student getStudent(ResultSet rs) {
        Student student = new Student();
        try {
            student.setName(rs.getString("name"));
            student.setAge(rs.getInt("age"));
            student.setInf(rs.getString("inf"));
            student.setMail(rs.getString("mail"));
            student.setMajor(rs.getString("major"));
            student.setPreNum(rs.getInt("pre_num"));
            student.setProvince(rs.getString("province"));
            student.setSchool(rs.getString("school"));
            student.setSex(rs.getString("sex"));
            student.setResearchArea(rs.getString("research_area"));
            student.setInterest(rs.getString("interest"));
            student.setFinalTeacherId(rs.getInt("final_teacher_id"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return student;
    }

    public static List<Student> getStudents(ResultSet rs) {
        List<Student> students = new ArrayList<>();

        try {
            while (rs.next()) {

                Student student = getStudent(rs);
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}
