package com.kailismart.com.service;

import com.kailismart.com.entity.FlowCourse;

import java.util.List;

/**
 * Created by Administrator on 2016-08-16.
 * 流程过程服务接口
 */
public interface FlowCourseService {
    /**
     * 根据流程id获取第一个审批过程对象
     * @param flowId 流程对象id
     * @return 流程过程
     */
    FlowCourse getFlowCourseFirst(String flowId);

    /**
     * 根据流程id获取该流程步骤集合
     * @param flowId 流程id
     * @return 过程集合
     */
    List<FlowCourse> getFlowCourseByFlow(String flowId);

    /**
     * 添加审批过程步骤到sdpo020b表中
     */
    void addFlowCourseB(FlowCourse flowCourse);

    void addFlowCourseBInstance(FlowCourse flowCourse);
}
