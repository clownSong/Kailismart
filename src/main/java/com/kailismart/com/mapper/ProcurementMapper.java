package com.kailismart.com.mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.Procurement;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-08-08.
 * 采购订单mapper
 */
@Repository
public interface ProcurementMapper {
    /**
     * 添加采购订单
     * @param procurementMapper 采购订单对象
     */
    void addProcurement(Procurement procurementMapper);

    /**
     * 通过采购订单id获取采购订单对象
     * @param pId
     * @return
     */
    Procurement getProcurementById(String pId);

    /**
     * 获取采购订单集合，通过指定页数和开始时间与结束时间
     * @param bounds 分页对象
     * @param params 开始时间和结束时间
     * @return
     */
    List<Procurement> getProcurements(PageBounds bounds, Map params);

    /**
     * 更新采购订单信息
     * @param procurementMapper 采购订单对象
     * @return 影响的行数
     */
    int updateProcurement(Procurement procurementMapper);

    /**
     * 通过指定订单id删除采购订单
     * @param pId 采购订单id
     * @return  影响的行数
     */
    int deleteProcurement(String pId);

    /**
     * 获取指定时间内的订单总数
     * @param params 开始和结束时间
     * @return 总行数
     */
    Integer getCount(Map<String, Object> params);

    /**
     * 更新采购订单状态
     * @param state 订单状态
     * @param proId 订单id
     */
    void updatePutState(@Param("state") byte state,@Param("proId") String proId);

    /**
     * 获取所有订单集合
     * @return 所有订单集合
     */
    List<Procurement> getProcurementAll();
}
