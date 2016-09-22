package com.kailismart.com.mapper;

import com.kailismart.com.entity.MyApproveAttached;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016-08-17.
 * 【我的审批附表】mapper
 */
@Repository
public interface MyApproveAttachedMapper {
    /**
     * 添加我的审批附表对象
     * @param attached 我的审批附表对象
     */
    void addApproveAttached(MyApproveAttached attached);
}
