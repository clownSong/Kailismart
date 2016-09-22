package com.kailismart.com.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.Company;
import com.kailismart.com.entity.Count;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-08-09.
 * 相关单位服务接口
 */
public interface CompanyService {
    /**
     * 单位对象
     * @param id 单位id
     * @return 单位
     */
    Company getCompanyById(String id);

    /**
     * 获取供应单位
     * @param bounds 分页对象
     * @return 供应单位集合
     */
    List<Company> getCompanyBySupply(PageBounds bounds);

    /**
     * 获取相关单位总数
     * @param type 单位类型{1：供应商}
     * @return 单位总数
     */
    int count(int type);

    /**
     * 检索单位集合
     * @param name 单位名称
     * @return 单位集合
     */
    List<Company> seek(String name);

    /**
     * 获取常用的单位集合
     * @return
     */
    List<Count> getSumCount(PageBounds bounds);

    List<Company> getCompanyByList(List<Count> count);

    /**
     * 获取常出库的20个单位
     * @return 单位集合
     */
    List<Count> getSumCountByOut();

    /**
     * 搜索所有单位对象
     * @param str 检索字符串
     * @return 单位集合
     */
    List<Company> seekAll(String str);
}
