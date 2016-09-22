package com.kailismart.com.mapper;

import com.kailismart.com.entity.ProMaterial;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-08-12.
 * 采购订单材料mapper
 */
@Repository
public interface ProMaterialMapper {
    /**
     * 添加采购订单材料，订单id不能为null
     * @param proMaterials 材料集合
     * @return 影响的行数
     */
    Integer addMaterial(@Param("maters") List<ProMaterial> proMaterials,@Param("proId") String proId);

    /**
     * 删除订单中材料
     * @param params {订单id，材料编码}
     * @return 影响的行数
     */
    Integer deleteMaterial(Map<String,String> params);

    /**
     * 根据订单id获取材料集合
     * @param proId
     * @return 材料集合
     */
    List<ProMaterial> getMaterials(String proId);

    /**
     * 获取未入库的订单材料集合
     * @param id 订单id
     * @return 未入库材料集合
     */
    List<ProMaterial> getNotMatersByProId(String id);

    /**
     * 更新材料入库数量和入库时间
     * @param materials 材料集合
     */
    void updatePutSum(@Param("materList") List<ProMaterial> materials);

    /**
     * 获取订单的材料数量和入库数量
     * @param id 订单id
     * @return 采购数量和入库数量
     */
    Map<String,BigDecimal> getCount(String id);

    /**
     * 通过采购订单材料表主键id获取订单材料对象
     * @param proMaterId 订单材料主键
     * @return 订单材料集合
     */
    ProMaterial getMatersById(@Param("id") String proMaterId);
}
