package com.kailismart.com.service;

import com.kailismart.com.entity.Flow;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-08-15.
 * 流程对象服务接口
 */
public interface FlowService {
    /**
     * 通过窗体代号获取该窗体绑定的流程集合
     * @param coding 窗体代码
     * @return 该窗体流程对象集合
     */
    List<Flow> getFlowByFrameCoding(String coding);

    /**
     * 根据流程id获取该流程所在目录编码
     * @param flowID 流程id
     * @return 目录编码
     */
    Map<String,String> getFlorderARemarkAName(String flowID);

}
