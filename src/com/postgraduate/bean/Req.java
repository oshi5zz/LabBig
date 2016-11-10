package com.postgraduate.bean;

import java.util.Date;

/**
 * Created by zhao on 2016/11/7.
 */
public class Req {
    private String req_id;
    private String stu_name;
    private String tea_name;
    private ReqStat status;
    private String flag;
    private String last_date;

    public String getReq_id() {
        return req_id;
    }

    public void setReq_id(String req_id) {
        this.req_id = req_id;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getTea_name() {
        return tea_name;
    }

    public void setTea_name(String tea_name) {
        this.tea_name = tea_name;
    }

    public ReqStat getStatus() {
        return status;
    }

    public void setStatus(ReqStat status) {
        this.status = status;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getLast_date() {
        return last_date;
    }

    public void setLast_date(String last_date) {
        this.last_date = last_date;
    }
}
