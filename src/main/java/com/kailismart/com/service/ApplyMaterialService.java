package com.kailismart.com.service;

import com.kailismart.com.entity.Apply;
import com.kailismart.com.entity.ApplyMaterial;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-08-06.
 */
public interface ApplyMaterialService {
    /**
     * 根据采购申请单ID获取材料集合
     * @param applyID 采购申请单ID
     * @return 材料集合
     */
    List<ApplyMaterial> getApplyMaterials(String applyID);

    /**
     * 更新申请单已采购数量
     * @param primentId 申请单材料id
     * @param sum 采购数量
     */
    int updateMaterProSum(String primentId, Double sum);

    /**
     * 根据申请单id获取已采购和未采购总数
     * @param s 申请单id
     * @return 已采购和未采购数量
     */
    Map<String,BigDecimal> getMaterSums(String s);
    /**
     * 更新采购订单材料入库字段
     * @param params {sum：入库数量，date：入库时间，#{proId：订单id，#{materId：订单材料主键id}}}
     * @return 影响的行数
     */
    Integer updatePutSum(Map<String,Object> params);
}
