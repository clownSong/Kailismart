package com.kailismart.com.service.impl;

import com.kailismart.com.entity.Apply;
import com.kailismart.com.entity.ApplyMaterial;
import com.kailismart.com.mapper.ApplyMaterialMapper;
import com.kailismart.com.service.ApplyMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-08-06.
 */
@Service("applyMaterialService")
public class ApplyMaterialServiceImpl implements ApplyMaterialService {
    @Autowired
    ApplyMaterialMapper applyMaterialMapper;
    public List<ApplyMaterial> getApplyMaterials(String applyID) {
        return applyMaterialMapper.getApplyMaterials(applyID);
    }

    public int updateMaterProSum(String primentId, Double sum) {
        if(primentId != null){
            Map<String,Object> params = new HashMap<String, Object>();
            params.put("majoy",primentId);
            params.put("sum",sum);
            return applyMaterialMapper.updateMaterial(params);
        }
        return -1;
    }

    public Map<String, BigDecimal> getMaterSums(String s) {
        return applyMaterialMapper.getMaterSums(s);
    }

    public Integer updatePutSum(Map<String, Object> params) {
        return applyMaterialMapper.updateMaterial(params);
    }
}
