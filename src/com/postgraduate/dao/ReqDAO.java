package com.postgraduate.dao;

import com.postgraduate.entity.Student;
import com.postgraduate.entity.Teacher;

import java.sql.*;

/**
 * Created by zhao on 2016/12/10.
 */
public class ReqDAO {
    private static DBConnection dbConnection = new DBConnection();;
    private Connection con = null;


    public int getReqStatus(boolean pre, Teacher tea, Student stu) {
        stu = StudentDAO.getStudentInf(stu.getStuId());
        tea = TeacherDAO.getTeacherInf(tea.getTeaId());
        con = dbConnection.getConnection();
        String sql;
        int teaUsedPreNum=0,teaUsedFinalNum=0;
        int teaPreNum =  Integer.parseInt(tea.getPreNum());
        int teaFinalNum = Integer.parseInt(tea.getFinalNum());
        int stuUsedPreNum=0;
        int stuPreNum = stu.getPreNum();
        int stuFinalNum = stu.getFinalTeacherId();
        try{
            sql = "SELECT count(*) FROM request WHERE tea_id=? AND status=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,tea.getTeaId());
            ps.setInt(2,2);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                teaUsedPreNum = rs.getInt(1);
            }
            ps.setInt(2,6);
            rs = ps.executeQuery();
            if (rs.next()) {
                teaUsedFinalNum = rs.getInt(1);
            }
            sql = "SELECT count(*) FROM request WHERE stu_id=? AND status=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,stu.getStuId());
            ps.setInt(2,2);
            rs = ps.executeQuery();
            if (rs.next()) {
                stuUsedPreNum = rs.getInt(1);
            }
            sql = "SELECT count(*) FROM request WHERE stu_id=? AND status=6";
            ps = con.prepareStatement(sql);
            ps.setInt(1,stu.getStuId());
            rs = ps.executeQuery();
            if (rs.next()) {
                stuFinalNum = rs.getInt(1);
            }
            if (teaUsedPreNum>=teaPreNum) {
                return -2;
            } else if (teaUsedFinalNum >= teaFinalNum) {
                return -3;
            }
            if (stuUsedPreNum>=stuPreNum) {
                return -4;
            } else if (stuFinalNum>0) {
                return -5;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -6;
        }

        try {
            sql = "SELECT status FROM request WHERE stu_id=? AND tea_id =? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,stu.getStuId());
            ps.setInt(2,tea.getTeaId());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getInt("status");
            else
                return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -6;
        }
    }

}
