<%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2016/12/4
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<div class="panel panel-default " style="width:80%; margin-left:10%;margin-top:20px;">
    <div class="panel panel-heading " align="center" style="margin-bottom: 0px">
        <h3 class="panel-title" id="panel-title"><span >

        </span></h3>
    </div>
    <div class="panel panel-body">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>姓名</th>
                <th>学校</th>
                <th>标签</th>
                <th>更新日期</th>
            </tr>
            </thead>

            <tbody>

            <s:iterator value="reqs">
                <tr class="req-list"  status="<s:property value="status" />" flag="<s:property value="flag" />">
                    <td><span><s:property value="student.name"/></span></td>

                    <td><span><s:property value="student.school" /></span></td>

                    <td><span>
                        <s:if test="flag==1">OUT</s:if>
                        <s:else>IN</s:else>
                        </span></td>
                    <td><span><s:property value="lastDate" /></span></td>
                    <s:if test="status==0 && flag==0">
                        <td><span><a href="/teacher/agreePreReq.action?stuid=<s:property value="student.stuId" />" >同意</a></span></td>
                        <td><span><a href="/teacher/refusePreReq.action?stuid=<s:property value="student.stuId" />" >拒绝</a></span></td>
                    </s:if>
                    <s:elseif test="status==2">
                        <td><span><a href="/teacher/refusePreReq.action?stuid=<s:property value="student.stuId" />" >解除关系</a></span></td>
                        <td><span><a href="/teacher/sendFinalReq.action?stuid=<s:property value="student.stuId" />" >发送最终请求</a></span></td>
                    </s:elseif>
                    <s:elseif test="status==4 && flag==0">
                        <td><span><a href="/teacher/agreeFinalReq.action?stuid=<s:property value="student.stuId" />" >同意</a></span></td>
                        <td><span><a href="/teacher/refuseFinalReq.action?stuid=<s:property value="student.stuId" />" >拒绝</a></span></td>
                    </s:elseif>
                </tr>
            </s:iterator>
            </tbody>
        </table>
    </div>
</div>

