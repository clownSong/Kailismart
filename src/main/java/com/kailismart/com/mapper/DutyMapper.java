package com.kailismart.com.mapper;

import com.kailismart.com.entity.Staff;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016-08-20.
 * 职务mapper
 */
@Repository
public interface DutyMapper {
    /**
     * 通过职务编码获取该职务所有人员
     * @return 职务人员集合
     */
    List<Staff> getStaffByDuty(String dutyId);
}
