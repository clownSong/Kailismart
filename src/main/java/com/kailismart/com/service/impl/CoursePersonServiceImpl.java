package com.kailismart.com.service.impl;

import com.kailismart.com.entity.CoursePerson;
import com.kailismart.com.mapper.CoursePersonMapper;
import com.kailismart.com.service.CoursePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016-08-20.
 */
@Service("coursePersonService")
public class CoursePersonServiceImpl implements CoursePersonService {
    @Autowired
    CoursePersonMapper coursePersonMapper;
    public List<CoursePerson> getPersonByCourseId(String courseId) {
        return coursePersonMapper.getPersonByCourseId(courseId);
    }
}
