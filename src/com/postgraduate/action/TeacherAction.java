package com.postgraduate.action;

import com.opensymphony.xwork2.ActionSupport;
import com.postgraduate.bean.*;
import com.postgraduate.dao.TeacherDAO;

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
    private List<Req> reqs = new ArrayList<>();

    private List<NewMsg> newMsgs = new ArrayList<>();
    private List<OldMsg> oldMsgs = new ArrayList<>();

    public List<NewMsg> getNewMsgs() {
        return newMsgs;
    }

    public void setNewMsgs(List<NewMsg> newMsgs) {
        this.newMsgs = newMsgs;
    }

    public List<OldMsg> getOldMsgs() {
        return oldMsgs;
    }

    public void setOldMsgs(List<OldMsg> oldMsgs) {
        this.oldMsgs = oldMsgs;
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

    public List<Req> getReqs() {
        return reqs;
    }

    public void setReqs(List<Req> reqs) {
        this.reqs = reqs;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String updateInf() {

        return SUCCESS;
    }

    public String editInf() {
//        teacher = teacherDAO.getTeacherInf(Integer.parseInt(teacher.getId()));
//        teacher = teacherDAO.getTeacherInf(1);

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
        return SUCCESS;
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
