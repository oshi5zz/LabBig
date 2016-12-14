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
    private List<List<String>> msgsJson = new ArrayList<>();
    private Msg msg = new Msg();
    private boolean firstFlag = false;
    private int newMsgNum = 0;
    private int perMsgNum = 0;

    public int getPerMsgNum() {
        return perMsgNum;
    }

    public void setPerMsgNum(int perMsgNum) {
        this.perMsgNum = perMsgNum;
    }

    public int getNewMsgNum() {
        return newMsgNum;
    }

    public void setNewMsgNum(int newMsgNum) {
        this.newMsgNum = newMsgNum;
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

    public String sendMsgToStudent() {
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        sendMsg(true);
        return firstFlag ? "view" : SUCCESS;
    }

    private String sendMsg(boolean toStudent) {
        msg.setStuId(student.getStuId());
        msg.setTeaId(teacher.getTeaId());
        msg.setFlag(toStudent ? 1 : 0);
        if (msgDAO.sendMsg(msg))
            return SUCCESS;
        else
            return ERROR;
    }

    public String getTeacherMsgNum() {
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        newMsgNum = msgDAO.getMsgNum(teacher.getTeaId(), -1, 0);
        return "success";
    }

    public String getTeacherNewMsgListNum() {
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        perMsgNum = msgDAO.getMsgNum(teacher.getTeaId(), student.getStuId(), 0);
        return "success";
    }

/*    public String getStudentNewMsgListNum() {
        student = (Student) ActionContext.getContext().getSession().get("student");
        perMsgNum = msgDAO.getMsgNum(teacher.getTeaId(), student.getStuId(), 1);
        return "success";
    }*/

    public String sendMsgToTeacher() throws Exception {
        student = (Student) ActionContext.getContext().getSession().get("student");
        String res = sendMsg(true);
        return firstFlag ? "view" : SUCCESS;
    }

    public String getTeacherList() throws Exception {
        student = (Student) ActionContext.getContext().getSession().get("student");
        teachers = msgDAO.getTeacherList(student.getStuId());
        return "success";
    }

/*    public String getTeacherMsg() throws Exception {
        int tea_id = teacher.getTeaId();
        student = (Student) ActionContext.getContext().getSession().get("student");
        msgs = msgDAO.getStudentMsgs(tea_id,student.getStuId());
        for (Msg m : msgs) {
            msgsJson.add(m.toList());
        }
        return "success";
    }*/

    public String getStudentMsgNum() throws Exception {
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        newMsgNum = msgDAO.getMsgNum(-1,student.getStuId(), 1);
        return "success";
    }

    public String updateTeacherReadMsg() throws Exception {
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        msgDAO.updateReadMsg(student.getStuId(), teacher.getTeaId(), 0);
        return "success";
    }

    public String updateStudentReadMsg() throws Exception {
        return "success";
    }

    public String getTeacherMsg() throws Exception {
        return "success";
    }
}
