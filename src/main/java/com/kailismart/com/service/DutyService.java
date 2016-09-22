package com.kailismart.com.service;

import com.kailismart.com.entity.Staff;

import java.util.List;

/**
 * Created by Administrator on 2016-08-20.
 * 职务服务接口
 */

public interface DutyService {
    /**
     * 通过职务编码获取该职务所有人员
     * @return 职务人员集合
     */
    List<Staff> getStaffByDuty(String dutyId);
}
