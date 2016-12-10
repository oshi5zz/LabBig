package com.postgraduate.dao;


import com.postgraduate.converter.StudentConverter;
import com.postgraduate.converter.TeacherConverter;
import com.postgraduate.entity.*;
import com.postgraduate.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhao on 2016/11/8.
 */
public class StudentDAO {
    private static DBConnection dbConnection = new DBConnection();;
    private Connection con = null;

    public Student getStudentInf(int id) {
        Student student = null;
        String sql = "SELECT * FROM student WHERE stu_id = ?";
        con = dbConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return StudentConverter.getStudent(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            student = null;
        }

        return student;
    }

    public List<Teacher> searchTeachers(Teacher teacher) {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT * FROM teacher WHERE 1=1";
        if (!teacher.getProvince().equals("")){
            sql += " AND province='"+teacher.getProvince()+"'";
        }
        if (!teacher.getSchool().equals("")) {
            sql += " AND school='"+teacher.getSchool()+"'";
        }
        if (!teacher.getMajor().equals("")) {
            sql += " AND major='"+teacher.getMajor()+"'";
        }
        if (!teacher.getResearchArea().equals("")) {
            sql += " AND research_area='"+teacher.getResearchArea()+"'";
        }
        if (!teacher.getName().equals("")){
            sql += " AND name='"+teacher.getName()+"'";
        }
        if (!teacher.getSex().equals("")) {
            sql += " AND sex='"+teacher.getSex()+"'";
        }
        con = dbConnection.getConnection();
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            teachers = TeacherConverter.getTeachers(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            teachers = null;
        }
        return teachers;
    }

    public List<Msg> getMsgs(int stuId) {
        List<Msg> msgs = new ArrayList<>();
        Connection con = dbConnection.getConnection();
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT * FROM msg WHERE stu_id=? ORDER BY last_date DESC ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,stuId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Msg m = new Msg();
                m.setMsgId(rs.getInt("msg_id"));
                m.setLastDate(rs.getString("last_date"));
                m.setMain(rs.getString("main"));
                m.setRead(rs.getInt("read"));
                m.setAbs(m.getMain().length() > 10 ? m.getMain().substring(0,10):m.getMain());
                m.setFlag(rs.getInt("flag"));
                int tea_id = rs.getInt("tea_id");
                sql = "SELECT * FROM teacher WHERE tea_id="+tea_id;
                ResultSet resultSet = con.createStatement().executeQuery(sql);
                if (resultSet.next()) {
                    m.setTeacher(TeacherConverter.getTeacher(resultSet));
                }
                msgs.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            msgs = null;
        }
        return msgs;
    }

    public Student updateStudentInf(Student student) {
        String sql = "UPDATE student SET name=?, age=?," +
                "province=?,school=?,major=?,research_area=?,inf=?,mail=?,sex=?," +
                "pre_num=?,interest=?,final_teacher_id=? WHERE stu_id="+student.getStuId();
        con = dbConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            if(!StudentConverter.updateStudent(ps,student))
                return null;
            if(ps.executeUpdate() == -1)
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return student;
    }

    public Teacher getTeacher(int id) {
        Teacher teacher = new Teacher();
        Connection con = dbConnection.getConnection();
        String sql = "SELECT * FROM teacher WHERE tea_id = "+id;
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if(rs.next()) {
                teacher = TeacherConverter.getTeacher(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            teacher = null;
        }
        return teacher;
    }

    public boolean sendMsg(Msg msg) {
        try {
            Connection con = dbConnection.getConnection();
            String sql = "INSERT INTO msg(stu_id, tea_id, main,last_date,`read`,flag) " +
                    "VALUES (?,?,?,NOW(),0,0)";
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

    private List<Teacher> viewReqList(String sql , int stuId) {
        Connection con = dbConnection.getConnection();
        List<Teacher> teachers = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,stuId);
            ResultSet rs = ps.executeQuery();
            List<Integer> ids = new ArrayList<>();
            while (rs.next()) {
                ids.add(rs.getInt("tea_id"));
            }
            sql = "SELECT * FROM teacher WHERE tea_id=?";
            ps = con.prepareStatement(sql);
            for (int id : ids) {
                ps.setInt(1,id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    Teacher teacher = TeacherConverter.getTeacher(rs);
                    if (teacher != null)
                        teachers.add(teacher);
                }
                ps.clearParameters();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    public List<Teacher> viewPreSucList(int stuId) {
        String sql = "SELECT tea_id FROM request WHERE stu_id=?  AND request.status = 2";
        return viewReqList(sql,stuId);
    }

    public List<Teacher> viewPreReqList(int stuId) {
        String sql = "SELECT tea_id FROM request WHERE stu_id=?  AND request.status = 0";
        return viewReqList(sql,stuId);
    }

    public int getReqStatus(boolean pre, Student stu, Teacher tea) {
        Connection con = dbConnection.getConnection();
        try {
            String sql2 = "SELECT * FROM request WHERE stu_id="+stu.getStuId()+" AND status="+ (pre?2:6);
            Statement stat = con.createStatement();
            ResultSet res = stat.executeQuery(sql2);
            int num = 0;
            while (res.next()) {
                num++;
            }
            System.out.println(stu.getPreNum());
            if (pre && num >= stu.getPreNum())
                return -2;
            else if(!pre && stu.getFinalTeacherId() != 0)
                return -3;

            String sql = "SELECT status FROM request WHERE stu_id=? AND tea_id =? ";
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
        }
        return -1;
    }

    public boolean updateReq(int status, int stuId, int teaId) {
        String sql = "";
        if (status==0) {
            sql = "INSERT INTO request(stu_id, tea_id,  status, flag, last_date)" +
                    " VALUES (?,?,?,0,NOW())";
        } else {
            sql = "UPDATE request SET status="+status+" WHERE stu_id=? AND tea_id=?";
        }
        try {
            Connection con = dbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,stuId);
            ps.setInt(2,teaId);
            if (status==0) {
                ps.setInt(3, 0);
            }
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Teacher> viewFinalSucList(int stuId) {
        String sql = "SELECT tea_id FROM request WHERE stu_id=?  AND request.status = 6";
        return viewReqList(sql,stuId);
    }

    public List<Request> getReqs(int stuId) {
        List<Request> reqs = new ArrayList<>();
        Connection con = dbConnection.getConnection();
        String sql = "SELECT * FROM request WHERE stu_id=? ORDER BY last_date DESC ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,stuId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Request request = new Request();
                request.setLastDate(rs.getString("last_date"));
                request.setFlag(rs.getInt("flag"));
                request.setStatus(rs.getInt("status"));
                request.setReqId(rs.getInt("req_id"));
                int tea_id = rs.getInt("stu_id");
                sql = "SELECT * FROM teacher WHERE tea_id="+tea_id;
                ResultSet resultSet = con.createStatement().executeQuery(sql);
                if (resultSet.next()) {
                    request.setStudent(StudentConverter.getStudent(resultSet));
                }
                reqs.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            reqs = null;
        }
        return reqs;
    }

    public List<Teacher> getRecommend(int stuId) {
        con = dbConnection.getConnection();
        try {
            String sql = "SELECT * FROM student WHERE stu_id="+stuId;
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                Student student = StudentConverter.getStudent(rs);
                return getRecommend(student);
            } else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    private List<Teacher> getRecommend(Student student) {
        List<Teacher> teachers = new ArrayList<>();
        con = dbConnection.getConnection();
        String area = student.getResearchArea();
        try {
            String sql = "SELECT * FROM teacher WHERE research_area LIKE '"+area+"' LIMIT 5";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Teacher tea = TeacherConverter.getTeacher(rs);
                teachers.add(tea);
            }
            return teachers;
        } catch (SQLException e) {
            e.printStackTrace();
            return teachers;
        }
    }

}
