package com.kailismart.com.model;

import com.kailismart.com.entity.ApplyMaterial;

import java.util.List;

/**
 * Created by 宋正根 on 2016/8/29.
 * 申请单材料model，支持spring-mvc集合映射
 */
public class AppMaterModel {
    private List<ApplyMaterial> material;

    public List<ApplyMaterial> getMaterial() {
        return material;
    }

    public void setMaterial(List<ApplyMaterial> material) {
        this.material = material;
    }
}
