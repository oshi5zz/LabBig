package com.postgraduate.action;

import com.opensymphony.xwork2.ActionSupport;
import com.postgraduate.dao.TeacherDAO;
import com.postgraduate.entity.Msg;
import com.postgraduate.entity.Request;
import com.postgraduate.entity.Student;
import com.postgraduate.entity.Teacher;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhao on 2016/11/5.
 */
public class TeacherAction extends ActionSupport {
    private TeacherDAO teacherDAO = new TeacherDAO();

    //
    private Teacher teacher = new Teacher();

    private Student student = new Student();
    //
    private List<Student> students = new ArrayList<>();
    private List<Msg> msgs = new ArrayList<>();
    private List<Request> reqs = new ArrayList<>();

    private Msg msg = new Msg();

    public Msg getMsg() {
        return msg;
    }

    public void setMsg(Msg msg) {
        this.msg = msg;
    }

    private String warning = new String();

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public List<Msg> getMsgs() {
        return msgs;
    }

    private String stuid = new String();

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public void setMsgs(List<Msg> msgs) {
        this.msgs = msgs;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Request> getReqs() {
        return reqs;
    }

    public void setReqs(List<Request> reqs) {
        this.reqs = reqs;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String updateInf() {
        if(teacherDAO.updateTeachInf(teacher))
            return SUCCESS;
        else
            return ERROR;
    }

    public String editInf() {
        teacher = teacherDAO.getTeacherInf(1);
        if (teacher != null) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }


    public String toSearch() {
        return SUCCESS;
    }

    public String viewSearchResult() {
        students = teacherDAO.searchStudents(student);
        if (students != null)
            return SUCCESS;
        else
            return ERROR;
    }

    public String login() {

        return SUCCESS;
    }

    public String getIndex() {

        return SUCCESS;
    }

    public String viewPreSucList() {
        students = teacherDAO.viewPreSucList(1);
        return SUCCESS;
    }

    public String sendPreReq() {
        int stu_id = 1;
        teacher.setTeaId(1);
        try {
            stu_id = Integer.parseInt(stuid);
        } catch (Exception e) {
            return ERROR;
        }
        if (!teacherDAO.hasPreReq(teacher.getTeaId(),stu_id))
            if (teacherDAO.sendPreReq(teacher.getTeaId(), stu_id)) {
                warning = "预录取请求已发送！";
                return SUCCESS;
            }
            else
                return ERROR;
        else {
            warning = "已给该生发送过预请求";
            return "exist";
        }
    }

    public String sendFinalReq() {
        return SUCCESS;
    }

    public String viewMsg() {
        msgs = teacherDAO.getMsgs();
        for (Msg msg : msgs) {

        }
        return SUCCESS;
    }

    public String sendMsg() {
        msg.setStuId(1);
        msg.setTeaId(1);
        if (msg.getMain().length() < 1)
            return INPUT;
        if (teacherDAO.sendMsg(msg)) {
            return SUCCESS;
        }
        else
            return INPUT;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String viewStudentDetail() {
        try {
            int id = Integer.parseInt(stuid);
            student = teacherDAO.getStudent(id);
            return SUCCESS;
        } catch (Exception e) {
            return ERROR;
        }
    }

    public String writeMsg() {
        return SUCCESS;
    }

    public String viewPreReqList() {
        students = teacherDAO.viewPreReqList(1);
        return SUCCESS;
    }

    public String agreePreReq(){
        teacher.setTeaId(1);
        try {
            int id = Integer.parseInt(stuid);
            if (teacherDAO.solveReq(true,true,teacher.getTeaId(), id)) {
                warning = "已同意预请求";
                return SUCCESS;
            }
            else {
                warning = "未知错误！";
                return ERROR;
            }
        } catch (Exception e) {
            warning = "链接错误，学生id不是数字！";
            return ERROR;
        }
    }

    public String refusePreReq() {
        teacher.setTeaId(1);
        try {
            int id = Integer.parseInt(stuid);
            if (teacherDAO.solveReq(false,true,teacher.getTeaId(), id)) {
                warning = "已拒绝预请求";
                return SUCCESS;
            }
            else {
                warning = "未知错误！";
                return ERROR;
            }
        } catch (Exception e) {
            warning = "链接错误，学生id不是数字！";
            return ERROR;
        }
    }
}
