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
                    <td><span><s:property value="teacher.name"/></span></td>

                    <td><span><s:property value="teacher.school" /></span></td>

                    <td><span>
                        <s:if test="flag==0">OUT</s:if>
                        <s:else>IN</s:else>
                        </span></td>
                    <td><span><s:property value="lastDate" /></span></td>
                    <s:if test="status==0 && flag==1">
                        <td><span><a href="/student/agreePreReq.action?teaid=<s:property value="teacher.teaId" />" >同意</a></span></td>
                        <td><span><a href="/student/refusePreReq.action?teaid=<s:property value="teacher.teaId" />" >拒绝</a></span></td>
                    </s:if>
                    <s:elseif test="status==2">
                        <td><span><a href="/student/refusePreReq.action?teaid=<s:property value="teacher.teaId" />" >解除关系</a></span></td>
                        <td><span><a href="/student/sendFinalReq.action?teaid=<s:property value="teacher.teaId" />" >发送最终请求</a></span></td>
                    </s:elseif>
                    <s:elseif test="status==4 && flag==1">
                        <td><span><a href="/student/agreeFinalReq.action?teaid=<s:property value="teacher.teaId" />" >同意</a></span></td>
                        <td><span><a href="/student/refuseFinalReq.action?teaid=<s:property value="teacher.teaId" />" >拒绝</a></span></td>
                    </s:elseif>
                </tr>
            </s:iterator>
            </tbody>
        </table>
    </div>
</div>

