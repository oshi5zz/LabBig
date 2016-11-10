package com.postgraduate.entity;

import java.util.Collection;

/**
 * Created by zhao on 2016/11/8.
 */
public class TeacherEntity {
    private int teaId;
    private String name;
    private Integer age;
    private String professionalTitle;
    private String province;
    private String school;
    private String major;
    private String researchArea;
    private String inf;
    private String mail;
    private String sex;
    private int preNum;
    private int finalNum;
    private String requirement;
    private Collection<MsgEntity> msgsByTeaId;
    private Collection<RequestEntity> requestsByTeaId;

    public int getTeaId() {
        return teaId;
    }

    public void setTeaId(int teaId) {
        this.teaId = teaId;
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

    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
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

    public String getResearchArea() {
        return researchArea;
    }

    public void setResearchArea(String researchArea) {
        this.researchArea = researchArea;
    }

    public String getInf() {
        return inf;
    }

    public void setInf(String inf) {
        this.inf = inf;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getPreNum() {
        return preNum;
    }

    public void setPreNum(int preNum) {
        this.preNum = preNum;
    }

    public int getFinalNum() {
        return finalNum;
    }

    public void setFinalNum(int finalNum) {
        this.finalNum = finalNum;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeacherEntity that = (TeacherEntity) o;

        if (teaId != that.teaId) return false;
        if (preNum != that.preNum) return false;
        if (finalNum != that.finalNum) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (professionalTitle != null ? !professionalTitle.equals(that.professionalTitle) : that.professionalTitle != null)
            return false;
        if (province != null ? !province.equals(that.province) : that.province != null) return false;
        if (school != null ? !school.equals(that.school) : that.school != null) return false;
        if (major != null ? !major.equals(that.major) : that.major != null) return false;
        if (researchArea != null ? !researchArea.equals(that.researchArea) : that.researchArea != null) return false;
        if (inf != null ? !inf.equals(that.inf) : that.inf != null) return false;
        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (requirement != null ? !requirement.equals(that.requirement) : that.requirement != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teaId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (professionalTitle != null ? professionalTitle.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (school != null ? school.hashCode() : 0);
        result = 31 * result + (major != null ? major.hashCode() : 0);
        result = 31 * result + (researchArea != null ? researchArea.hashCode() : 0);
        result = 31 * result + (inf != null ? inf.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + preNum;
        result = 31 * result + finalNum;
        result = 31 * result + (requirement != null ? requirement.hashCode() : 0);
        return result;
    }

    public Collection<MsgEntity> getMsgsByTeaId() {
        return msgsByTeaId;
    }

    public void setMsgsByTeaId(Collection<MsgEntity> msgsByTeaId) {
        this.msgsByTeaId = msgsByTeaId;
    }

    public Collection<RequestEntity> getRequestsByTeaId() {
        return requestsByTeaId;
    }

    public void setRequestsByTeaId(Collection<RequestEntity> requestsByTeaId) {
        this.requestsByTeaId = requestsByTeaId;
    }
}
