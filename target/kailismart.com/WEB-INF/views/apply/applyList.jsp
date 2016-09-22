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
    <link rel="stylesheet" href="${basePath}/assets/css/child.css"/>
    <style type="text/css">
        table {width:600px;table-layout:fixed;}
        td {white-space:nowrap;overflow:hidden;word-break:keep-all;text-overflow:ellipsis}
        .btn{
            margin-left:10px;
        }
        .modal-title{
            min-height: 25.45px;
        }
        #myModal .fixed-table-container{
            overflow: hidden;
        }
        .success{
            color: #45ff13;
        }
        /*#myModal th{*/
            /*cursor: w-resize;*/
        /*}*/
        .modal-body{
            /*//火狐浏览器禁止选中文字*/
            -moz-user-select:none;
        }
        #myModal .fixed-table-body{
            max-height:88% !important;
            height: 88%;
        }
    </style>
</head>
<body>
<%--顶部结束--%>
<div class="row">
    <div class="col-sm-12">
        <section class="panel">
            <header class="panel-heading col-md-8">
                采购管理/<span style="color:#65CEA7;">采购申请单</span>
            </header>
            <div class="col-md-4" style="height:50px;">

            </div>
            <div class="panel-body no-padding">
                <div class="adv-table">
                    <table class="table-bordered table-striped" id="applys">
                        <thead>
                        <tr>

                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                        <tfoot>
                        <tr>

                        </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </section>
    </div>
</div>
<!-- Button trigger modal -->

<!-- Modal 摸态框 -->
<div class="modal fade bs-example-modal-sm" onselectstart="return false;" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" id="prev" class="btn btn-primary btn-sm pull-right">上一个</button>
                <button type="button" id="next" class="btn btn-primary btn-sm pull-right">下一个</button>
                <h4 class="modal-title col-ls-8" id="myModalLabel">采购申请单</h4>
            </div>
            <div class="modal-body">
                <table id="material">
                    <thead>
                    <tr>

                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="openProByApps">走起</button>
            </div>
        </div>
    </div>
</div>
<form id="test" method="post" action="${basePath}/managent/toProByMaters" enctype="application/x-www-form-urlencoded">

</form>
<%@ include file="/WEB-INF/views/common/js.jsp" %>
<script src="${basePath}/assets/adminex/js/jquery.nicescroll.js"></script>
<!--bootstrap表格插件-->
<script src="${basePath}/assets/bootstrap-3.3.5-dist/table/bootstrap-table.js"></script>
<!--表格可移动插件-->
<script src="${basePath}/assets/js/table/colResizable-1.6.js"></script>
<!--自己的通用脚本-->
<script src="${basePath}/assets/js/common.js"></script>
<!--dynamic table initialization -->
<script src="${basePath}/assets/js/applyMaterial.js"></script>
<%--<script src="${basePath}/assets/js/tableMove.js"></script>--%>
</body>
</html>