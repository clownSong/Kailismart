<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/27 0027
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/views/common/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>卓茂科技物资管理系统登录</title>
    <%@include file="/WEB-INF/views/common/css.jsp" %>
</head>
<body class="login-body">
<div class="container">
    <form class="form-signin" id="login" method="post" enctype="application/x-www-form-urlencoded">
        <div class="form-signin-heading text-center">
            <h1 class="sign-title">登   录</h1>
            <img src="${basePath}/assets/adminex/images/zmkj_log.png" alt=""/>
        </div>
        <div class="login-wrap">
            <input type="text" class="form-control" name="name" placeholder="用户名" autofocus/>
            <input type="password" class="form-control" name="passwd" placeholder="密码"/>
            <button class="btn btn-lg btn-login btn-block" type="submit"/>
                <i class="fa fa-check"></i>
            </button>
            <div class="registration">
                忘记密码?
                <a class="" href="">
                    点我
                </a>
            </div>
            <label class="checkbox">
                <input type="checkbox" value="remember-me"> 记住我
            </label>

        </div>

    </form>
        <!-- Modal -->
        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal"
             class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Forgot Password ?</h4>
                    </div>
                    <div class="modal-body">
                        <p>Enter your e-mail address below to reset your password.</p>
                        <input type="text" name="email" placeholder="Email" autocomplete="off"
                               class="form-control placeholder-no-fix">
                    </div>
                    <div class="modal-footer">
                        <button data-dismiss="modal" class="btn btn-default" type="button">返回</button>
                        <button class="btn btn-primary" type="button">登录</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- modal -->

</div>
<%@include file="/WEB-INF/views/common/js.jsp" %>
<script src="${basePath}/assets/js/login.js"></script>
</body>
</html>
