package com.kailismart.com.mapper;

import com.kailismart.com.entity.FlowMessage;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016-08-16.
 * 审批消息【我的发起】主表mapper
 */
@Repository
public interface FlowMessageMapper {
    /**
     * 添加审批消息
     * @param flowMessage 审批消息对象
     * @return  影响的行数
     */
    Integer addFlowMessage(FlowMessage flowMessage);

    /**
     * 修改流程消息状态
     * @param id 流程消息id
     * @param state 状态码 {4：取消，1：审批中}
     * @return
     */
    Integer updateMessage(String id,int state);

    /**
     * 添加【我的发起】消息到主记录表中
     * @param flowMessage 我的发起对象
     */
    void addFlowMessageHistory(FlowMessage flowMessage);
}
