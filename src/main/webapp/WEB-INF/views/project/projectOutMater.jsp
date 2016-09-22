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
            width: 600px;
            table-layout: fixed;
        }

        td {
            white-space: nowrap;
            overflow: hidden;
            word-break: keep-all;
            text-overflow: ellipsis
        }
        #myModal{
            padding-left:0px !important;
        }
        .panel{
            min-height: 432px;
        }
    </style>
</head>
<body>
<%--顶部结束--%>
<div class="row">
    <div class="col-sm-12">
        <section class="panel">
            <div class="header-section">
            <header class="panel-heading col-md-12">
                仓库管理/<span style="color:#65CEA7;">项目领料记录</span>
                <button class="btn btn-xs btn-success" type="button" id="export">导出</button>
            </header>
                <%--项目选择器--%>
                <div class="col-md-4" id="testSelect" style="margin-top:17px;">
                    <div class="dropdown iconic-input" style="margin-left:30px;">
                        <i class="fa fa-home"></i>
                        <input type="text" autocomplete="off" name="project.name" style="" class="form-control" id="project" data-toggle="dropdown" placeholder="选择项目" value="" aria-expanded="false">
                        <ul class="dropdown-menu col-lg-12" aria-labelledby="dropdownMenu2">

                            <li data-project="19584040-6066-4983-9774-065E7B912A42"><a>朝阳新村地块安置房智能化</a></li><li data-project="1A44C60A-3E1B-401E-9B78-783B1B8158C9"><a>小海低价位商品房七期（北侧地块)</a></li><li data-project="4917E3A5-6354-4BE6-A532-F242C834FD84"><a>苏州天籁城二期智能化</a></li><li data-project="5E5912E2-CC19-4F25-9BFE-9CFA048321D2"><a>如皋市紫竹园一组团智能化系统</a></li><li data-project="9357D940-B963-4802-819A-EBE618F5CA54"><a>测试项目160617</a></li><li data-project="99080368-7B2A-40BE-B8CD-53A63F0A8A5B"><a>常州景瑞望府一二期智能化</a></li><li data-project="A1EE1F60-E247-4BD1-8B4F-1E2A2C5098F2"><a>南通碧桂园二期</a></li><li data-project="B1717595-BED3-4C81-A8CF-49FA284C302F"><a>景瑞.海门御江山项目智能化系统</a></li><li data-project="BCDE25B1-0590-4A58-9284-5026B1D8F396"><a>星苏二期</a></li><li data-project="C6DCD7FC-974A-43D7-BA48-251DEFDD45EB"><a>镇江优山美地五期161-164</a></li><li data-project="CCDE629D-EF02-46BE-AEC4-58B520174050"><a>R15010地块（幸福地块二）智能化工程</a></li><li data-project="D1E901BE-FA62-4698-8562-9C407CB99D3A"><a>港闸区人武部监控中心弱电系统</a></li><li data-project="D751F86F-877C-4110-A62E-8006A142DFE3"><a>万濠星城智能化改造工程</a></li><li data-project="DA39CF88-9CD0-421D-AA49-6B2DC8A89F4E"><a>世家二期智能化安防监控设备采购及深化设计</a></li><li data-project="ECAB29BE-05B5-40E7-86CE-54E473B5453B"><a>茗馨花园监控、道闸系统</a></li><li data-project="F997950D-69C6-429C-9438-34E29C140E7D"><a>东方瑞园住宅小区安全防范系统工程施工合同</a></li><li data-project="FC373C5B-4B8F-4419-AD09-DB81C7755D5A"><a>黎明润景</a></li></ul>
                        <input type="hidden" name="project.ID" data-cid="" value="" placeholder="选择项目">
                    </div>
                </div>
                <%--合计信息--%>
                <div class="col-md-2" id="count" style="margin-top:17px;">
                    <span>合计：<span class="sum">0</span></span></div>
            </div>
            <div class="panel-body no-padding">
                <div class="adv-table">
                    <table class="table-bordered table-striped" id="projectOutMaterTable">

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

<%--打印脚本--%>
<script src="${basePath}/assets/js/jquery.jqprint-0.3.js"></script>
<script src="${basePath}/assets/js/jquery-migrate-1.1.0.min.js"></script>
<script src="${basePath}/assets/js/moneyParse.js"></script>
<!--自己的通用脚本-->
<script src="${basePath}/assets/js/common.js"></script>
<!--dynamic table initialization -->
<script src="${basePath}/assets/js/projectOutMater.js"></script>
</body>
</html>