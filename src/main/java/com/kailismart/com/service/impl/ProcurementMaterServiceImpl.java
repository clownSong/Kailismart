package com.kailismart.com.service.impl;

import com.kailismart.com.entity.ProMaterial;
import com.kailismart.com.mapper.ProMaterialMapper;
import com.kailismart.com.service.ProcurementMaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-08-12.
 */
@Service("procurementMaterService")
public class ProcurementMaterServiceImpl implements ProcurementMaterService {
    @Autowired
    ProMaterialMapper proMaterialMapper;
    public Integer addMater(List<ProMaterial> applyMaterialList, String proId) {
        return proMaterialMapper.addMaterial(applyMaterialList,proId);
    }

    public List<ProMaterial> getProMatersByProId(String id) {
        return proMaterialMapper.getMaterials(id);
    }

    public List<ProMaterial> getNotMatersByProId(String id) {
        return proMaterialMapper.getNotMatersByProId(id);
    }

    public void updatePutSum(List<ProMaterial> materials) {
        proMaterialMapper.updatePutSum(materials);
    }

    public Map<String, BigDecimal> getCount(String id) {
        return proMaterialMapper.getCount(id);
    }

    public ProMaterial getMatersById(String proMaterId) {
        return proMaterialMapper.getMatersById(proMaterId);
    }
}
