package com.kailismart.com.service;

import com.kailismart.com.entity.MyApprove;
import com.kailismart.com.entity.MyApproveAttached;

/**
 * Created by Administrator on 2016-08-17.
 * 【我的审批】服务接口
 */
public interface MyApproveService {
    /**
     * 添加【我的审批】对象
     * @param myApprove 【我的审批】对象
     * @param myApproveAttached 【我的审批】附表
     */
    void addMyApprove(MyApprove myApprove, MyApproveAttached myApproveAttached);
    void addMyApproveFirst(MyApprove myApprove);
}
