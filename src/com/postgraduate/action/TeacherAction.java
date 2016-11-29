package com.postgraduate.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.postgraduate.dao.TeacherDAO;
import com.postgraduate.entity.Msg;
import com.postgraduate.entity.Request;
import com.postgraduate.entity.Student;
import com.postgraduate.entity.Teacher;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhao on 2016/11/5.
 */
public class TeacherAction extends ActionSupport {
    private TeacherDAO teacherDAO = new TeacherDAO();
    private String status = new String();
    //
    private Teacher teacher = teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
    private Student student = new Student();
    private List<Student> students = new ArrayList<>();
    private List<Msg> msgs = new ArrayList<>();
    private List<Request> reqs = new ArrayList<>();
    private Msg msg = new Msg();
    private String warning = new String("出错了！");
    private String stuid = new String();

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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Request> getReqs() {
        return reqs;
    }

    public void setReqs(List<Request> reqs) {
        this.reqs = reqs;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String updateInf() {
        Teacher tmp = (Teacher) ActionContext.getContext().getSession().get("teacher");
        teacher.setTeaId(tmp.getTeaId());
        ActionContext.getContext().getSession().put("teacher", teacher);
        if(teacherDAO.updateTeachInf(teacher))
            return SUCCESS;
        else
            return ERROR;
    }

    public String editInf() {
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        teacher = teacherDAO.getTeacherInf(teacher.getTeaId());
        if (teacher != null) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public String toSearch() {
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        return SUCCESS;
    }

    public String viewSearchResult() {
        students = teacherDAO.searchStudents(student);
        if (students != null) {
            if (!students.isEmpty())
                return SUCCESS;
            else {
                warning = "未找到符合条件的学生";
                return ERROR;
            }
        }
        else {
            warning = "查询错误！";
            return ERROR;
        }
    }

    public String getIndex() {
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        return SUCCESS;
    }

    public String viewPreSucList() {
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        students = teacherDAO.viewPreSucList(teacher.getTeaId());
        return SUCCESS;
    }

    public String sendPreReq() {
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        int stu_id;
        try {
            stu_id = Integer.parseInt(stuid);
        } catch (Exception e) {
            return ERROR;
        }

        int status = teacherDAO.getReqStatus(teacher.getTeaId(), stu_id);
        switch (status) {
            //未曾建立过联系
            case -1:
                if (teacherDAO.sendReq(true, teacher.getTeaId(), stu_id)) {
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
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        int stu_id;
        try {
            stu_id = Integer.parseInt(stuid);
        } catch (Exception e) {
            return ERROR;
        }

        if (teacherDAO.sendReq(false,teacher.getTeaId(), stu_id)) {
            warning = "最终请求已发送！";
            return SUCCESS;
        }
        else
            return ERROR;
    }

    public String viewMsg() {
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        msgs = teacherDAO.getMsgs(teacher.getTeaId());

        return SUCCESS;
    }

    public String sendMsg() {
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        int stu_id;
        try {
            stu_id = Integer.parseInt(stuid);
        } catch (Exception e) {
            return ERROR;
        }
        msg.setStuId(stu_id);
        msg.setTeaId(teacher.getTeaId());
        if (msg.getMain().length() < 1)
            return INPUT;
        if (teacherDAO.sendMsg(msg)) {
            return SUCCESS;
        }
        else
            return INPUT;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String viewStudentDetail() {
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        try {
            int id = Integer.parseInt(stuid);
            student = teacherDAO.getStudent(id);
            return SUCCESS;
        } catch (Exception e) {
            return ERROR;
        }
    }

    public String writeMsg() {
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        return SUCCESS;
    }

    public String viewPreReqList() {
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        students = teacherDAO.viewPreReqList(teacher.getTeaId());
        return SUCCESS;
    }

    public String agreePreReq(){
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        try {
            int id = Integer.parseInt(stuid);
            if (teacherDAO.solveReq(true,true,teacher.getTeaId(), id)) {
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
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        try {
            int id = Integer.parseInt(stuid);
            if (teacherDAO.solveReq(false,true,teacher.getTeaId(), id)) {
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
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        try {
            int id = Integer.parseInt(stuid);
            if (teacherDAO.cancelPreReq(teacher.getTeaId(), id)) {
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
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        reqs = teacherDAO.getReqs(teacher.getTeaId());
        if (reqs != null)
            return "success";
        else
            return "error";
    }

    public String viewFinalSucList() {
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        students = teacherDAO.viewFinalSucList(teacher.getTeaId());
        return SUCCESS;
    }

    public String logout() {
        ActionContext.getContext().getSession().clear();
        return "login";
    }
}
