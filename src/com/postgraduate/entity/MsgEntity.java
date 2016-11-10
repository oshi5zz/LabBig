package com.postgraduate.entity;

import java.sql.Timestamp;

/**
 * Created by zhao on 2016/11/8.
 */
public class MsgEntity {
    private int msgId;
    private String abs;
    private int stuId;
    private int teaId;
    private String main;
    private Timestamp lastDate;
    private int read;
    private int flag;
    private StudentEntity studentByStuId;
    private TeacherEntity teacherByTeaId;

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

        MsgEntity msgEntity = (MsgEntity) o;

        if (msgId != msgEntity.msgId) return false;
        if (stuId != msgEntity.stuId) return false;
        if (teaId != msgEntity.teaId) return false;
        if (read != msgEntity.read) return false;
        if (flag != msgEntity.flag) return false;
        if (abs != null ? !abs.equals(msgEntity.abs) : msgEntity.abs != null) return false;
        if (main != null ? !main.equals(msgEntity.main) : msgEntity.main != null) return false;
        if (lastDate != null ? !lastDate.equals(msgEntity.lastDate) : msgEntity.lastDate != null) return false;

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

    public StudentEntity getStudentByStuId() {
        return studentByStuId;
    }

    public void setStudentByStuId(StudentEntity studentByStuId) {
        this.studentByStuId = studentByStuId;
    }

    public TeacherEntity getTeacherByTeaId() {
        return teacherByTeaId;
    }

    public void setTeacherByTeaId(TeacherEntity teacherByTeaId) {
        this.teacherByTeaId = teacherByTeaId;
    }
}
