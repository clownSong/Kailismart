package com.kailismart.com.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.City;
import com.kailismart.com.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016-08-11.
 * 收货地址控制器
 */
@Controller
@RequestMapping("/managent")
public class CityController {
    @Autowired
    CityService cityService;
    /**
     * 获取收货地址对象
     * @param id 地址id
     * @return 地址对象
     */
    @RequestMapping("/getCityById")
    @ResponseBody
    City getCityById(String id){
        return cityService.getCityById(id);
    }

    /**
     * @param index 页码
     * @return 地址集合
     */
    @RequestMapping("/getCitys")
    @ResponseBody
    List<City> getCitys(Integer index){
        if(null == index)
            index = 0;
        return cityService.getCitys(new PageBounds(index,cityService.count()));
    }

    /**
     * 检索收货地址
     * @param str 字符串
     * @return 地址集合
     */
    @RequestMapping("/citySeek")
    @ResponseBody
    List<City> seek(String str){
        if(null == str){
            return cityService.getCitys(new PageBounds(0,100));
        }
        return cityService.seek(str);
    }
}
