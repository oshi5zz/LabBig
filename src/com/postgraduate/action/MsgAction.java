package com.postgraduate.action;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.postgraduate.dao.MsgDAO;
import com.postgraduate.entity.Msg;
import com.postgraduate.entity.Student;
import com.postgraduate.entity.Teacher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhao on 2016/12/7.
 */
public class MsgAction extends ActionSupport {
    private List<Student> students = new ArrayList<>();
    private List<Teacher> teachers = new ArrayList<>();
    private Student student = new Student();
    private Teacher teacher = new Teacher();
    private List<Msg> msgs = new ArrayList<>();
    private MsgDAO msgDAO = new MsgDAO();

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Msg> getMsgs() {
        return msgs;
    }

    public void setMsgs(List<Msg> msgs) {
        this.msgs = msgs;
    }

    public String getStudentList() {
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        students = msgDAO.getStudentList(teacher.getTeaId());
        return "success";
    }

    public String getStudentMsg() {

        return "success";
    }

}
