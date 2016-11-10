package com.postgraduate.entity;

import java.sql.Timestamp;

/**
 * Created by zhao on 2016/11/8.
 */
public class RequestEntity {
    private int reqId;
    private int stuId;
    private int teaId;
    private Integer firstMsgId;
    private int status;
    private int flag;
    private Timestamp lastDate;
    private StudentEntity studentByStuId;
    private TeacherEntity teacherByTeaId;

    public int getReqId() {
        return reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
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

    public Integer getFirstMsgId() {
        return firstMsgId;
    }

    public void setFirstMsgId(Integer firstMsgId) {
        this.firstMsgId = firstMsgId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Timestamp getLastDate() {
        return lastDate;
    }

    public void setLastDate(Timestamp lastDate) {
        this.lastDate = lastDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestEntity that = (RequestEntity) o;

        if (reqId != that.reqId) return false;
        if (stuId != that.stuId) return false;
        if (teaId != that.teaId) return false;
        if (status != that.status) return false;
        if (flag != that.flag) return false;
        if (firstMsgId != null ? !firstMsgId.equals(that.firstMsgId) : that.firstMsgId != null) return false;
        if (lastDate != null ? !lastDate.equals(that.lastDate) : that.lastDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reqId;
        result = 31 * result + stuId;
        result = 31 * result + teaId;
        result = 31 * result + (firstMsgId != null ? firstMsgId.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + flag;
        result = 31 * result + (lastDate != null ? lastDate.hashCode() : 0);
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
