package com.postgraduate.interceptors;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.postgraduate.entity.Msg;
import com.postgraduate.entity.Request;
import com.postgraduate.entity.Student;
import com.postgraduate.entity.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhao on 2016/11/14.
 */
public class TeacherLoginInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        ActionContext ctx = invocation.getInvocationContext();
        Map<String,Object> session = ctx.getSession();
        Teacher teacher = (Teacher) session.get("teacher");
        if(teacher == null)
            return "login";
        else {
            return invocation.invoke();
        }
    }
}
