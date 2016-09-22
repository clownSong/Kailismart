/**
 * Created by 宋正根 on 2016/9/4.
 */
$(function () {
    var $project = $("#project");   //项目元素
    var $count = $("#count");       //统计元素
    var counts = 0;
    var getProject = "http://" + webIp + "/managent/getProjectBySeek";
    var res;
    initProject(true);

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
            if (data[0] != undefined && type) {
                $project.val(data[0].name);
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
    $("#parentSearch", parent.document).before($("#testSelect"));
    //添加合计元素到父窗口中
    $("#parentSearch", parent.document).after($count);
    var projectTable;

    function initPorjectMater(id) {
        if (projectTable != undefined) {
            $(projectTable).bootstrapTable("refresh", {url: "http://" + webIp + "/managent/getMaterOutChildByProject?projectId=" + id});
            return;
        }
        projectTable = initTable($("#projectOutMaterTable"),
            "http://" + webIp + "/managent/getMaterOutChildByProject?projectId=" + id,
            {
                search: true,
                height: 'auto',
                loadSuccess: function (data) {
                    var search = $("#projectOutMaterTable").closest(".bootstrap-table").children(".fixed-table-toolbar");
                    $(search).find(".search").removeClass("pull-right").addClass(".pull-left");
                    $("#parentSearch", parent.document).append(search);
                },
            }, function (maters) {
                counts = 0;
                $(maters).each(function (index, val) {
                    counts += this.taxMoney;
                    this.outNumber = '<span title="' + this.outNumber + '" class="alert-info">' + this.outNumber + '</span>';
                })
                $count.find(".sum").text(counts.toFixed(4));
                return maters;
            }, [{
                field: 'material.name',
                width: '100px',
                sortable: true,
                title: '材料名称',
            }, {
                field: 'material.model',
                width: '100px',
                sortable: true,
                title: '型号',
            }, {
                field: 'material.brand',
                width: '80px',
                sortable: true,
                title: '品牌',
            }, {
                field: 'sum',
                width: '80px',
                sortable: true,
                title: '领用数量',
            }, {
                field: 'taxPrice',
                width: '80px',
                sortable: true,
                title: '领用单价',
            }, {
                field: 'taxMoney',
                width: '80px',
                sortable: true,
                title: '金额'
            }, {
                field: 'outDate',
                width: '80px',
                sortable: true,
                title: '时间'
            }, {
                field: 'outNumber',
                sortable: true,
                width: '80px',
                title: '单号'
            }]);
    }

    var temp;
    $("#project", parent.document).on("input", function () {
        $(this).dropdown();
        if(temp != undefined){
            return;
        }
        temp = window.setTimeout(function () {
            initProject(false);
            window.clearTimeout(temp);
            temp = undefined;
        },300);
    })
    $("#project", parent.document).on("focus",function () {
        $(this).dropdown();
    })
    $("#project", parent.document).on("blur",function () {
        var $input = $(this);
        window.setTimeout(function () {
            $input.closest("div").removeClass("open");
        },200);
    })
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
     * 页面离开时调用
     */
    window.onunload = function () {
        //移动元素到父窗口中
        $("#testSelect", parent.document).remove();
        $("#parentSearch", parent.document).children().remove();
        //添加合计元素到父窗口中
        $count.remove();        //删除该页面的搜索元素
        return true;
    }

    /**
     * 导出excel
     * @type {any}
     */
    var $materContaner = $("#projectOutMaterTable");
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
})