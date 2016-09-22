<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016-08-09
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/head.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="/WEB-INF/views/common/css.jsp" %>
    <link href="${basePath}/assets/bootstrap-3.3.5-dist/select/bootstrap-select.min.css" rel="stylesheet"/>
    <link href="${basePath}/assets/css/child.css" rel="stylesheet"/>
    <!--ios7-->
    <link rel="stylesheet" type="text/css" href="${basePath}/assets/adminex/js/ios-switch/switchery.css"/>
    <!--多选-->
    <link href="${basePath}/assets/adminex/js/iCheck/skins/square/blue.css" rel="stylesheet">
    <!--日期插件-->
    <link rel="stylesheet" type="text/css"
          href="${basePath}/assets/bootstrap-3.3.5-dist/datepicher/css/bootstrap-datepicker.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="${basePath}/assets/bootstrap-3.3.5-dist/datepicher/css/bootstrap-datepicker.standalone.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="${basePath}/assets/bootstrap-3.3.5-dist/datepicher/css/bootstrap-datepicker3.min.css"/>


    <style type="text/css">
        .bootstrap-table > .fixed-table-container {
            overflow: hidden;
        }

        #maters input {
            width: auto;
            max-width: 60px;
        }
        #myModal{
            padding-left:0px !important;
        }
        #proSubmit .dropdown-menu {
            height: 200px;
        }

        #materialList {
            width: 100%;
            table-layout: fixed;
        }
        #materialList td {
            white-space: nowrap;
            overflow: hidden;
            word-break: keep-all;
            text-overflow: ellipsis;
        }

        .border-waring {
            border-color: red;
        }
        #applys td{
            background-color: rgba(255, 0, 0, 0);
        }
    </style>
</head>
<body>
<div class="row">
    <form id="proSubmit" method="POST">
        <%--基本信息--%>
        <div class="col-lg-12">
            <section class="panel">
                <header class="panel-heading">
                    采购订单
                    <div class="dropdown pull-right">
                        <button class="btn btn-default dropdown-toggle" type="button"
                                id="flowChoose" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            <span class="label label-sm btn-primary">选择流程</span>
                            <input type="hidden" value="" name="flow.ID"/>
                            <input type="hidden" value="" name="flow.frameCoding"/>
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="flowChoose">

                        </ul>
                    </div>
                </header>
                <div class="panel-body" id="pro">
                    <%--供应单位--%>
                    <div class="form-group col-lg-6">
                        <div class="dropdown iconic-input">
                            <i class="fa fa-home"></i>
                            <input type="text" autocomplete="off" class="form-control" id="company" data-toggle="dropdown"
                                   placeholder="供应单位">
                            <ul class="dropdown-menu col-lg-12" aria-labelledby="dropdownMenu2">

                            </ul>
                            <input type="hidden" name="company.ID" data-cid="1" placeholder="供应单位">
                        </div>
                    </div>
                    <%--订单编号--%>
                    <div class="form-group col-lg-4">
                        <div class="iconic-input right">
                            <i class="fa fa-book"></i>
                            <input type="text" autocomplete="off" name="pmNumber" class="form-control" placeholder="订单编号"/>
                        </div>
                    </div>
                    <%--采购员--%>
                    <div class="form-group col-lg-2">
                        <div class="dropdown">
                            <input type="text" autocomplete="off" class="form-control" data-toggle="dropdown" id="staff"
                                   placeholder="${user.name}">
                            <ul class="dropdown dropdown-menu col-lg-12" aria-labelledby="dropdownMenu3">

                            </ul>
                            <input type="hidden" name="staff.ID" value="${user.ID}" data-cid=""/>
                        </div>
                    </div>
                    <%--合同名称--%>
                    <div class="form-group col-lg-6">
                        <div class="dropdown iconic-input left">
                            <i class="fa fa-book"></i>
                            <input type="text" autocomplete="off" class="form-control" id="contract" data-toggle="dropdown"
                                   placeholder="合同名称">
                            <ul class="dropdown dropdown-menu col-lg-12" aria-labelledby="dropdownMenu4">

                            </ul>
                            <input type="hidden" data-company="" name="contract.ID"/>
                        </div>
                    </div>
                    <%--收货地址--%>
                    <div class="form-group col-lg-4">
                        <div class="dropdown iconic-input right">
                            <i class="fa fa-book"></i>
                            <input type="text" autocomplete="off" class="form-control" id="address" data-toggle="dropdown"
                                   placeholder="收货地址">
                            <ul class="dropdown dropdown-menu col-lg-12" aria-labelledby="dropdownMenu5">

                            </ul>
                            <input type="hidden" name="city.ID"/>
                        </div>
                    </div>
                    <%--税率--%>
                    <div class="form-group col-lg-2">
                        <input type="text" class="form-control" id="tax" name="tax" value="17" placeholder="17%">
                    </div>
                    <div class="form-group col-lg-8 remark">
                        <textarea name="remark" placeholder="备注..." style=""></textarea>
                    </div>
                </div>
            </section>
        </div>
    </form>
    <%--材料集合--%>
    <div class="col-lg-12">
        <section class="panel" id="materlist-body" style="max-height:440px;height:440px;overflow:hidden;">
            <header class="panel-heading">
                材料列表
                <button type="button" class="btn btn-primary pull-right" style="margin-left:20px" id="sub">走起
                </button>
                <button type="button" id="chooseMaterial" data-loading-text="Loading..."
                        class="btn btn-primary pull-right" autocomplete="off">
                    选择材料
                </button>
                <strong class="my-hide alert-danger pull-right">请选择材料...</strong>
            </header>
            <div class="panel-body">
                <table class="table" id="materialList" style="max-height:440px !important;">
                    <thead>
                    <tr class="success">
                        <th class="project" style="width:200px">材料名称</th>
                        <th class="applyName" style="width:200px">型号</th>
                        <th class="hidden-phone date" style="width:80px;">品牌</th>
                        <th class="hidden-phone personName" style="width:100px">数量</th>
                        <th class="hidden-phone personName no-wrap" style="width:100px;word-break:break-all;">单价</th>
                        <th class="hidden-phone personName no-wrap" style="width:100px;word-break:break-all;">金额</th>
                        <th class="hidden-phone personName no-wrap">不含税价</th>
                        <th class="hidden-phone personName no-wrap">备注</th>
                        <th class="hidden-phone oper">入库数量</th>
                        <th class="hidden-phone oper">计划到货日期</th>
                    </tr>
                    </thead>
                    <tbody id="maters">

                    </tbody>
                </table>
            </div>
        </section>
    </div>
</div>
<%--选择材料的模态框--%>
<div class="modal fade bs-example-modal-sm"
     id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="padding-left:0px !important;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">选择材料</h4>
            </div>
            <div class="modal-body">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading col-lg-8" style="border-bottom: 0px;">
                            申请单
                        </header>
                        <%--表格搜索框--%>
                        <div class="col-lg-4" style="height:50px;">

                        </div>
                        <%--表格开始--%>
                        <div class="panel-body">
                            <table id="applys">
                                <thead>
                                <tr>
                                    <th class="project">项目名称</th>
                                    <th class="applyName">申请单</th>
                                    <th class="hidden-phone date">申请人</th>
                                    <th class="hidden-phone personName">申请日期</th>
                                    <th class="hidden-phone personName">采购执行情况</th>
                                    <th class="hidden-phone personName no-wrap">备注</th>
                                </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                        </div>
                    </section>
                </div>
                <%--材料列表--%>
                <div class="col-lg-12">
                    <section class="panel">
                        <%--材料检索框--%>
                        <div class="col-lg-4" style="height:50px;">

                        </div>
                        <%--材料列表开始--%>
                        <div class="panel-body">
                            <table id="material">
                                <thead>
                                <tr>
                                    <th class="project">材料名称</th>
                                    <th class="applyName">型号</th>
                                    <th class="hidden-phone date">品牌</th>
                                    <th class="hidden-phone personName">数量</th>
                                    <th class="hidden-phone personName no-wrap">单价</th>
                                    <th class="hidden-phone personName no-wrap">金额</th>
                                    <th class="hidden-phone personName no-wrap">价税合计</th>
                                    <th class="hidden-phone personName no-wrap">备注</th>
                                    <th class="hidden-phone oper">入库数量</th>
                                    <th class="hidden-phone oper">计划到货日期</th>
                                </tr>
                                </thead>
                                <tbody>

                                </tbody>
                                <tfoot>
                                <tr>
                                    <th colspan="5">累计：</th>
                                    <th colspan="5">1000元</th>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                    </section>
                </div>
            </div>
            <%--模态框底部--%>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="close" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="enter">走起</button>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/views/common/js.jsp" %>
<script src="${basePath}/assets/adminex/js/jquery.nicescroll.js"></script>
<script src="${basePath}/assets/bootstrap-3.3.5-dist/table/bootstrap-table.js"></script>
<script src="${basePath}/assets/bootstrap-3.3.5-dist/table/bootstrap-table-zh-CN.min.js"></script>
<!--ios7-->
<script src="${basePath}/assets/adminex/js/ios-switch/switchery.js"></script>
<script src="${basePath}/assets/adminex/js/ios-switch/ios-init.js"></script>
<!--icheck -->
<script src="${basePath}/assets/adminex/js/iCheck/jquery.icheck.js"></script>
<!--日期插件-->
<script src="${basePath}/assets/bootstrap-3.3.5-dist/datepicher/bootstrap-datepicker.min.js"></script>
<script src="${basePath}/assets/bootstrap-3.3.5-dist/datepicher/css/bootstrap-datepicker.zh-CN.min.js"></script>
<!--自定义通用脚本-->
<script src="${basePath}/assets/js/common.js"></script>
<!--页面所需脚本-->
<script src="${basePath}/assets/js/pmAdd.js"></script>
<script src="${basePath}/assets/js/mit.js"></script>
</body>
</html>
