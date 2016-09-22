package com.kailismart.com.mapper;

import com.kailismart.com.entity.FlowHistory;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016-08-16.
 * 流程记录mapper
 */
@Repository
public interface FlowHistoryMapper {
    /**
     * 添加流程使用记录
     * @param flowHistory 记录对象
     * @return  影响的行数
     */
    Integer addHistory(FlowHistory flowHistory);
}
