package com.kailismart.com.service.impl;

import com.kailismart.com.entity.CoursePerson;
import com.kailismart.com.entity.FlowCourse;
import com.kailismart.com.mapper.FlowCourseMapper;
import com.kailismart.com.service.CoursePersonService;
import com.kailismart.com.service.FlowCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016-08-16.
 * 流程过程服务接口
 */
@Service("flowCourseService")
public class FlowCourseServiceImpl implements FlowCourseService {
    @Autowired
    FlowCourseMapper flowCourseMapepr;
    @Autowired
    CoursePersonService coursePersonService;
    public FlowCourse getFlowCourseFirst(String flowId) {
        return flowCourseMapepr.getFlowCourseFirst(flowId);
    }

    public List<FlowCourse> getFlowCourseByFlow(String flowId) {
        return flowCourseMapepr.getFlowCourseByFlow(flowId);
    }

    public void addFlowCourseB(FlowCourse flowCourse) {
        flowCourseMapepr.addFlowCourseB(flowCourse);
    }

    public void addFlowCourseBInstance(FlowCourse flowCourse) {
        flowCourseMapepr.addFlowCourseBInstance(flowCourse);
    }
}
