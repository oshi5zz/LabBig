package com.postgraduate.dao;

import com.postgraduate.converter.StudentConverter;
import com.postgraduate.entity.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        String sql = "SELECT * FROM msg WHERE tea_id="+teaId+" ORDER BY last_date";
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
}
