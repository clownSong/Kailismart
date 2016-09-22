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
        <link href="${basePath}/assets/css/print.css" rel="stylesheet" type="text/css" media="print">
        <link href="${basePath}/assets/css/print_style.css" rel="stylesheet" type="text/css"/>
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
        td{
            white-space: nowrap;
        }
        .invoice-title{
            color: #6bc5a4;
            font-size: 25px;
            text-transform: uppercase;
            margin-top: 0px;
        }
        .col-sm-offset-4{
            margin-bottom:25px;
        }
        .col-sm-offset-4 input,.col-sm-offset-4 textarea,#put{
            width:100%;
            border:0px;
            border-bottom: 3px solid #6bcdce;
            background-color: #ffffff;
            outline:none;
        }
        #remark{
            overflow-x:hidden;
            overflow-y:hidden;
            padding-top: 28px;
            resize : none;
        }
        #remark:focus{
            border:0px;
            border-bottom: 3px solid #5ecdce;
        }
        table {width:600px;table-layout:fixed;}
        td {white-space:nowrap;overflow:hidden;word-break:keep-all;text-overflow:ellipsis}
    </style>
</head>
<body>
<%--顶部结束--%>
<div class="row">
    <div class="col-sm-12">
        <section class="panel">
            <header class="panel-heading col-md-8">
                仓库管理/<span style="color:#65CEA7;">出库单集合</span>
                <button type="button" class="btn btn-info btn-sm select-top-filter" id="select-approve">未|审</button>
                <button type="button" class="btn btn-info btn-sm select-top-filter" data-put="true" id="select-put">出库</button>
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

                    </table>
                </div>
            </div>
        </section>
    </div>
</div>
<!-- Button trigger modal -->

<!-- 入库单详情Modal 摸态框 -->
<div class="modal fade bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">入库单详情</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-4 col-sm-4">
                        <h6 class="invoice-title" id="company">供应单位</h6>
                        <h6 class="invoice-title" id="pro">采购单</h6>
                        <input type="text" name="put" data-type="1" id="put"/>
                    </div>
                    <div class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 pull-right" style="height:53px;">
                        <textarea id="remark" name="remark" data-type="5" style="height: 100%;" placeholder="备注..."></textarea>
                    </div>
                    <div class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 pull-right" style="">
                        <input type="text" name="tax" id="tax" data-type="2" placeholder="税率"/>
                    </div>
                    <div class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 pull-right" style="">
                        <input type="text" name="putDate" id="putDate" data-type="4" placeholder="出库日期"/>
                    </div>
                </div>
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
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="pass">pass</button>
            </div>
        </div>
    </div>
</div>
<!-- 选择出库 摸态框 -->
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
<!-- 打印 摸态框 -->
<!--startprint-->
<div class="row hide">
    <div id="printCon">
        <div class="col-md-12 col-sm-12">

            <p class="title">南通卓茂科技开发有限公司</p>
            <p class="address">公司地址：江苏省南通市工农路203号新景大厦3楼</p>
            <p class="fax">FAX：0513-85516123&nbsp&nbspTEL：0513-85516128</p>
            <p class="put">出库单</p>
            <div class="messages">
                <p class="message" id="putNumber">出库单编号：<span></span></p>
                <p class="message" id="proNumber">项目：<span></span></p>
                <p class="message" id="storage">发出仓库：<span></span></p>
                <p class="message" id="company_print">领料单位：<span></span></p>
                <p class="message" id="print_data">出库日期：<span></span></p>
                <p class="message" id="print_person">领料人：<span></span></p>
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
                <%--<th class="text-center">税率</th>--%>
                <%--<th class="text-center">税额</th>--%>
                <th class="text-center">金额</th>
            </tr>
            </thead>
            <tbody id="materPrint_message">

            </tbody>
            <tfoot>
            <tr>
                <td colspan="6" style="text-align: left;">金额合计：<span>（人民币大写）贰佰元</span></td>
                <td colspan="2" style="text-align: left;" id="moneyCount">￥<span></span></td>
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
<script src="${basePath}/assets/bootstrap-3.3.5-dist/table/bootstrap-table-zh-CN.min.js"></script>
<!--icheck -->
<script src="${basePath}/assets/adminex/js/iCheck/jquery.icheck.js"></script>

<%--打印脚本--%>
<script src="${basePath}/assets/js/jquery.jqprint-0.3.js"></script>
<script src="${basePath}/assets/js/jquery-migrate-1.1.0.min.js"></script>
<script src="${basePath}/assets/js/moneyParse.js"></script>

<!--自己的通用脚本-->
<script src="${basePath}/assets/js/common.js"></script>
<!--dynamic table initialization -->
<script src="${basePath}/assets/js/outMaterial.js"></script>
</body>
</html>