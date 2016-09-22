/**
 * Created by Administrator on 2016-08-09.
 */
/**
 * 初始化表格插件
 * @param table 表格对象
 * @param url 数据地址
 * @param mparam 参数
 * @param res 回调函数
 * @param colums 列数组
 */
var webIp = window.location.host;
function initTable(table, url, param, res, columns) {
    if(param == null || param == "" || param == undefined){
        param = {size:10,height:"auto",pagination:false,loadSuccess:function (data) {
            var parent = $(table).parent().parent().parent().parent();
            var $search = $(parent).find(".fixed-table-toolbar").appendTo($(parent).prev()).end();
        },search:true};
    }
    var table = $(table).bootstrapTable({
        url: url,
        method: 'POST',           //请求方式（*）
        toolbar: '#toolbar',        //工具按钮用哪个容器
        striped: true,           //是否显示行间隔色
        cache: false,            //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        sortable: true,           //是否启用排序
                  //排序方式
        // queryParams: param,//传递参数（*）
        queryParamsType: 'String',      //设置参数类型为restfull风格
        // sidePagination: "client",      //分页方式：client客户端分页，server服务端分页（*）
        // pageNumber: 1,            //初始化加载第一页，默认第一页
        pageSize: param.size,            //每页的记录行数（*）
        pageList: [10,20],    //可供选择的每页的行数（*）
        strictSearch: false,             //false:模糊搜索
        clickToSelect: true,        //是否启用点击选中行
        height:param.height,            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        pagination: param.pagination,          //是否显示分页（*）
        uniqueId: "id",           //每一行的唯一标识，一般为主键列
        cardView: false,          //是否显示详细视图
        detailView: false,          //是否显示父子表
        responseHandler: res,       //服务器返回数据后的处理
        columns: columns,             //列集合
        search:param.search,                //是否开启搜索
        queryParams: function (params) {
            // params = param;
            // alert(JSON.stringify(param)+param.applyId);
            return '';
        },
        onLoadSuccess:param.loadSuccess,
    });
    return table;
}
/**
 * 加载本地json数据
 * @param table
 * @param data
 * @param res
 * @param columns
 */
var sync = true;
function initTableLocal(tab,data,columns){
    var table = $(tab).bootstrapTable({
        onAll:function (name,args) {
            // alert(JSON.stringify(name));
            // $("#material").colResizable();
            // console.log(args);
            // console.log($("#myModal table:eq(1)").attr("id"));
            // console.log("事件名称："+name);
            if(name == "post-header.bs.table" && sync){
                console.log("事件名称："+name);
                $("#myModal table:eq(1)").colResizable({
                    liveDrag:true,      //跟随鼠标移动时改变大小
                    partialRefresh:true,        //局部刷新
                    onResize:function (e) {     //改变大小时触发
                    var test = e.currentTarget;
                    $(test).children("thead").clone().replaceAll($(test).parent().prev().find("thead"));
                }});
                $(table).bootstrapTable("resetView");       //重置视图
                sync = false;
            }
        },
        data:data,
        method: 'POST',           //请求方式（*）
        toolbar: '#toolbar',        //工具按钮用哪个容器
        striped: true,           //是否显示行间隔色
        cache: false,            //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        sortable: true,           //是否启用排序
        queryParamsType: 'String',      //设置参数类型为restfull风格
        // pageSize: 10,            //每页的记录行数（*）
        // pageList: [],    //可供选择的每页的行数（*）
        strictSearch: false,             //false:模糊搜索
        clickToSelect: true,        //是否启用点击选中行
        height:'350',            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        pagination: false,          //是否显示分页（*）
        uniqueId: "iD",           //每一行的唯一标识，一般为主键列
        cardView: false,          //是否显示详细视图
        detailView: false,          //是否显示父子表
        columns: columns,             //列集合
        search:false,                //是否开启搜索
    });
    return table;
}
/**
 * 初始化滚动条
 * @param $element
 */
function initScroll($element){
    $element.niceScroll({
        styler: "fb",
        cursorcolor: "#65cea7",
        cursorwidth: '6',
        cursorborderradius: '0px',
        background: '#424f63',
        spacebarenabled: false,
        cursorborder: '0',
        zindex: '1000'
    });
}

/**
 获取一礼拜后的时间
 */
function getDate(){
    var now = new Date();
    var date = new Date(now.getTime() - 7 * 24 * 3600 * 1000);
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    // var hour = date.getHours();
    // var minute = date.getMinutes();
    // var second = date.getSeconds();
    return year + '-' + month + '-' + day;
}

function getDateNow(){
    var now = new Date();
    return now.getFullYear()+""+(now.getMonth()+1)+""+now.getDate()+""+now.getHours()+""+now.getMinutes();
}

function getNowDate(){
    var now = new Date();
    return now.getFullYear()+"-"+parse0((now.getMonth()+1))+"-"+parse0(now.getDate());
}
/**
 * 小数点格式化
 * @param v 值
 * @param e 保留数
 * @returns {number}
 */
function round(v,e){

    var t=1;

    for(;e>0;t*=10,e--);

    for(;e<0;t/=10,e++);

    return Math.round(v*t)/t;

}

/* 获取上一个月的日期
*
* @date 格式为yyyy-mm-dd的日期，如：2014-01-25
*/
function getPreMonth(date) {
    var arr = date.split('-');
    var year = arr[0]; //获取当前日期的年份
    var month = arr[1]; //获取当前日期的月份
    var day = arr[2]; //获取当前日期的日
    var days = new Date(year, month, 0);
    days = days.getDate(); //获取当前日期中月的天数
    var year2 = year;
    var month2 = parseInt(month) - 1;
    if (month2 == 0) {//如果是1月份，则取上一年的12月份
        year2 = parseInt(year2) - 1;
        month2 = 12;
    }
    var day2 = day;
    var days2 = new Date(year2, month2, 0);
    days2 = days2.getDate();
    if (day2 > days2) {//如果原来日期大于上一月的日期，则取当月的最大日期。比如3月的30日，在2月中没有30
        day2 = days2;
    }
    var t2 = year2 + '-' +parse0(month2) + '-' + parse0(day2);
    return t2;
}
/**
 * 获取日期中月份的第一天
 */
function getfirstDate(firstDate){
    firstDate.setDate(1); //第一天
    firstDate.setMonth((firstDate.getMonth()+1));
    return firstDate.getFullYear()+"-"+parse0(firstDate.getMonth())+"-"+parse0(firstDate.getDate());
}
/**
 * 获取日期中月份的最后一天
 * @param data
 */
function getEndDate(endDate){
    endDate.setMonth((endDate.getMonth()+1));
    endDate.setDate(0);    //最后一天
    return endDate.getFullYear()+"-"+parse0(endDate.getMonth()+1)+"-"+parse0(endDate.getDate());
}
/**
 * 日期补零方法
 * @param s
 * @returns {string}
 */
function parse0(s) {
    return s < 10 ? '0' + s: s;
}

/**
 *获取半年前的日期对象
 */
function getHaifDate() {
    var dt = new Date();
    dt.setMonth(dt.getMonth()-6);
    return dt;
}

function parseIntForZ(num){
    if(num.indexOf(".") != -1){
        if(num.split(".")[1] > 0){
            num = parseInt(num.split(".")[0]) + 1;
        }
    }
    return num;
}