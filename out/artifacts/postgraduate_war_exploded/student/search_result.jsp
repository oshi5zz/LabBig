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
    <title>搜索老师列表</title>
    <link rel="stylesheet" href="../res/frame_a.css" type="text/css" media="all">
    <link rel="stylesheet" id="dt-main-css" href="../res/main.css" type="text/css" media="all">
    <link rel="stylesheet" href="../res/frame_b.css" type="text/css" media="all">
    <meta name="description" content="考研师生互选">

    <%@include file="/bootstrap.jsp"%>

</head>


<body class="home page page-id-6209 page-template-default content-fullwidth image-blur mini-boxed-layout fix-width btn-flat wpb-js-composer js-comp-ver-4.0.1 vc_responsive no-mobile is-webkit"
      data-pid="6209" data-pkey="a12ac14c0e7e681c1c26c894d0cde02f">

<div id="page" class="contentFixWidth fix-width">

    <%@include file="student_header.jsp" %>

    <div id="main"  style="margin-left: 5%;margin-right: 5%">
        <table class="table table-striped">
            <thead>

                <tr>
                    <th>姓名</th>
                    <th>学校</th>
                    <th>详情</th>
                    <th>发消息</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>

            <s:iterator value="teachers">
                <tr>
                    <td><span><s:property value="name" /></span></td>

                    <td><span>(<s:property value="school" />)</span></td>

                    <td>
                        <a href="/student/viewTeacherDetail.action?teaid=<s:property value="teaId" />">&nbsp;&nbsp;<span class="glyphicon glyphicon-eye-open"></a>
                    </td>

                    <td>
                        <a href="/student/writeMsg.action?teaid=<s:property value="teaId" />">&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-comment"></span></a>
                    </td>

                    <td><span>
                        <a href="/student/sendPreReq.action?teaid=<s:property value="teaId" />">发送预请求</a>
                    </span></td>
                </tr>
            </s:iterator>
            </tbody>

        </table>


<%--<s:debug></s:debug>--%>
    </div><!-- #main -->

    <%@include file="../footer.jsp"%>
</div>

</body>

</html>