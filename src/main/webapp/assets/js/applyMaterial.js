$(function () {
    var materialTable;
    var proId;      //订单id
    var modalTr;        //当前模态框打开后的实例
    var $sObject = $("#storages");  //仓库对象
    var $tableContaner = $("#applys");
    var putMaterList;   //入库材料集合
    var page = 1;           //当前页码
    var pageCount;          //总页数
    var applyObjectList = new Array();        //申请单集合
    var tax;
    loadStorages();
    /*
     *  获取仓库集合
     */

    /*
     * 初始化数据
     * */
    var applyColumns = [{
        field: 'project.name',
        sortable:true,
        title: '项目名称',
    }, {
        field: 'serialNumber',
        sortable:true,
        class: 'company',
        title: '申请单'
    }, {
        field: 'date',
        width:'90px',
        sortable:true,
        class: 'contract',
        title: '申请日期',
    }, {
        field: 'staff.name',
        title: '申请人',
        sortable:true,
        width: '70px'
    }, {
        field: 'stateMessage',
        title: '状态',
        sortable:true,
        align:"center",
        width: '55px'
    },  {
        field: 'remark',
        title: '备注',
        width: '200px'
    }, {
        field: 'operation',
        width: '150px',
        title: '操作'
    },{
        field:'tempPutState',
        visible:false,
        title:'入库状态',
    }
    ];
    /**
     * 材料列集合
     * @type {*[]}
     */
    var materialColumns = [{
        field: 'serialNumber',
        width: '50px',
        sortable:true,
        order:"desc",
        sortName:"serialNumber",
        title: '编号',
        class:'width-0'
    }, {
        field: 'name',
        width:'16%',
        class:'width-1',
        title: '材料名称'
    }, {
        field: 'model',
        title: '型号',
        class:'width-2',
        width:'20%px',
    }, {
        title: '单位',
        width:'50px',
        class:'width-3',
        field: 'unit.name'
    }, {
        title: '品牌',
        width:'100px',
        class:'width-4',
        field: 'brand'
    },{
        title: '申请数量',
        width:'100px',
        class:'width-5',
        field: 'sum'
    }, {
        title: '单价',
        width:'100px',
        class:'width-6',
        field: 'planPrice'
    }, {
        title: '金额',
        width:'120px',
        class:'width-7',
        field: 'moneyTax'
    }, {
        title: '备注',
        class:'width-8',
        width:'150px',
        field: 'remark'
    }, {
        title: '已采购',
        width:'100px',
        class:'width-9',
        field: 'ySum'
    }
    ];

    var applyRes = function (res) {
        pageCount = parseIntForZ((res.length / 10)+"");
        $(res).each(function (index, element) {
            this.operation = '<button class="btn btn-success btn-xs material" disabled="disabled" data-approve="1" type="button">采购</button>';
            this.remark = '<span title="'+this.remark+'">'+this.remark+'</span>';
            if(this.state == 0){        //未采购
                this.stateMessage = '<span class="glyphicon glyphicon-ok red" aria-hidden="true"></span>';
            }else if(this.state == 1){   //部分材料狗
                this.stateMessage = '<span class="glyphicon glyphicon-ok waring" aria-hidden="true"></span>';
            }else if (this.state == 2){      //完全采购
                this.stateMessage = '<span class="glyphicon glyphicon-ok success" aria-hidden="true"></span>';
            }
        });
        // alert(JSON.stringify(res));
        return res;
    };
    var rowIndex;       //申请单下标
    var rowObject;      //申请单原对象
    var applyObject;    //申请单提交对象
    var $title = $("#myModalLabel");
    //加载申请单集合
    var table = serverTable("applyByJson", {
        loadSuccess: function (data) {//            数据加载成功后调用
            var parent = $tableContaner.parent().parent().parent().parent();
            $(parent).find(".pagination-detail").remove();
            var $search = $(parent).find(".fixed-table-toolbar").appendTo($(parent).parent().prev()).end();
        }
    }, applyRes, applyColumns);
    $(table).bootstrapTable("refreshOptions",{
        onDblClickRow:function(row,$element,field){
            rowObject = row;
            rowIndex = $element.data("index");
            $title.html(row.serialNumber+"<i class='glyphicon glyphicon-italic color-red'></i><span title='"+row.project.name+"' class='btn-white'>"+row.project.name+"</span><i class='glyphicon glyphicon-italic color-red'></i><span>"+row.date+"</span>");
            $("#myModal").modal("show");
            if(materialTable != undefined){
                sync = true;
                materialTable.bootstrapTable("load",refreshMater(row.applyMaterialList));
            }else{
                materialTable = initTableLocal($("#material"),refreshMater(row.applyMaterialList), materialColumns);
            }
            $(materialTable).bootstrapTable("refreshOptions",{
                onClickRow:function(row2,$select,field){
                    if($select.find("span").is(".red")){
                        applyObjectList.splice(applyObjectList.indexOf(row2),1);
                        $select.find("span").removeClass("red");
                        row2.serialNumber = $select.find(".serial").prop("outerHTML");
                        row2.unit.name = $select.find(".unit").prop("outerHTML");
                        row2.model = $select.find(".model").prop("outerHTML");
                        row2.brand = $select.find(".brand").prop("outerHTML");
                        row2.name = $select.find(".name").prop("outerHTML");
                        row2.remark = $select.find(".remark2").prop("outerHTML");
                        row2.sum = $select.find(".sum").prop("outerHTML");
                        row2.planPrice = $select.find(".price").prop("outerHTML");
                        row2.moneyTax = $select.find(".moneytax").prop("outerHTML");
                        row2.ySum = $select.find(".ysum").prop("outerHTML");
                    }else{
                        applyObjectList.push(row2);
                        $select.find("span").addClass("red");
                        row2.serialNumber = $select.find(".serial").prop("outerHTML");
                        row2.unit.name = $select.find(".unit").prop("outerHTML");
                        row2.model = $select.find(".model").prop("outerHTML");
                        row2.brand = $select.find(".brand").prop("outerHTML");
                        row2.name = $select.find(".name").prop("outerHTML");
                        row2.remark = $select.find(".remark2").prop("outerHTML");
                        row2.sum = $select.find(".sum").prop("outerHTML");
                        row2.planPrice = $select.find(".price").prop("outerHTML");
                        row2.moneyTax = $select.find(".moneytax").prop("outerHTML");
                        row2.ySum = $select.find(".ysum").prop("outerHTML");
                    }
                    $(materialTable).bootstrapTable("updateRow",{
                       index:$select.data("index"),
                        row:row2,
                    });
                },
                sortOrder:'serialNumber asc',
            });
            initScroll($(".fixed-table-body"));

        }
    });
    //注册上一页，下一页按钮事件
    $("#next,#prev").on("click",function () {
        var $this = $(this);
        $this.attr("disabled",true);
        var id;
        if ($(this).attr("id") == "next") {     //下一页
            id = $($tableContaner.children("tbody").children("tr")[++rowIndex]).data("uniqueid");
            if(id != undefined){
                rowObject = getRowDate(id);
                $title.html(rowObject.serialNumber+"<i class='glyphicon glyphicon-italic color-red'></i><span title='"+rowObject.project.name+"' class='btn-white'>"+rowObject.project.name+"</span><i class='glyphicon glyphicon-italic color-red'></i><span>"+rowObject.date+"</span>");
                materialTable.bootstrapTable("load",refreshMater(rowObject.applyMaterialList));
            }else if(page < pageCount){
                $(table).bootstrapTable("nextPage");
                rowIndex = 0;
                page++;
                $(this).trigger("click");
            }else{
                alert("到底啦");
                rowIndex--;
            }
        } else {    //上一页
            id = $($tableContaner.children("tbody").children("tr")[--rowIndex]).data("uniqueid");
            if(id != undefined){
                rowObject = getRowDate(id);
                $title.html(rowObject.serialNumber+"<i class='glyphicon glyphicon-italic color-red'></i><span title='"+rowObject.project.name+"' class='btn-white'>"+rowObject.project.name+"</span><i class='glyphicon glyphicon-italic color-red'></i><span>"+rowObject.date+"</span>");
                materialTable.bootstrapTable("load",refreshMater(rowObject.applyMaterialList));
            }else if(page != 1){
                $(table).bootstrapTable("prevPage");
                rowIndex = 10;
                page--;
                $(this).trigger("click");
            }else{
                alert("到底啦");
                rowIndex++;
            }
        }
        // applyObject = $.extend(true,{},rowObject);
        var temp = setTimeout(function(){
            $this.attr("disabled",false);
            window.clearTimeout(temp);
        },500);
    });


    var filter = {voucherName:undefined,state:undefined,tempPutState:undefined};
    //注册筛选按钮事件
    $(".select-top-filter").click(function () {
        var value = $(this).text();
        var temp;
        switch ($(this).attr("id")) {
            case "select-myPro":        //我的订单
                if($(this).data("myis")){
                    $(this).css("background-color","rgba(91, 192, 222, 0.72)");
                    filter.voucherName = $(this).data('person');
                    $(this).data("myis",false);
                }else{
                    $(this).css("background-color","#5bc0de");
                    $(this).data("myis",true);
                    filter.voucherName = undefined;
                }
                break;
            case "select-approve":      //已审核或未审核
                if (value == "已审核") {
                    $(this).css("background-color","rgba(91, 192, 222, 0.72)");
                    $(this).text("未审核");
                    filter.state = $(this).text();
                } else if(value == "未审核"){
                    $(this).css("background-color","#5bc0de");
                    $(this).text("未|审");
                    filter.state = undefined;
                } else{
                    $(this).css("background-color","rgba(91, 192, 222, 0.72)");
                    $(this).text("已审核");
                    filter.state = $(this).text();
                }
                break;
            case "select-put":          //可入库
                if($(this).data("put")){
                    $(this).css("background-color","rgba(91, 192, 222, 0.72)");
                    filter.tempPutState = 1;
                    $(this).data("put",false);
                }else{
                    $(this).css("background-color","#5bc0de");
                    filter.tempPutState = undefined;
                    $(this).data("put",true);
                }
                break;
        }
        temp = filter;
        for (var test in temp){
            if(temp[test] == undefined){
                delete temp[test];
            }
        }
        $(table).bootstrapTable("filterBy",temp);
    });

    //注册更新时输入框事件
    $("#update-modal-body").on("input", "#company,input[name='company.ID']", function () {
        var input = event.target;
        switch ($(input).attr('id')) {
            case 'company':
                if ($(input).val() == "") {
                    url = 'http://' + webIp + '/managent/getCompanys';
                    params = {index: 0};
                } else {
                    url = 'http://' + webIp + '/managent/seek';
                    params = {name: $(input).val()};
                }
                initCompany(input, url, params);
                break;
            case 'contract':
                if ($(input).val() == "") {
                    url = 'getCompanys';
                    params = {index: 0};
                } else {
                    url = 'seek';
                    params = {name: $(input).val()};
                }
                initCompany(input, url, params);
                break;
            case 'tax':
                tax = $(input).val();
                if (!/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(tax)) {
                    $(input).val(17);
                    break;
                }
                break;
        }
    });
    //合同获取焦点事件
    $("#contract").focus(function () {
        initContract(this, 'ByComId', {comId: $("#company").next().next().val(), index: 0});
    });
    //模态框关闭事件
    $("#selectPut").on("hide.bs.modal", function () {
        //关闭模态框后的处理
    })
    //注册选择入库关闭按钮事件
    $("#closeSelect").click(function () {
        $("#selectPut").modal("hide");
    })

    $("#openProByApps").click(function(){
        var inputs = new Array();
        $(applyObjectList).each(function (index,val) {
            inputs[index] = '<div class="appMaters">' +
                '<input type="hidden" name="material['+index+'].major" value="'+this.clone.major+'"/>' +
                '<input type="hidden" name="material['+index+'].ID" value="'+this.clone.iD+'"/>' +
                '<input type="hidden" name="material['+index+'].name" value="'+this.clone.name+'"/>' +
                '<input type="hidden" name="material['+index+'].unit.ID" value="'+this.clone.unit.iD+'"/>' +
                '<input type="hidden" name="material['+index+'].brand" value="'+this.clone.brand+'"/>' +
                '<input type="hidden" name="material['+index+'].model" value="'+this.clone.model+'"/>' +
                '<input type="hidden" name="material['+index+'].producingArea" value="'+this.clone.producingArea+'"/>' +
                '<input type="hidden" name="material['+index+'].planPrice" value="'+this.clone.planPrice+'"/>' +
                '<input type="hidden" name="material['+index+'].apply.projectID" value="'+this.clone.apply.projectId+'"/>' +
                '<input type="hidden" name="material['+index+'].apply.ID" value="'+this.clone.apply.iD+'"/>' +
                '<input type="hidden" name="material['+index+'].serialNumber" value="'+this.clone.serialNumber+'"/>' +
                '<input type="hidden" name="material['+index+'].applyDate" value="'+this.clone.applyDate+'"/>' +
                '<input type="hidden" name="material['+index+'].sum" value="'+this.clone.sum+'"/>' +
                '<input type="hidden" name="material['+index+'].remark" value="'+this.clone.apply.project.name+'"/>' +
                '<input type="hidden" name="material['+index+'].notSum" value="'+this.clone.notSum+'"/>' +
                '<input type="hidden" name="material['+index+'].ySum" value="'+this.clone.ySum+'"/>' +
                '</div>';
        });
        $("#test").append(inputs);
        $("#test").submit();
    })

    /**
     * 下拉项选中时触发
     */
    $(".dropdown").on("click", "li", function () {
        var $ul = $(this).parent();
        if ($ul.attr("aria-labelledby") == "selectDate") {    //根据时间加载数据
            //判断选择的日期是否符合重新加载数据
            var type = $ul.prev().children(".btn-info").data("type");
            if (type == $(this).data("type")) {
                return;
            }
            var start;
            var end;
            var date;
            switch ($(this).data("type")) {
                case "thisM":   //本月
                    start = getfirstDate(new Date());
                    end = getEndDate(new Date());
                    break;
                case "prevM":   //上月
                    date = getPreMonth(getNowDate());
                    start = getfirstDate(new Date(date));
                    end = getEndDate(new Date(date));
                    break;
                case "haifY":   //半年
                    start = getfirstDate(getHaifDate());
                    end = getNowDate();
                    break;
                case "thisY":   //本年
                    date = new Date();
                    date.setMonth(0);
                    start = getfirstDate(date);
                    end = getNowDate();
                    break;
                case "all":     //所有
                    start = null;
                    end = null;
                    break;
            }
            ;
            $ul.prev().children(".btn-info").text($(this).text()).data("type", $(this).data("type"));
            $(table).bootstrapTable("refresh", {url: 'getProList?index=1&start=' + start + "&end=" + end});
        } else {      //流程选择后的处理
            var id = $ul.prev().attr("id");
            if (id == "contract") {
                inputValue({name: $(this).data('company'), id: $(this).data('comid')}, $("#company"));
            } else if (id == "company") {
                inputValue({name: "", id: ""}, $("#contract"));
                inputValue({name: $(this).text(), id: $(this).data('cid')}, $ul.prev());
            }
            inputValue({name: $(this).text(), id: $(this).data('cid')}, $ul.prev());
        }
    });
    //修改按钮点击事件
    $("#update-submit").click(function () {
        var company = $("input[name='company.ID']").val();
        var contract = $("input[name='contract.ID']").val();
        var tax = $("#tax").val();
        if (tax != "" && company != "" && proId != undefined && proId != "") {
            $.ajax({
                url: "http://" + webIp + "/managent/updatePro",
                type: "post",
                async: false,    //同步更新
                data: "tax=" + tax + "&company.ID=" + company + "&contract.ID=" + contract + "&ID=" + proId,
                success: function (state) {
                    if (state != "-1") {
                        var temp = state.split(",")
                        $(modalTr).children(".company").text(temp[0]);
                        $(modalTr).children(".contract").text(temp[1]);
                        $("#update").modal("hide");
                        return;
                    }
                    alert("更新失败...好奇怪");
                }
            })
        }
    });

    /**
     * 处理材料数据
     */
    var tempAyyar = new Array();
    function refreshMater(maters){
        if(tempAyyar.indexOf(maters) != -1){
            return maters;
        }
        $(maters).each(function (index, element) {
            this.clone = $.extend(true,{},element);
            this.clone.apply = $.extend({},rowObject);  //浅层复制申请单对象
            this.clone.apply.projectId = rowObject.project.iD;
            this.serialNumber = '<span class="serial">'+this.serialNumber+'</span>';
            this.unit.name = '<span class="unit">'+this.unit.name+'</span>';
            this.moneyTax = this.sum * this.planPrice;
            this.model = "<span class='model' title='"+this.model+"'>"+this.model+"</span>";
            this.name = "<span class='name' title='"+this.name+"'>"+this.name+"</span>";
            this.brand = "<span class='brand' title='"+this.brand+"'>"+this.brand+"</span>";


            this.remark = "<span class='remark2' title='"+this.remark+"'>"+this.remark+"</span>";


            this.sum = '<span class="sum">'+this.sum+'</span>';
            this.planPrice = '<span class="price">'+this.planPrice+'</span>';
            this.moneyTax = '<span class="moneytax">'+this.moneyTax+'</span>';
            this.ySum = '<span class="ysum">'+this.ySum+'</span>';
        });
        tempAyyar.push(maters);
        return maters;
    }

    /**
     * 服务端分页
     * @returns {jQuery}
     */
    function serverTable(url, param, res, columns) {
        var table = $tableContaner.bootstrapTable({
            showHeader:true,       //是否显示列头
            url: url,
            method: 'get',           //请求方式（*）
            toolbar: '#toolbar',        //工具按钮用哪个容器
            striped: true,           //是否显示行间隔色
            cache: false,            //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            sortable: true,           //是否启用排序
            sortOrder: "asc",          //排序方式
            // queryParams: param,//传递参数（*）
            queryParamsType: 'limit',      //设置参数类型为restfull风格
            sidePagination: "client",      //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,            //初始化加载第一页，默认第一页
            pageSize: 10,            //每页的记录行数（*）
            pageList: [],    //可供选择的每页的行数（*）
            strictSearch: false,             //false:模糊搜索,true精确搜索
            clickToSelect: false,        //是否启用点击选中行
            height: "550",            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            pagination: true,          //是否显示分页（*）
            uniqueId: "iD",           //每一行的唯一标识，一般为主键列
            cardView: false,          //是否显示详细视图
            detailView: false,          //是否显示父子表
            responseHandler: res,       //服务器返回数据后的处理
            columns: columns,             //列集合
            search: true,                //是否开启搜索
            showFooter: false,
            queryParams: function (params) {
                // params = param;
                // alert(JSON.stringify(param)+param.applyId);
                return params;
            },
            onLoadSuccess: param.loadSuccess,
        });
        return table;
    }

    /**
     * 初始化供应单位数据
     * @param input
     */
    function initCompany(input, url, params) {
        initScroll($(".dropdown-menu"));
        $(input).next().children().remove();
        var res;
        res = initData(url, params);
        res.done(function (data) {
            $(data).each(function () {
                $(input).next().append('<li data-cid="' + this.iD + '"><a href="#">' + this.name + '</a></li>');
            });
        });
    }

    function initData(url, params) {
        return $.ajax({
            url: url,
            dataType: 'json',
            data: params,
            type: 'POST',
        });
    }

    /**
     * 加载合同数据
     * @param input 输入框对象
     * @param url 加载地址
     * @param comId 单位id
     */
    function initContract(input, url, comId) {
        var response = initData(url, comId);
        response.done(function (contracts) {
            $(input).next().children().remove();
            $(contracts).each(function () {
                $(input).next().append('<li data-company="' + this.partyB.name + '" data-comId="' + this.partyB.iD + '" ' +
                    'data-cid="' + this.iD + '"><a href="#">' + this.name + '</a></li>');
            });
        })
    }

    /**
     * input标签赋值
     * @param value
     * @param input
     */
    function inputValue(values, input) {
        $(input).val(values.name);
        $(input).next().next().val(values.id);
    }

    /**
     * 加载仓库集合
     */
    function loadStorages() {
        var $sUl = $sObject.next();
        var res = initData("http://" + webIp + "/managent/getStorages", null);
        //服务器返回的数据
        res.done(function (storages) {
            if (storages[1] != undefined) {
                $sObject.children(".label").text(storages[1].name);
                $sObject.children(1).val(storages[1].iD);
            }
            $(storages).each(function () {
                $sUl.append('<li data-storage="' + this.iD + '"><a href="#body">' + this.name + '</a></li>');
            })
        });
        //滚动条初始化
        initScroll($(".dropdown-menu"));
        $sUl.on("click", "li", function () {
            $sObject.children(".label").text($(this).text());
            $sObject.children(1).val($(this).data("storage"));
        })
    }

    /**
     * 添加入库单
     */
    function addPutStorage(putStorage, but, url) {
        $.ajax({
            url: 'http://' + webIp + '/managent/' + url,
            type: 'post',
            contentType: 'application/json',
            data: JSON.stringify(putStorage),
            async: false,        //同步
            success: function (state) {
                switch (url) {
                    case "addPutStorage":   //一件入库
                        break;
                    case "addPutStorageSelect": //选择入库
                        $("#closeSelect").trigger("click"); //关闭模态框
                        break;
                }
                if (state == 4) {   //完全入库
                    putStorage = undefined;
                    var material = $(but).next().find(".material").prop("outerHTML");       //详情按钮
                    material += '<button class="btn btn-success btn-xs update" type="button">修改</button>';
                    updateCell(table, $(but).closest("tr").data("index"), "operation", material);
                } else if (state == 3) {     //部分入库
                    putStorage = undefined;
                } else {
                    alert("入库失败");
                }
            }
        })
    }

    /**
     * 加载未入库的材料集合
     * @param pro
     */
    var selectMaterO = undefined;

    /**
     * 加载订单中未入库的材料集合
     * @param pro 订单对象
     */
    var procurent;
    var div;

    function selectMater(pro, but) {
        procurent = pro;
        div = but;
        putMaterList = new Array();     //创建入库材料集合数组
        tax = pro.tax;
        if (selectMaterO != undefined) {
            //刷新数据
            selectMaterO.bootstrapTable("refresh", {url: "getNotProMaterial?id=" + pro.proId});
            return;
        } else {  //初始化
            selectMaterO = initTable($("#selectMater"), "getNotProMaterial?id=" + pro.proId, {
                size: 10, height: "450", pagination: false, loadSuccess: function (data) {
                    var parent = $(table).parent().parent().parent().parent();
                    var $search = $(parent).find(".fixed-table-toolbar").appendTo($(parent).prev()).end();
                    initChebox();
                }, search: true
            }, function (data) {
                $(data).each(function (index, val) {
                    this.serial = '<div class="checkbox">' +
                        '<input type="checkbox" name="materialList[' + index + ']">' +
                        '</div>';
                    this.putSum = (this.sum - this.inSum);        //入库数量等于采购数量减去已入库的数量
                    this.material.name = '<span class="label label-xs btn-success maters" ' +
                        'data-project="' + this.projectId + '" ' +  //项目id
                        'data-pro-mater-id="' + this.iD + '" ' +       //订单材料主键id
                        'data-price="' + this.price + '"' +      //不含税单价
                        'data-money="' + this.money + '"' +      //不含税金额
                        'data-taxMoney="' + this.taxMoney + '"' +      //税额
                        'data-index="' + index + '"' +      //下标
                        '>' + this.material.name + '</span>';
                    this.unit.name = '<span class="label label-sm btn-info unit-object" data-unit="' + this.unit.iD + '" data-mater="' + this.material.iD + '">' + this.unit.name + '</span>';
                });
                return data;
            }, [{
                field: 'serial',
                width: '45px',
                class: 'serial',
                title: '选择',
            }, {
                field: 'material.name',
                width: '147px',
                class: 'mater',
                title: '材料名称',
            }, {
                field: 'material.model',
                width: '139px',
                title: '型号',
                class: 'model',
            }, {
                field: 'unit.name',
                title: '单位',
                width: '42px',
                class: 'unit',
            }, {
                field: 'sum',
                title: '采购数量',
                width: '116px',
                class: 'sum',
            }, {
                field: 'inSum',
                width: '50px',
                title: '已入库数量',
                class: 'inSum',
            }, {
                field: 'putSum',
                width: '116px',
                title: '数量',
                class: 'putSum edit',
            }, {
                field: 'priceTax',
                width: '116px',
                title: '单价',
                class: 'priceTax edit',
            }, {
                field: 'moneyTax',
                title: '金额',
                class: 'moneyTax',
            },
            ]);
        }
        //注册点击事件
        $(selectMaterO).bootstrapTable("refreshOptions", {
            onClickCell: function (field, value, row, $element) {
                if ($element.is('.edit')) {
                    insertInput($element, value);
                } else {
                    var $check = $element.find("ins");
                    $check.trigger('click');
                }
            }
        });
        //注册入库按钮提交事件
        $("#putStorage").click(function () {
            var putMaterTr = $("#selectMater").find(".put-storage-ok");
            if (putMaterTr.length <= 0 || putMaterTr.length == undefined) {
                return;
            }
            procurent.materialList = new Array();     //初始化入库单材料集合
            var $mater;     //信息集合{}
            var $unit;   //单位对象
            //遍历选中的材料，并添加到数组中
            $(putMaterTr).each(function () {
                $mater = $(this).find(".maters");
                $unit = $(this).find(".unit-object");
                procurent.materialList.push(
                    {
                        material: {     //材料对象
                            iD: $unit.data("mater"),
                            unit: {
                                iD: $unit.data("unit")
                            }
                        },
                        inSum: $(this).find(".sum").text(),  //应该入库的数量
                        putSum: $(this).find(".putSum").text(),      //实际入库数量
                        price: $mater.data("price"), //不含税单价
                        money: $mater.data("money"), //不含税金额
                        taxPrice: $(this).find(".priceTax").text(),      //含税单价
                        moneyTax: $(this).find(".moneyTax").text(),      //含税金额
                        taxMoney: $mater.data("taxmoney"),               //税额
                        proMaterId: $mater.data("pro-mater-id"),         //采购订单材料表主键id
                        projectId: $mater.data("project"),               //项目id
                    }
                );
            });
            addPutStorage(procurent, div, "addPutStorageSelect");
        })

    }

    /**
     * 初始化多选
     */
    function initChebox() {
        //材料选中事件
        $('input[type="checkbox"]').on('ifChecked', function () {
            //添加class
            $(this).closest("tr").addClass("put-storage-ok");
        });
        //材料移除事件
        $("input[type='checkbox']").on('ifUnchecked', function () {
            //删除class
            $(this).closest("tr").removeClass("put-storage-ok");
        });
        $('input[type="checkbox"]').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '10%', // optional
        });
    }

    function edit() {
        $("#selectMater").on('click', '.edit', function (event) {
            var td = event.target;
            if (!$(td).is("td")) {
                td = $(td).parent();
            }
            var $span = $(td).children(0);
            var text = $(td).text();
            insertInput($(td), text, $span);
        });
    }

    /**
     * 添加输入框
     * @param elem
     * @param val
     * @param $span
     */
    var input = undefined;
    var editMessage = '<input type="text" style="width:100%;" id="edit_message"/>';

    function insertInput(elem, val, $span) {
        if (input != undefined) {
            return;
        }
        $(elem).text("");

        $(elem).on("blur", "#edit_message", function () {
            val = $(this).val() != "" ? $(this).val() : val;
            if (!/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(val) || parseFloat(val) <= 0) {//判断输入的值是否合法
                $(this).addClass("border-waring").focus();
                return;
            }
            var $td = $(this).parent();
            var $tr = $(this).closest("tr");
            var $priceTax = $tr.find(".priceTax");     //获取含税单价标签
            var $maters = $tr.find(".maters");     //获取信息集合标签
            var $putSum = $tr.find(".putSum");      //入库数量标签
            var $moneyTax = $tr.find(".moneyTax");  //含税总金额标签
            $td.text(val);
            var sum_Price;      //含税总金额
            var priceTax;      //含税单价
            var money;          //不含税金额
            var taxMoney;       //税额
            var price;          //不含税单价
            if ($td.is(".putSum")) {      //入库数量
                sum_Price = val * parseFloat($priceTax.text());     //计算含税总金额
                priceTax = parseFloat($priceTax.text());            //含税单价


                money = sum_Price / (parseInt(1) + (tax / 100));        //不含税金额 = 含税总金额 / (1 + 税率/100)
                taxMoney = sum_Price - money;            //税额 = 含税金额 - 不含税金额

                $maters.attr("data-money", money);            //设置不含税金额
                $maters.attr("data-taxmoney", taxMoney);      //设置税额
            } else if ($td.is(".priceTax")) {  //单价
                sum_Price = parseFloat($putSum.text()) * val;       //计算含税总金额
                priceTax = val;            //含税单价


                money = sum_Price / (parseInt(1) + (tax / 100));        //不含税价 = 含税总金额 / (1 + 税率/100)
                taxMoney = sum_Price - money;            //税额 = 含税金额 - 不含税金额
                price = money / parseFloat($putSum.text());        //不含税单价 = 不含税金额 / 入库数量


                $maters.attr('data-price', price);            //设置不含税单价
                $maters.attr("data-money", money);            //设置不含税金额
                $maters.attr("data-taxmoney", taxMoney);      //设置税额
            }


            $moneyTax.text(sum_Price);      //设置标签值

            $(this).remove();
            input = undefined;
        });
        input = $(editMessage).appendTo(elem).focus().end();
        //判断是否是时间选择，时间插件不能注册失去焦点事件
        $(input).keydown(function (event) {
            var code = event.keyCode;
            var tr = $(elem).parent();
            //判断输入类型
            var style;
            if ($(elem).is(".planPrice")) {
                style = "planPrice";
            } else if ($(elem).is(".sum")) {
                style = 'sum';
            } else {
                style = 'placeDate'
            }
            var nexTr = undefined;
            var success = false;
            switch (code) {
                case 40:
                    nexTr = $(tr).next().get(0);
                    //下箭头
                    success = true;
                    break;
                case 38:
                    nexTr = $(tr).prev().get(0);
                    success = true;
                    break;
            }
            if (nexTr != undefined) {
                var td = $(nexTr).find("." + style);
                //触发input失去焦点事件
                $(this).blur();
                insertInput($(td), $(td).text(), $(td).children(0));
            } else if (success) {
                $(this).blur();
                $(this).unbind('keydown');
            }
        })
    }

    /**
     * 更新bootstrapTable列值
     */
    function updateCell(table, index, field, value) {
        $(table).bootstrapTable("updateCell", {
            index: index,
            field: field,
            value: value,
        })
    }

    function getRowDate(id){
        return $(table).bootstrapTable("getRowByUniqueId",id);
    }
});

