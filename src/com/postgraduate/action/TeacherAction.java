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
        return SUCCESS;
    }

    public String sendPreReq() {
        return SUCCESS;
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
        return SUCCESS;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
