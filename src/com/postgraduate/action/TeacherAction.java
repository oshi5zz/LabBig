package com.postgraduate.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.postgraduate.dao.ReqDAO;
import com.postgraduate.dao.TeacherDAO;
import com.postgraduate.entity.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhao on 2016/11/5.
 */
public class TeacherAction extends ActionSupport {
    private TeacherDAO teacherDAO = new TeacherDAO();
    private ReqDAO reqDAO = new ReqDAO();
    //
    private Teacher teacher = teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
    private Student student = new Student();
    private List<Student> students = new ArrayList<>();
    private List<Msg> msgs = new ArrayList<>();
    private List<Request> reqs = new ArrayList<>();
    private Msg msg = new Msg();
    private String warning = new String("出错了！");
    private String stuid = new String();
    private int usedFinalNum = 0;
    private int usedPreNum = 0;

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
        if((teacher = teacherDAO.updateTeachInf(teacher))!=null) {
            return SUCCESS;
        }
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
        students = teacherDAO.getRecommend(teacher.getTeaId());
        return SUCCESS;
    }

    public String viewPreSucList() {
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        students = teacherDAO.viewPreSucList(teacher.getTeaId());
        usedPreNum = students.size();
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
        student.setStuId(stu_id);

        int status = reqDAO.getReqStatus(true, teacher, student);
        if (status==-1) {
            if (teacherDAO.updateReq(0, teacher.getTeaId(), stu_id)) {
                warning = "预录取请求已发送！";
                return WARNING;
            } else
                return ERROR;
        } else {
            warning = ReqStatus.status[ReqStatus.BASE+status];
            return WARNING;
        }
        /*switch (status) {
            //未曾建立过联系
            case -6:
                warning = "未知错误！";
                return WARNING;
            case -5:
                warning = "失败！学生已经被正式录取！";
                return WARNING;
            case -4:
                warning = "失败！学生预录取名额已满！";
                return WARNING;
            case -3:
                warning = "失败！导师正式录取名额已满！";
                return WARNING;
            case -2:
                warning = "失败！导师预录取名额已满！";
                return WARNING;
            case -1:
                if (teacherDAO.updateReq(0, teacher.getTeaId(), stu_id)) {
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
        }*/
    }

    public String sendFinalReq() {
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        System.out.println(teacher.getFinalNum());
        int stu_id;
        try {
            stu_id = Integer.parseInt(stuid);
        } catch (Exception e) {
            return ERROR;
        }
        int status = teacherDAO.getReqStatus(false, teacher, student);
        if (status==-3) {
            warning = "发送失败！正式录取名额已满！";
            return WARNING;
        }
        if (teacherDAO.updateReq(4,teacher.getTeaId(), stu_id)) {
            warning = "最终请求已发送！";
            return SUCCESS;
        }
        else
            return ERROR;
    }

    public String viewMsg() {
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        msgs = teacherDAO.getMsgs(teacher.getTeaId());
//        students = msgDao.getStudentList(teacher.getTeaId());
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
        usedPreNum = students.size();
        return SUCCESS;
    }

    public String agreePreReq(){
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        try {
            int id = Integer.parseInt(stuid);
            if (teacherDAO.updateReq(2,teacher.getTeaId(), id)) {
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
            if (teacherDAO.updateReq(3,teacher.getTeaId(), id)) {
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
            if (teacherDAO.updateReq(3,teacher.getTeaId(), id)) {
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
        usedFinalNum = students.size();
        return SUCCESS;
    }

    public String logout() {
        ActionContext.getContext().getSession().clear();
        return "login";
    }

    public String viewMsgDetail() {
        msg = teacherDAO.getMsgDetail(msg.getMsgId());
        stuid = ""+msg.getStuId();
        if (msg != null) {
            return SUCCESS;
        } else
            return ERROR;
    }

    public String agreeFinalReq() {
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        int status = teacherDAO.getReqStatus(false, teacher, student);
        if (status==-3) {
            warning = "不能同意！正式录取名额已满！";
            return WARNING;
        }
        try {
            int id = Integer.parseInt(stuid);
            if (teacherDAO.updateReq(6,teacher.getTeaId(), id)) {
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
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        try {
            int id = Integer.parseInt(stuid);
            if (teacherDAO.updateReq(7,teacher.getTeaId(), id)) {
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

    public String validatePreNum() throws Exception {
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        usedPreNum = teacherDAO.getUsedNum(teacher.getTeaId(),2);
        return "success";
    }

    public String validateFinalNum() throws Exception {
        teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        usedFinalNum = teacherDAO.getUsedNum(teacher.getTeaId(),6);
        return "success";
    }
}
