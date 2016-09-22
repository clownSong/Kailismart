package com.kailismart.com.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.Flow;
import com.kailismart.com.entity.ProMaterial;
import com.kailismart.com.entity.Procurement;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-08-08.
 * 采购订单服务
 */
public interface ProcurementService {
    /**
     * 添加采购订单
     * @param procurement 采购订单对象
     *                    @param applyy 申请单集合
     *                                  @param flow 流程对象
     */
    public void addProcurement(Procurement procurement,String[] applyy,Flow flow);

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
     * 获取指定时间的总行数
     * @param params 开始和结束时间
     * @return 行数
     */
    Integer getCount(Map<String, Object> params);

    /**
     * 更新采购订单状态
     * @param b 状态码{0：未入库，3：部分入库，4：完全入库}
     * @param proId 订单id
     */
    void updatePutState(byte b, String proId, List<ProMaterial> materials);

    /**
     * 获取所有订单集合
     * @return 订单集合
     */
    List<Procurement> getProcurementAll();

}
