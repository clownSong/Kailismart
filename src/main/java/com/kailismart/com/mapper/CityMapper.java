package com.kailismart.com.mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.City;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016-08-11.
 * 收货地址对象
 */
@Repository
public interface CityMapper {
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

    /**
     * 获取收货地址总数
     * @return 总数
     */
    int count();
}
