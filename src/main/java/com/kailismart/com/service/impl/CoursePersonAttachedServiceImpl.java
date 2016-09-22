package com.kailismart.com.service.impl;

import com.kailismart.com.entity.CoursePersonAttached;
import com.kailismart.com.mapper.CoursePersonAttachedMapper;
import com.kailismart.com.service.CoursePersonAttachedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016-08-17.
 */
@Service("coursePersonAttachedService")
public class CoursePersonAttachedServiceImpl implements CoursePersonAttachedService {
    @Autowired
    CoursePersonAttachedMapper coursePersonAttachedMapper;
    public void addAttached(CoursePersonAttached attached) {
        coursePersonAttachedMapper.addPersonAttached(attached);
    }
}
