package com.kailismart.com.mapper;

import com.kailismart.com.entity.Apply;
import com.kailismart.com.entity.ApplyMaterial;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-08-06.
 * 采购申请单材料mapper
 */
@Repository
public interface ApplyMaterialMapper {
    /**
     * 根据申请单id获取材料集合
     * @param applyID 申请单id
     * @return 材料集合
     */
    List<ApplyMaterial> getApplyMaterials(String applyID);

    /**
     * 更新采购生清单材料状态
     * @param params {ySum:已采购数量,applyId:采购申请单id，materCoding:材料编码,cnfParam:配置参数}
     * @return
     */
    Integer updateMaterial(Map<String,Object> params);

    /**
     * 根据申请单id ，获取已采购和未采购总数
     * @param s 申请单id
     * @return 已采购、未采购数量
     */
    Map<String,BigDecimal> getMaterSums(@Param("applyId") String s);

    /**
     * 更新采购订单材料入库字段
     * @param params {sum：入库数量，date：入库时间，#{proId：订单id，#{materId：订单材料主键id}}}
     * @return 影响的行数
     */
    Integer updatePutSum(Map<String,Object> params);
}
