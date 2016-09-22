/**
 * Created by Administrator on 2016-08-09.
 * 添加采购订单脚本
 */
var tax = $("#tax").val() == "" ? 17 : $("#tax").val();   //税率
$(function () {
    var applyTable;     //申请单表格
    var applyMater;     //申请单材料表格
    var proMaterList = $("#materialList");  //订单材料
    var project;        //项目
    var singleId;
    /*
     * 下拉菜单显示时调用
     * */
    $(".dropdown").on('show.bs.dropdown', function () {

    });
    /**
     * 下拉菜单隐藏时触发
     */
    $(".dropdown").on('hide.bs.dropdown', function () {

    });
    /**
     * 下拉项选中时触发
     */
    $(".dropdown").on("click", "li", function () {
        var $ul = $(this).parent();
//            设置单位名称
//         $ul.prev().val($(this).text());
//            设置单位id
//         $ul.next().val($(this).data('cid'));
        var id = $ul.prev().attr("id");
        if (id == "contract") {
            inputValue({name: $(this).data('company'), id: $(this).data('comid')}, $("#company"));
        } else if (id == "company") {
            inputValue({name: "", id: ""}, $("#contract"));
            inputValue({name: $(this).text(), id: $(this).data('cid')}, $ul.prev());
            $("#company").trigger("change");
        }
        inputValue({name: $(this).text(), id: $(this).data('cid')}, $ul.prev());
    });

    /**
     * input标签赋值
     * @param value
     * @param input
     */
    function inputValue(values, input) {
        $(input).val(values.name);
        $(input).next().next().val(values.id);
    }

    //初始化表格
    var materColumns = [{
        field: 'index',
        class:'project serial',
        title: '序号',
    }, {
        field: 'name',
        class:'materName',
        title: '材料名称',
    }, {
        field: 'model',
        class:'materModel',
        title: '型号'
    }, {
        field: 'brand',
        class:'materBrand',
        title: '品牌',
    }, {
        field: 'sum',
        title: '数量',
        class: 'edit sum'
    }, {
        field: 'planPrice',
        title: '单价',
        class: 'edit planPrice'
    }, {
        field: 'sumPrice',
        class:'materSumPrice',
        title: '金额',
    }, {
        field: 'price_tax',
        title: '不含税价',
        class: 'hide no_tax'
    }, {
        field: 'remark',
        class:'edit mater_remark',
        title: '备注'
    }, {
        field: 'ySum',
        title: '已采购数量'
    }, {
        field: 'placeDate',
        title: '需求日期',
        class: 'edit placeDate'
    }];


    /**
     * 加载材料方法
     * @param applyId 申请单id
     */
    function loadMater(applyId) {
        singleId = applyId;
        $(applyMater).bootstrapTable('refresh', {url: 'material?applyId=' + applyId});
        /* var done = $.ajax({
         url: "material",     //请求地址
         type: 'POST',
         data: 'applyId=' + applyId,    //参数
         dataType: 'json',        //服务器返回的数据类型
         }).done(function (data) {
         if (applyMater != undefined) {
         $(data).each(function (index, element) {
         this.index = '<div class="checkbox">' +
         '<input type="checkbox" checked>' +
         '</div>';
         })
         $(applyMater).bootstrapTable("load", data);
         }
         }).fail(function () {
         alert("加载失败");
         });*/
    }

//    初始化材料集合表

    /**
     * 材料选择按钮
     */
    $('#chooseMaterial').on('click', function () {
        initApply();        //初始化表格
        $("#myModal").modal("show");
    });
    //注册单位选择后的事件
    $("#company").change(function () {
        if ($(this).next().next().val() != "") {
            $(this).removeClass("border-waring");
        }
    })
    /**
     * 初始化表格
     */
    function initApply() {
        //判断表格对象是否为undefined
        if (applyTable != undefined) {
            $(applyMater).bootstrapTable('refresh', {url: 'material?applyId=' + singleId});
            return;
        }
        applyTable = initTable($("#applys"), "applyByJson", {
                //自定义表格配置
                size: 0,
                height: "220",
                pagination: false,
                search: true,
                loadSuccess: function (data) {//            数据加载成功后调用
                    //调整搜索框位置
                    var parent = $("#applys").parent().parent().parent().parent();
                    var $search = $(parent).find(".fixed-table-toolbar").appendTo($(parent).closest(".modal-body").prev().children("div")).end();
                    //初始化材料表
                    singleId = data[0].iD;
                    project = data[0].project.iD;
                    applyMater = initTable($("#material"), "material?applyId=" + singleId, {
                        //    表格配置
                        size: 10,
                        height: "auto",
                        pagination: false,
                        loadSuccess: function (data) {
                            //数据加载成功后初始化多选样式
                            initChebox();
                        },
                    }, function (res) {
                        $(res).each(function (index, element) {
                            this.brand = '<span title="'+this.brand+'">'+this.brand+'</span>';
                            this.sumPrice = this.sum * this.planPrice;      //总价
                            var no_taxMoney = (this.sumPrice / (1 + (tax / 100)));     //计算不含税价
                            this.price_tax = !isNaN(no_taxMoney) ? no_taxMoney.toFixed(4) : 0;  //不含税价
                            //项目id / 不含税单价 / 税额
                            this.name = '<span class="label label-sm btn-success projects" data-project="' + project + '" ' +
                                'data-price="' + (this.price_tax >= 0 ? (this.price_tax / this.sum) : 0) + '" ' +
                                'data-unit="' + this.unit.iD + '" ' +
                                'data-taxmoney="' + (this.sumPrice - this.price_tax) + '">' + this.name + '</span>';
                            this.sum = '<span class="label label-warning label-mini mater-cid" id="' + this.iD + '"' +
                                ' data-apply-id="' + this.major + '" data-apply="'+this.applyID+'">' + this.sum + '</span>';
                            if ($(proMaterList).find("#" + this.iD).get(0) != undefined) {
                                this.index = '<div class="checkbox">' +
                                    '<input type="checkbox" checked="true" name="material[' + index + '"].coding>' +
                                    '</div>';
                            } else {
                                this.index = '<div class="checkbox">' +
                                    '<input type="checkbox" name="material[' + index + ']".coding>' +
                                    '</div>';
                            }

                            this.remark = '<span title="'+this.remark+'">'+this.remark+'</span>';
                        })
                        return res;
                    }, materColumns);
                    //申请单滚动条
                    $(".fixed-table-body").niceScroll({
                        styler: "fb",
                        cursorcolor: "#65cea7",
                        cursorwidth: '6',
                        cursorborderradius: '0px',
                        background: '#424f63',
                        spacebarenabled: false,
                        cursorborder: '0',
                        zindex: '1000',
                        scrollspeed:10,   //滚动速度
                        mousescrollstep:12.6666,     //滚动像素
                        cursorminheight:10, //滚动条最小高度
                        cursorheight:10,
                    });
                    //申请单滚动事件
                    $("#myModal").on("mousewheel DOMMouseScroll",function(event){
                        console.log($(this).scrollTop());
                    })

                    var index = 0;
                }
            },
            /*
             * 服务端返回数据的处理
             * */
            function (res) {
                $(res).each(function () {
                    switch (this.state) {
                        case 0:
                            this.state = "<span data-apply-id='" + this.iD + "' data-project='" + this.project.iD + "' class='label label-danger label-mini'>未采购</span>";
                            break;
                        case 1:
                            this.state = "<span data-apply-id='" + this.iD + "' data-project='" + this.project.iD + "' class='label label-warning label-mini'>部分采购</span>";
                            break;
                        default:
                            this.state = "什么鬼";
                            break;
                    }
                });
                return res;
            },
            [{
                field: 'project.name',
                title: '项目名称',
            }, {
                field: 'serialNumber',
                title: '申请单'
            }, {
                field: 'staff.name',
                title: '申请人',
            }, {
                field: 'prepareDate',
                title: '申请日期',
            }, {
                field: 'state',
                title: '采购执行状态',
            }, {
                field: 'remark',
                title: '备注',
            },]
        );
        //注册事件
        $(applyTable).bootstrapTable("refreshOptions", {
            onClickRow: function (row, $element, field) {
                singleId = $element.children(4).children(0).data("apply-id");   //获取申请单id
                project = $element.children(4).children(0).data("project");//获取项目id
                loadMater(singleId);//加载材料
            }
        });
        $("#applys").on("mouseover","tr",function () {
            $(this).trigger("onClickRow");
        })
        //注册材料单击事件
        initMater();
        function initMater() {
            $("#material").on('click', $("#material > tr"), function (event) {
                var $this = $(event.target).parent();
                var $check = $this.find("ins");
                $check.trigger('click');
                // $(this).find(".mater_apply").first().data("mater")
            });
        }
    }

    /**
     * checkbox插件初始化
     */
    var index = 0;

    function initChebox() {
        var maters = $("#maters");
        //材料选中事件
        $('input[type="checkbox"]').on('ifChecked', function () {
            //判断材料是否被选择过
            if (cids.indexOf(temp) != -1) {
                return false;
            }
            //判断申请单是否被选择过
            if (applys.indexOf(singleId) == -1) {
                //添加该申请单id到数组中
                applys.push(singleId);
            }
            //复制行
            var $check = $(this);   //选择器对象
            var $this = $check.closest("tr");   //行对象
            var temp = $this.find('.mater-cid').attr('id');
            cids[index] = temp;
            // $check.attr("checked",true);
            // $check.parent().addClass("checked");
            var $tr = $this.prop('outerHTML');
            $(maters).append($tr);                //添加到tbody材料集合中
            $(maters).find(".project").remove(); //删除单选按钮
            $(maters).find(".placeDate").text(getDate());   //为到货时间赋值
            $(maters).find(".hide").removeClass("hide");    //显示隐藏元素

            sumAllText += parseFloat($this.find(".planPrice").next().text());
            $sumAll.text(sumAllText);

            $this.data('for-index', index);
            index++;
        });
        //材料移除事件
        $("input[type='checkbox']").on('ifUnchecked', function () {
            var $check = $(this);   //选择器对象
            var $this = $check.closest("tr");   //行对象
            var $span = $this.find('.mater-cid');
            var temp = {applyId:$span.data('apply'), id:$span.attr('id')};    //获取材料id
            deleteMater($(proMaterList).find("#" + temp.id).closest('tr'), temp);
            index--;
        });
        $('input[type="checkbox"]').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%', // optional
        });
    }

    /**
     * 模态框事件初始化
     */
    $("#myModal").on("hide.bs.modal", function () {

    });
    /**
     * 材料选择好后的事件
     */
    $("#enter").click(function () {
        $("#myModal").modal("hide");
    });

    initScroll($("#materlist-body"));
    initScroll($(".dropdown-menu"));
    initEdit();
    /**
     * 单击输入事件
     * 双击删除事件
     */
    function initEdit() {
        $(proMaterList).on('click', '.edit', function (event) {
            var td = event.target;
            if (!$(td).is("td")) {
                td = $(td).parent();
            }
            var $span = $(td).children(0);
            var text = $(td).text();
            insertInput($(td), text, $span);
        });
        $(proMaterList).on("dblclick", 'tr', function (event) {
            if (!$(event.target).is('input')) {
                deleteMater($(this),{applyId:$(this).find('.mater-cid').data('apply'),id:$(this).find('.mater-cid').attr('id')});
            } else {

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
        $(elem).text("");
        // placeDate 时间插件初始化
        if ($(elem).is(".placeDate")) {
            $(elem).on("focus", "#edit_message", function () {
                //判断是否是到货日期元素
                //初始化时间选择器
                $(this).datepicker({
                    language: "zh-CN",
                    autoclose: true,
                    format: "yyyy-mm-dd",
                    hide: function () {

                    }
                });
            });
            $(elem).on("hide", "#edit_message", function (data) {
                $(this).parent().text($(this).val());
                $(input).remove();
                input = undefined;
            })
        }else {
            $(elem).on("blur", "#edit_message", function () {
                val = $(this).val() != "" ? $(this).val() : val;

                if($(elem).is(".mater_remark")){  //备注
                    $span.appendTo($(this).parent()).end().text(val);
                    $span.attr("title",val);
                    $(this).remove();
                    input = undefined;
                    return;
                }

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
                var $sum = $(tr).find(".sum");   //数量
                var $price = $(tr).find(".planPrice"); //单价
                var $sum_price = $price.next(); //金额
                var $notax = $sum_price.next();    //不含税价格
                var sum_priceText;      //总金额
                if ($(elem).is(".sum")) {      //数量类型
                    sum_priceText = val * $price.text();      //获取总金额
                } else if ($(elem).is(".planPrice")) {
                    sum_priceText = $sum.text() * val;  //获取总价
                }
                if (!isNaN(sum_priceText) || sum_priceText != undefined) {
                    sumAllText -= parseFloat($sum_price.text());        //减去原来的总金额，后面附上新的总金额
                    sumAllText += sum_priceText;
                    $sum_price.text(sum_priceText);  //总金额赋值
                    $notax.text((sum_priceText / (1 + (tax / 100))).toFixed(4));   //不含税总价赋值
                    $(tr).find(".projects").attr("data-taxmoney", (parseFloat(sum_priceText) - parseFloat($notax.text())));       //设置税额
                    $(tr).find(".projects").attr("data-price", (parseFloat($notax.text()) / parseFloat($sum.text())));       //设置不含税单价
                    $sumAll.text(sumAllText.toFixed(4));
                }
                $(this).remove();
                input = undefined;
            });
        }
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
                // input = undefined;
                insertInput($(td), $(td).text(), $(td).children(0));
            } else if (success) {
                $(this).blur();
                $(this).unbind('keydown');
            }
        })
    }

    /**
     * 移除材料方法
     * @param tr
     */
    function deleteMater(tr, cid) {
        sumAllText -= isNaN(parseFloat($(tr).find(".planPrice").next().text())) ? 0 : parseFloat($(tr).find(".planPrice").next().text());
        $sumAll.text(sumAllText);

        $(tr).hide(200, function () {
            $(tr).detach();     //删除元素
            cids.splice(cids.indexOf(cid.id), 1);  //把该材料从数组中移除
        });
        var is = true;
        /*
        遍历列表中是否存在移除材料类型的申请单，没有后删除申请单id，有则继续
         */
        $(tr).siblings().each(function () {
            if ($(this).find('.mater-cid').data("apply") == cid.applyId){
                is = false;
                return false;
            }
        });
        //是否删除申请单id
        if(is){
            applys.splice(applys.indexOf(cid.applyId),1);     //删除该申请单在数组中的id
        }
    }
})
