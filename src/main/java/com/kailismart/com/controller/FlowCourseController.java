package com.kailismart.com.controller;

import com.kailismart.com.entity.FlowCourse;
import com.kailismart.com.service.FlowCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016-08-16.
 * 流程过程控制器
 */
@Controller
@RequestMapping("/managent")
public class FlowCourseController {
    @Autowired
    FlowCourseService flowCourseService;

    @RequestMapping("/getCourseFirst")
    @ResponseBody
    public FlowCourse getCourseFirst(String flowId){
        FlowCourse course = flowCourseService.getFlowCourseFirst(flowId);
        return course;
    }
}
