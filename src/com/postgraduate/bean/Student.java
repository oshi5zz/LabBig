package com.postgraduate.bean;

/**
 * Created by zhao on 2016/11/5.
 */
public class Student {
    private String id;
    private String name;
    private String age;
    private String province;
    private String school;
    private String major;
    private String research_area;
    private String inf;
    private String mail;
    private String sex;

    public String getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    public String getInf() {
        return inf;
    }

    public String getMajor() {
        return major;
    }

    public String getName() {
        return name;
    }

    public String getProvince() {
        return province;
    }

    public String getResearch_area() {
        return research_area;
    }

    public String getMail() {
        return mail;
    }

    public String getSchool() {
        return school;
    }

    public String getSex() {
        return sex;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setInf(String inf) {
        this.inf = inf;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setResearch_area(String research_area) {
        this.research_area = research_area;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Student(String id, String name,
                   String age, String professional_title,
                   String province, String school, String major,
                   String research_area, String inf, String mail,
                   String sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.province = province;
        this.school = school;
        this.major = major;
        this.research_area = research_area;
        this.inf = inf;
        this.mail = mail;
        this.sex = sex;
    }

    public Student(){}
}
