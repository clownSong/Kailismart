package com.kailismart.com.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.ContractType;
import com.kailismart.com.entity.Contract;
import com.kailismart.com.mapper.ContractMapper;
import com.kailismart.com.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016-08-11.
 */
@Service("contractService")
public class ContractServiceImpl implements ContractService {
    @Autowired
    ContractMapper contractMapper;
    public List<Contract> getContractByCompany(String companyId, PageBounds bounds) {
        return contractMapper.getContractByCompany(companyId,bounds);
    }
    public List<Contract> getContracts(PageBounds bounds) {
        return contractMapper.getContracts(bounds);
    }

    public List<Contract> seek(String str, ContractType type) {
        return contractMapper.seek(str,type);
    }

    public Contract getContractById(String id) {
        return contractMapper.getContractById(id);
    }

}
