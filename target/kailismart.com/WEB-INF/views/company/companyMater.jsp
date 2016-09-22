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
    <link rel="stylesheet" href="${basePath}/assets/bootstrap-3.3.5-dist/datepicher/css/bootstrap-datepicker.min.css"/>
    <link rel="stylesheet" href="${basePath}/assets/css/child.css"/>
    <link href="${basePath}/assets/css/print.css" rel="stylesheet" type="text/css" media="print">
    <link href="${basePath}/assets/css/print_style.css" rel="stylesheet" type="text/css"/>
    <style type="text/css">
        .adv-table {
            margin-top: 0px;
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

        td {
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

        table {

            table-layout: fixed;
        }

        td {
            white-space: nowrap;
            overflow: hidden;
            word-break: keep-all;
            text-overflow: ellipsis
        }

        #myModal {
            padding-left: 0px !important;
        }

        .panel {
            min-height: 432px;
        }

        .datepicker-orient-bottom {
            height: auto;
        }
    </style>
</head>
<body>
<%--顶部结束--%>
<div class="row">
    <div class="col-sm-12">
        <section class="panel">
            <div class="header-section">
                <header class="panel-heading col-md-8">
                    仓库管理/<span style="color:#65CEA7;">供应商材料历史</span>
                    <button class="btn btn-xs btn-success" type="button" id="export">导出</button>
                </header>
                <%--项目选择器--%>
                <div class="col-md-4" id="testSelect" style="margin-top:17px;">
                    <div class="dropdown iconic-input" style="margin-left:30px;">
                        <i class="fa fa-home"></i>
                        <input type="text" autocomplete="off" name="project.name" style="" class="form-control"
                               id="company" data-toggle="dropdown" placeholder="选择单位" value="" aria-expanded="false">
                        <ul class="dropdown-menu col-lg-12" aria-labelledby="dropdownMenu2">
                        </ul>
                        <input type="hidden" name="project.ID" data-cid="" value="" placeholder="选择项目">
                    </div>
                </div>
                <%--合计信息--%>
                <div class="col-md-2" id="count" style="margin-top:17px;">
                    <span>合计：<span class="sum">0</span></span>
                </div>
                <%--开始时间--%>
                <div class="col-md-2" id="testSelect" style="margin-top:17px;">
                    <div class="dropdown iconic-input" style="margin-left:30px;">
                        <i class="fa fa-home"></i>
                        <input type="text" autocomplete="off" name="project.name" style="" class="form-control"
                               id="start" data-toggle="dropdown" placeholder="开始时间" value="" aria-expanded="false">
                    </div>
                </div>
                <%--结束时间--%>
                <div class="col-md-2" id="testSelect" style="margin-top:17px;">
                    <div class="dropdown iconic-input" style="margin-left:30px;">
                        <i class="fa fa-home"></i>
                        <input type="text" autocomplete="off" name="project.name" style="" class="form-control"
                               id="end" data-toggle="dropdown" placeholder="开始时间" value="" aria-expanded="false">
                    </div>
                </div>
            </div>
            <div class="panel-body no-padding">
                <div class="adv-table">
                    <table class="table-bordered table-striped" id="companyMater">

                    </table>
                </div>
            </div>
        </section>
    </div>
</div>
<!-- Button trigger modal -->


<form id="exportForm" enctype="application/x-www-form-urlencoded" method="post">

</form>

<!-- 打印 摸态框 -->
<!--startprint-->
<div class="row hide">
    <div id="printCon">
        <div class="col-md-12 col-sm-12">

            <p class="title">南通卓茂科技开发有限公司</p>
            <p class="address">公司地址：江苏省南通市工农路203号新景大厦3楼</p>
            <p class="fax">FAX：0513-85516123&nbsp&nbspTEL：0513-85516128</p>
            <p class="put">入库单</p>
            <div class="messages">
                <p class="message" id="putNumber">入库单编号：<span></span></p>
                <p class="message" id="proNumber">商品订单号：<span></span></p>
                <p class="message" id="storage">收入仓库：<span></span></p>
                <p class="message" id="company_print">供应商：<span></span></p>
                <p class="message" id="print_data">入库日期：<span></span></p>
                <p class="message" id="print_person">收货人：<span></span></p>
                <p class="message" id="print_remark">备注：<span></span></p>
            </div>
        </div>
        <table class="table-bordered" id="materPrint">
            <thead>
            <tr>
                <th>序号</th>
                <th>材料编码</th>
                <th>材料名称</th>
                <th class="text-center">规格</th>
                <th class="text-center">单位</th>
                <th class="text-center">入库数量</th>
                <th class="text-center">单价</th>
                <th class="text-center">税率</th>
                <th class="text-center">税额</th>
                <th class="text-center">金额</th>
            </tr>
            </thead>
            <tbody id="materPrint_message">

            </tbody>
            <tfoot>
            <tr>
                <td colspan="6" style="text-align: left;">不含税合计：<span>（人民币大写）贰佰元</span></td>
                <td colspan="2" style="text-align: left;" id="moneyCount">￥<span></span></td>
                <td colspan="2" style="text-align: left;">￥<span></span></td>
            </tr>
            </tfoot>
        </table>

    </div>
</div>
<!--endprint-->

<%@ include file="/WEB-INF/views/common/js.jsp" %>
<script src="${basePath}/assets/adminex/js/jquery.nicescroll.js"></script>
<!--bootstrap表格插件-->
<script src="${basePath}/assets/bootstrap-3.3.5-dist/table/bootstrap-table.js"></script>
<!--icheck -->
<script src="${basePath}/assets/adminex/js/iCheck/jquery.icheck.js"></script>
<script src="${basePath}/assets/js/jquery.jqprint-0.3.js"></script>
<!--日期插件-->
<script src="${basePath}/assets/bootstrap-3.3.5-dist/datepicher/bootstrap-datepicker.min.js"></script>
<script src="${basePath}/assets/bootstrap-3.3.5-dist/datepicher/css/bootstrap-datepicker.zh-CN.min.js"></script>
<%--打印脚本--%>
<script src="${basePath}/assets/js/jquery.jqprint-0.3.js"></script>
<script src="${basePath}/assets/js/jquery-migrate-1.1.0.min.js"></script>
<script src="${basePath}/assets/js/moneyParse.js"></script>
<!--自己的通用脚本-->
<script src="${basePath}/assets/js/common.js"></script>
<!--dynamic table initialization -->
<script src="${basePath}/assets/js/companyMater.js"></script>
</body>
</html>