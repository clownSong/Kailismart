package com.kailismart.com.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.Company;
import com.kailismart.com.entity.Count;
import com.kailismart.com.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-08-09.
 */
@Controller
@RequestMapping("/managent")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    /**
     * 单位对象
     *
     * @param id 单位id
     * @return 单位
     */
    @RequestMapping("/getCompanyById")
    @ResponseBody
    Company getCompanyById(String id) {
        return companyService.getCompanyById(id);
    }

    /**
     * 获取供应单位
     *
     * @return 供应单位集合
     */
    @RequestMapping("/getCompanys")
    @ResponseBody
    List<Company> getCompanyBySupply(int index) {
        PageBounds bounds = new PageBounds(index, companyService.count(1));
        return companyService.getCompanyBySupply(bounds);
    }

    /**
     * 检索单位集合
     *
     * @param name 模糊查询的字符串
     * @return 单位集合
     */
    @RequestMapping("/seek")
    @ResponseBody
    List<Company> seek(String name) {
        if (null == name || "".equals(name)) {
            List<Count> count = companyService.getSumCount(new PageBounds(0, ApplyController.PAGESIZE));
            List<Company> companies = companyService.getCompanyByList(count);
            return companies;
        }
        return companyService.seek(name);
    }

    /**
     * 搜索单位，默认加载常用的出库单位
     *
     * @param str 单位名称
     * @return 单位集合
     */
    @RequestMapping("/seekByOut")
    @ResponseBody
    List<Company> seekByOutMater(String str) {
        if (str == null || "".equals(str)) {
            List<Count> counts = companyService.getSumCountByOut();
            return companyService.getCompanyByList(counts);
        }
        return companyService.seekAll(str);

    }

}
