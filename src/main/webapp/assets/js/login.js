/**
 * Created by Administrator on 2016-08-20.
 */

$(function () {
    /**
     * 提交事件
     */
    $("#login").submit(function(){
        if($('input[name="name"]').val() != undefined && $('input[name="name"]').val() != ""){
            $(this).attr("action","login");
        }
        return true;
    })
})
