package com.kailismart.com.service;

import com.kailismart.com.entity.Material;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-08-06.
 */
public interface MaterialService {
    /**
     * 根据材料ID(编码)获取材料对象
     * @param ID 材料编码
     * @return
     */
    Material getMaterialByID(String ID);

    /**
     * 更新材料库存数量
     * @return 影响的行数
     */
    Integer updateMaterSum(Material material);

    /**
     * 批量更新材料库存数量
     * @return 影响的行数
     */
    Integer updateMaterSums(@Param("maters") List<Material> materials);

    /**
     * 更新材料库存数量 -=
     * @param material 材料对象
     * @return
     */
    Integer updateMaterSum_(Material material);

    /**
     * 根据参数信息获取材料集合
     * @param params 参数{index,type,str}
     * @return 材料集合
     */
    List<Material> getMaterialByType(Map<String, Object> params);
}
