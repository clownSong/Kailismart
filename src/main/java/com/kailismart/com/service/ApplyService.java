package com.kailismart.com.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.Apply;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-06-20.
 */
public interface ApplyService {
    /**
     * 获取采购申请单
     * @param bounds 分页对象
     * @return
     */
    List<Apply> getApplyList(PageBounds bounds);

    /**
     * 根据指定日期获取采购申请单
     * @param bounds 分页对象
     * @param map   开始日期、结束日期；
     * @return
     */
    List<Apply> getApplysByDate(PageBounds bounds, Map map);

    /**
     * 更新采购申请单状态
     * @param s 申请单id
     * @param stateAllApply 状态代码
     */
    void updateState(String s, int stateAllApply);
}
