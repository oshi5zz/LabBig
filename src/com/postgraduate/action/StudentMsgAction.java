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
 * Created by zhao on 2016/12/10.
 */
public class StudentMsgAction extends ActionSupport{
    private List<Teacher> teachers = new ArrayList<>();
    private Student student = new Student();
    private Teacher teacher = new Teacher();
    private List<Msg> msgs = new ArrayList<>();
    private MsgDAO msgDAO = new MsgDAO();
    private List<List<String>> msgsJson = new ArrayList<>();
    private Msg msg = new Msg();
    private boolean firstFlag = false;
    private int msgNum = 0;
    private int perMsgNum = 0;

    public int getMsgNum() {
        return msgNum;
    }

    public void setMsgNum(int msgNum) {
        this.msgNum = msgNum;
    }

    public int getPerMsgNum() {
        return perMsgNum;
    }

    public void setPerMsgNum(int perMsgNum) {
        this.perMsgNum = perMsgNum;
    }

    public boolean isFirstFlag() {
        return firstFlag;
    }

    public void setFirstFlag(boolean firstFlag) {
        this.firstFlag = firstFlag;
    }

    public Msg getMsg() {
        return msg;
    }

    public void setMsg(Msg msg) {
        this.msg = msg;
    }

    public List<List<String>> getMsgsJson() {
        return msgsJson;
    }

    public void setMsgsJson(List<List<String>> msgsJson) {
        this.msgsJson = msgsJson;
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


    public String sendMsg() throws Exception {
        student = (Student) ActionContext.getContext().getSession().get("student");
        msg.setStuId(student.getStuId());
        msg.setTeaId(teacher.getTeaId());
        msg.setFlag(0);
        msgDAO.sendMsg(msg);
        return firstFlag ? "view" : SUCCESS;
    }

    public String viewMsgPanel() throws Exception {
        student = (Student) ActionContext.getContext().getSession().get("student");
        teachers = msgDAO.getTeacherList(student.getStuId());
        return "success";
    }

    public String getPersonMsgs() throws Exception {
        int tea_id = teacher.getTeaId();
        student = (Student) ActionContext.getContext().getSession().get("student");
        msgs = msgDAO.getMsgs(student.getStuId(),tea_id);
        for (Msg m : msgs) {
            msgsJson.add(m.toList());
        }
        return "success";
    }

    public String getNewMsgNum() throws Exception {
        student = (Student) ActionContext.getContext().getSession().get("student");
        msgNum = msgDAO.getMsgNum(-1,student.getStuId(), 1);
        return "success";
    }

    public String updateReadMsg() throws Exception {
        student = (Student) ActionContext.getContext().getSession().get("student");
        msgDAO.updateReadMsg(student.getStuId(), teacher.getTeaId(), 1);
        return "success";
    }
}
