package com.kailismart.com.service;

import com.kailismart.com.entity.ApplyMaterial;
import com.kailismart.com.entity.ProMaterial;
import com.kailismart.com.mapper.ApplyMaterialMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-08-12.
 * 采购订单材料服务接口
 */
public interface ProcurementMaterService {
    /**
     * 添加采购订单材料
     * @param applyMaterialList 采购订单集合
     * @return 影响的行数
     */
    Integer addMater(List<ProMaterial> applyMaterialList, String proId);

    /**
     * 根据订单id获取材料集合
     * @param id 订单id
     * @return 材料集合
     */
    List<ProMaterial> getProMatersByProId(String id);

    /**
     * 根据订单id获取未入库的材料集合
     * @param id
     * @return
     */
    List<ProMaterial> getNotMatersByProId(String id);

    /**
     * 更新材料入库数量和入库时间
     * @param materials 材料对象集合
     */
    void updatePutSum(List<ProMaterial> materials);

    /**
     * 获取该订单采购材料数量和已入库的数量
     * @param id 订单id
     * @return 采购数量和入库数量
     */
    Map<String,BigDecimal> getCount(String id);

    /**
     * 通过采购订单材料表主键id获取订单材料对象
     * @param proMaterId 订单材料主键
     * @return 订单材料集合
     */
    ProMaterial getMatersById(String proMaterId);
}
