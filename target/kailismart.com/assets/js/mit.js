/**
 * Created by Administrator on 2016-08-10.
 * 采购订单提交
 */

$(function () {




    var $company = $("#company"); //单位元素
    var $flow = $("#flowChoose");       //流程元素
    loadFlow();                         //加载流程
    initCompany($company, 'getCompanys', {index: 0});//初始化单位
    loadStaff($("#staff"), 'proStaff', null);     //初始化职员
    var url;
    var params;
    //注册input输入事件
    $("#pro").on('input', 'input', function (event) {
        var input = event.target;
        switch ($(input).attr('id')) {
            case 'company':
                if ($(input).val() == "") {
                    $(input).next().next().val("");
                    url = 'getCompanys';
                    params = {index: 0};
                } else {
                    url = 'seek';
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
                if(!/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(tax)){
                    $(input).val(17);
                    break;
                }
                reCount(tax);
                break;
        }
    });
    //注册合同输入框事件
    $("#contract").focus(function () {
        initContract(this, 'ByComId', {comId: $company.next().next().val(),index:0});
    })
    $("#contract").on("input",function(){
        initContract(this,'contractBySeek',{str:$(this).val()});
    });
    //注册收货地址输入框事件
    $("#address").focus(function () {
        var $input = $(this);
        var res = initData("getCitys", {index: null});
        res.done(function (citys) {
            $input.next().children().remove();
            $(citys).each(function () {
                $input.next().append('<li data-cid="' + this.iD + '" ' +
                    '><a href="#">' + this.name + '</a></li>');
            })
        })
    });
    //注册备注的事件
    $(".remark").mouseover(function(){
        $(this).css("bottom","26%");
        $(this).children("textarea").focus();
    })
    //备注失去焦点的事件
    $(".remark > textarea").blur(function(){
        $(this).parent().css("bottom","90%");
    });

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
     * 加载采购职员
     * @param input
     * @param url
     * @param params
     */
    function loadStaff(input, url, params) {
        var response = initStaff(input, url, params);
        response.done(function (staffs) {
            $(staffs).each(function () {
                $(input).next().append('<li data-cid="' + this.iD + '"><a href="#">' + this.name + '</a></li>');
            })
        })
    }

    /**
     * 初始化采购职员
     * @param input
     * @param url
     * @param params
     * @returns {*}
     */
    function initStaff(input, url, params) {
        return $.ajax({
            url: url,
            dataType: 'json',
            data: params,
            type: 'POST',
        })
    }

    /**
     * 初始化供应单位数据
     * @param input
     */
    function initCompany(input, url, params) {
        $(input).next().children().remove();
        var res;
        res = loadCompany(url, params);
        res.done(function (data) {
            $(data).each(function () {
                $(input).next().append('<li data-cid="' + this.iD + '"><a href="#">' + this.name + '</a></li>');
            });
        });
    }

    /**
     * 初始化数据
     * @param url 加载地址
     * @param params 参数
     * @returns ajax回调
     */
    function initData(url, params) {
        return $.ajax({
            url: url,
            dataType: 'json',
            data: params,
            type: 'POST',
        });
    }

    /**
     * 加载供应单位数据
     * */
    function loadCompany(url, param) {
        var done = $.ajax({
            url: 'seek',
            data: param,
            dataType: 'json',
            type: 'post'
        });
        return done;
    }

    /**
     * 加载流程数据
     */
    function loadFlow(){
        var res = initData('getFlowByCoding',null);
        res.done(function(flows){
            if(flows[1] == undefined){
                $flow.children(".btn-primary").text(flows[0].name);      //给流程选择器赋初始值
                $flow.children("input[name='flow.ID']").val(flows[0].iD);
                $flow.children("input[name='flow.frameCoding']").val(flows[0].frameCoding);
            }else{
                $flow.children(".btn-primary").text(flows[1].name);      //给流程选择器赋初始值
                $flow.children("input[name='flow.ID']").val(flows[1].iD);
                $flow.children("input[name='flow.frameCoding']").val(flows[1].frameCoding);
            }
            //遍历出所有流程
            $(flows).each(function(){
                $flow.next().append('<li data-flow="'+this.iD+'" ata-flow-frame="'+this.frameCoding+'"><a href="#">'+this.name+'</a></li>');
            })
        });
        //绑定流程选择事件
        $flow.next().on('click','li',function(){
            $flow.children("input[name='flow.ID']").val($(this).data('flow'));
            $flow.children("input[name='flow.frameCoding']").val($(this).data('flow-frame'));
            $flow.children(".btn-primary").text($(this).text());
        })
    }

    function newPro() {
        this.company;
    }

    function reCount(tax){
        $("#maters").find("tr").each(function(){
            var $sum = $(this).find(".sum");   //数量
            var $price = $(this).find(".planPrice"); //单价
            var $sum_price = $price.next(); //金额
            var $notax = $sum_price.next();    //不含税价格
            var sum_priceText;
            sum_priceText = parseFloat($sum.text()) * parseFloat($price.text());      //获取总金额
            if (!isNaN(sum_priceText) || sum_priceText != undefined) {
                $sum_price.text(sum_priceText);  //总金额赋值
                $notax.text((sum_priceText / (1+(tax/100))).toFixed(4));   //不含税价赋值
            }
        })
    }

    /**
     * 提交事件
     */
    $("#sub").click(function () {
        var $pmNumber = $("input[name='pmNumber']");
        if($pmNumber.val() == "" || $pmNumber.val() == undefined || $pmNumber.val().length > 40){
            $pmNumber.val("采购订单-"+getDateNow()+Math.ceil(Math.random()*1000));
        }
        //判断材料是否有选
        if(applys.length <= 0){
            $(this).next().next().show(300);
            return;
        }
        var form = $("#proSubmit");     //form表单
        var maters = $("#maters");  //材料集合容器
        var trList = $(maters).find('tr');   //材料集合
        var inputs = new Array();
        //遍历材料集合，判断数据是否正常
        $(trList).each(function (index, element) {
            var mater = $(this).find(".mater-cid");
            var sum = $(mater).text();       //获取该材料的采购数量
            var materId = $(mater).attr("id");  //获取材料编码
            var applyId = $(mater).data("apply-id");    //获取申请材料id
            var planPrice = $(this).find(".planPrice"); //单价对象
            var planPriceNumber = $(planPrice).text();  //获取单价
            var sumPrice = $(planPrice).next().text();  //获取金额
            var money = $(planPrice).next().next().text();      //不含税金额
            var $projects = $(this).find(".projects");   //项目对象
            var projectId = $projects.data('project');   //获取项目id
            var price = $projects.data("price");   //不含税单价
            var taxMoney = $projects.data("taxmoney"); //税额
            var unit = $projects.data("unit");          //获取单位编码
            var placeDate = $(this).find(".placeDate").text();      //到货日期
            var remark = $(this).find(".mater_remark").text();
            // var inSum = $(this).find(".placeDate").text();      //到货日期
            inputs[index] = '<div class="hide proMaterials"><input type="hidden" value="' + sum + '" name="material[' + index + '].sum">' +   //采购数量
                '<input type="hidden" value="' + materId + '" name="material[' + index + '].ID">' +          //材料编码
                '<input type="hidden" value="' + planPriceNumber + '" name="material[' + index + '].priceTax">' +  //单价(含税)
                '<input type="hidden" value="' + sumPrice + '" name="material[' + index + '].moneyTax">'+    //金额（含税）
                '<input type="hidden" value="' + applyId + '" name="material[' + index + '].major">' +   //材料申请单主键
                '<input type="hidden" value="' + projectId + '" name="material[' + index + '].projectId">'+    //项目id
                '<input type="hidden" value="' + unit + '" name="material[' + index + '].unit.ID">'+   //单位id
                '<input type="hidden" value="' + money + '" name="material[' + index + '].money">'+   //不含税金额
                '<input type="hidden" value="' + price + '" name="material[' + index + '].price">' +    //不含税单价
                '<input type="hidden" value="' + taxMoney + '" name="material[' + index + '].taxMoney">' +    //税额
                '<input type="hidden" value="' + placeDate + '" name="material[' + index + '].dhDate">' +    //到货日期
                '<input type="hidden" value="' + remark + '" name="material[' + index + '].remark">' +    //备注
                '</div>';
        });
        //遍历申请单id
        var applyIds = new Array();
        $(applys).each(function(index,val){
            if(val == undefined || val == null || val == ""){

            }else{
                applyIds[index] = '<input type="hidden" value="' + val + '" name="applyy">';
            }

        });
        var company = $('input[name="company.ID"]');        //单位id
        var tax = $("#tax").val() == "" ? 17 : $("#tax").val();
        // var maters = .find(".mater-cid");       //材料数量和材料id号
        // var planPrice = $(maters).find("planPrice");    //材料单价
        $(maters).each(function (index, element) {
            if (!/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(($(this).text()))) {
                $(this).addClass("border-waring");
            } else {
                //成功
            }
        });
        $(tax).val(tax);
        if (company.val() == "") {
            $(company).prevAll("input").addClass("border-waring");
            return;
        }
        //添加标签到表单中
        $(form).find(".proMaterials").remove();
        $(form).append(inputs);     //添加材料集合
        $(form).append(applyIds);   //添加申请单数量
        $(form).attr("action","http://"+webIp+"/managent/addProcurement");
        $(form).submit();
    });
})
