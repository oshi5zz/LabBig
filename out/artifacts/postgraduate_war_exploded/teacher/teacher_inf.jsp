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

    <%@include file="/bootstrap.jsp"%>

</head>


<body class="home page page-id-6209 page-template-default content-fullwidth image-blur mini-boxed-layout fix-width btn-flat wpb-js-composer js-comp-ver-4.0.1 vc_responsive no-mobile is-webkit"
      data-pid="6209" data-pkey="a12ac14c0e7e681c1c26c894d0cde02f">

<div id="page" class="contentFixWidth fix-width">

    <%@include file="teacher_header.jsp" %>

    <div id="main" class="bit_main_content">
        <div class="panel panel-default " style="width:80%; margin-left:10%;margin-top:20px;">
        <div class="panel panel-heading " align="center">
        	<h3 class="panel-title"><span >更改个人信息</span></h3>
        </div>
        <div class="panel panel-body">
            <form role="form" method="post" action="/teacher/updateTeacherInf.action">
				<div class="form-group col-lg-6">
    	            <label for="teacher-name"><span>姓名：</span></label>
	                <input name="teacher.name" type="text" id="teacher-name" value="<s:property value="teacher.name" />" readonly/>
                </div>

				<div class="form-group col-lg-5">
    	            <label for="teacher-sex"><span>性别：</span></label>
					<input name="teacher.sex" type="text" id="teacher-sex" value="<s:property value="teacher.sex" />" />
                    
	               <!-- <input name="teacher.sex" type="text" id="teacher-sex" value="<s:property value="teacher.sex" />" /> -->
                </div>

				<div class="form-group col-lg-6">
    	            <label for="teacher-age"><span>年龄：</span></label>
	                <input name="teacher.age" type="number" id="teacher-age" value="<s:property value="teacher.age" />" />
                </div>     

				<div class="form-group col-lg-6">
    	            <label for="teacher-professionalTitle"><span>职称：</span></label>
	                <input name="teacher.professionalTitle" type="text" id="teacher-professionalTitle" value="<s:property value="teacher.professionalTitle" />" />
                </div>                              

				<div class="form-group col-lg-6">
    	            <label for="teacher-province"><span>省份：</span></label>
	                <input name="teacher.province" type="text" id="teacher-province" value="<s:property value="teacher.province" />" />
                </div>   

				<div class="form-group col-lg-6">
    	            <label for="teacher-school"><span>学校：</span></label>
	                <input name="teacher.school" type="text" id="teacher-school" value="<s:property value="teacher.school" />" />
                </div>  

				<div class="form-group col-lg-6">
    	            <label for="teacher-major"><span>专业：</span></label>
	                <input name="teacher.major" type="text" id="teacher-major" value="<s:property value="teacher.major" />" />
                </div>                                    

				<div class="form-group col-lg-6">
    	            <label for="teacher-researchArea"><span>方向：</span></label>
	                <input name="teacher.researchArea" type="text" id="teacher-researchArea" value="<s:property value="teacher.researchArea" />" />
                </div>  
                
				<div class="form-group col-lg-6">
    	            <label for="teacher-preNum"><span>招生人数：</span></label>
	                <input name="teacher.preNum" type="number" id="teacher-preNum" value="<s:property value="teacher.preNum" />" />
                </div>                    

				<div class="form-group col-lg-6 hidden">
    	            <label for="teacher-finalNum"><span>最终招生人数：</span></label>
	                <input name="teacher.finalNum" type="text" id="teacher-finalNum" value="<s:property value="teacher.finalNum" />" />
                </div>                    


				<div class="form-group col-lg-6">
    	            <label for="teacher-mail"><span>邮箱：</span></label>
	                <input name="teacher.mail" type="text" id="teacher-mail" value="<s:property value="teacher.mail" />" />
                </div>   
                
				<div class="form-group col-lg-6">
    	            <label for="teacher-inf"><span>个人介绍：</span></label>
	                <textarea name="teacher.inf"  id="teacher-inf" ><s:property value="teacher.inf" /></textarea>
                </div>                               

				<div class="form-group col-lg-6">
    	            <label for="teacher-requirement"><span>招生需求：</span></label>
	                <textarea name="teacher.requirement"  id="teacher-requirement" ><s:property value="teacher.requirement" /></textarea>
                </div>   
                
				<div class="panel panel-footer" align="center">
                	<button type="submit" class="btn btn-primary btn-lg " >保存</button>
	                <button type="button" class="btn wpb_btn-success btn-lg">取消</button>
                </div>
            </form>
            </div>

        </div>
    </div><!-- #main -->

    <%@include file="../footer.jsp"%>
</div>

</body>

</html>