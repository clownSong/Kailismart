package com.kailismart.com.mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.Material;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-08-06.
 * 材料mapper
 */
@Repository("materialMapper")
public interface MaterialMapper {
    /**
     * 根据材料ID(编码)获取材料对象
     * @param ID 材料编码
     * @return
     */
    Material getMaterialByID(String ID);

    /**
     * 更新材料库存数量 =
     * @return 影响的行数
     */
    Integer updateMaterSum(Material material);
    /**
     * 批量更新材料库存数量 +=
     * @return 影响的行数
     */
    Integer updateMaterSums(@Param("maters") List<Material> materials);

    /**
     * 更新材料库存数量 -=
     * @param material
     * @return
     */
    Integer updateMaterSum_(Material material);

    List<Material> getMaterialByType(PageBounds index, Map<String, Object> params);
}
