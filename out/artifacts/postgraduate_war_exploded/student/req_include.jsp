<%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2016/12/4
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<div class="wf-wrap">
<div class="wf-container-main">
<div id="content" class="content" role="main">

<div class="main-outer-wrapper ">
<div class="bit_row">
<div class="twelve columns left-sidebar ">
<div class="bit_row">
    <section class="three columns left-sidebar-wrapper">
        <div class="bitLeftSider wpb_row" bitdataaction="site_widget_container" bitdatamarker="bitLeftSider" style="">
            <div id="nav_menu-6" style="margin-top:0px;margin-bottom:20px;"
                 class="mobileHidden bitRightLeftSiderWidget widget_nav_menu site_tooler">
                <jsp:include page="../res/sidebar_style.jsp"/>
                <span class="notDeleteWarning" style="display:none"></span>
                <div class="widget-title"><span class="bit_widget_title">所有请求</span></div>
                <div class="widget-content" style="color:#333">
                    <div class="bitWidgetFrame ">
                        <ul class="menu">
                            <li class="menu-item" status="0">
                                <a id="item-0"><span style="font-size:14px;">未处理的预请求</span></a>
                            </li>

                            <li class="menu-item" status="2">
                                <a id="item-2"><span style="font-size:14px;">已通过的预请求</span></a>
                            </li>

                            <li class="menu-item" status="3">
                                <a id="item-3"><span style="font-size:14px;">失败的预请求</span></a>
                            </li>

                            <li class="menu-item" status="4">
                                <a id="item-4"><span style="font-size:14px;">未处理的最终请求</span></a>
                            </li>

                            <li class="menu-item" status="6">
                                <a id="item-6"><span style="font-size:14px;">已通过的最终请求</span></a>
                            </li>

                            <li class="menu-item" status="7">
                                <a id="item-7"><span style="font-size:14px;">失败的最终请求</span></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
    </section>

    <section class="nine columns bitMainTopSider-wrapper ">
        <div class="bit_row"></div>
        <div class="bit_row">
            <div class="content-wrapper twelve columns " style="margin-bottom:0px;">
                <section data-fixheight=""
                         class="qfy-row-1-5843f02d3b00493932 section     no  section-text-no-shadow section-inner-no-shadow section-normal"
                         id="bit_df8n9" style="margin-bottom:0px;border-radius:0px;color:#494949;">
                    <div data-time="" data-imagebgs="" class="background-media  "
                         style="background-image: url('../res/trans.jpg'); background-repeat:repeat-x; background-size:auto; background-attachment:scroll; background-position:0 0%;">
                    </div>
                    <div class="background-overlay grid-overlay-0 " style="background-color: transparent;"></div>
                    <div class="container">
                        <jsp:include page="req_include_old.jsp" />
                    </div>
                </section>
            </div>
        </div>
    </section>

<div class="clear"></div>
</div>
</div>
<div class="clear"></div>
</div>
<div class="clear"></div>
    </div>

</div>
</div><!-- .wf-container -->
</div>

<script type="text/javascript" >
    var filter = function (status) {
        var table = $("tbody").children("tr");
        for (var i=0; i<table.length; i++) {
            var iter = table[i];
            if (iter.getAttribute("flag").trim() == "1") {
                iter.className = "req-list info"
            } else {
                iter.className = "req-list success"
            }
            if (iter.getAttribute("status") == status) {
                $(iter).show();
            } else {
                $(iter).hide();
            }
        }
    }

    $(document).ready(function () {
        filter(0);
        $(".menu-item").click(function () {
            var status = this.getAttribute("status");
            filter(status);
        });
    })

</script>