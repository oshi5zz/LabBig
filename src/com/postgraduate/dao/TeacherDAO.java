package com.postgraduate.dao;


import com.postgraduate.converter.ReqConverter;
import com.postgraduate.converter.StudentConverter;
import com.postgraduate.converter.TeacherConverter;
import com.postgraduate.entity.Msg;
import com.postgraduate.entity.Request;
import com.postgraduate.entity.Student;
import com.postgraduate.entity.Teacher;
import org.apache.commons.lang3.ObjectUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    public Student getStudent(int id) {
        Student student = new Student();
        Connection con = dbConnection.getConnection();
        String sql = "SELECT * FROM student WHERE stu_id = "+id;
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if(rs.next()) {
                student = StudentConverter.getStudent(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            student = null;
        }

        return student;
    }

    public boolean sendMsg(Msg msg) {
        String sql = "INSERT INTO msg(stu_id, tea_id, main,last_date,`read`,flag) " +
                "VALUES (?,?,?,NOW(),0,1)";
        Connection con = dbConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, msg.getStuId());
            ps.setInt(2, msg.getTeaId());
            ps.setString(3,msg.getMain());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hasPreReq(int teaId, int stuId) {
        String sql = "SELECT * FROM request WHERE stu_id=? AND tea_id =?";
        Connection con = dbConnection.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,stuId);
            ps.setInt(2,teaId);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return true;
            else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean sendPreReq(int teaId, int stuId) {
        String sql = "INSERT INTO request(stu_id, tea_id,  status, flag, last_date)" +
                " VALUES (?,?,0,1,NOW())";
        Connection con = dbConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,stuId);
            ps.setInt(2,teaId);
            //设置第一条请求的消息
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private List<Student> viewPreList(String sql ,int teaId) {
        Connection con = dbConnection.getConnection();
        List<Student> students = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,teaId);
            ResultSet rs = ps.executeQuery();
            List<Integer> ids = new ArrayList<>();
            while (rs.next()) {
                ids.add(rs.getInt("stu_id"));
            }
            sql = "SELECT * FROM student WHERE stu_id=?";
            ps = con.prepareStatement(sql);
            for (int id : ids) {
                ps.setInt(1,id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    Student student = StudentConverter.getStudent(rs);
                    if (student != null)
                        students.add(student);
                }
                ps.clearParameters();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public List<Student> viewPreSucList(int teaId) {
        String sql = "SELECT stu_id FROM request WHERE tea_id=?  AND request.status = 2";
        return viewPreList(sql,teaId);
    }

    public List<Student> viewPreReqList(int teaId) {
        String sql = "SELECT stu_id FROM request WHERE tea_id=?  AND request.status = 0";

        return viewPreList(sql,teaId);
    }

    public boolean agreePreReq(int teaId, int stuId) {
        String sql = "UPDATE request SET request.status=2 WHERE stu_id=? AND tea_id =? AND request.status=0";
        Connection con = dbConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,stuId);
            ps.setInt(2,teaId);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean refusePreReq(int teaId, int stuId) {
        String sql = "UPDATE request SET request.status=3 WHERE stu_id=? AND tea_id =? AND request.status=0";
        Connection con = dbConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,stuId);
            ps.setInt(2,teaId);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}
