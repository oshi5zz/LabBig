package com.postgraduate.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.postgraduate.dao.StudentDAO;
import com.postgraduate.entity.Msg;
import com.postgraduate.entity.Request;
import com.postgraduate.entity.Student;
import com.postgraduate.entity.Teacher;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhao on 2016/11/5.
 */
public class StudentAction extends ActionSupport {
    private StudentDAO studentDAO = new StudentDAO();
    private Student student = student = (Student) ActionContext.getContext().getSession().get("student");
    private Teacher teacher = new Teacher();
    private List<Teacher> teachers = new ArrayList<>();
    private List<Msg> msgs = new ArrayList<>();
    private List<Request> reqs = new ArrayList<>();
    private Msg msg = new Msg();
    private String warning = new String("出错了！");
    private String stuid = new String();
    private int usedFinalNum = 0;
    private int usedPreNum = 0;

    public String getTeaid() {
        return teaid;
    }

    public void setTeaid(String teaid) {
        this.teaid = teaid;
    }

    private java.lang.String teaid;

    public int getUsedPreNum() {
        return usedPreNum;
    }

    public void setUsedPreNum(int usedPreNUm) {
        this.usedPreNum = usedPreNUm;
    }

    public int getUsedFinalNum() {
        return usedFinalNum;
    }

    public void setUsedFinalNum(int usedFinalNum) {
        this.usedFinalNum = usedFinalNum;
    }

    private static final String WARNING = "warning";

    public Msg getMsg() {
        return msg;
    }

    public void setMsg(Msg msg) {
        this.msg = msg;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public List<Msg> getMsgs() {
        return msgs;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public void setMsgs(List<Msg> msgs) {
        this.msgs = msgs;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Request> getReqs() {
        return reqs;
    }

    public void setReqs(List<Request> reqs) {
        this.reqs = reqs;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public String updateInf() {
        Student tmp = (Student) ActionContext.getContext().getSession().get("student");
        student.setStuId(tmp.getStuId());
        ActionContext.getContext().getSession().put("student", student);
        if((student = studentDAO.updateStudentInf(student))!=null) {
            return SUCCESS;
        }
        else
            return ERROR;
    }

    public String editInf() {
        student = (Student) ActionContext.getContext().getSession().get("student");
        student = studentDAO.getStudentInf(student.getStuId());
        if (student != null) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public String toSearch() {
        student = (Student) ActionContext.getContext().getSession().get("student");
        return SUCCESS;
    }

    public String viewSearchResult() {
        teachers = studentDAO.searchTeachers(teacher);
        if (teachers != null) {
            if (!teachers.isEmpty())
                return SUCCESS;
            else {
                warning = "未找到符合条件的老师";
                return ERROR;
            }
        }
        else {
            warning = "查询错误！";
            return ERROR;
        }
    }

    public String getIndex() {
        student = (Student) ActionContext.getContext().getSession().get("student");
        teachers = studentDAO.getRecommend(student.getStuId());
        return SUCCESS;
    }

    public String viewPreSucList() {
        student = (Student) ActionContext.getContext().getSession().get("student");
        teachers = studentDAO.viewPreSucList(student.getStuId());
        usedPreNum = teachers.size();
        return SUCCESS;
    }

    public String sendPreReq() {
        student = (Student) ActionContext.getContext().getSession().get("student");
        int tea_id;
        try {
            tea_id = Integer.parseInt(stuid);
        } catch (Exception e) {
            return ERROR;
        }
        teacher.setTeaId(tea_id);

        int status = studentDAO.getReqStatus(true, student, teacher);
        switch (status) {
            //未曾建立过联系
            case -3:
                warning = "失败！正式录取名额已满！";
                return WARNING;
            case -2:
                warning = "失败！预录取名额已满！";
                return WARNING;
            case -1:
                if (studentDAO.updateReq(0, student.getStuId(), tea_id)) {
                    warning = "预录取请求已发送！";
                    return WARNING;
                } else
                    return ERROR;
            case 0:
                warning = "已经发送预请求，对方还未审查！";
                return WARNING;
            case 1:
                warning = "对方已阅读您的预请求，请耐心等待回应！";
                return WARNING;
            case 2:
                warning = "你们已经通过了预请求关系！";
                return WARNING;
            case 3:
                warning = "你们曾经建立预请求关系失败，无法再发送请求！";
                return WARNING;
            case 4:
            case 5:
                warning = "你们已经建立了预请求关系，并有了最终关系请求！";
                return WARNING;
            case 6:
                warning = "无法发送！你们已经建立了最终的录取关系！";
                return WARNING;
            case 7:
                warning = "你们最终录取请求未通过，无法再发送请求！";
                return WARNING;
            default:
                warning = "未知状态！";
                return WARNING;
        }
    }

    public String sendFinalReq() {
        student = (Student) ActionContext.getContext().getSession().get("student");
        int tea_id;
        try {
            tea_id = Integer.parseInt(stuid);
        } catch (Exception e) {
            return ERROR;
        }
        int status = studentDAO.getReqStatus(false, student, teacher);
        if (status==-3) {
            warning = "发送失败！正式录取名额已满！";
            return WARNING;
        }
        if (studentDAO.updateReq(4,student.getStuId(), tea_id)) {
            warning = "最终请求已发送！";
            return SUCCESS;
        }
        else
            return ERROR;
    }

    public String viewMsg() {
        student = (Student) ActionContext.getContext().getSession().get("student");
        msgs = studentDAO.getMsgs(student.getStuId());
        return SUCCESS;
    }

    public String sendMsg() {
        student = (Student) ActionContext.getContext().getSession().get("student");
        int tea_id;
        try {
            tea_id = Integer.parseInt(stuid);
        } catch (Exception e) {
            return ERROR;
        }
        msg.setTeaId(tea_id);
        msg.setStuId(student.getStuId());
        if (msg.getMain().length() < 1)
            return INPUT;
        if (studentDAO.sendMsg(msg)) {
            return SUCCESS;
        }
        else
            return INPUT;
    }



    public String viewTeacherDetail() {
        student = (Student) ActionContext.getContext().getSession().get("student");
        try {
            int id = Integer.parseInt(teaid);
            teacher = studentDAO.getTeacher(id);
            return SUCCESS;
        } catch (Exception e) {
            return ERROR;
        }
    }

    public String writeMsg() {
        student = (Student) ActionContext.getContext().getSession().get("student");
        return SUCCESS;
    }

    public String viewPreReqList() {
        student = (Student) ActionContext.getContext().getSession().get("student");
        teachers = studentDAO.viewPreReqList(student.getStuId());
        usedPreNum = teachers.size();
        return SUCCESS;
    }

    public String agreePreReq(){
        student = (Student) ActionContext.getContext().getSession().get("student");
        try {
            int id = Integer.parseInt(teaid);
            if (studentDAO.updateReq(2,student.getStuId(), id)) {
                warning = "已同意预请求";
                return SUCCESS;
            }
            else {
                warning = "未知错误！";
                return ERROR;
            }
        } catch (Exception e) {
            warning = "链接错误，学生id不是数字！";
            return ERROR;
        }
    }

    public String refusePreReq() {
        student = (Student) ActionContext.getContext().getSession().get("student");
        try {
            int id = Integer.parseInt(teaid);
            if (studentDAO.updateReq(3,student.getStuId(), id)) {
                warning = "已拒绝预请求";
                return SUCCESS;
            }
            else {
                warning = "未知错误！";
                return ERROR;
            }
        } catch (Exception e) {
            warning = "链接错误，学生id不是数字！";
            return ERROR;
        }
    }

    public String cancelPreReq() {
        student = (Student) ActionContext.getContext().getSession().get("student");
        try {
            int id = Integer.parseInt(teaid);
            if (studentDAO.updateReq(3,student.getStuId(), id)) {
                warning = "已取消预请求通过关系";
                return SUCCESS;
            }
            else {
                warning = "未知错误！";
                return ERROR;
            }
        } catch (Exception e) {
            warning = "链接错误，学生id不是数字！";
            return ERROR;
        }
    }

    public String viewAllReq() {
        student = (Student) ActionContext.getContext().getSession().get("student");
        reqs = studentDAO.getReqs(student.getStuId());
        if (reqs != null)
            return "success";
        else
            return "error";
    }

    public String viewFinalSucList() {
        student = (Student) ActionContext.getContext().getSession().get("student");
        teachers = studentDAO.viewFinalSucList(student.getStuId());
        usedFinalNum = teachers.size();
        return SUCCESS;
    }

    public String logout() {
        ActionContext.getContext().getSession().clear();
        return "login";
    }


    public String agreeFinalReq() {
        student = (Student) ActionContext.getContext().getSession().get("student");
        int status = studentDAO.getReqStatus(false, student, teacher);
        if (status==-3) {
            warning = "不能同意！正式录取名额已满！";
            return WARNING;
        }
        try {
            int tea_id = Integer.parseInt(teaid);
            if (studentDAO.updateReq(6,student.getStuId(), tea_id)) {
                warning = "已同意最终请求";
                return SUCCESS;
            }
            else {
                warning = "未知错误！";
                return ERROR;
            }
        } catch (Exception e) {
            warning = "链接错误，学生id不是数字！";
            return ERROR;
        }
    }

    public String refuseFinalReq() {
        student = (Student) ActionContext.getContext().getSession().get("student");
        try {
            int id = Integer.parseInt(teaid);
            if (studentDAO.updateReq(7,student.getStuId(), id)) {
                warning = "已拒绝最终请求";
                return SUCCESS;
            }
            else {
                warning = "未知错误！";
                return ERROR;
            }
        } catch (Exception e) {
            warning = "链接错误，学生id不是数字！";
            return ERROR;
        }
    }
}
