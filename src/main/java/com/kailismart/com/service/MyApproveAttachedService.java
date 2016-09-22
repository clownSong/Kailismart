package com.kailismart.com.service;

import com.kailismart.com.entity.MyApproveAttached;

/**
 * Created by Administrator on 2016-08-17.
 * 【我的审批附表】服务接口
 */
public interface MyApproveAttachedService {
    /**
     * 添加【我的审批附表】对象到数据库
     * @param myApproveAttached 【我的审批】附表对象
     */
    void addApproveAttached(MyApproveAttached myApproveAttached);
}
