package com.kailismart.com.service;

import com.kailismart.com.entity.CoursePerson;

import java.util.List;

/**
 * Created by Administrator on 2016-08-20.
 * 步骤人员服务
 */
public interface CoursePersonService {
    /**
     * 根据过程id获取审批人员集合
     * @param courseId 过程id
     * @return 审批人员对象集合
     */
    List<CoursePerson> getPersonByCourseId(String courseId);
}
