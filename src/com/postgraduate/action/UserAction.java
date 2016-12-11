package com.postgraduate.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.postgraduate.dao.UserDAO;
import com.postgraduate.entity.Student;
import com.postgraduate.entity.Teacher;
import com.postgraduate.entity.User;
import org.apache.struts2.components.Radio;

/**
 * Created by zhao on 2016/11/14.
 */
public class UserAction extends ActionSupport {
    private User user = new User();
    private String type = new String();
    private String data = new String();

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private String passtmp = new String();

    public String getPasstmp() {
        return passtmp;
    }

    public void setPasstmp(String passtmp) {
        this.passtmp = passtmp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private UserDAO userDAO = new UserDAO();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /*public String login() {
        int userId;
        try {
            userId = Integer.parseInt(user.getUserId());
            if (type.equals("teacher")) {
                Teacher teacher = userDAO.loginTeacher(userId, user.getPassword());
                if(teacher==null){
                    data = "login";
                    return SUCCESS;
                }
                ActionContext.getContext().getSession().put("teacher", teacher);
                data = "teacher";
            } else if (type.equals("student")) {
                Student student = userDAO.loginStudent(userId,user.getPassword());
                if(student==null){
                    data = "login";
                    return SUCCESS;
                }
                ActionContext.getContext().getSession().put("student", student);
                data = "student";
            } else
                data = "login";
        } catch (Exception e) {
            data =  "login";
        }
        return SUCCESS;
    }*/

    public String login() {
        int userId;
        try {
            userId = Integer.parseInt(user.getUserId());
            if (type.equals("teacher")) {
                Teacher teacher = userDAO.loginTeacher(userId, user.getPassword());
                if(teacher==null){
                    return "login";
                }
                ActionContext.getContext().getSession().put("teacher", teacher);
                return  "teacher";
            } else if (type.equals("student")) {
                Student student = userDAO.loginStudent(userId,user.getPassword());
                if(student==null){
                    return "login";
                }
                ActionContext.getContext().getSession().put("student", student);
                return  "student";
            } else
               return  "login";
        } catch (Exception e) {
            return "login";
        }
    }

    public String logup() {
        int userId;
        try {
            userId = Integer.parseInt(user.getUserId());
        } catch (Exception e) {
            return "input";
        }

        if(type.equals("teacher")) {
            user.setType(1);
            if(userDAO.logupTeacher(user)) {
                return SUCCESS;
            }
            else
                return "input";
        } else {
            user.setType(0);
            if(userDAO.logupStudent(user))
                return SUCCESS;
            else return INPUT;
        }

    }

    public String validateId() throws Exception {
        if(userDAO.validateId(user.getUserId())) {
            data = "0";
        } else {
            data = "1";
        }
        return "success";
    }

    public String getQuestion() throws Exception {
        data = userDAO.getQuestion(user.getUserId());
        return SUCCESS;
    }

    public String validateAnswer() throws Exception {
        data = userDAO.validateAnswer(user);
        return "success";
    }

    public String resetPass() throws Exception {
        data = userDAO.resetPass(user);
        return "success";
    }
}
