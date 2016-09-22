package com.kailismart.com.mapper;

import com.kailismart.com.entity.FlowApprove;
import com.kailismart.com.entity.Sdpo004PrintShow;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016-08-16.
 * 审批流程步骤mapper
 */
@Repository
public interface FlowApproveMapper {
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
     * 添加流程审批步骤关系表对象 sdpo004_printShow表，控制【我的审批】消息显示
     * @param sdpo004PrintShow sdpo004_printShow 关系表对象
     */
    void addPrintShow(Sdpo004PrintShow sdpo004PrintShow);
}
