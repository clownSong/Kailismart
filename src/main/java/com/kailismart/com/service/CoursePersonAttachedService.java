package com.kailismart.com.service;

import com.kailismart.com.entity.CoursePersonAttached;

/**
 * Created by Administrator on 2016-08-17.
 * 审批过程人员记录服务
 */
public interface CoursePersonAttachedService {
    /**
     * 添加审批过程记录人员到数据库
     * @param attached 审批过程关系记录
     */
    void addAttached(CoursePersonAttached attached);
}
