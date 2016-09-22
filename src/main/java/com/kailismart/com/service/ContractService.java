package com.kailismart.com.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.ContractType;
import com.kailismart.com.entity.Contract;

import java.util.List;

/**
 * Created by Administrator on 2016-08-11.
 * 合同服务接口
 */
public interface ContractService {
    /**
     * 根据供应单位id后去采购合同集合
     *
     * @param companyId 单位id
     * @param bounds    分页对象
     * @return 采购合同集合
     */
    List<Contract> getContractByCompany(String companyId, PageBounds bounds);

    /**
     * 获取采购合同集合
     * @param bounds 分页对象
     * @return 合同集合
     */
    List<Contract> getContracts(PageBounds bounds);
    /**
     * 检索合同
     * @param str 检索字符串
     *            @param type 合同类型
     * @return 合同集合
     */
    List<Contract> seek(String str,ContractType type);

    /**
     * 通过合同id获取合同对象
     * @param id 合同id
     * @return
     */
    Contract getContractById(String id);
}
