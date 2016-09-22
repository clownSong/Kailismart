package com.kailismart.com.service;

import com.kailismart.com.entity.FlowApprove;
import com.kailismart.com.entity.Sdpo004PrintShow;

import java.util.List;

/**
 * Created by Administrator on 2016-08-16.
 * 发起流程步骤服务
 */
public interface FlowApproveService {
    /**
     * 添加单个审批步骤对象
     * @param approve 审批步骤对象
     */
    void addApprove(FlowApprove approve);

    /**
     * 添加流程步骤集合
     * @param approveList 流程对象集合
     */
    void addApproves(List<FlowApprove> approveList);

    /**
     * 添加pringShow对象到表中
     * @param sdpo004PrintShow sdpo004_printShow 对象
     */
    void addPrintShow(Sdpo004PrintShow sdpo004PrintShow);
}
