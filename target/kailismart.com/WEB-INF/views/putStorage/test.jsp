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
</head>
<body>
<button type="button" id="print">打印</button>
<%@ include file="/WEB-INF/views/common/js.jsp" %>
<script src="${basePath}/assets/adminex/js/jquery.nicescroll.js"></script>
<!--bootstrap表格插件-->
<script src="${basePath}/assets/bootstrap-3.3.5-dist/table/bootstrap-table.js"></script>
<!--icheck -->
<script src="${basePath}/assets/adminex/js/iCheck/jquery.icheck.js"></script>
<script src="${basePath}/assets/js/jquery.jqprint-0.3.js"></script>
<script src="${basePath}/assets/js/jquery-migrate-1.1.0.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#print").click(function () {
            $("#printCon").jqprint();
            $('#printCon').jqprint({operaSupport: true});
        })
    })
</script>
</body>
</html>