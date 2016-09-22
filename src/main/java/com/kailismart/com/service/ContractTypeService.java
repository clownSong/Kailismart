package com.kailismart.com.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.Contract;
import com.kailismart.com.entity.ContractType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016-08-11.
 * 合同类型服务
 */
@Repository
public interface ContractTypeService {
    /**
     * 根据字符串获取合同对象
     * @param str 合同名称
     * @return 合同对象
     */
    ContractType getTypeByStr(String str);

    /**
     * 根据类型id获取类型对象
     * @param id 类型id
     * @return 类型对象
     */
    ContractType getTypeById(String id);
}
