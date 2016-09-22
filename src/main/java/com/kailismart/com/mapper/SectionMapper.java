package com.kailismart.com.mapper;

import com.kailismart.com.entity.Section;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016-08-06.
 * 部门mapper
 */
@Repository
public interface SectionMapper {
    /**
     * 通过部门ID获取部门对象
     * @Param ID 部门id
     * @return 部门对象
     */
    Section getSevtionByID(String ID);
}
