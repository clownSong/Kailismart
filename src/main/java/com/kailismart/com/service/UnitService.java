package com.kailismart.com.service;

import com.kailismart.com.entity.Unit;

/**
 * Created by Administrator on 2016-08-12.
 * 材料单位服务接口
 */
public interface UnitService {
    /**
     * 通过单位id获取材料对象
     * @param id 材料id
     * @return 单位对象
     */
    Unit getUnit(String id);
}
