/**
 * Created by 宋正根 on 2016/8/31.
 */
$(function () {
    var $project = $("#project");   //项目元素
    var $company = $("#company");   //单位元素
    var $putStorage = $("#putStorage"); //入库单元素
    var $outPerson = $("#outPerson");           //领料人元元素
    var $section = $("#section");           //部门元素
    var $remark = $("#remark");           //备注
    var $outNumber = $("#outNumber");     //领料单号
    var $outDate = $("#outDate");     //领料时间
    $outDate.val(getNowDate());
    var $tax = $("#tax");     //税率

    var matersList = new Array();




    $("#outMaterButton").click(function(){
        if($project.next().next().val() == ""){ //判断项目是否为空
            $project.addClass("border-waring");
            return;
        }

        if($company.next().next().val() == ""){     //判断单位是否为空
            $company.addClass("border-waring");
            return;
        }

        if($outPerson.next().next().val() == ""){   //判断领料人是否为空
            $outPerson.addClass("border-waring");
            return;
        }

        if($section.next().next().val() == ""){     //判断领料部门是否为空
            $section.addClass("border-waring")
            return;
        }
        var $message;       //出库数量和材料id和入库单材料id
        var $price;        //出库单价
        var $moneytax;      //出库金额
        var temp;
        var isMit = false;
        $("#outMaterListBody").find("tr").each(function(index,val){
            $message = $(this).find(".putsum");
            $message.text();        //出库数量
            $message.data("mater"); //材料id
            $price = $(this).find(".taxprice");     //单价
            $moneytax = $(this).find(".moneytax");      //金额
            temp = newOutMater($message.data("mater"),$message.text(),$price.text(),$moneytax.text(),$message.data("putmater"));

            if(temp.outSum <= 0){
                isMit = true;
                return false;
            }

            if(temp.price <= 0){
                isMit = true;
                return false;
            }
            matersList[index] = "<div class='outFormH'>" +
                "<input type='hidden' value='"+temp.outSum+"' name='materOuts["+index+"].sum'/>" +      //领用数量
                "<input type='hidden' value='"+temp.mater+"' name='materOuts["+index+"].material.ID'/>" +   //领用材料
                "<input type='hidden' value='"+temp.price+"' name='materOuts["+index+"].taxPrice'/>" +      //领用单价
                "<input type='hidden' value='"+temp.money+"' name='materOuts["+index+"].taxMoney'/>" +      //领用金额
                "<input type='hidden' value='"+temp.putId+"' name='materOuts["+index+"].putMaterId'/>" +      //入库单材料id
                "</div>";

        });

        if(isMit){
            alert("材料信息不正确");
            return;
        }
        if(matersList.length <= 0){
            alert("请选择材料");
            return;
        }


        var $form = $("#toOut");
        $form.append(matersList);       //添加材料到表单中
        $form.attr("action","http://"+webIp+"/managent/addOutMater");
        $form.submit();







    })

    function newOutMater(materId, outSum, price, money, putId,id) {
        this.mater = materId;
        this.outSum = outSum;
        this.price = price;
        this.money = money;
        this.putId = putId;
        this.id = id;
        return this;
    }
})