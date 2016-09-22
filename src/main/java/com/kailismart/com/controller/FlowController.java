package com.kailismart.com.controller;

import com.kailismart.com.entity.Flow;
import com.kailismart.com.service.FlowService;
import com.kailismart.com.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016-08-15.
 * 流程控制器
 */
@Controller
@RequestMapping("/managent")
public class FlowController {
    @Autowired
    FlowService flowService;
    /**
     * 获取采购订单流程集合
     * @return 流程集合
     */
    @RequestMapping("/getFlowByCoding")
    @ResponseBody
    public List<Flow> getFlowByCoding(){
        return flowService.getFlowByFrameCoding(Constant.FRAME_CODING_PROCUREMENT);
    }
}
