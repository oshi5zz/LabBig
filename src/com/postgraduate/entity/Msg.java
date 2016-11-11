package com.postgraduate.entity;

import java.sql.Timestamp;

/**
 * Created by zhao on 2016/11/10.
 */
public class Msg {
    private int msgId;
    private String abs;
    private int stuId;
    private int teaId;
    private String main;
    private Timestamp lastDate;
    private int read;
    private int flag;
    private Student studentByStuId;
    private Teacher teacherByTeaId;

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public String getAbs() {
        return abs;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public int getTeaId() {
        return teaId;
    }

    public void setTeaId(int teaId) {
        this.teaId = teaId;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public Timestamp getLastDate() {
        return lastDate;
    }

    public void setLastDate(Timestamp lastDate) {
        this.lastDate = lastDate;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Msg msg = (Msg) o;

        if (msgId != msg.msgId) return false;
        if (stuId != msg.stuId) return false;
        if (teaId != msg.teaId) return false;
        if (read != msg.read) return false;
        if (flag != msg.flag) return false;
        if (abs != null ? !abs.equals(msg.abs) : msg.abs != null) return false;
        if (main != null ? !main.equals(msg.main) : msg.main != null) return false;
        if (lastDate != null ? !lastDate.equals(msg.lastDate) : msg.lastDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = msgId;
        result = 31 * result + (abs != null ? abs.hashCode() : 0);
        result = 31 * result + stuId;
        result = 31 * result + teaId;
        result = 31 * result + (main != null ? main.hashCode() : 0);
        result = 31 * result + (lastDate != null ? lastDate.hashCode() : 0);
        result = 31 * result + read;
        result = 31 * result + flag;
        return result;
    }

    public Student getStudentByStuId() {
        return studentByStuId;
    }

    public void setStudentByStuId(Student studentByStuId) {
        this.studentByStuId = studentByStuId;
    }

    public Teacher getTeacherByTeaId() {
        return teacherByTeaId;
    }

    public void setTeacherByTeaId(Teacher teacherByTeaId) {
        this.teacherByTeaId = teacherByTeaId;
    }
}
