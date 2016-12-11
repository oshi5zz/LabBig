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
    <title>密码找回</title>
    <link rel="stylesheet" href="../res/frame_a.css" type="text/css" media="all">
    <link rel="stylesheet" id="dt-main-css" href="../res/main.css" type="text/css" media="all">
    <link rel="stylesheet" href="../res/frame_b.css" type="text/css" media="all">
    <meta name="description" content="考研师生互选">

    <%@include file="/bootstrap.jsp"%>

</head>


<body class="home page page-id-6209 page-template-default content-fullwidth image-blur mini-boxed-layout fix-width btn-flat wpb-js-composer js-comp-ver-4.0.1 vc_responsive no-mobile is-webkit"
      data-pid="6209" data-pkey="a12ac14c0e7e681c1c26c894d0cde02f">

<div id="page" class="contentFixWidth fix-width">

    <%@include file="/header.jsp" %>

    <div id="main"  style="margin-left: 5%;margin-right: 5%">
        <div class="panel panel-default " style="width:80%; margin-left:10%;margin-top:20px;">
            <div class="panel panel-heading " align="center" style="margin-bottom: 0px;">
                <h3 class="panel-title"><span >找回密码</span></h3>
            </div>
            <div class="panel panel-body" align="center">
                <div class="form-group ">
                    <label for="input-id" class="control-label">学工号:</label>
                    <input type="text" id="input-id"  name="user.userId" placeholder="学工号" />
                </div>
                <div class="container" id="div-reset" >
                    <div class="form-group">
                        <label for="question" class="control-label">密保问题：</label>
                        <input type="text" id="question" name="user.question" readonly/>
                    </div>
                    <div class="form-group">
                        <label for="answer" class="control-label">答案：</label>
                        <input type="text" id="answer" name="user.answer"/>
                    </div>
                    <div class="container" id="pass-reset">
                        <div class="form-group ">
                            <label for="input-pass1" class="control-label">密码:</label>
                            <input type="password" id="input-pass1"  name="user.password" placeholder="密码" />
                        </div>

                        <div class="form-group ">
                            <label for="input-pass2" class="control-label">密码:</label>
                            <input type="password" id="input-pass2"  name="passtmp" placeholder="密码" />
                        </div>
                        <button class="btn btn-primary" id="btn-reset">重置密码</button>
                    </div>
                </div>
            </div>
            <script type="text/javascript">
                $(document).ready(function () {
                    $("#div-reset").hide();
                    $("#pass-reset").hide();

                    $(document).on('keydown', '#input-id', function(e) {
                        if(e.keyCode == 13 ) {
                            $.post("getQuestion", {
                                "user.userId": $("#input-id").val(),
                            }, function (data) {
                                if (data != "") {
                                    $("#question").val(data);
                                    $("#div-reset").show();
                                } else {
                                    $("#div-reset").hide();
                                    alert("未找到此学工号！");
                                }
                            });
                        }
                    });

                    $(document).on('keydown', '#answer', function(e) {
                        if(e.keyCode == 13 ) {
                            $.post("validateAnswer",{
                                "user.userId":$("#input-id").val(),
                                "user.answer":$("#answer").val(),
                            },function (data) {
                                $("#pass-reset").hide();
                                if(data!="") {
                                    $("#question").val(data);
                                    $("#pass-reset").show();
                                } else {
                                    $("#pass-reset").hide();
                                    alert("答案错误！");
                                }
                            });
                        }
                    });

                    /*$("#answer").blur(function () {
                        $.post("validateAnswer",{
                            "user.userId":$("#input-id").val(),
                            "user.question":$("#question").val(),
                            "user.answer":$("#answer").val(),
                        },function (data) {
                            $("#pass-reset").hide();
                            if(data!="") {
                                $("#question").val(data);
                                $("#pass-reset").show();
                            } else {
                                $("#pass-reset").hide();
                                alert("答案错误！");
                            }
                        });
                    });*/
                    $("#btn-reset").click(function () {
                        var pa1 = $("#input-pass1").val();
                        var pa2 = $("#input-pass2").val();
                        if (pa1!=pa2){
                            alert("两次密码不相等！");
                        } else if(pa1.length<6){
                            alert("密码至少6位");
                        } else {
                            $.post("resetPass",{
                                "user.userId":$("#input-id").val(),
                                "user.password":pa1,
                            },function (data) {
                                if(confirm(data)) {
                                    window.location.href="/login.jsp";
                                } else {
                                    window.location.href="/reset_pass.jsp";
                                }
                            });
                        }
                    });
                });

            </script>
        </div>


        <%--<s:debug></s:debug>--%>
    </div><!-- #main -->

    <%@include file="/footer.jsp"%>
</div>

</body>


</html>