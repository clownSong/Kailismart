package com.kailismart.com.service.impl;

import com.kailismart.com.entity.FlowApprove;
import com.kailismart.com.entity.Sdpo004PrintShow;
import com.kailismart.com.mapper.FlowApproveMapper;
import com.kailismart.com.service.FlowApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016-08-16.
 * 流程发起步骤实现类
 */
@Service("flowApproveService")
public class FlowApproveServiceImpl implements FlowApproveService {
    @Autowired
    FlowApproveMapper flowApproveMapper;
    public void addApprove(FlowApprove approve) {
        flowApproveMapper.addApprove(approve);
    }

    public void addApproves(List<FlowApprove> approveList) {
        flowApproveMapper.addApproves(approveList);
    }

    public void addPrintShow(Sdpo004PrintShow sdpo004PrintShow) {
        flowApproveMapper.addPrintShow(sdpo004PrintShow);
    }
}
