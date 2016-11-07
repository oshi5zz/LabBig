package com.postgraduate.action;

import com.opensymphony.xwork2.ActionSupport;
import com.postgraduate.bean.Teacher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhao on 2016/11/5.
 */
public class TeacherAction extends ActionSupport{

    //
    private Teacher teacher = new Teacher();

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    //
    private List<String> msg = new ArrayList<>();

    public List<String> getMsg() {
        return msg;
    }

    public void setMsg(List<String> msg) {
        this.msg = msg;
    }

    public String updateInf() {
//        teacher.setName("zzy");
        return SUCCESS;
    }

    public String editInf() {
        return SUCCESS;
    }


    public String searchStudent() {
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
        return SUCCESS;
    }

    public String sendMsg() {
        return SUCCESS;
    }
}
