package com.kailismart.com.service.impl;

import com.kailismart.com.entity.MyApproveAttached;
import com.kailismart.com.mapper.MyApproveAttachedMapper;
import com.kailismart.com.service.MyApproveAttachedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016-08-17.
 */
@Service("myApproveAttachedService")
public class MyApproveAttachedServiceImpl implements MyApproveAttachedService {
    @Autowired
    MyApproveAttachedMapper myApproveAttachedMapper;
    public void addApproveAttached(MyApproveAttached myApproveAttached) {
        myApproveAttachedMapper.addApproveAttached(myApproveAttached);
    }
}
