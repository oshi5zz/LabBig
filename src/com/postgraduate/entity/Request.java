package com.postgraduate.entity;

/**
 * Created by zhao on 2016/11/10.
 */
public class Request {
    private int reqId;
    private int stuId;
    private int teaId;
    private Integer firstMsgId;
    private int status;
    private int flag;
    private String lastDate;
    private Student student;
    private Teacher teacher;

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

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (reqId != request.reqId) return false;
        if (stuId != request.stuId) return false;
        if (teaId != request.teaId) return false;
        if (status != request.status) return false;
        if (flag != request.flag) return false;
        if (firstMsgId != null ? !firstMsgId.equals(request.firstMsgId) : request.firstMsgId != null) return false;
        if (lastDate != null ? !lastDate.equals(request.lastDate) : request.lastDate != null) return false;

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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student studentByStuId) {
        this.student = studentByStuId;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacherByTeaId) {
        this.teacher = teacherByTeaId;
    }
}
