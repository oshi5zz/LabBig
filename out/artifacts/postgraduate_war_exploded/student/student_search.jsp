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
    <title>查找学生</title>
    <link rel="stylesheet" href="../res/frame_a.css" type="text/css" media="all">
    <link rel="stylesheet" id="dt-main-css" href="../res/main.css" type="text/css" media="all">
    <link rel="stylesheet" href="../res/frame_b.css" type="text/css" media="all">
    <meta name="description" content="考研师生互选">

    <%@include file="../bootstrap.jsp"%>

</head>


<body class="home page page-id-6209 page-template-default content-fullwidth image-blur mini-boxed-layout fix-width btn-flat wpb-js-composer js-comp-ver-4.0.1 vc_responsive no-mobile is-webkit"
      data-pid="6209" data-pkey="a12ac14c0e7e681c1c26c894d0cde02f">

<div id="page" class="contentFixWidth fix-width">

    <%@include file="student_header.jsp" %>

    <div id="main" class="bit_main_content">

    <div class="panel panel-default " style="width:80%; margin-left:10%;margin-top:20px;">
        <div class="panel panel-heading " align="center" style="margin-bottom: 0px;">
        	<h3 class="panel-title"><span >按条件查找学生</span></h3>
        </div>
        <div class="panel panel-body">
        <form role="form"  action="/student/viewSearchResult.action" method="post">
            <div class="form-group col-lg-6">
                <label for="teacher-province"><span>省份：</span></label>
                <input name="teacher.province" type="text" id="teacher-province" value="" />
            </div>   

            <div class="form-group col-lg-6">
                <label for="teacher-school"><span>学校：</span></label>
                <input name="teacher.school" type="text" id="teacher-school" value="" />
            </div>  

            <div class="form-group col-lg-6">
                <label for="teacher-major"><span>专业：</span></label>
                <input name="teacher.major" type="text" id="teacher-major" value="" />
            </div>   
             
				<div class="form-group col-lg-6">
    	            <label for="teacher-researchArea"><span>方向：</span></label>
	                <input name="teacher.researchArea" type="text" id="teacher-researchArea" value="" />
                </div>  

				<div class="form-group col-lg-6">
    	            <label for="teacher-name"><span>姓名：</span></label>
	                <input name="teacher.name" type="text" id="teacher-name" value="" />
                </div>


            <div class="form-group col-lg-6">
                <label for="teacher-sex"><span>性别：</span></label>
                <select name="teacher.sex" id="teacher-sex"  />>
                <option value=""></option>
                <option value="男">男</option>
                <option value="女">女</option>
                </select>
            </div>

			<div class="panel panel-footer" align="center">
            	<button type="submit" class="btn btn-lg btn-success" >搜索</button>
            </div>
        </form>
    </div><!-- #main -->                         

    <%@include file="../footer.jsp"%>
</div>

</body>

</html>