package com.kailismart.com.mapper;

import com.kailismart.com.entity.FlowCourse;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016-08-16.
 * 流程过程mapper
 */
@Repository
public interface FlowCourseMapper {
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
     * 添加审批过程记录到数据库sdpo020b表中
     * @param flowCourse
     */
    void addFlowCourseB(FlowCourse flowCourse);

    /**
     * 添加审批过程实例化记录
     * @param flowCourse 审批过程
     */
    void addFlowCourseBInstance(FlowCourse flowCourse);
}
