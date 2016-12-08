package com.postgraduate.dao;

import com.postgraduate.converter.MsgConverter;
import com.postgraduate.converter.StudentConverter;
import com.postgraduate.entity.Msg;
import com.postgraduate.entity.Student;
import com.postgraduate.service.Recommend;
import net.sf.json.JSONObject;

import javax.json.Json;
import javax.json.JsonObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhao on 2016/12/7.
 */
public class MsgDAO {
    private static DBConnection dbConnection = new DBConnection();;
    private Connection con = null;

    public List<Student> getStudentList(int teaId) {
        List<Student> students = new ArrayList<>();
        con = dbConnection.getConnection();
        String sql = "SELECT * FROM msg WHERE tea_id="+teaId+" ORDER BY last_date DESC ";
        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<Integer> stuIdList = new ArrayList<>();
            while (rs.next()) {
                int stu_id = rs.getInt("stu_id");
                boolean flag = true;
                for (int i : stuIdList) {
                    if (i == stu_id) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    stuIdList.add(stu_id);
                }
            }
            for (int i : stuIdList) {
                sql = "SELECT * FROM student WHERE stu_id="+i;
                rs = statement.executeQuery(sql);
                if (rs.next()) {
                    students.add(StudentConverter.getStudent(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public List<Msg> getStudentMsgs(int stu_id, int tea_id) {
        List<Msg> msgs = new ArrayList<>();
        con = dbConnection.getConnection();
        String sql = "SELECT * FROM msg WHERE stu_id=? AND tea_id=? ORDER BY last_date";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,stu_id);
            ps.setInt(2,tea_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Msg msg = MsgConverter.getMsg(rs);
                if (msg != null)
                    msgs.add(msg);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return msgs;
    }

    public boolean sendMsg(Msg msg) {
        String sql = "INSERT INTO msg(stu_id, tea_id, main,last_date,`read`,flag) " +
                "VALUES (?,?,?,NOW(),1,?)";
        Connection con = dbConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, msg.getStuId());
            ps.setInt(2, msg.getTeaId());
            ps.setString(3,msg.getMain());
            ps.setInt(4, msg.getFlag());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getMsgNum(int teaId) {
        con = dbConnection.getConnection();
        String sql = "SELECT count(*) FROM msg WHERE tea_id="+teaId+" AND `read`=0 AND msg.flag=0";
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updateReadMsg(int stuId, int teaId) {
        con = dbConnection.getConnection();
        String sql = "UPDATE msg SET `read`=1 WHERE stu_id="+stuId+" AND tea_id="+teaId+" AND msg.flag=0";
        try {
            Statement statement = con.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
