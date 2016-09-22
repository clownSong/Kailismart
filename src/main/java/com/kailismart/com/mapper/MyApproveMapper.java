package com.kailismart.com.mapper;

import com.kailismart.com.entity.MyApprove;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016-08-17.
 * 【我的审批】mapper
 */
@Repository
public interface MyApproveMapper {
    /**
     * 添加我的审批消息
     * @param myApprove 我的审批对象
     */
    void addMyApprove(MyApprove myApprove);
}
