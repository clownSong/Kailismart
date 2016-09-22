/**
 * Created by 宋正根 on 2016/9/5.
 */
/**
 * Created by 宋正根 on 2016/9/4.
 */
$(function () {
    var $project = $("#company");   //供应单位元素
    var $count = $("#count");       //统计元素
    var counts = 0;
    var getCompany = "http://" + webIp + "//managent/seek";
    var startDate = getfirstDate(new Date());     //默认开始时间为本月的第一天
    var end = getNowDate();           //默认结束时间为当前日期
    var $start = $("#start");
    var $end = $("#end");
    $start.val(startDate);  //开始时间赋值
    $end.val(end);        //结束时间赋值
    var res;
    initCompany(true);

    /**
     * 初始化供应单位
     */
    function initCompany(type) {
        if (type) {
            res = loadDate(getCompany, null);
        } else {
            res = loadDate(getCompany, {name: $project.val()});
        }
        res.done(function (data) {
            if(data[0] != undefined && type){
                $project.val(data[0].name);
                $project.next().next().val(data[0].iD);
                initPorjectMater(data[0].iD);
            }
            $project.next().children().remove();
            $(data).each(function (index, val) {
                $project.next().append("<li data-project='" + this.iD + "'><a>" + this.name + "</a></li>");
            })
        })
    }

    $(".dropdown").on("click", "li", function () {
        var $ul = $(this).parent();
        var id = $(this).data("project");
        $ul.prev().val($(this).text());
        $ul.next().val(id);
        initPorjectMater(id);
    });
    //移动元素到父窗口中
    $("#parentSearch",parent.document).before($("#testSelect"));
    //添加合计元素到父窗口中
    $("#parentSearch",parent.document).after($count);
    var projectTable;
    function initPorjectMater(id) {
        if(projectTable != undefined){
            $(projectTable).bootstrapTable("refresh", {url: "http://"+webIp+"/managent/getMaterByCompany?companyId="+id+"&start="+startDate+"&end="+end});
            return;
        }
        projectTable = initTable($("#companyMater"),
            "http://"+webIp+"/managent/getMaterByCompany?companyId="+id+"&start="+startDate+"&end="+end,
            {
                search:true,
                height:'auto',
                loadSuccess:function (data) {
                    var search = $("#companyMater").closest(".bootstrap-table").children(".fixed-table-toolbar");
                    $(search).find(".search").removeClass("pull-right").addClass(".pull-left");
                    $("#parentSearch",parent.document).append(search);
                },
            },function(maters){
                counts = 0;
                $(maters).each(function (index, val) {
                    counts += this.moneyTax;
                    this.material.model = '<span title="'+this.material.model+'" class="alert-warning">'+this.material.model+'</span>'
                    this.material.name = '<span title="'+this.material.name+'" class="font-blank">'+this.material.name+'</span>'
                    this.putNumber = '<span title="'+this.putNumber+'" class="alert-danger">'+this.putNumber+'</span>';
                })
                $count.find(".sum").text(counts.toFixed(4));
                return maters;
            },[{
                field:'material.name',
                width:'100px',
                sortable:true,
                title:'材料名称',
            },{
                field:'material.model',
                width:'100px',
                sortable:true,
                title:'型号',
            },{
                field:'material.brand',
                width:'80px',
                sortable:true,
                title:'品牌',
            },{
                field:'putSum',
                width:'80px',
                sortable:true,
                title:'入库数量',
            },{
                field:'taxPrice',
                width:'80px',
                sortable:true,
                title:'单价',
            },{
                field:'moneyTax',
                width:'80px',
                sortable:true,
                title:'金额'
            },{
                field:'tax',
                width:'50px',
                sortable:true,
                title:'税率'
            },{
                field:'putDate',
                width:'80px',
                sortable:true,
                title:'时间'
            },{
                field:'putNumber',
                sortable:true,
                width:'80px',
                title:'入库单号'
            }
            ]);
    }

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

    /**
     * 供应单位输入框事件
     */
    var temp;
    $("#company",parent.document).on("input",function () {
        if(temp != undefined){
            return;
        }
        temp = window.setTimeout(function () {
            initCompany(false);
            window.clearTimeout(temp);
            temp = undefined;
        },300);
    });
    $("#company", parent.document).on("focus",function () {
        $(this).dropdown();
    })
    $("#company", parent.document).on("blur",function () {
        var $input = $(this);
        window.setTimeout(function () {
            $input.closest("div").removeClass("open");
        },200);
    })

    /**
     * 页面离开时调用
     */
    window.onunload = function () {
        //移动元素到父窗口中
        $("#testSelect",parent.document).remove();
        $("#parentSearch",parent.document).children().remove();
        //添加合计元素到父窗口中
        $count.remove();        //删除该页面的搜索元素
        return true;
    }

    /**
     * 导出excel
     * @type {any}
     */
    var $materContaner = $("#companyMater");
    var $exportForm = $("#exportForm");
    $("#export").click(function () {
        var array = "";
        var fileName = "<input type='hidden' name='key' value='"+$project.val()+"'/>";
        $materContaner.find("tr").each(function (index, val) {
            array[index] = "";
            $(this).find("td,th").each(function (index2, val2) {
                // array[index] = "<input type='hidden' name='map.values['"+index+"']["+index2+"]' value='"+$(val2).text()+"'/>";
                // form.append("map.values["+index+"]["+index2+"]",$(val2).text());
                array += "<input type='hidden' name='cells["+index+"].cell["+index2+"]' value='"+$(val2).text()+"'/>";
            });
        });
        $exportForm.children().remove();
        $exportForm.append(fileName);
        $exportForm.append(array);
        $exportForm.attr("action","http://"+webIp+"/managent/exportExcel");
        $exportForm.submit();
    });
    /**
     * 时间改变的事件
     */
    $(document.body).on("change","#start,#end",function () {
        var temp = $(this).val();
        if($(this).attr("id") == "start"){
            startDate = temp;
        }else{
            end = temp;
        }
        initPorjectMater($project.next().next().val());
    });

    /**
     * 时间插件初始化
     */
    $($start).datepicker({
        language: "zh-CN",
        autoclose: true,
        format: "yyyy-mm-dd",
        maxDate:new Date(),
    });
    $end.datepicker({
        language: "zh-CN",
        autoclose: true,
        format: "yyyy-mm-dd",
        maxDate:new Date(),
    });
})