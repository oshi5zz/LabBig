package com.postgraduate.entity;

import java.util.Collection;

/**
 * Created by zhao on 2016/11/8.
 */
public class StudentEntity {
    private int stuId;
    private String name;
    private Integer age;
    private String sex;
    private String mail;
    private String province;
    private String school;
    private String major;
    private String inf;
    private String researchArea;
    private int preNum;
    private int finalTeacherId;
    private String interest;
    private Collection<MsgEntity> msgsByStuId;
    private Collection<RequestEntity> requestsByStuId;

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getInf() {
        return inf;
    }

    public void setInf(String inf) {
        this.inf = inf;
    }

    public String getResearchArea() {
        return researchArea;
    }

    public void setResearchArea(String researchArea) {
        this.researchArea = researchArea;
    }

    public int getPreNum() {
        return preNum;
    }

    public void setPreNum(int preNum) {
        this.preNum = preNum;
    }

    public int getFinalTeacherId() {
        return finalTeacherId;
    }

    public void setFinalTeacherId(int finalTeacherId) {
        this.finalTeacherId = finalTeacherId;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentEntity that = (StudentEntity) o;

        if (stuId != that.stuId) return false;
        if (preNum != that.preNum) return false;
        if (finalTeacherId != that.finalTeacherId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;
        if (province != null ? !province.equals(that.province) : that.province != null) return false;
        if (school != null ? !school.equals(that.school) : that.school != null) return false;
        if (major != null ? !major.equals(that.major) : that.major != null) return false;
        if (inf != null ? !inf.equals(that.inf) : that.inf != null) return false;
        if (researchArea != null ? !researchArea.equals(that.researchArea) : that.researchArea != null) return false;
        if (interest != null ? !interest.equals(that.interest) : that.interest != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stuId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (school != null ? school.hashCode() : 0);
        result = 31 * result + (major != null ? major.hashCode() : 0);
        result = 31 * result + (inf != null ? inf.hashCode() : 0);
        result = 31 * result + (researchArea != null ? researchArea.hashCode() : 0);
        result = 31 * result + preNum;
        result = 31 * result + finalTeacherId;
        result = 31 * result + (interest != null ? interest.hashCode() : 0);
        return result;
    }

    public Collection<MsgEntity> getMsgsByStuId() {
        return msgsByStuId;
    }

    public void setMsgsByStuId(Collection<MsgEntity> msgsByStuId) {
        this.msgsByStuId = msgsByStuId;
    }

    public Collection<RequestEntity> getRequestsByStuId() {
        return requestsByStuId;
    }

    public void setRequestsByStuId(Collection<RequestEntity> requestsByStuId) {
        this.requestsByStuId = requestsByStuId;
    }
}
