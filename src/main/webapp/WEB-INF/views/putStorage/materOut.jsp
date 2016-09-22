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

        #putMaterial td {
            white-space: nowrap;
        }

        .invoice-title {
            color: #6bc5a4;
            font-size: 25px;
            text-transform: uppercase;
            margin-top: 0px;
        }

        .col-sm-offset-4 {
            margin-bottom: 25px;
        }

        .col-sm-offset-4 input, .col-sm-offset-4 textarea, #put {
            width: 100%;
            border: 0px;
            border-bottom: 3px solid #6bcdce;
            background-color: #ffffff;
            outline: none;
        }

        #remark {
            overflow-x: hidden;
            overflow-y: hidden;
            padding-top: 28px;
            resize: none;
        }

        #remark:focus {
            border: 0px;
            border-bottom: 3px solid #5ecdce;
        }

        #putMaterial, #outMaterList {
            width: 100%;
            table-layout: fixed;
        }

        #putMaterial td, #outMaterList td {
            white-space: nowrap;
            overflow: hidden;
            word-break: keep-all;
            text-overflow: ellipsis;
        }

        body {
            background-color: #FFFFFF;
        }

        .datepicker-orient-bottom {
            height: auto;
        }

        #edit_message {
            margin-left: 1px;
            width: 100%;
            height: 100%;
            border: 0px;
        }
        .fixed-table-container{
            overflow:hidden;
        }
        .border-waring{
            border-color:red;
        }
    </style>
</head>
<body>
<form method="post" id="toOut" enctype="application/x-www-form-urlencoded">
    <div class="row" id="outMater">
        <%--选择项目--%>
        <div class="col-md-4 pull-left" style="margin-top:28px;">
            <div class="dropdown iconic-input" style="margin-left:30px;">
                <i class="fa fa-home"></i>
                <input type="text" autocomplete="off" name="project.name" style="" class="form-control" id="project"
                       data-toggle="dropdown"
                       placeholder="选择项目" value="${outObject.project.name}">
                <ul class="dropdown-menu col-lg-12" aria-labelledby="dropdownMenu2">

                </ul>
                <input type="hidden" name="project.ID" data-cid="" value="${outObject.project.ID}" placeholder="选择项目">
            </div>
        </div>
        <%--选择入库单--%>
        <div class="col-md-4 pull-left" style="margin-top:28px;">
            <div class="dropdown iconic-input" style="margin-left:30px;">
                <i class="fa fa-home"></i>
                <input type="text" autocomplete="off" style="" class="form-control" id="putStorage"
                       data-toggle="dropdown"
                       placeholder="入库单" value="">
                <ul class="dropdown-menu col-lg-12" aria-labelledby="dropdownMenu3">

                </ul>
                <input type="hidden" name="putId" data-cid="" value="" placeholder="入库单">
            </div>
        </div>
        <%--领料单位--%>
        <div class="col-md-4 pull-left" style="margin-top:28px;">
            <div class="dropdown iconic-input" style="margin-left:30px;">
                <i class="fa fa-home"></i>
                <input type="text" autocomplete="off" style="" class="form-control" id="company"
                       data-toggle="dropdown"
                       placeholder="领料单位" value="${outObject.company.name}">
                <ul class="dropdown-menu col-lg-12" aria-labelledby="dropdownMenu4">

                </ul>
                <input type="hidden" name="company.ID" value="${outObject.company.ID}" data-cid="" placeholder="领料单位">
            </div>
        </div>
        <%--领料人--%>
        <div class="col-md-4 pull-left" style="margin-top:28px;">
            <div class="dropdown iconic-input" style="margin-left:30px;">
                <i class="fa fa-home"></i>
                <input type="text" autocomplete="off" style="" class="form-control" id="outPerson"
                       data-toggle="dropdown"
                       placeholder="领料人" value="${outObject.outPerson.name}">
                <ul class="dropdown-menu col-lg-12" aria-labelledby="dropdownMenu5">

                </ul>
                <input type="hidden" name="outPerson.ID" value="${outObject.outPerson.ID}" data-cid=""
                       placeholder="领料人">
            </div>
        </div>
        <%--领料部门--%>
        <div class="col-md-4 pull-left" style="margin-top:28px;">
            <div class="dropdown iconic-input" style="margin-left:30px;">
                <i class="fa fa-home"></i>
                <input type="text" autocomplete="off" style="" class="form-control" id="section"
                       data-toggle="dropdown"
                       placeholder="领料部门" value="${outObject.section.name}">
                <ul class="dropdown-menu col-lg-12" aria-labelledby="dropdownMenu6">

                </ul>
                <input type="hidden" name="section.ID" value="${outObject.section.ID}" data-cid="" placeholder="领料部门">
            </div>
        </div>
        <%--备注--%>
        <div class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 pull-left" style="height:53px;margin-left:0px;">
            <textarea id="remark" name="remark" data-type="5" style="height: 100%;" placeholder="备注..."></textarea>
        </div>

        <%--选择材料的按钮--%>
        <div class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 pull-right" style="margin-left:0px;">
            <button type="button" class="btn btn-sm btn-success" id="choose">入库单</button>
            <button type="button" class="btn btn-sm btn-success" id="chooseMater">材料库</button>
            <%--仓库选择器--%>
            <div class="dropdown pull-left">
                <button class="btn btn-default dropdown-toggle" type="button"
                        id="storages" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    <span class="label label-sm btn-primary">公司总仓</span>
                    <input type="hidden" value="" id="storage" name="storage.ID"/>
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" style="height:200px;" aria-labelledby="flowChoose">

                </ul>
            </div>
            <button type="button" class="btn btn-sm btn-success pull-right" id="outMaterButton">出库</button>
        </div>
        <%--单号--%>
        <div class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 pull-right" style="margin-left:0px;">
            <input type="text" name="outNumber" id="outNumber" data-type="2"
                   placeholder="领料单号"/>
        </div>
        <%--税率--%>
        <div class="col-md-2 col-md-offset-4 col-sm-4 col-sm-offset-4 pull-right" style="margin-left:0px;">
            <input type="text" name="tax" id="tax" data-type="2" placeholder="税率"/>
        </div>
        <%--领料日期--%>
        <div class="col-md-2 col-md-offset-4 col-sm-4 col-sm-offset-4 pull-right" style="margin-left:0px;">
            <input readonly="readonly" type="text" name="outDate" id="outDate" value="" data-type="4"
                   placeholder="领料日期"/>
        </div>

        <div class="col-lg-12" id="outMaterListContaner" style="width: 100%;min-height: 350px;max-height: 350px;">
            <table class="table table-bordered" style="height:100%;" id="outMaterList">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>材料名称</th>
                    <th class="text-center">型号</th>
                    <th class="text-center">品牌</th>
                    <th class="text-center">单位</th>
                    <th class="text-center">数量</th>
                    <th class="text-center">单价</th>
                    <th class="text-center">金额</th>
                    <th class="text-center">税额</th>
                    <th class="text-center">项目</th>
                </tr>
                </thead>
                <tbody id="outMaterListBody">

                </tbody>
            </table>
        </div>
    </div>
</form>


<!-- 入库单材料 摸态框 -->
<div class="modal fade bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="height:90%;max-height: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">材料集合</h4>
            </div>
            <div class="modal-body" style="max-height: 100%;height: 100%;">
                <table class="table table-bordered" id="putMaterial">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>材料名称</th>
                        <th class="text-center">型号</th>
                        <th class="text-center">品牌</th>
                        <th class="text-center">单位</th>
                        <th class="text-center">数量</th>
                        <th class="text-center">单价</th>
                        <th class="text-center">金额</th>
                        <th class="text-center">项目</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <%--<button type="button" class="btn btn-default" id="pass">确定</button>--%>
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
<!--日期插件-->
<script src="${basePath}/assets/bootstrap-3.3.5-dist/datepicher/bootstrap-datepicker.min.js"></script>
<script src="${basePath}/assets/bootstrap-3.3.5-dist/datepicher/css/bootstrap-datepicker.zh-CN.min.js"></script>
<!--自己的通用脚本-->
<script src="${basePath}/assets/js/common.js"></script>
<script src="${basePath}/assets/js/materOut.js"></script>
<script src="${basePath}/assets/js/outMit.js"></script>
</body>
</html>