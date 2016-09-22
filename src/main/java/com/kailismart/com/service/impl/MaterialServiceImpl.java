package com.kailismart.com.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.Material;
import com.kailismart.com.mapper.MaterialMapper;
import com.kailismart.com.service.MaterialService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-08-06.
 */
@Service("materialService")
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    MaterialMapper materialMapper;
    public Material getMaterialByID(String ID) {
        return materialMapper.getMaterialByID(ID);
    }

    public Integer updateMaterSum(Material material) {
        return materialMapper.updateMaterSum(material);
    }

    public Integer updateMaterSums(@Param("maters") List<Material> materials) {
        return materialMapper.updateMaterSums(materials);
    }

    public Integer updateMaterSum_(Material material) {
        return materialMapper.updateMaterSum_(material);
    }

    public List<Material> getMaterialByType(Map<String, Object> params) {
        return materialMapper.getMaterialByType((PageBounds) params.get("index"),params);
    }
}
