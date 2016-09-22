/**
 * Created by 宋正根 on 2016/9/8.
 */
$(function () {
    /**
     * 自己写的列移动插件
     */
    /*var $th;
    var moveType;
    var offset = {x:0,y:0};
    var sync = false;
    var index;      //列索引
    var $table = $("#myModal");
    //鼠标按下事件
    $("#myModal").on("mousedown","th",function (event) {
        $th = $(this);
        index = $(this).index();
        sync = true;
;    });
    //鼠标按键抬起事件
    $("#myModal").on("mouseup","th",function (event) {
        sync = false;
    });
    //鼠标移动事件
    $("#myModal").on("mousemove","th",function (event) {
        if(event.pageX < offset.x){
            moveType = 0;
        }else if(event.pageX >= offset.x){
            moveType = 1;
        }

        if(sync){
            var widths = "." + $th.attr("class");
            if(moveType == 0){      //左移动
                var temp = parseFloat($th.width());
                console.log("左移动前："+temp);
                temp = temp - 5;
                temp += "px";
                console.log("左移动后："+temp);
                // $th.width("width",temp+"");
                $table.find(widths).width(temp);
            }else if(moveType == 1){    //右移动
                $th.width(($th.width()+5));
                console.log("右移动"+$th.width());
                $table.find(widths).width($th.width());
            }
        }

        offset.x = event.pageX;
        offset.y = event.pageY;
    })*/


    /**
     * 初始化表格移动插件
     */
})
