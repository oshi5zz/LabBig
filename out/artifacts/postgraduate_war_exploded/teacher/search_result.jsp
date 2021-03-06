<%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2016/11/5
  Time: 0:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html lang="zh-CN"><!--<![endif]-->
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>搜索学生列表</title>
    <link rel="stylesheet" href="../res/frame_a.css" type="text/css" media="all">
    <link rel="stylesheet" id="dt-main-css" href="../res/main.css" type="text/css" media="all">
    <link rel="stylesheet" href="../res/frame_b.css" type="text/css" media="all">
    <meta name="description" content="考研师生互选">

    <%@include file="/bootstrap.jsp"%>

</head>


<body class="home page page-id-6209 page-template-default content-fullwidth image-blur mini-boxed-layout fix-width btn-flat wpb-js-composer js-comp-ver-4.0.1 vc_responsive no-mobile is-webkit"
      data-pid="6209" data-pkey="a12ac14c0e7e681c1c26c894d0cde02f">

<div id="page" class="contentFixWidth fix-width">

    <%@include file="teacher_header.jsp" %>

    <div id="main"  style="margin-left: 5%;margin-right: 5%">
        <table class="table table-striped">
            <thead>

                <tr>
                    <td>姓名</td>
                    <td>学校</td>
                    <td>详情</td>
                    <td>发送预请求</td>
                    <td>发送消息</td>
                </tr>
            </thead>
            <tbody>

            <s:iterator value="students">
                <tr>
                    <td>
                        <span><s:property value="name" /></span>
                    </td>
                    <td>
                        <span>(<s:property value="school" />)</span>
                    </td>

                    <td>
                        <span><a href="/teacher/viewStudentDetail.action?stuid=<s:property value="stuId" />">查看详情</a></span>
                    </td>

                    <td>
                        <span><a href="/teacher/writeMsg.action?stuid=<s:property value="stuId" />">发送消息</a></span>
                    </td>

                    <td>
                        <span><a href="/teacher/sendPreReq.action?stuid=<s:property value="stuId" />">发送预请求</a></span>
                    </td>
                </tr>
                <br />
            </s:iterator>
            </tbody>

        </table>


<%--<s:debug></s:debug>--%>
    </div><!-- #main -->

    <%@include file="../footer.jsp"%>
</div>

</body>

</html>