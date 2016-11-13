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
    <title>学生详情</title>
    <link rel="stylesheet" href="../res/frame_a.css" type="text/css" media="all">
    <link rel="stylesheet" id="dt-main-css" href="../res/main.css" type="text/css" media="all">
    <link rel="stylesheet" href="../res/frame_b.css" type="text/css" media="all">
    <meta name="description" content="考研师生互选">
</head>


<body class="home page page-id-6209 page-template-default content-fullwidth image-blur mini-boxed-layout fix-width btn-flat wpb-js-composer js-comp-ver-4.0.1 vc_responsive no-mobile is-webkit"
      data-pid="6209" data-pkey="a12ac14c0e7e681c1c26c894d0cde02f">

<div id="page" class="contentFixWidth fix-width">

    <%@include file="teacher_header.jsp" %>

    <div id="main" class="bit_main_content">
        <hr />
        <div class="student-inf">
            <form class="student-inf-form" >
                <span>姓名：</span>
                <input name="student.name" value="<s:property value="student.name" /> " />
                <br />

                <span>性别：</span>
                <input name="student.sex" value="<s:property value="student.sex" /> " />
                <br />

                <span>年龄：</span>
                <input name="student.age" value="<s:property value="student.age" /> " />
                <br />

                <span>省份：</span>
                <input name="student.province" value="<s:property value="student.province" /> " />
                <br />

                <span>学校：</span>
                <input name="student.school" value="<s:property value="student.school" /> " />
                <br />

                <span>专业：</span>
                <input name="student.major" value="<s:property value="student.major" /> " />
                <br />

                <span>研究方向：</span>
                <input type="text" name="student.researchArea" value="<s:property value="student.researchArea" /> " />
                <br />

                <span>邮箱：</span>
                <input name="student.mail" value="<s:property value="student.mail" /> " />
                <br />

                <span>预录取剩余名额：</span>
                <input name="student.preNum" value="<s:property value="student.preNum" /> " />
                <br />

                <span>个人介绍：</span>
                <textarea type="text" name="student.inf" value=" " > <s:property value="student.inf" /></textarea>
                <br />

            </form>

        </div>
    </div><!-- #main -->

    <%@include file="../footer.jsp"%>
</div>

</body>

</html>