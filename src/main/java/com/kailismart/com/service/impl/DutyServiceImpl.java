package com.kailismart.com.service.impl;

import com.kailismart.com.entity.Staff;
import com.kailismart.com.mapper.DutyMapper;
import com.kailismart.com.service.DutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016-08-20.
 */
@Service("dutyService")
public class DutyServiceImpl implements DutyService {
    @Autowired
    DutyMapper dutyMapper;
    public List<Staff> getStaffByDuty(String dutyId) {
        return dutyMapper.getStaffByDuty(dutyId);
    }
}
