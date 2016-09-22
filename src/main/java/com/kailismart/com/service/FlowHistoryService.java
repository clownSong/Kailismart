package com.kailismart.com.service;

import com.kailismart.com.entity.FlowHistory;

/**
 * Created by Administrator on 2016-08-16.
 * 流程记录服务接口
 */
public interface FlowHistoryService {
    /**
     * 添加流程使用记录
     * @param flowHistory 记录对象
     * @return 影响的行数
     */
    Integer addHistory(FlowHistory flowHistory);
}
