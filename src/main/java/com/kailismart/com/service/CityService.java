package com.kailismart.com.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.City;

import java.util.List;

/**
 * Created by Administrator on 2016-08-11.
 * 地址服务
 */
public interface CityService {
    /**
     * 获取收货地址对象
     * @param id 地址id
     * @return 地址对象
     */
    City getCityById(String id);

    /**
     * @param bounds 分页对象
     * @return 地址集合
     */
    List<City> getCitys(PageBounds bounds);

    /**
     * 检索收货地址
     * @param str 字符串
     * @return 地址集合
     */
    List<City> seek(String str);

    int count();
}
