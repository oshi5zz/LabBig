package com.postgraduate.dao;


import com.postgraduate.converter.StudentConverter;
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

    public List<Student> searchTeachers(Student student) {
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

    public List<Msg> getMsgs(int teaId) {
        List<Msg> msgs = new ArrayList<>();
        Connection con = dbConnection.getConnection();
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM msg WHERE tea_id=? ORDER BY last_date DESC ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,teaId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Msg m = new Msg();
                m.setMsgId(rs.getInt("msg_id"));
                m.setLastDate(rs.getString("last_date"));
                m.setMain(rs.getString("main"));
                m.setRead(rs.getInt("read"));
                m.setAbs(m.getMain().length() > 10 ? m.getMain().substring(0,10):m.getMain());
                m.setFlag(rs.getInt("flag"));
                int stu_id = rs.getInt("stu_id");
                sql = "SELECT * FROM student WHERE stu_id="+stu_id;
                ResultSet resultSet = con.createStatement().executeQuery(sql);
                if (resultSet.next()) {
                    m.setStudent(StudentConverter.getStudent(resultSet));
                }
                msgs.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            msgs = null;
        }
        return msgs;
    }

    public Student updateTeachInf(Student student) {
        String sql = "UPDATE student SET name=?, age=?,professional_title=?," +
                "province=?,school=?,major=?,research_area=?,inf=?,mail=?,sex=?," +
                "pre_num=?,final_num=?,requirement=? WHERE tea_id="+student.getTeaId();
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

    private List<Teacher> viewReqList(String sql , int teaId) {
        Connection con = dbConnection.getConnection();
        List<Teacher> students = new ArrayList<>();
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

    public List<Teacher> viewPreSucList(int teaId) {
        String sql = "SELECT stu_id FROM request WHERE tea_id=?  AND request.status = 2";
        return viewReqList(sql,teaId);
    }

    public List<Teacher> viewPreReqList(int teaId) {
        String sql = "SELECT stu_id FROM request WHERE tea_id=?  AND request.status = 0";

        return viewReqList(sql,teaId);
    }

   /* public boolean solveReq(boolean agree, boolean pre, int teaId, int stuId) {
        //已用完名额
        if (!hasNum(pre,teaId)) {
            return false;
        }

        String sql = "";
        //同意请求
        if (agree) {
            //同意预请求
            if (pre)
                sql = "UPDATE request SET request.status=2 WHERE stu_id=? AND tea_id =? AND request.status=0 OR request.status=1";
            //同意最终请求
            else
                sql = "UPDATE request SET request.status=6 WHERE stu_id=? AND tea_id =? AND (request.status=4 OR request.status=5)";
        }
        //拒绝请求
        else {
            //拒绝预请求
            if (pre)
                sql = "UPDATE request SET request.status=3 WHERE stu_id=? AND tea_id =? AND request.status=0 OR request.status=1";
            //拒绝最终请求
            else
                sql = "UPDATE request SET request.status=7 WHERE stu_id=? AND tea_id =? AND (request.status=4 OR request.status=5)";
        }

        Connection con = dbConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,stuId);
            ps.setInt(2,teaId);
            if(ps.executeUpdate() == 1){
                updateNum(agree,pre,teaId);
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }*/

 /*   private boolean hasNum(boolean pre, int teaId) {
        String sql = "";
        if (pre)
            sql = "SELECT pre_num FROM student WHERE tea_id=?";
        else
            sql = "SELECT final_num FROM student WHERE tea_id=?";
        Connection con = dbConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, teaId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (pre)
                    return rs.getInt("pre_num") > 0;
                else
                    return rs.getInt("final_num") > 0;
            }
            else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }*/

    public int getReqStatus(boolean pre, Student stu, Teacher tea) {
        Connection con = dbConnection.getConnection();
        try {
            String sql2 = "SELECT * FROM request WHERE tea_id="+stu.getTeaId()+" AND status="+ (pre?2:6);
            Statement stat = con.createStatement();
            ResultSet res = stat.executeQuery(sql2);
            int num = 0;
            while (res.next()) {
                num++;
            }
            if (pre && num >= Integer.parseInt(stu.getPreNum()))
                return -2;
            else if(!pre && num >= Integer.parseInt(stu.getFinalNum()))
                return -3;

            String sql = "SELECT status FROM request WHERE stu_id=? AND tea_id =? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,stu.getStuId());
            ps.setInt(2,stu.getTeaId());
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

    public boolean updateReq(int status, int teaId, int stuId) {
        String sql = "";
        if (status==0) {
            sql = "INSERT INTO request(stu_id, tea_id,  status, flag, last_date)" +
                    " VALUES (?,?,?,1,NOW())";
        } else {
            sql = "UPDATE request SET status="+status+" WHERE stu_id=? AND tea_id=?";
        }
        Connection con = dbConnection.getConnection();
        try {
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

    public List<Teacher> viewFinalSucList(int teaId) {
        String sql = "SELECT stu_id FROM request WHERE tea_id=?  AND request.status = 6";
        return viewReqList(sql,teaId);
    }

    public List<Request> getReqs(int teaId) {
        List<Request> reqs = new ArrayList<>();
        Connection con = dbConnection.getConnection();
        String sql = "SELECT * FROM request WHERE tea_id=? ORDER BY last_date DESC ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,teaId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Request request = new Request();
                request.setLastDate(rs.getString("last_date"));
                request.setFlag(rs.getInt("flag"));
                request.setStatus(rs.getInt("status"));
                request.setReqId(rs.getInt("req_id"));
                int stu_id = rs.getInt("stu_id");
                sql = "SELECT * FROM student WHERE stu_id="+stu_id;
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

    public List<Teacher> getRecommend(int teaId) {
        con = dbConnection.getConnection();
        try {
            String sql = "SELECT * FROM student WHERE tea_id="+teaId;
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

    private List<Student> getRecommend(Student student) {
        List<Student> students = new ArrayList<>();
        con = dbConnection.getConnection();
        String area = student.getResearchArea();
        try {
            String sql = "SELECT * FROM student WHERE research_area LIKE '"+area+"' LIMIT 5";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Student stu = StudentConverter.getStudent(rs);
                students.add(stu);
            }
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
            return students;
        }
    }

    public Msg getMsgDetail(int msgId) {
        Msg msg = new Msg();
        msg.setMsgId(msgId);
        con = dbConnection.getConnection();
        try {
            String sql = "SELECT * FROM msg WHERE msg_id="+msgId;
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                msg.setMsgId(rs.getInt("msg_id"));
                msg.setLastDate(rs.getString("last_date"));
                msg.setMain(rs.getString("main"));
                msg.setRead(rs.getInt("read"));
                msg.setFlag(rs.getInt("flag"));
                msg.setStuId(rs.getInt("stu_id"));
                msg.setTeaId(rs.getInt("tea_id"));
            }
            return msg;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Student updateStudentInf(Student student) {
        return null;
    }

    public List<Teacher> searchTeachers(Teacher teacher) {
        return null;
    }

    public Teacher getTeacher(int id) {
        return null;
    }
}
