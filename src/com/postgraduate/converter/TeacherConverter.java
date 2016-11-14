package com.postgraduate.converter;

import com.postgraduate.entity.Teacher;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.xalan.internal.utils.XMLSecurityManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhao on 2016/11/12.
 */
public class TeacherConverter {
    public static Teacher getTeacher(ResultSet rs) {
        Teacher teacher = new Teacher();
        try {
            teacher.setName(rs.getString("name"));
            teacher.setAge(rs.getString("age"));
            teacher.setFinalNum(rs.getString("final_num"));
            teacher.setInf(rs.getString("inf"));
            teacher.setMail(rs.getString("mail"));
            teacher.setMajor(rs.getString("major"));
            teacher.setPreNum(rs.getString("pre_num"));
            teacher.setProfessionalTitle(rs.getString("professional_title"));
            teacher.setProvince(rs.getString("province"));
            teacher.setSchool(rs.getString("school"));
            teacher.setSex(rs.getString("sex"));
            teacher.setResearchArea(rs.getString("research_area"));
            teacher.setTeaId(rs.getInt("tea_id"));
            teacher.setRequirement(rs.getString("requirement"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;

    }

    public static boolean updateTeacher(PreparedStatement ps, Teacher teacher) {
        try {
            ps.setString(1,teacher.getName());
            ps.setString(2,teacher.getAge());
            ps.setString(3,teacher.getProfessionalTitle());
            ps.setString(4,teacher.getProvince());
            ps.setString(5,teacher.getSchool());
            ps.setString(6,teacher.getMajor());
            ps.setString(7,teacher.getResearchArea());
            ps.setString(8,teacher.getInf());
            ps.setString(9,teacher.getMail());
            ps.setString(10,teacher.getSex());
            ps.setString(11, teacher.getPreNum());
            ps.setString(12, teacher.getFinalNum());
            ps.setString(13, teacher.getRequirement());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
