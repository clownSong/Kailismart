/**
 * Created by 宋正根 on 2016/8/30.
 */
var outMaterArray = new Array();        //出库单材料集合
var outMaterIds = new Array;        //材料id集合
$(function () {
    var $project = $("#project");   //项目元素
    var $company = $("#company");   //单位元素
    var $putStorage = $("#putStorage"); //入库单元素
    var $outPerson = $("#outPerson");           //领料人元元素
    var $section = $("#section");           //部门元素
    var $sObject = $("#storages");          //仓库选择器
    var $storage = $("#storage");          //仓库隐藏元素
    var $outNumber = $("#outNumber");
    var type = 1;           //选择材料类型{1：入库单，2：材料}


    var getProject = "http://" + webIp + "/managent/getProjectBySeek";
    var res;

    /**
     * 初始化项目
     */
    function initProject(type) {
        if (type) {
            res = loadDate(getProject, null);
        } else {
            res = loadDate(getProject, {str: $project.val()});
        }
        res.done(function (data) {
            $project.next().children().remove();
            $(data).each(function (index, val) {
                $project.next().append("<li data-project='" + this.iD + "'><a>" + this.name + "</a></li>");
            })
        })
    }

    /**
     * 初始化单位
     */
    function initCompany(url, type) {
        if (type) {
            res = loadDate(url, null);
        } else {
            res = loadDate(url, {str: $company.val()});
        }

        res.done(function (data) {
            $company.next().children().remove();
            $(data).each(function (index, val) {
                $company.next().append("<li data-project='" + this.iD + "'><a>" + this.name + "</a></li>");
            })
        })
    }

    /**
     * 初始化入库单对象
     * @param url
     */
    function initPutStorage(url, type, param) {
        if (param != undefined) {
            res = loadDate(url, param);
        } else if ($putStorage.val() == "" || type) {
            res = loadDate(url, {start: getfirstDate(new Date()), end: getEndDate(new Date())});
        } else {
            res = loadDate(url, {str: $putStorage.val()});
        }

        res.done(function (data) {
            if (data.length == 0) {
                initPutStorage("http://" + webIp + "/managent/seekPutStorage", true, {
                    start: getfirstDate(new Date(getPreMonth(getNowDate()))),
                    end: getEndDate(new Date(getPreMonth(getNowDate())))
                });
            }
            $putStorage.next().children().remove();
            $(data).each(function (index, val) {
                $putStorage.next().append("<li data-cid='" + this.iD + "'><a>" + this.putSerial + "</a></li>");
            })
        })
    }

    /**
     * 初始化领料人对象
     */
    function initOutPerson(url, type) {
        if (type) {
            res = loadDate(url, null);
        } else {
            res = loadDate(url, {str: $outPerson.val()});
        }

        res.done(function (data) {
            $outPerson.next().children().remove();
            $(data).each(function (index, val) {
                $outPerson.next().append("<li data-section='" + this.sectionCoding + "' data-cid='" + this.iD + "'><a>" + this.name + "</a></li>");
            })
        })
    }

    /**
     * input标签输入事件
     */
    var temp = undefined;
    $("#outMater").on('input', 'input', function (event) {
        var input = event.target;
        switch ($(input).attr('id')) {
            case 'project':
                if (temp == undefined) {
                    temp = window.setTimeout(function () {
                        initProject(false);
                        window.clearTimeout(temp);
                        temp = undefined;
                    }, 300);
                }
                break;
            case 'company':
                if (temp == undefined) {
                    temp = window.setTimeout(function () {
                        initCompany("http://" + webIp + "/managent/seekByOut", false);
                        window.clearTimeout(temp);
                        temp = undefined;
                    }, 300);
                }
                break;
            case 'putStorage':
                if (temp == undefined) {
                    temp = window.setTimeout(function () {
                        initPutStorage("http://" + webIp + "/managent/seekPutStorage", false);
                        window.clearTimeout(temp);
                        temp = undefined;
                    }, 300);
                }
                break;
            case 'outPerson':
                if (temp == undefined) {
                    temp = window.setTimeout(function () {
                        initOutPerson("http://" + webIp + "/managent/seekStaff", false);
                        window.clearTimeout(temp);
                        temp = undefined;
                    }, 300);
                }
                break;
        }
    });
    /**
     * input获得焦点事件
     */
    $("#outMater").on("focus", "input", function () {
        var input = event.target;
        switch ($(input).attr('id')) {
            case 'project':
                initProject(true);
                break;
            case 'company':
                initCompany("http://" + webIp + "/managent/seekByOut", true);
                break;
            case 'putStorage':
                initPutStorage("http://" + webIp + "/managent/seekPutStorage", true);
                break;
            case 'outPerson':
                if (temp == undefined) {
                    temp = window.setTimeout(function () {
                        initOutPerson("http://" + webIp + "/managent/seekStaff", true);
                        window.clearTimeout(temp);
                        temp = undefined;
                    }, 300);
                }
                break;
            case 'outDate':
                $(this).datepicker({
                    language: "zh-CN",
                    autoclose: true,
                    format: "yyyy-mm-dd",
                    hide: function () {

                    }
                });
                break;
        }
    });

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
            title: '品牌',
            width: '130px',
            field: 'material.brand'
        }, {
            title: '单位',
            width: '60px',
            field: 'material.unit.name'
        }, {
            title: '数量',
            class: 'edit',
            width: '100px',
            field: 'putSum'
        }, {
            title: '单价',
            class: 'edit',
            width: '70px',
            field: 'taxPrice'
        }, {
            title: '金额',
            width: '100px',
            field: 'moneyTax'
        }, {
            title: '税额',
            width: '50px',
            field: 'taxMoney'
        }, {
            title: '项目',
            width: '200px',
            field: 'project.name'
        }
    ];
    /**
     * 选择材料按钮绑定事件
     */
    $("#choose,#chooseMater").click(function () {
        if ($(this).attr("id") == "chooseMater") {
            initMater2("http://" + webIp + "/managent/getMaterByStorage?id=" + $storage.val());
        } else {
            initMater();
        }
        $("#myModal").modal("show");
    });
    /**
     *
     * 下拉项选中时触发
     */
    $(".dropdown").on("click", "li", function () {
        var $ul = $(this).parent();
//            设置单位名称
//         $ul.prev().val($(this).text());
//            设置单位id
//         $ul.next().val($(this).data('cid'));
        var id = $ul.prev().attr("id");
        if (id == "project") {
            inputValue({name: $(this).text(), id: $(this).data('project')}, $project);
        } else if (id == "company") {
            inputValue({name: "", id: ""}, $("#contract"));
            inputValue({name: $(this).text(), id: $(this).data('project')}, $ul.prev());
            $("#company").trigger("change");
        } else if (id == "outPerson") {
            inputValue({name: $(this).text(), id: $(this).data("cid")}, $outPerson);
            var section = loadDate('http://' + webIp + '/managent/getSectionByCoding', {coding: $(this).data("section")});
            section.done(function (section) {
                inputValue({name: section.name, id: section.iD}, $section);
            })
        } else if (id == "putStorage") {
            inputValue({name: $(this).text(), id: $(this).data("cid")}, $putStorage);
        }
        $(this).parent().prev().removeClass("border-waring");
    });


    /**
     * 加载服务器数据
     * @param url 地址
     * @param param 参数
     * @returns {*}
     */
    function loadDate(url, param) {
        return $.ajax({
            url: url,
            type: 'post',
            data: param,
            dataType: 'json'
        })
    }

    //注册滚动条
    initScroll($(".dropdown-menu"));

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
     * 加载入库单材料集合
     */
    var materialTable;

    function initMater() {
        type = 1;
        if (materialTable != undefined) {
            $(materialTable).bootstrapTable("refresh", {
                silent: true,
                url: "http://" + webIp + "/managent/getOkMaterByPutId?putId=" + $putStorage.next().next().val()
            });
            return;
        }
        materialTable = initTable($("#putMaterial"), "http://" + webIp + "/managent/getOkMaterByPutId?putId=" + $putStorage.next().next().val(),
            {
                pagination: false,
                height: '348',
                loadSuccess: function (data) {
                    initScroll($(".fixed-table-body"));        //滚动条初始化
                }
            },
            function (materials) {
                if (type == 1) {  //入库单
                    $(materials).each(function (index, val) {
                        var style = "";
                        if (outMaterIds.indexOf(this.material.iD) != -1) {
                            style = " red";
                        }
                        this.clone = $.extend(true, {}, val);        //克隆材料对象
                        this.index = '<span class="index' + style + '">' + index + '</span>';       //序号
                        this.material.name = '<span data-put="' + this.iD + '" class="materialName' + style + '">' + this.material.name + '</span>';   //材料名称
                        this.material.model = '<span class="model' + style + '">' + this.material.model + '</span>';        //品牌
                        this.material.brand = '<span class="brand' + style + '">' + this.material.brand + '</span>';        //材料型号
                        this.material.unit.name = '<span class="unit' + style + '">' + this.material.unit.name + '</span>'; //材料单位
                        this.putSum = '<span data-putmater="'+this.iD+'" data-mater="'+this.material.iD+'" data-sum="' + this.putSum + '" class="putsum' + style + '">' + this.putSum + '</span>';           //入库数量
                        this.taxPrice = '<span class="taxprice' + style + '">' + this.taxPrice + '</span>';     //含税单价
                        this.moneyTax = '<span class="moneytax' + style + '">' + this.moneyTax + '</span>';     //含税总价
                        this.taxMoney = '<span class="taxmoney' + style + '">' + this.taxMoney + '</span>';     //税额
                        this.project.name = '<span class="project' + style + '">' + this.project.name + '</span>';  //项目名称
                    });
                } else {  //直接加载材料
                    $(materials).each(function (index, val) {
                        var style = "";
                        if (outMaterIds.indexOf(this.material.iD) != -1) {
                            style = " red";
                        }
                        this.clone = $.extend(true, {}, val);        //克隆材料对象
                        this.index = '<span class="index' + style + '">' + index + '</span>';       //序号
                        this.material.name = '<span class="materialName' + style + '">' + this.material.name + '</span>';   //材料名称
                        this.material.brand = '<span class="brand' + style + '">' + this.material.brand + '</span>';   //品牌
                        this.material.model = '<span class="model' + style + '">' + this.material.model + '</span>';        //材料型号
                        this.material.unit.name = '<span class="unit' + style + '">' + this.material.unit.name + '</span>'; //材料单位
                        this.putSum = '<span data-putmater="" data-mater="'+this.material.iD+'" data-sum="' + this.sum + '" class="putsum' + style + '">' + this.sum + '</span>';           //入库数量
                        this.taxPrice = '<span class="taxprice' + style + '">' + this.price + '</span>';     //含税单价
                        this.moneyTax = '<span class="moneytax' + style + '">' + this.money + '</span>';     //含税总价
                        this.taxMoney = '<span class="taxmoney' + style + '">' + 0 + '</span>';     //税额
                        this.project = {name: '<span class="project' + style + '">-</span>'};  //项目名称
                    });
                }
                return materials;
            }, materialColumns);
        //注册材料行{单击}选择事件
        materialTable.bootstrapTable("refreshOptions", {
            onClickRow: function (row2, $select, field) {
                if ($select.find("span").is(".red")) {
                    deleteMater(row2.material.iD);
                    outMaterArray.splice(outMaterArray.indexOf(row2.clone), 1);
                    $select.find("span").removeClass("red");
                } else {
                    loadOutMater($select.clone(), row2.material.iD);
                    outMaterArray.push(row2.clone);
                    outMaterIds.push(row2.material.iD);      //添加该入库材料id到集合中
                    $select.find("span").addClass("red");
                }
                row2.index = $select.find(".index").prop("outerHTML");
                row2.material.name = $select.find(".materialName").prop("outerHTML");
                row2.material.brand = $select.find(".brand").prop("outerHTML");
                row2.material.model = $select.find(".model").prop("outerHTML");
                row2.material.unit.name = $select.find(".unit").prop("outerHTML");
                row2.putSum = $select.find(".putsum").prop("outerHTML");
                row2.taxPrice = $select.find(".taxprice").prop("outerHTML");
                row2.moneyTax = $select.find(".moneytax").prop("outerHTML");
                row2.taxMoney = $select.find(".taxmoney").prop("outerHTML");
                row2.project.name = $select.find(".project").prop("outerHTML");

                $(materialTable).bootstrapTable("updateRow", {
                    index: $select.data("index"),
                    row: row2,
                });
            }
        })
    }

    function initMater2(url) {
        type = 2;
        if (materialTable != undefined) {
            $(materialTable).bootstrapTable("refresh", {
                silent: true,
                url: url,
            });
            return;
        } else {
            materialTable = initTable($("#putMaterial"), url,
                {
                    pagination: false,
                    height: '348',
                    loadSuccess: function (data) {
                        initScroll($(".fixed-table-body"));        //滚动条初始化
                    }
                },
                function (materials) {
                    if (type == 1) {  //入库单
                        $(materials).each(function (index, val) {
                            var style = "";
                            if (outMaterIds.indexOf(this.material.iD) != -1) {
                                style = " red";
                            }
                            this.clone = $.extend(true, {}, val);        //克隆材料对象
                            this.index = '<span class="index' + style + '">' + index + '</span>';       //序号
                            this.material.name = '<span class="materialName' + style + '">' + this.material.name + '</span>';   //材料名称
                            this.material.model = '<span class="model' + style + '">' + this.material.model + '</span>';        //材料型号
                            this.material.brand = '<span class="brand' + style + '">' + this.material.brand + '</span>';        //品牌
                            this.material.unit.name = '<span class="unit' + style + '">' + this.material.unit.name + '</span>'; //材料单位
                            this.putSum = '<span data-putmater="'+this.iD+'" data-mater="'+this.material.iD+'" data-sum="' + this.putSum + '" class="putsum' + style + '">' + this.putSum + '</span>';           //入库数量
                            this.taxPrice = '<span class="taxprice' + style + '">' + this.taxPrice + '</span>';     //含税单价
                            this.moneyTax = '<span class="moneytax' + style + '">' + this.moneyTax + '</span>';     //含税总价
                            this.taxMoney = '<span class="taxmoney' + style + '">' + this.taxMoney + '</span>';     //税额
                            this.project.name = '<span class="project' + style + '">' + this.project.name + '</span>';  //项目名称
                        });
                    } else {  //直接加载材料
                        $(materials).each(function (index, val) {
                            var style = "";
                            if (outMaterIds.indexOf(this.material.iD) != -1) {
                                style = " red";
                            }
                            this.clone = $.extend(true, {}, val);        //克隆材料对象
                            this.index = '<span class="index' + style + '">' + index + '</span>';       //序号
                            this.material.name = '<span class="materialName' + style + '">' + this.material.name + '</span>';   //材料名称
                            this.material.brand = '<span class="brand' + style + '">' + this.material.brand + '</span>';   //品牌
                            this.material.model = '<span class="model' + style + '">' + this.material.model + '</span>';        //材料型号
                            this.material.unit = {name: '<span class="unit' + style + '">' + this.material.unit.name + '</span>'}; //材料单位
                            this.putSum = '<span data-putmater="" data-mater="'+this.material.iD+'" data-sum="' + this.sum + '" class="putsum' + style + '">' + this.sum + '</span>';           //入库数量
                            this.taxPrice = '<span class="taxprice' + style + '">' + this.price + '</span>';     //含税平均单价
                            this.moneyTax = '<span class="moneytax' + style + '">' + this.sum * this.price + '</span>';     //含税总价
                            this.taxMoney = '<span class="taxmoney' + style + '">' + 0 + '</span>';     //税额
                            this.project = {name: '<span class="project' + style + '">-</span>'};  //项目名称
                        });
                    }
                    return materials;
                }, materialColumns);
            //注册材料行{单击}选择事件
            materialTable.bootstrapTable("refreshOptions", {
                onClickRow: function (row2, $select, field) {
                    if ($select.find("span").is(".red")) {
                        deleteMater(row2.material.iD);
                        outMaterArray.splice(outMaterArray.indexOf(row2.clone), 1);
                        $select.find("span").removeClass("red");
                    } else {
                        loadOutMater($select.clone(), row2.material.iD);
                        outMaterArray.push(row2.clone);
                        outMaterIds.push(row2.material.iD);      //添加该入库材料id到集合中
                        $select.find("span").addClass("red");
                    }
                    row2.index = $select.find(".index").prop("outerHTML");
                    row2.material.name = $select.find(".materialName").prop("outerHTML");
                    row2.material.brand = $select.find(".brand").prop("outerHTML");
                    row2.material.model = $select.find(".model").prop("outerHTML");
                    row2.material.unit.name = $select.find(".unit").prop("outerHTML");
                    row2.putSum = $select.find(".putsum").prop("outerHTML");
                    row2.taxPrice = $select.find(".taxprice").prop("outerHTML");
                    row2.moneyTax = $select.find(".moneytax").prop("outerHTML");
                    row2.taxMoney = $select.find(".taxmoney").prop("outerHTML");
                    row2.project.name = $select.find(".project").prop("outerHTML");

                    $(materialTable).bootstrapTable("updateRow", {
                        index: $select.data("index"),
                        row: row2,
                    });
                }
            })
        }
    }

    /**
     * 添加入库材料到出库表格中
     * @type {any}
     */
    var $outMaterListBody = $("#outMaterListBody");

    function loadOutMater(index, id) {
        $(index).appendTo($outMaterListBody);       //添加该材料元素到出库集合中
        $(index).attr("id", id);         //设置该材料行id
    }

    /**
     * 删除出库表格中材料
     * @param id
     */
    function deleteMater(id) {
        outMaterIds.splice(outMaterIds.indexOf(id), 1);      //从出库集合中删除该材料
        id = "#" + id;
        $($outMaterListBody).find(id).remove();     //删除元素
    }

    //初始化输入框事件
    initEdit();
    /**
     * 单击输入事件
     * 双击删除事件
     */
    function initEdit() {
        $("#outMaterList").on('click', '.edit', function (event) {
            var td = event.target;
            if (!$(td).is("td")) {
                td = $(td).parent();
            }
            var $span = $(td).children(0);
            var text = $(td).text();
            insertInput($(td), text, $span);
        });
        $("#outMaterList").on("dblclick", 'tr', function (event) {
            if (!$(event.target).is('input')) {
                //从出库表格中删除材料
            } else {
                //忽略input的双击
            }
        })
    }

    /**
     * 插入输入标签
     * @param elem
     * @param val
     */
    var input;
    var editMessage = '<input type="text" id="edit_message"/>';

    function insertInput(elem, val, $span) {
        if (input != undefined) {
            return;
        }
        $(elem).css("padding","0px");
        $(elem).text("");
        //注册输入框失去焦点的事件
        $(elem).on("blur", "#edit_message", function () {
            val = $(this).val() != "" ? $(this).val() : val;
            if (!/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(val) || parseFloat(val) <= 0) {//判断输入的值是否合法
                $(this).addClass("border-waring").focus();
                return;
            }

            //判断子标签是否存在
            if ($span.get(0) != undefined) {
                $span.appendTo($(this).parent()).end().text(val);
            } else {
                $(this).parent().text(val);
            }

            var tr = $(elem).closest("tr");  //获取tr元素
            var $sum = $(tr).find(".putsum");   //入库数量
            var $price = $(tr).find(".taxprice"); //单价
            var $sum_price = $(tr).find(".moneytax"); //金额
            var $notax = $(tr).find(".taxmoney").next();    //税额

            var sum_priceText;      //总金额
            if ($(elem).children(0).is(".putsum")) {      //出库数量
                if (parseFloat(val) > parseFloat($sum.data("sum"))) {
                    $(this).focus();
                    return;
                }
                sum_priceText = parseFloat(val) * parseFloat($price.text());      //获取总金额
            } else if ($(elem).children(0).is(".taxprice")) {
                sum_priceText = parseFloat($sum.text()) * parseFloat(val);  //获取总金额
            }
            if (!isNaN(sum_priceText) || sum_priceText != undefined) {
                $sum_price.text(sum_priceText.toFixed(4));
            }
            $(this).remove();
            input = undefined;
            $(elem).css("padding","10px");
        });
        input = $(editMessage).appendTo(elem).focus().end();
    }

    //初始化出库材料滚动条
    initScroll($("#outMaterListContaner"));

    loadStorages();
    /**
     * 加载仓库集合,并注册选择事件
     */
    function loadStorages() {
        var $sUl = $sObject.next();
        var res = loadDate("http://" + webIp + "/managent/getStorages", null);
        //服务器返回的数据
        res.done(function (storages) {
            if (storages[1] != undefined) {
                $sObject.children(".label").text(storages[1].name);
                $sObject.children(1).val(storages[1].iD);
            }
            $(storages).each(function () {
                $sUl.append('<li data-storage="' + this.iD + '"><a href="#body">' + this.name + '</a></li>');
            });
        });
        //滚动条初始化
        initScroll($(".dropdown-menu"));
        $sUl.on("click", "li", function () {
            $sObject.children(".label").text($(this).text());
            $sObject.children(1).val($(this).data("storage"));
        })
    }
    initNumber();
    function initNumber() {
        var temp = $outNumber.val();
        var number = parseInt(temp.substring(temp.length-3,temp.length));
        if(!isNaN(number)){
            number++;
            $outNumber.val(temp + number);
        }else{

        }
    }
})