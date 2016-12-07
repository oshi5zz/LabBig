<%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2016/11/5
  Time: 0:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header id="header" class="logo-classic headerInnerPM menuPositionHolder" role="banner">
    <div class="wf-wrap">
        <div class="wf-table">
            <div id="branding" class="wf-td bit-logo-bar" style="">
                <a class="bitem logo small" style="display: table-cell;"
                ><span class="logospan"><img class="preload-me" src="res/index_img.png"
                                             width="382" height="94"
                                             alt="教育网站"></span></a>

            </div>

            <div class="wf-td assistive-info    " role="complementary" style="">
                <div style="display:inline-block;" class="top-bar-right right bit_widget_more"
                     bitdatamarker="bitHeader-2" bitdataaction="site_fix_container" bitdatacolor="white">
                    <div id="text-18" style="margin-top:0px;margin-bottom:20px;"
                         class="mobileHidden headerWidget_1 widget_text site_tooler">
                        <div class="textwidget ckeditorInLine bitWidgetFrame" bitrlt="text" bitkey="text"
                             wid="text-18">
                            <div>
                                    <span style="font-size: 14px;">
                                        <span style="color: rgb(255, 255, 255);">
                                            您好！<s:property value="teacher.name"></s:property>老师！
                                        </span>
                                        <span >|</span>
                                        <span style="color: rgb(255, 255, 255);"><a href="/teacher/logout">注销</a></span>
                                    </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


        </div><!-- .wf-table -->
    </div><!-- .wf-wrap -->
    <div class="navigation-holder">
        <div>
            <!-- !- Navigation -->
            <nav id="navigation" class="wf-wrap" bitdataaction="site_menu_container"
                 bitdatalocation="primary">
                <ul id="main-nav" data-st="0" data-sp="0" data-fh="" data-mw="" data-lh=""
                    class="mainmenu fancy-rollovers wf-mobile-hidden bit-menu-default"
                    data-bit-menu="bit-menu-default" data-bit-float-menu="underline-hover">
                    <li id="toTeacherIndex" class=" menu-item">
                        <a href="/teacher/toTeacherIndex"><span>教师首页</span></a></li>
                    <li id="editTeacherInf" class=" menu-item">
                        <a href="/teacher/editTeacherInf"><span>更改个人信息</span></a>
                    </li>
                    <li id="toSearch" class=" menu-item">
                        <a href="/teacher/toSearch"><span>按条件查找</span></a></li>
                    <li id="viewPreSucList" class=" menu-item ">
                        <a href="/teacher/viewPreSucList"><span>查看预录取</span></a>
                    </li>
                    <li id="viewTeacherMsg" class=" menu-item ">
                        <a href="/teacher/getStudentList"><span>查看消息</span></a>
                    </li>
                    <li id="viewAllReq" class=" menu-item">
                        <a href="/teacher/viewAllReq"><span>查看所有请求</span></a>
                    </li>
                    <li id="viewFinalSucList" class=" menu-item ">
                        <a href="/teacher/viewFinalSucList"><span>查看已录取</span></a>
                    </li>
                </ul>

                <script type="text/javascript">
                    $(document).ready(function () {
                        var href = window.location.href;
                        var lst = href.split('/');
                        var href_id = lst[lst.length - 1];

                        if(href_id == "login.action") {
                            href_id = "toTeacherIndex";
                        }
                        document.getElementById(href_id).className = "menu-item act";
                    })
                </script>
            </nav>
            <div style="display:none;" id="main-nav-slide">
                <div class="main-nav-slide-inner" data-class="">
                    <div class="floatmenu-bar-right bit_widget_more" bitdatamarker="bitHeader-3"
                         bitdataaction="site_fix_container" bitdatacolor="white">
                    </div>
                </div>
            </div>
        </div>
    </div><!-- .navigation-holder -->
</header>

