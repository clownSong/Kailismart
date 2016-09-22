package com.kailismart.com.service.impl;

import com.kailismart.com.entity.Flow;
import com.kailismart.com.mapper.FlowMapper;
import com.kailismart.com.service.FlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-08-15.
 * 流程对象服务实现
 */
@Service("flowService")
public class FlowServiceImpl implements FlowService {
    @Autowired
    FlowMapper flowMapper;
    public List<Flow> getFlowByFrameCoding(String coding) {
        return flowMapper.getFlowByFrameCoding(coding);
    }

    public Map<String,String> getFlorderARemarkAName(String flowID) {
        return flowMapper.getFlorderARemarkAName(flowID);
    }
}
