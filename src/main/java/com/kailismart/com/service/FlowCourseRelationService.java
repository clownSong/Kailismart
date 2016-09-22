package com.kailismart.com.service;

import com.kailismart.com.entity.FlowCourse_Relation;

import java.util.List;

/**
 * Created by Administrator on 2016-08-18.
 * 过程关系表服务接口
 */
public interface FlowCourseRelationService {
    /**
     * 通过过程id获取关系对象
     * @param courseId 过程对象
     * @return 过程关系对象集合
     */
    List<FlowCourse_Relation> getRelationByCourseId(String courseId);

    /**
     * 添加流转关系对象到sdpo020b_relation表中
     * @param relation 流转关系对象
     */
    void addRelationB(FlowCourse_Relation relation);
}
