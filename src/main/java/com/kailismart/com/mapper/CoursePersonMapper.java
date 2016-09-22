package com.kailismart.com.mapper;

import com.kailismart.com.entity.CoursePerson;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016-08-16.
 * 审批过程人员mapper
 */
@Repository
public interface CoursePersonMapper {
    /**
     * 根据过程id获取审批人员集合
     * @param courseId 过程id
     * @return 审批人员对象集合
     */
    List<CoursePerson> getPersonByCourseId(String courseId);
}
