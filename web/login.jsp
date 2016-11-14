<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN"><!--<![endif]-->
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>考研师生互选系统</title>
    <link rel="stylesheet" href="res/frame_a.css" type="text/css" media="all">
    <link rel="stylesheet" id="dt-main-css" href="res/main.css" type="text/css" media="all">
    <link rel="stylesheet" href="res/frame_b.css" type="text/css" media="all">
    <meta name="description" content="考研师生互选">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

</head>


<body class="home page page-id-6209 page-template-default content-fullwidth image-blur mini-boxed-layout fix-width btn-flat wpb-js-composer js-comp-ver-4.0.1 vc_responsive no-mobile is-webkit"
      data-pid="6209" data-pkey="a12ac14c0e7e681c1c26c894d0cde02f">

<div id="page" class="contentFixWidth fix-width">

    <%@include file="header.jsp" %>

    <div id="main" class="bit_main_content" style="width: 60%;margin-top: 20px;margin-left: 10%">

        <div class="panel panel-success ">
            <form action="login.action" method="post">
                <div class="panel-heading">
                    <h3 class="panel-title">用户登录</h3>
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label for="input-id" class="col-sm-2 control-label">学工号</label>
                        <div class="col-sm-8">
                            <input type="text" id="input-id"  class="form-control" name="user.userId" placeholder="学工号">
                        </div>
                    </div>
                    <br />

                    <div class="form-group">
                        <label for="input-pass" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-8">
                            <input type="password" id="input-pass" class="form-control" name="user.password" placeholder="密码">
                        </div>
                    </div>

                    <br />
                    <div class="form-group">
                        <label class="radio-inline col-md">
                            <input type="radio" name="type" id="radio-student" value="student"> 学生
                        </label>
                        <label class="radio-inline col-md">
                            <input type="radio" name="type" id="radio-teacher" value="teacher"> 教师
                        </label>
                    </div>
                </div>
                <div class="panel-footer">
                    <input type="submit" id="input-login" class="btn btn-primary" value="登录">
                </div>
            </form>
        </div>

    </div><!-- #main -->

    <%@include file="footer.jsp"%>

</div>


</body>

</html>
<%--
<script type="text/javascript">
    $(document).ready(function () {

        $("#input-login").click(function () {
            $.post("/login.action",
                    {
                        "user.userId":$("#input-id").val(),
                        "user.password":$("#input-pass").val(),
                        "type":$("input[name='type']:checked").val(),
                    },function (data) {

                    }
            );
        });
    });
</script>--%>


