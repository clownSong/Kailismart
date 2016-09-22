package com.kailismart.com.mapper;

import com.kailismart.com.entity.Flow;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-08-15.
 * 流程对象mapper
 */
@Repository
public interface FlowMapper {
    /**
     * 通过窗体代码获取该窗体所有流程
     * @param coding 窗体代码
     * @return 流程对象集合
     */
    List<Flow> getFlowByFrameCoding(String coding);

    /**
     * 根据流程id获取该流程所在的目录编码
     * @param ID 流程id
     * @return 流程文件夹编码
     */
    Map<String,String> getFlorderARemarkAName(String ID);
}
