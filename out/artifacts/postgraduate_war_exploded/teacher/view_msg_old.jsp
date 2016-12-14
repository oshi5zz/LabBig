<%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2016/11/5
  Time: 0:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="zh-CN"><!--<![endif]-->
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>考研师生互选系统</title>
    <link rel="stylesheet" href="../res/frame_a.css" type="text/css" media="all">
    <link rel="stylesheet" id="dt-main-css" href="../res/main.css" type="text/css" media="all">
    <link rel="stylesheet" href="../res/frame_b.css" type="text/css" media="all">
    <meta name="description" content="考研师生互选">

    <%@include file="/bootstrap.jsp" %>

</head>


<body class="home page page-id-6209 page-template-default content-fullwidth image-blur mini-boxed-layout fix-width btn-flat wpb-js-composer js-comp-ver-4.0.1 vc_responsive no-mobile is-webkit"
      data-pid="6209" data-pkey="a12ac14c0e7e681c1c26c894d0cde02f">

<div id="page" class="contentFixWidth fix-width">

    <%@include file="teacher_header.jsp" %>

    <div id="main" style="margin-left: 5%;margin-right: 5%">
        <div class="panel panel-default " style="width:80%; margin-left:10%;margin-top:20px;">
            <div class="panel panel-heading " align="center" style="margin-bottom: 0px;">
                <h3 class="panel-title"><span>消息列表</span></h3>
            </div>
            <div class="panel panel-body">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>来自</th>
                        <th>学校</th>
                        <th>摘要</th>
                        <th>更新日期</th>
                        <th>查看</th>
                        <th>状态</th>
                        <th>回复</th>
                    </tr>
                    </thead>

                    <tbody>
                    <s:iterator value="msgs">
                        <tr>
                            <td>
                                <span><s:property value="student.name"/></span>
                            </td>
                            <td><span><s:property value="student.school"/></span></td>
                            <td>
                                <span><s:property value="abs"/> </span>
                            </td>
                            <td>
                                <span><s:property value="lastDate"/> </span>
                            </td>
                            <td>
                                <span><a
                                        href="/teacher/viewMsgDetail.action?msg.msgId=<s:property value="msgId" />">查看</a> </span>
                            </td>


                            <td>
                                <span><s:property value="flag"/> </span>
                            </td>
                            <td>
                                <span><a
                                        href="/teacher/writeMsg.action?stuid=<s:property value="student.stuId" />">回复</a> </span>
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>

        </div>

    </div><!-- #main -->

    <%@include file="../footer.jsp" %>
</div>

</body>

</html>