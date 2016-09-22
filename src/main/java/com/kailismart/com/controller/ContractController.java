package com.kailismart.com.controller;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.Contract;
import com.kailismart.com.entity.ContractType;
import com.kailismart.com.service.ContractService;
import com.kailismart.com.service.ContractTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016-08-11.
 */
@Controller
@RequestMapping("/managent")
public class ContractController {
    @Autowired
    ContractService contractService;
    @Autowired
    ContractTypeService contractTypeService;

    /**
     * 根据单位id获取合同集合
     *
     * @param comId 单位id
     * @return 合同集合
     */
    @RequestMapping("/ByComId")
    @ResponseBody
    public List<Contract> getContractByComId(String comId, Integer index) {
        if (null == index)
            index = 0;
        PageBounds bounds = new PageBounds(index, 100);
        if (null == comId || "".equals(comId)) {
            return contractService.getContracts(bounds);
        }
        return contractService.getContractByCompany(comId, bounds);
    }

    /**
     * 获取所有采购合同集合
     *
     * @return 合同集合
     */
    @RequestMapping("/getContracts")
    @ResponseBody
    public List<Contract> getContracts(int index) {
        PageBounds bounds = new PageBounds(1, 100);
        return contractService.getContracts(bounds);
    }

    /**
     * 检索合同
     *
     * @param str 检索字符串
     *            return 合同集合
     */
    @RequestMapping("/contractBySeek")
    @ResponseBody
    public List<Contract> seek(String str) {
        ContractType contract = contractTypeService.getTypeById("20IBAN7E");
        return contractService.seek(str, contract);
    }


}
