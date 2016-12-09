package com.postgraduate.interceptors;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.postgraduate.entity.Student;
import java.util.Map;

/**
 * Created by zhao on 2016/11/14.
 */
public class StudentLoginInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        ActionContext ctx = invocation.getInvocationContext();
        Map<String,Object> session = ctx.getSession();
        Student student = (Student) session.get("student");
        if(student == null)
            return "login";
        else {
            return invocation.invoke();
        }
    }
}
