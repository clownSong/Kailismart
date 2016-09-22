package com.kailismart.com.service.impl;

import com.kailismart.com.entity.FlowCourseRelation;
import com.kailismart.com.entity.FlowCourse_Relation;
import com.kailismart.com.mapper.FlowCourseRelationMapper;
import com.kailismart.com.service.FlowCourseRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016-08-18.
 */
@Service("flowCourseRelationService")
public class FlowCourseRelationServiceImpl implements FlowCourseRelationService {
    @Autowired
    FlowCourseRelationMapper flowCourseRelationMapper;
    public List<FlowCourse_Relation> getRelationByCourseId(String courseId) {
        return flowCourseRelationMapper.getRelationByCourseId(courseId);
    }

    public void addRelationB(FlowCourse_Relation relation) {
        flowCourseRelationMapper.addRelationB(relation);
    }
}
