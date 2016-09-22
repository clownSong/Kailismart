/**
 * Created by Administrator on 2016-08-09.
 * 添加采购订单脚本
 */
var tax = $("#tax").val() == "" ? 17 : $("#tax").val();   //税率
$(function () {
    var applyTable;     //申请单表格
    var rowObject;      //材料行数据，单例
    var rowIndex;           //行下标 单例
    var applyMater;     //申请单材料表格
    var proMaterList = $("#materialList");  //订单材料
    var project;        //项目
    var singleId;       //申请单id
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
    var materColumns = [
        {
            field: 'index',
            title: '序号',
            class: 'project',
            width: '20px'
        }, {
            field: 'serialNumber',
            order: "asc",
            class: 'hide super-hide app-project',
            title: '项目名称',
        }, {
            field: 'apply.project.name',
            class: 'hide super-hide app-project',
            title: '项目名称',
        }, {
            field: 'apply.serialNumber',
            class: 'hide super-hide app-serial',
            title: '申请单'
        }, {
            field: 'apply.staff.name',
            class: 'hide super-hide app-staff',
            title: '申请人',
            width: '40px'
        }, {
            field: 'apply.prepareDate',
            class: 'hide super-hide app-date',
            title: '申请日期',
            width: '100px'
        }, {
            field: 'apply.state',
            title: '采购执行状态',
            class: 'hide super-hide app-state',
            width: '60px'
        }, {
            field: 'name',
            title: '材料名称',
        }, {
            field: 'model',
            title: '型号',
            width: '150px',
        }, {
            field: 'brand',
            title: '品牌',
            width: '50px'
        }, {
            field: 'sum',
            title: '数量',
            width: '80px',
            class: 'edit sum'
        }, {
            field: 'planPrice',
            title: '单价',
            width: '80px',
            class: 'edit planPrice'
        }, {
            field: 'sumPrice',
            title: '金额',
        }, {
            field: 'price_tax',
            title: '不含税价',
            class: 'hide no_tax'
        }, {
            field: 'remark',
            title: '备注'
        }, {
            field: 'ySum',
            sortable: true,
            title: '已采购数量'
        }, {
            field: 'placeDate',
            title: '需求日期',
            width: '80px',
            class: 'edit placeDate'
        }
    ];

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
            // $(applyMater).bootstrapTable('refresh', {url: 'material?applyId=' + singleId});
            return;
        }
        applyTable = initTable($("#material"), "applyByJson", {
                //自定义表格配置
                size: 0,
                height: "450",
                pagination: false,
                search: true,
                loadSuccess: function (data) {//            数据加载成功后调用
                    //调整搜索框位置
                    var parent = $("#material").parent().parent().parent().parent();
                    var $search = $(parent).find(".fixed-table-toolbar").appendTo($(parent).prev()).end();
                    //初始化选择器
                    initChebox();
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
                        scrollspeed: 60,   //滚动速度
                    });
                }
            },
            /*
             * 服务端返回数据的处理
             * */
            function (res) {

                if (res[0] != undefined) {
                    var firstApply = res[0];
                    firstApply.state = parseState(firstApply);
                    showApply(firstApply);
                }
                var temp = new Array();
                $(res).each(function (index1, val1) {
                    this.state = parseState(this);
                    var apply = this;
                    $(res[index1].applyMaterialList).each(function (index, val) {
                        this.serialNumber = apply.serialNumber + this.serialNumber;
                        //如果申请单材料id不等于申请单id，及是下一个申请单开始
                        this.apply = apply;
                        this.sumPrice = this.sum * this.planPrice;      //总价
                        var no_taxMoney = (this.sumPrice / (1 + (tax / 100)));     //计算不含税价
                        this.price_tax = !isNaN(no_taxMoney) ? no_taxMoney.toFixed(4) : 0;  //不含税价
                        //项目id / 不含税单价 / 税额
                        this.name = '<span class="label label-sm btn-success projects" data-project="' + apply.project.iD + '" ' +
                            'data-price="' + (this.price_tax >= 0 ? (this.price_tax / this.sum) : 0) + '" ' +
                            'data-unit="' + this.unit.iD + '" ' +
                            'data-taxmoney="' + (this.sumPrice - this.price_tax) + '">' + this.name + '</span>';
                        this.sum = '<span class="label label-warning label-mini mater-cid" id="' + this.iD + '"' +
                            ' data-apply-id="' + this.major + '" data-apply="' + this.applyID + '">' + this.sum + '</span>';
                        this.index = '<div class="checkbox">' +
                            '<input type="checkbox" name="material[' + index + ']".coding>' +
                            '</div>';
                        /*if ($(proMaterList).find("#" + this.iD).get(0) != undefined) {
                         this.index = '<div class="checkbox">' +
                         '<input type="checkbox" checked="true" name="material[' + index + '"].coding>' +
                         '</div>';
                         } else {

                         }*/
                        temp.push(this);
                    });
                });
                return temp;
            }, materColumns);
        //注册材料单击事件和搜索后的初始化checkd
        $(applyTable).bootstrapTable("refreshOptions", {
            onClickRow: function (row, $element, field) {
                rowObject = row;
                rowIndex = $element.data("index");
                var $span = $element.find(".app-state").children(0);
                singleId = $span.data("apply-id");   //获取申请单id
                project = $span.data("project");//获取项目id
                var $check = $element.find(".iCheck-helper");
                $check.click();
            },
            onSearch: function (text) {
                initChebox();
            }
        });
    }

    /**
     * checkbox插件初始化
     */
    var index = 0;

    function initChebox() {
        var maters = $("#maters");
        //材料选中事件
        $('input[type="checkbox"]').on('ifChecked', function () {
            //判断申请单是否被选择过
            if (applys.indexOf(singleId) == -1) {
                //添加该申请单id到数组中
                applys.push(singleId);
            }
            //复制行
            var $check = $(this);   //选择器对象
            var $this = $check.closest("tr");   //行对象
            var temp = $this.find('.mater-cid').attr('id'); //获取材料id
            //判断材料是否被选择过
            if (cids.indexOf(temp) != -1) {
                return false;
            }

            cids[index] = temp;
            // $check.attr("checked",true);
            // $check.parent().addClass("checked");
            var $tr = $this.prop('outerHTML');
            $(maters).append($tr);                //添加到tbody材料集合中
            $(maters).find(".super-hide").remove();     //删除申请单列
            $(maters).find(".project").remove(); //删除单选按钮
            $(maters).find(".placeDate").text(getDate());   //为到货时间赋值
            $(maters).find(".hide").removeClass("hide");    //显示隐藏元素


            sumAllText += parseFloat($this.find(".planPrice").next().text());
            $sumAll.text(sumAllText);
            //更新行数据
            $this.find(".icheckbox_square-blue").addClass("checked");
            rowObject.index = $check.closest(".checkbox").prop('outerHTML');
            updateRow();
            $this.data('for-index', index);
            index++;
        });
        //材料移除事件
        $('input[type="checkbox"]').on('ifUnchecked', function () {
            var $check = $(this);   //选择器对象
            var $this = $check.closest("tr");   //行对象
            var $span = $this.find('.mater-cid');
            var temp = {applyId: $span.data('apply'), id: $span.attr('id')};    //获取材料id
            deleteMater($(proMaterList).find("#" + temp.id).closest('tr'), temp);
            //更新行数据
            $this.find(".icheckbox_square-blue").removeClass("checked");
            rowObject.index = $check.closest(".checkbox").prop('outerHTML');
            updateRow();
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
                deleteMater($(this), {
                    applyId: $(this).find('.mater-cid').data('apply'),
                    id: $(this).find('.mater-cid').attr('id')
                });
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
        } else {
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
                if (!isNaN(sum_priceText) && sum_priceText != undefined) {
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
            if ($(this).find('.mater-cid').data("apply") == cid.applyId) {
                is = false;
                return false;
            }
        });
        //是否删除申请单id
        if (is) {
            applys.splice(applys.indexOf(cid.applyId), 1);     //删除该申请单在数组中的id
        }
    }

    /**
     * 切换申请单信息
     * @param applyObject
     */
    function showApply(applyObject) {
        $("#applyTitle").text(applyObject.serialNumber + "/" + applyObject.project.name + "/" + applyObject.staff.name + "/" + applyObject.prepareDate);
        $("#applyTitle").append(applyObject.state);
    }

    /**
     * 申请单状态转换
     * @param applyObject
     * @returns {*}
     */
    function parseState(applyObject) {
        var temp;
        switch (applyObject.state) {
            case 0:
                temp = "<span data-apply-id='" + applyObject.iD + "' data-project='" + applyObject.project.iD + "' class='label label-danger label-mini'>未采购</span>";
                break;
            case 1:
                temp = "<span data-apply-id='" + applyObject.iD + "' data-project='" + applyObject.project.iD + "' class='label label-warning label-mini'>部分采购</span>";
                break;
            default:
                temp = applyObject.state;
                break;
        }
        return temp;
    }

    onMaterials();
    /**
     * 鼠标在材料上的行事件
     */
    function onMaterials() {
        $("#material").on("mouseover", "tr", function () {
            var $serial = $(this).find(".app-serial");
            var $project = $(this).find(".app-project");
            var $date = $(this).find(".app-date");
            var $state = $(this).find(".app-state");
            var $staff = $(this).find(".app-staff");
            // alert($serial.get(0));
            showApply(new apply($serial.text(), {name: $project.text()}, {name: $staff.text()},
                $date.text(), $state.html()))
        })
    }

    function apply(name, project, staff, prepareDate, state) {
        this.serialNumber = name;
        this.project = project;
        this.staff = staff;
        this.prepareDate = prepareDate;
        this.state = state;
    }

    function updateRow() {
        $(applyTable).bootstrapTable("updateRow", {
            index: rowIndex,
            row: rowObject,
        });
        initChebox();
    }
})
