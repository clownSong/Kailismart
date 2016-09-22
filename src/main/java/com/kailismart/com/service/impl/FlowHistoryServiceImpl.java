package com.kailismart.com.service.impl;

import com.kailismart.com.entity.FlowHistory;
import com.kailismart.com.mapper.FlowHistoryMapper;
import com.kailismart.com.service.FlowHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016-08-16.
 * 流程记录服务实现类
 */
@Service("flowHistoryService")
public class FlowHistoryServiceImpl implements FlowHistoryService {
    @Autowired
    FlowHistoryMapper flowHistoryMapper;
    public Integer addHistory(FlowHistory flowHistory) {
        return flowHistoryMapper.addHistory(flowHistory);
    }
}
