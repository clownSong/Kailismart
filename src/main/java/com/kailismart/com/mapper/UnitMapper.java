package com.kailismart.com.mapper;

import com.kailismart.com.entity.Unit;

/**
 * Created by Administrator on 2016-08-12.
 * 材料单位mapper
 */
public interface UnitMapper {
    /**
     * 通过单位id获取单位对象
     * @param id 单位id
     * @return
     */
    Unit getUnit(String id);
}
