package com.kailismart.com.service.impl;

import com.kailismart.com.entity.Unit;
import com.kailismart.com.mapper.UnitMapper;
import com.kailismart.com.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016-08-12.
 */
@Service("unitService")
public class UnitServiceImpl implements UnitService {
    @Autowired
    UnitMapper unitMapper;
    public Unit getUnit(String id) {
        return unitMapper.getUnit(id);
    }
}
