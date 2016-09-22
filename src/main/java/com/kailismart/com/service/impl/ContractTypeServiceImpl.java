package com.kailismart.com.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.Contract;
import com.kailismart.com.entity.ContractType;
import com.kailismart.com.mapper.ContractMapper;
import com.kailismart.com.mapper.ContractTypeMapper;
import com.kailismart.com.service.ContractTypeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016-08-11.
 */
@Service("contractTypeService")
public class ContractTypeServiceImpl implements ContractTypeService {
    @Autowired
    ContractTypeMapper contractTypeMapper;

    public ContractType getTypeByStr(String str) {
        return contractTypeMapper.getTypeByStr(str);
    }

    public ContractType getTypeById(String id) {
        return contractTypeMapper.getTypeById(id);
    }
}
