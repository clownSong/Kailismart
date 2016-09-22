package com.kailismart.com.mapper;

import com.kailismart.com.entity.CoursePersonAttached;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016-08-17.
 * 审批过程人员记录表mapper
 */
@Repository
public interface CoursePersonAttachedMapper {
    /**
     * 添加审批过程人员记录
     * @param attached 审批过程人员记录
     */
    void addPersonAttached(CoursePersonAttached attached);
}
