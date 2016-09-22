<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016-08-05
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/head.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>卓茂科技管理系统</title>
    <%@ include file="/WEB-INF/views/common/css.jsp" %>
    <link href="${basePath}/assets/css/home.css" rel="stylesheet"/>
</head>
<body class="sticky-header left-side-collapsed">

<section>
    <!--顶部和左侧导航栏-->
    <div class="left-side sticky-left-side">
        <!--log设置-->
        <div class="logo" test="${basePath}">
            <a href="http://222.184.233.10:8088/"><img src="${basePath}/assets/adminex/images/zmkj_log.png"
                                                       style="height: 100%;" alt=""></a>
        </div>
        <div class="logo-icon text-center">
            <a href="http://222.184.233.10:8088/"><img src="${basePath}/assets/adminex/images/zmkj_log.png"
                                                       style="height: 100%;" alt=""></a>
        </div>
        <!--log结束-->
        <div class="left-side-inner">
            <!-- 顶部信息 -->
            <div class="visible-xs hidden-sm hidden-md hidden-lg">
                <div class="media logged-user">
                    <img alt="" src="${basePath}/assets/adminex/images/photos/user-avatar.png" class="media-object">
                    <div class="media-body">
                        <h4><a href="#">用户1</a></h4>
                        <span>"你好..."</span>
                    </div>
                </div>
                <h5 class="left-nav-title">账户信息</h5>
                <ul class="nav nav-pills nav-stacked custom-nav">
                    <li><a href="#"><i class="fa fa-cog"></i> <span>设置</span></a></li>
                    <li><a href="${basePath}/login"><i class="fa fa-sign-out"></i> <span>退出</span></a></li>
                </ul>
            </div>
            <!--导航栏开始-->
            <ul class="nav nav-pills nav-stacked custom-nav">
                <li><a href="#"><i class="fa fa-home"></i> <span>首页</span></a></li>
                <li class="menu-list"><a href=""><i class="fa fa-laptop"></i> <span>采购管理</span></a>
                    <ul class="sub-menu-list">
                        <li><a href="${basePath}/managent/getPage?pageName=apply/applyList" target="managerCon"> 申请单</a></li>
                        <li><a href="${basePath}/managent/getPage?pageName=procurement/pmAdd2" target="managerCon"> 新增订单</a></li>
                        <li><a href="${basePath}/managent/getPage?pageName=procurement/pmList" target="managerCon"> 订单管理</a></li>
                        <li><a href="#"> 采购合同</a></li>
                        <li><a href="#"> 合同付款</a></li>
                    </ul>
                </li>
                <li class="menu-list"><a href=""><i class="fa fa-book"></i> <span>仓库管理</span></a>
                    <ul class="sub-menu-list">
                        <li><a href="${basePath}/managent/getPage?pageName=putStorage/putList" target="managerCon"> 采购入库单</a></li>
                        <li><a href="${basePath}/managent/toOutPage" target="managerCon"> 物资出库</a></li>
                        <li><a href="${basePath}/managent/getPage?pageName=putStorage/outList" target="managerCon"> 出库管理</a></li>
                        <li><a href="${basePath}/managent/getPage?pageName=project/projectOutMater" target="managerCon"> 项目领料记录</a></li>
                        <li><a href="${basePath}/managent/getPage?pageName=company/companyMater" target="managerCon"> 供货商入库记录</a></li>
                    </ul>
                </li>
                <li><a href="${basePath}/login"><i class="fa fa-sign-in"></i> <span>返回登录界面</span></a></li>
            </ul>
            <!--导航栏结束-->
        </div>
    </div>
    <!-- 顶部和左侧结束-->

    <!--页面正文开始-->
    <div class="main-content">
        <!-- header section start-->
        <div class="header-section">

            <!--菜单开关按钮-->
            <a class="toggle-btn menu-collapsed"><i class="fa fa-bars"></i></a>
            <!--菜单开关按钮 end-->

            <!--搜索框-->
            <div class="searchform" id="parentSearch">

            </div>
            <!--搜索框end-->
            <%@ include file="/WEB-INF/views/common/menu.jsp" %>
        </div>
        <!-- header section end-->
        <div style="height: 100%;">
            <div class="wrapper" style="height:100%;">
                <!--包含内容开始-->
                <iframe id="content" name="managerCon" src="${basePath}/managent/getPage?pageName=apply/applyList" class="no-border width-100"
                        scrolling="no">

                </iframe>
            </div>
        </div>
        <!--包裹内容结束end-->
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
    </div>
    <!-- main content end-->
</section>
<%--<%@ include file="/WEB-INF/views/common/js.jsp"%>--%>
<%@ include file="/WEB-INF/views/common/js.jsp" %>
<%--滚动条--%>
<script src="${basePath}/assets/adminex/js/jquery.nicescroll.js"></script>
<script src="${basePath}/assets/js/index.js"></script>
<script language="javascript">
    function SetWinHeight(obj) {
        var win = obj;
        if (document.getElementById) {
            if (win && !window.opera) {
                if (win.contentDocument && win.contentDocument.body.offsetHeight)
                    win.height = win.contentDocument.body.offsetHeight;
                else if (win.Document && win.Document.body.scrollHeight)
                    win.height = win.Document.body.scrollHeight;
            }
        }
    }
    window.onload = function () {
        var iframe = document.getElementById("content");
        window.setInterval(function(){
            SetWinHeight(iframe);
        },1000);
    }
</script>
</body>
</html>
