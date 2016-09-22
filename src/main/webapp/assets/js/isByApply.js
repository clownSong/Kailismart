/**
 * Created by 宋正根 on 2016/8/29.
 */
var applys = new Array();       //申请单id集合
var cids = new Array();         //材料Id集合
var $sumAll = $("#sumAll");
var sumAllText = 0;
$(function(){
    var applyId;            //申请单id临时变量
    var materId;            //材料id临时变量
    var $sum;   //数量
    var $price; //单价
    var $sum_price; //金额
    var $notax;    //不含税价格
    var sum_priceText;      //总金额
    var $messages;          //消息集合
    var tax = 17;       //默认税率
    //遍历申请单中跳转过来的材料
    $("#maters").find("tr").each(function(){
        applyId = $(this).find(".mater-cid").data("apply");
        materId = $(this).find(".mater-cid").attr("id");
        $sum = $(this).find(".sum");
        $price = $(this).find(".planPrice");
        $sum_price = $price.next();
        $notax = $sum_price.next();
        $messages = $(this).find(".projects");

        //判断申请单id是否存在
        if(applys.indexOf(applyId) == -1){
            applys.push(applyId);
        }

        if(cids.indexOf(materId) == -1){
            cids.push(materId);     //添加到数组中
        }

        $(this).find(".placeDate").text(getDate());   //为到货时间赋值
        sum_priceText = parseFloat($sum.text()) * parseFloat($price.text());
        sumAllText += sum_priceText;
        if(!isNaN(sum_priceText) && sum_priceText != undefined){
            $sum_price.text(sum_priceText);  //总金额赋值
            $notax.text((sum_priceText / (1 + (tax / 100))).toFixed(4));   //不含税总价赋值
            $messages.attr("data-taxmoney", (sum_priceText - parseFloat($notax.text())));       //设置税额
            $messages.attr("data-price", (parseFloat($notax.text()) / parseFloat($sum.text())));       //设置不含税单价
        }
    });
    $sumAll.text(sumAllText);
})
