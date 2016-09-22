package com.kailismart.com.mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.Company;
import com.kailismart.com.entity.Count;
import com.kailismart.com.entity.Procurement;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-08-09.
 * 相关单位mapper
 */
@Repository
public interface CompanyMapper {
    /**
     * 供应商 单位对象
     * @param id 单位id
     * @return 供应商单位对象
     */
    Company getCompanyById(String id);

    Company getCompanyByIdAll(String id);
    /**
     * 获取供应单位
     * @param bounds 分页对象
     * @return 供应单位集合
     */
    List<Company> getCompanyBySupply(PageBounds bounds);

    /**
     * 获取相关单位总数
     * @param type 单位类型
     * @return 单位总数
     */
    int count(int type);

    /**
     * 检索单位集合
     * @param name 单位简称
     * @return
     */
    List<Company> seek(String name);

    /**
     * 获取常用的供应商
     * @return 供应商集合
     */
    List<Count> getSumCount(PageBounds bounds);

    List<Company> getCompanyByList(List<Count> count);

    /**
     * 获取常用的出库单位集合
     * @return 单位集合
     */
    List<Count> getSumCountByOut();

    /**
     * 检索所有单位对象
     * @param str 检索字符串
     * @return 单位集合
     */
    List<Company> seekAll(@Param("name") String str);
}
