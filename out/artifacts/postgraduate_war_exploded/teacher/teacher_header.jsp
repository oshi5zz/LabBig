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
                    <li class=" menu-item menu-item-type-post_type menu-item-object-page current-menu-item page_item page-item-6209 current_page_item bit-menu-post-id-6209 menu-item-6210 act first">
                        <a href="/teacher/toTeacherIndex.action"><span>教师首页</span></a></li>
                    <li class=" menu-item menu-item-type-post_type menu-item-object-page bit-menu-post-id-5714 menu-item-has-children menu-item-5717 has-children">
                        <a href="/teacher/editTeacherInf.action"><span>更改个人信息</span></a>
                    </li>
                    <li class=" menu-item menu-item-type-post_type menu-item-object-page bit-menu-post-id-6064 menu-item-6066">
                        <a href="/teacher/toSearch.action"><span>按条件查找</span></a></li>
                    <li class=" menu-item menu-item-type-post_type menu-item-object-page bit-menu-post-id-5654 menu-item-has-children menu-item-5655 has-children">
                        <a href="/teacher/viewPreSucList"><span>查看已通过的预请求</span></a>
                    </li>
                    <li class=" menu-item menu-item-type-post_type menu-item-object-page bit-menu-post-id-6082 menu-item-has-children menu-item-6828 has-children">
                        <a href="/teacher/viewTeacherMsg"><span>查看消息</span></a>
                    </li>
                    <li class=" menu-item menu-item-type-post_type menu-item-object-page bit-menu-post-id-6793 menu-item-has-children menu-item-6794 has-children">
                        <a href="/teacher/viewAllReq"><span>查看所有请求</span></a>
                    </li>
                    <li class=" menu-item menu-item-type-post_type menu-item-object-page bit-menu-post-id-6796 menu-item-has-children menu-item-6887 has-children">
                        <a href="/teacher/viewFinalSucList"><span>查看已录取的学生</span></a>
                    </li>
                </ul>
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

