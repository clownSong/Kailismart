package com.kailismart.com.service.impl;

import com.kailismart.com.entity.FlowMessage;
import com.kailismart.com.mapper.FlowMessageMapper;
import com.kailismart.com.service.FlowMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016-08-16.
 * 审批流程消息服务实现类
 */
@Service("flowMessageService")
public class FlowMessageServiceImpl implements FlowMessageService {
    @Autowired
    FlowMessageMapper flowMessageMapper;
    public Integer addFlowMessage(FlowMessage flowMessage) {
        return flowMessageMapper.addFlowMessage(flowMessage);
    }

    public Integer updateMessage(String id, int state) {
        return flowMessageMapper.updateMessage(id,state);
    }

    public void addFlowMessageHistory(FlowMessage flowMessage) {
        flowMessageMapper.addFlowMessageHistory(flowMessage);
    }
}
