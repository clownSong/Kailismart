<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/head.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%--表格样式文件--%>
    <%--<link href="${basePath}/assets/adminex/js/advanced-datatable/css/demo_page.css" rel="stylesheet"/>--%>
    <%--<link href="${basePath}/assets/adminex/js/advanced-datatable/css/demo_table.css" rel="stylesheet"/>--%>
    <%@ include file="/WEB-INF/views/common/css.jsp" %>
    <%--<link rel="stylesheet" href="${basePath}/assets/adminex/js/data-tables/DT_bootstrap.css"/>--%>
        <%--多选按钮样式--%>
    <link href="${basePath}/assets/adminex/js/iCheck/skins/square/blue.css" rel="stylesheet">
    <link rel="stylesheet" href="${basePath}/assets/css/child.css"/>
    <style type="text/css">
        .adv-table {
            margin-top: 50px;
        }

        .fixed-table-body {
            height: 95%;
        }

        .update-modal {
            width: 600px;
            height: 450px;
        }

        .update-modal-body {
            height: 300px;
        }

        .btn, .btn-group {
            margin-left: 3px;
        }
    </style>

</head>
<body>
<%--顶部结束--%>
<div class="row">
    <div class="col-sm-12">
        <section class="panel">
            <header class="panel-heading col-md-8">
                采购管理/<span style="color:#65CEA7;">订单集合</span>
                <button type="button" class="btn btn-info btn-sm select-top-filter" data-myis="true" data-person="${user.name}" id="select-myPro">我的订单</button>
                <button type="button" class="btn btn-info btn-sm select-top-filter" id="select-approve">未|审</button>
                <button type="button" class="btn btn-info btn-sm select-top-filter" data-put="true" id="select-put">可入库</button>
                <button type="button" class="btn btn-success btn-sm" id="toApply" data-put="true">to申请单</button>
                <%--仓库选择器--%>
                <div class="dropdown pull-right">
                    <button class="btn btn-default dropdown-toggle" type="button"
                            id="storages" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        <span class="label label-sm btn-primary"></span>
                        <input type="hidden" value="" name="storage.ID"/>
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" style="height:200px;" aria-labelledby="flowChoose">

                    </ul>
                </div>

                <%--时间选择--%>
                <div class="dropdown pull-right">
                    <button class="btn btn-default dropdown-toggle" type="button"
                            id="selectDate" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        <span class="label label-sm btn-info" data-type="thisM">本月</span>
                        <input type="hidden" value="" name="storage.ID"/>
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" style="height:200px;" aria-labelledby="selectDate">
                        <li data-type="thisM"><a href="#body">本月</a></li>
                        <li data-type="prevM"><a href="#body">上月</a></li>
                        <li data-type="haifY"><a href="#body">半年</a></li>
                        <li data-type="thisY"><a href="#body">本年</a></li>
                        <li data-type="all"><a href="#body">所有</a></li>
                    </ul>
                </div>
            </header>
            <div class="col-md-4" style="height:50px;">

            </div>
            <div class="panel-body no-padding">
                <div class="adv-table">
                    <table class="table-bordered table-striped" id="applys">
                        <thead>
                        <tr>
                            <th class="project">项目名称</th>
                            <th class="applyName">订单号</th>
                            <th class="hidden-phone date">采购日期</th>
                            <th class="hidden-phone personName">采购人员</th>
                            <th class="hidden-phone personName no-wrap">订单总额</th>
                            <th class="hidden-phone oper">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%--<tr class="gradeA">
                            <td>${applys.project.name}</td>
                            <td class="hidden-phone">${applys.serialNumber}</td>
                            <td class="center"><span class="label label-warning label-mini">${applys.prepareDate}</span></td>
                            <td class="center"><span class="label label-success label-mini">${applys.staff.name}</span></td>
                            <td class="center no-wrap"  title="${applys.section.name}">${applys.section.name}</td>
                            <td class="center" data-applyID="${applys.ID}">
                                <button class="btn btn-warning" type="button" id="applyM">材料</button>
                                <button class="btn btn-success" type="button">生成采购订单</button>
                            </td>
                        </tr>--%>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>
    </div>
</div>
<!-- Button trigger modal -->

<!-- 订单详情Modal 摸态框 -->
<div class="modal fade bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">采购申请单</h4>
            </div>
            <div class="modal-body">
                <table id="material">
                    <thead>
                    <tr>
                        <th class="project">选择</th>
                        <th class="project">材料名称</th>
                        <th class="applyName">型号</th>
                        <th class="applyName">参数</th>
                        <th class="hidden-phone date">申请</th>
                        <th class="hidden-phone personName">单价</th>
                        <th class="hidden-phone personName no-wrap">金额</th>
                        <th class="hidden-phone personName no-wrap">单位</th>
                        <th class="hidden-phone personName no-wrap">备注</th>
                        <th class="hidden-phone oper">已采数量</th>
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
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--修改订单 模态框-->
<div class="modal fade bs-example-modal-lg" id="update" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog update-modal" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="updatePro">修改订单</h4>
            </div>
            <div class="modal-body update-modal-body" id="update-modal-body" style="overflow: hidden;">
                <%--供应单位--%>
                <div class="form-group col-lg-12">
                    <div class="dropdown iconic-input">
                        <i class="fa fa-home"></i>
                        <input type="text" autocomplete="off" class="form-control" id="company" data-toggle="dropdown"
                               placeholder="供应单位">
                        <ul class="dropdown-menu col-lg-12" aria-labelledby="dropdownMenu2" style="height:150px;">
                        </ul>
                        <input type="hidden" name="company.ID" data-cid="1" placeholder="供应单位">
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
                <%--税率--%>
                <div class="form-group col-lg-6">
                    <input type="text" class="form-control" id="tax" name="tax" value="17" placeholder="17%">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="update-submit">走起</button>
            </div>
        </div>
    </div>
</div>
<!-- 选择入库 摸态框 -->
<div class="modal fade bs-example-modal-sm" id="selectPut" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="selectPut-">选择入库</h4>
            </div>
            <div class="modal-body">
                <table id="selectMater">
                    <thead>
                    <tr>

                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                    <tfoot>
                    <tr>
                        <th colspan="5">合计：</th>
                        <th colspan="5">1000元</th>
                    </tr>
                    </tfoot>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="closeSelect">关闭</button>
                <button type="button" class="btn btn-primary" id="putStorage">走起</button>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/views/common/js.jsp" %>
<script src="${basePath}/assets/adminex/js/jquery.nicescroll.js"></script>
<!--bootstrap表格插件-->
<script src="${basePath}/assets/bootstrap-3.3.5-dist/table/bootstrap-table.js"></script>
<!--icheck -->
<script src="${basePath}/assets/adminex/js/iCheck/jquery.icheck.js"></script>
<!--自己的通用脚本-->
<script src="${basePath}/assets/js/common.js"></script>
<!--dynamic table initialization -->
<script src="${basePath}/assets/js/material.js"></script>
</body>
</html>