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

    public String login() {
        int userId;
        try {
            userId = Integer.parseInt(user.getUserId());
        } catch (Exception e) {
            return "login";
        }
        if (type.equals("teacher")) {
            Teacher teacher = userDAO.loginTeacher(userId, user.getPassword());
            ActionContext.getContext().getSession().put("teacher", teacher);
            return "teacher";
        } else if (type.equals("student")) {
            Student student = userDAO.loginStudent(userId,user.getPassword());
            ActionContext.getContext().getSession().put("student", student);
            return "student";
        } else
            return "login";
    }

}
