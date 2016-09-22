package com.kailismart.com.service.impl;

import com.kailismart.com.entity.MyApproveMain;
import com.kailismart.com.mapper.MyApproveMainMapper;
import com.kailismart.com.service.MyApproveMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016-08-18.
 */
@Service("myApproveMainService")
public class MyApproveMainServiceImpl implements MyApproveMainService {
    @Autowired
    MyApproveMainMapper myApproveMainMapper;
    public void addApproveMain(MyApproveMain main) {
        myApproveMainMapper.addApproveMain(main);
    }
}
