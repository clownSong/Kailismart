/**
 * Created by 宋正根 on 2016/9/2.
 */
$(function () {
    var materialTable;
    var putObject;      //入库单对象
    var modalTr;        //当前模态框打开后的实例
    var $sObject = $("#storages");  //仓库对象
    var putMaterList;   //入库材料集合
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
        width: '120px',
        sortable: true,
        title: '项目名称',
    }, {
        field: 'outNumber',
        width: '120px',
        class: 'company',
        sortable: true,
        title: '领料单号'
    }, {
        field: 'outDate',
        width: '120px',
        class: 'contract',
        sortable: true,
        title: '领料日期',
    }, {
        field: 'outPerson.name',
        title: '领料人',
        sortable: true,
        width: '90px'
    }, {
        field: 'company.name',
        title: '领料单位',
        sortable: true,
        width: '100px'
    }, {
        field: 'type',
        title: '状态',
        sortable: true,
        width: '30px'
    }, {
        field: 'state',
        title: '状态',
        visible: false,
    }, {
        field: 'operation',
        align: "center",
        width: '80px',
        title: '操作'
    }
    ];
    /**
     * 材料列集合
     * @type {*[]}
     */
    var materialColumns = [
        {
            field: 'index',
            width: '38px',
            title: '序号'
        }, {
            field: 'material.name',
            width: '200px',
            title: '材料名称'
        }, {
            title: '型号',
            width: '130px',
            field: 'material.model'
        }, {
            title: '单位',
            width: '60px',
            field: 'material.unit.name'
        }, {
            title: '数量',
            width: '100px',
            field: 'sum'
        }, {
            title: '领用单价',
            width: '70px',
            field: 'taxPrice'
        }, {
            title: '合计金额',
            width: '100px',
            field: 'taxMoney'
        }, {
            title: '税额',
            width: '50px',
            field: 'taxMoney'
        }
    ];

    //服务器返回数据的回调
    var putRes = function (res) {
        $(res).each(function (index, element) {
            if(this.project == undefined){
                this.project = {name:"项目不存在"};
            }
            if(this.company == undefined){
                this.company = {name:"单位不存在"};
            }
            this.printP = this.company.name;

            this.projectName = this.project.name;
            this.project.name='<span title="'+this.project.name+'">'+this.project.name+'</span>';
            this.company.name = '<span title="'+this.company.name+'">'+this.company.name+'</span>';
            if (this.state == 0) {      //未审核
                this.operation = '<button class="btn btn-danger btn-xs delete" type="button">删除</button>' +
                    '<button class="btn btn-success btn-xs approve" data-approve="' + this.state + '" type="button">审核</button>'+
                    '<button class="btn btn-success btn-xs print" type="button">打印</button>';
                this.type = '<span class="glyphicon glyphicon-ok alert-danger" aria-hidden="true"></span>';
            } else {      //已审核
                this.operation = '<button class="btn btn-danger btn-xs delete" disabled="disabled" type="button">删除</button>' +
                    '<button class="btn btn-success btn-xs approve" data-approve="' + this.state + '" type="button">反审核</button>'+
                    '<button class="btn btn-success btn-xs print" type="button">打印</button>';
                this.type = '<span class="glyphicon glyphicon-ok alert-success" aria-hidden="true"></span>';
            }
        });
        return res;
    };
    //默认加载本月出库单
    var table = serverTable("getMaterOuts?start=" + getfirstDate(new Date()) + "&end=" + getEndDate(new Date()) + "", {
        loadSuccess: function (data) {//            数据加载成功后调用
            var parent = $("#applys").parent().parent().parent().parent();
            $(parent).find(".pagination-detail").remove();
            var $search = $(parent).find(".fixed-table-toolbar").appendTo($(parent).parent().prev()).end();
        }
    }, putRes, applyColumns);
    //详细信息元素集合
    var putStorage = {
        pro: $("#pro"),
        company: $("#company"),
        tax: $("#tax"),
        mixMoney: $("#mixMoney"),
        remark: $("#remark"),
        putDate: $("#putDate"),
        put: $("#put")
    };
    //注册双击行事件
    table.bootstrapTable("refreshOptions", {
        onDblClickRow: function (row, $element, field) {
            putObject = row;
            $(putStorage.pro).html(putObject.project.name);     //项目名称
            $(putStorage.put).val(putObject.outNumber);                //出库单编号赋值
            $(putStorage.company).html(putObject.company.name);         //供应单位赋值
            $(putStorage.tax).val(putObject.tax);              //税率赋值
            $(putStorage.remark).val(putObject.remark);              //备注赋值
            $(putStorage.putDate).val(putObject.outDate);              //入库时间
            if(putObject.approveType == 0){
                $("#myModal input,textarea").attr("disabled",false);
                $("#pass").text("审核");
            }else{
                $("#myModal input,textarea").attr("disabled",true);
                $("#pass").text("反审核");
            }
            if(materialTable != undefined){
                $(materialTable).bootstrapTable(("refresh"),{url:"/managent/getChildByOutId?outId=" + row.iD});
            }
            materialTable = initTable($("#putMaterial"), "/managent/getChildByOutId?outId=" + row.iD, {}, function (materials) {
                $(materials).each(function (index, val) {
                    this.index = index;
                });
                return materials;
            }, materialColumns);
            $("#myModal").modal("show");
        }
    })

    //注册删除和审核事件
    $("#applys").on("click", ".delete,.approve,.print", function () {
        if ($(this).is(".approve")) { //审核|反审核按钮
            approvePut($(this).closest("tr").data("uniqueid"), $(this));
        } else if($(this).is(".print")){  //打印
            appendPrint($(table).bootstrapTable("getRowByUniqueId", $(this).closest("tr").data("uniqueid")));
        }else{  //删除

        }
    });
    //注册模态框pass按钮事件
    $("#pass").click(function(){
        approvePut(putObject.iD,$(this));
    });
    //注册输入框失去焦点事件
    var parentText;
    $("#myModal input,textarea").focus(function(){
        parentText = $(this).val();
    })
    $("#myModal input,textarea").blur(function(){
        var type =  $(this).data("type");
        if(parentText == $(this).val()){
            return;
        }
        switch (type){
            case 1:     //更新订单号
                putObject.putSerial = $(this).val();
                type = 1;
                break;
            case 2:     //更新税率
                type = 2;
                putObject.tax = $(this).val();
                break;
            case 3:     //更新运杂费
                type = 3;
                putObject.mixMoney = $(this).val();
                break;
            case 4:     //更细入库时间
                putObject.putDate = $(this).val();
                type = 4;
                break;
            case 5:     //更新备注
                type = 5;
                putObject.remark = $(this).val();
                break;
            default:
                type = -1;
                break;
        }
        if(type == 2){
            updatePutTax();     //更新税率
        }else{
            updatePutStorage();     //更新订单
        }
    });



    var filter = {state: undefined, state: undefined, tempPutState: undefined};
    //注册筛选按钮事件
    $(".select-top-filter").click(function () {
        var value = $(this).text();
        var temp;
        switch ($(this).attr("id")) {
            case "select-approve":      //已审核或未审核
                if (value == "已审核") {
                    $(this).css("background-color", "rgba(91, 192, 222, 0.72)");
                    $(this).text("未审核");
                    filter.state = 0;
                } else if (value == "未审核") {
                    $(this).css("background-color", "#5bc0de");
                    $(this).text("未|审");
                    filter.state = undefined;
                } else {
                    $(this).css("background-color", "rgba(91, 192, 222, 0.72)");
                    $(this).text("已审核");
                    filter.state = 1;
                }
                break;
        }
        temp = filter;
        for (var test in temp) {
            if (temp[test] == undefined) {
                delete temp[test];
            }
        }
        $(table).bootstrapTable("filterBy", temp);
    })

    //模态框关闭事件
    $("#selectPut").on("hide.bs.modal", function () {
        //关闭模态框后的处理
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
            $(table).bootstrapTable("refresh", {url: 'getMaterOuts?index=1&start=' + start + "&end=" + end});
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

    /**
     * 服务端分页
     * @returns {jQuery}
     */
    function serverTable(url, param, res, columns) {
        var table = $("#applys").bootstrapTable({
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

    function initData(url, params) {
        return $.ajax({
            url: url,
            dataType: 'json',
            data: params,
            type: 'POST',
        });
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

    function updateRow(index, row) {
        $(table).bootstrapTable("updateRow", {
            index: index,
            row: row,
        });
    }

    /**
     * 过滤表格中已审核数据或未审核
     */
    function filterBy(table, value) {
        $(table).bootstrapTable("filterBy", {"state": value});
    }

    /**
     * 审核入库单方法
     * id 入库单id
     *  index 行下标
     */
    function approvePut(id, btn) {
        var temp = $(table).bootstrapTable("getRowByUniqueId", id);
        $.ajax({
            url: "http://" + webIp + "/managent/approveOut",
            data: "outId="+temp.iD,
            type: 'post',
            success: function (mess) {
                if (mess != -1) {
                    switch (temp.state) {
                        case 0:
                            temp.state = 1;
                            temp.operation = '<button class="btn btn-danger btn-xs delete" disabled="disabled" type="button">删除</button>' +
                                '<button class="btn btn-success btn-xs approve" data-approve="1" type="button">反审核</button>'+
                                '<button class="btn btn-success btn-xs print" type="button">打印</button>';
                            break;
                        case 1:
                            temp.state = 0;
                            temp.operation = '<button class="btn btn-danger btn-xs delete" type="button">删除</button>' +
                                '<button class="btn btn-success btn-xs approve" data-approve="0" type="button">审核</button>'+
                                '<button class="btn btn-success btn-xs print" type="button">打印</button>';
                            break;
                    }
                    updateRow(temp.index,temp);
                    if($(btn).attr("id") == "pass"){
                        $(btn).text("r pass");
                    }
                } else {
                    alert("审核失败");
                }
            }
        })
    }

    /**
     * 更新入库单信息
     */
   /* function updatePutStorage(){
        return $.ajax({
            url:'http://'+webIp+"/managent/updatePutMessage",
            data: JSON.stringify(putObject),
            type: 'post',
            contentType: "application/json;charset=utf-8",
        })
    }*/

    /**
     * 更新入库单税率
     */
   /* function updatePutTax(){
        $.ajax({
            url:"http://"+webIp+"/managent/updatePutTax",
            data:JSON.stringify(putObject),
            type: 'post',
            contentType: "application/json;charset=utf-8",
            success:function(mess){
                if(mess != -1){
                    alert("修改成功");
                }
            }

        })
    }*/

    /**
     * 添加材料到打印页面
     * @param putObject
     */
    function appendPrint(putObject) {
        $("#materPrint_message").children().remove();


        $("#putNumber").children(0).text(putObject.outNumber);
        $("#proNumber").children(0).text(putObject.projectame);
        $("#storage").children(0).text(putObject.storage.name);
        $("#company_print").children(0).text(putObject.printP);
        $("#print_data").children(0).text(putObject.NoutDate);
        $("#print_person").children(0).text(putObject.staff.name);
        $("#print_remark").children(0).text(putObject.remark);
        if(putObject.materOuts == undefined){
            $.ajax({
                url:'http://'+webIp+'/managent/getChildByOutId',
                type:'post',
                data:'outId='+putObject.iD,
                success:function (mater) {
                    putObject.materOuts = mater;

                    var sum = 0;
                    var taxSum = 0;
                    $(putObject.materOuts).each(function (index,val) {
                        sum += this.taxMoney;
                        // taxSum += this.moneyTax - this.taxMoney;     //含税金额 - 税额 = 不含税金额
                        $("#materPrint_message").append('<tr>' +
                            '<td>'+(index+1)+'</td>' +
                            '<td>'+this.material.iD+'</td>' +
                            '<td>'+this.material.name+'</td>' +
                            '<td>'+this.material.model+'</td>' +
                            '<td>'+this.material.unit.name+'</td>' +
                            '<td>'+this.sum+'</td>' +
                            '<td>'+this.taxPrice+'</td>' +
                            // '<td>'+(putObject.tax/100)+'%</td>' +
                            '<td>'+this.taxMoney+'</td>' +
                            // '<td>'+this.moneyTax+'</td>' +
                            '</tr>');
                    })
                    $("#moneyCount").children(0).text(sum.toFixed(4));
                    // $("#moneyCount").next().children(0).text(sum.toFixed(4));
                    $("#moneyCount").prev().children(0).text(formatRMB(sum));

                    //打印
                    $('#printCon').jqprint({operaSupport: true});
                }
            })
        }else{
            var sum = 0;
            var taxSum = 0;
            $(putObject.materialList).each(function (index,val) {
                sum += this.moneyTax;
                taxSum += this.moneyTax - this.taxMoney;     //含税金额 - 税额 = 不含税金额
                $("#materPrint_message").append('<tr>' +
                    '<td>'+index+'</td>' +
                    '<td>'+this.material.iD+'</td>' +
                    '<td>'+this.material.name+'</td>' +
                    '<td>'+this.material.model+'</td>' +
                    '<td>'+this.material.unit.name+'</td>' +
                    '<td>'+this.putSum+'</td>' +
                    '<td>'+this.taxPrice+'</td>' +
                    '<td>'+(putObject.tax/100)+'%</td>' +
                    '<td>'+this.taxMoney+'</td>' +
                    '<td>'+this.moneyTax+'</td>' +
                    '</tr>');
            })
            $("#moneyCount").children(0).text(taxSum);
            $("#moneyCount").next().children(0).text(sum);

            //打印
            $('#printCon').jqprint({operaSupport: true});
        }
    }
});

