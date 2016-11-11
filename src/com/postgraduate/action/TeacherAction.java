package com.postgraduate.action;

import com.opensymphony.xwork2.ActionSupport;
import com.postgraduate.dao.HibernateSessionFactory;
import com.postgraduate.dao.TeacherDAO;
import com.postgraduate.entity.Msg;
import com.postgraduate.entity.Request;
import com.postgraduate.entity.Student;
import com.postgraduate.entity.Teacher;
import org.hibernate.Session;

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

        return SUCCESS;
    }

    public String editInf() {
        teacherDAO = new TeacherDAO();
        teacher = teacherDAO.getTeacherInf(1);
        System.out.println(teacher.getAge());
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
        teacherDAO = new TeacherDAO();
        students = teacherDAO.searchStudents(student);
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
