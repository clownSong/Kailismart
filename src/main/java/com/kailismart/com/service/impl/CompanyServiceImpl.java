package com.kailismart.com.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.Company;
import com.kailismart.com.entity.Count;
import com.kailismart.com.mapper.CompanyMapper;
import com.kailismart.com.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-08-09.
 * 相关单位服务实现
 */
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyMapper companyMapper;
    public Company getCompanyById(String id) {
        return companyMapper.getCompanyById(id);
    }

    public List<Company> getCompanyBySupply(PageBounds bounds) {
        return companyMapper.getCompanyBySupply(bounds);
    }

    public int count(int type) {
        return companyMapper.count(type);
    }

    public List<Company> seek(String name) {
        return companyMapper.seek(name);
    }

    public List<Count> getSumCount(PageBounds bounds) {
        return companyMapper.getSumCount(bounds);
    }

    public List<Company> getCompanyByList(List<Count> count) {
        return companyMapper.getCompanyByList(count);
    }

    public List<Count> getSumCountByOut() {
        return companyMapper.getSumCountByOut();
    }

    public List<Company> seekAll(String str) {
        return companyMapper.seekAll(str);
    }

}
