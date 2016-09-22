package com.kailismart.com.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.City;
import com.kailismart.com.mapper.CityMapper;
import com.kailismart.com.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016-08-11.
 * 地址服务实现
 */
@Service("cityService")
public class CityServiceImpl implements CityService {
    @Autowired
    CityMapper cityMapper;
    public City getCityById(String id) {
        return cityMapper.getCityById(id);
    }

    public List<City> getCitys(PageBounds bounds) {
        return cityMapper.getCitys(bounds);
    }

    public List<City> seek(String str) {
        return cityMapper.seek(str);
    }

    public int count() {
        return cityMapper.count();
    }
}
