package com.kailismart.com.service.impl;

import com.kailismart.com.entity.MyApprove;
import com.kailismart.com.entity.MyApproveAttached;
import com.kailismart.com.mapper.MyApproveAttachedMapper;
import com.kailismart.com.mapper.MyApproveMapper;
import com.kailismart.com.service.MyApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016-08-17.
 */
@Service("myApproveService")
public class MyApproveServiceImpl implements MyApproveService {
    @Autowired
    MyApproveMapper myApproveMapper;        //【我的审批】mapepr
    @Autowired
    MyApproveAttachedMapper myApproveAttachedMapper;
    public void addMyApprove(MyApprove myApprove, MyApproveAttached myApproveAttached) {
        myApproveMapper.addMyApprove(myApprove);        //添加【我的审批】到数据库
        myApproveAttachedMapper.addApproveAttached(myApproveAttached);      //添加【我的审批】附表对象到数据库
    }

    /**
     * 单独添加【我的审批】主表记录
     * @param myApprove
     */
    public void addMyApproveFirst(MyApprove myApprove) {
        myApproveMapper.addMyApprove(myApprove);
    }
}
