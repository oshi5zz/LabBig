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
    <title>考研师生互选系统</title>
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
        <div class="teacher-inf">
            <form class="teacher-inf-form" method="post" action="/teacher/updateTeacherInf.action">
                <span>姓名：</span>
                <input name="teacher.name" value="<s:property value="teacher.name" /> " />
                <br />

                <span>性别：</span>
                <input name="teacher.sex" value="<s:property value="teacher.sex" /> " />
                <br />

                <span>年龄：</span>
                <input name="teacher.age" value="<s:property value="teacher.age" /> " />
                <br />

                <span>职称：</span>
                <input name="teacher.professionalTitle" value="<s:property value="teacher.professionalTitle" /> " />
                <br />

                <span>省份：</span>
                <input name="teacher.province" value="<s:property value="teacher.province" /> " />
                <br />

                <span>学校：</span>
                <input name="teacher.school" value="<s:property value="teacher.school" /> " />
                <br />

                <span>专业：</span>
                <input name="teacher.major" value="<s:property value="teacher.major" /> " />
                <br />

                <span>研究方向：</span>
                <input type="text" name="teacher.researchArea" value="<s:property value="teacher.researchArea" /> " />
                <br />

                <span>预招生人数：</span>
                <input type="text" name="teacher.preNum" value="<s:property value="teacher.preNum" /> " />
                <br />

                <span>最终招生人数：</span>
                <input type="text" name="teacher.finalNum" value="<s:property value="teacher.finalNum" /> " />
                <br />

                <span>邮箱：</span>
                <input name="teacher.mail" value="<s:property value="teacher.mail" /> " />
                <br />

                <span>个人介绍：</span>
                <textarea type="text" name="teacher.inf" value=" " > <s:property value="teacher.inf" /></textarea>
                <br />

                <span>招生需求：</span>
                <textarea type="text" name="teacher.requirement" value=" " > <s:property value="teacher.requirement" /></textarea>
                <br />

                <input type="submit" value="保存修改" />
                <input type="button" value="取消" />
            </form>

        </div>
    </div><!-- #main -->

    <%@include file="../footer.jsp"%>
</div>

</body>

</html>