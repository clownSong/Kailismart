package com.kailismart.com.mapper;

import com.kailismart.com.entity.MyApproveMain;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016-08-18.
 * 【我的审批】超级主表 sdpo100
 */
@Repository
public interface MyApproveMainMapper {
    /**
     * 添加【我的审批】超级主表
     * @param myApproveMain 【我的审批】超级主表对象
     */
    void addApproveMain(MyApproveMain myApproveMain);
}
