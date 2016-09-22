package com.kailismart.com.service.impl;

import com.kailismart.com.entity.Fio;
import com.kailismart.com.entity.Material;
import com.kailismart.com.entity.StorageMaterial;
import com.kailismart.com.mapper.PutStorageMaterialMapper;
import com.kailismart.com.service.MaterialService;
import com.kailismart.com.service.PutStorageMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-08-23.
 */
@Service("putStorageMaterialService")
public class PutStorageMaterialServiceImpl implements PutStorageMaterialService {
    @Autowired
    PutStorageMaterialMapper putStorageMaterialMapper;
    @Autowired
    MaterialService materialService;
    public int addMaterials(List<StorageMaterial> storageMaterial) {
        return putStorageMaterialMapper.addMaterials(storageMaterial);
    }

    public List<StorageMaterial> getMaterialByPutId(String putId) {
        return putStorageMaterialMapper.getMaterialByPutId(putId);
    }

    public Double getFifoiMax() {
        return putStorageMaterialMapper.getFifoiMax();
    }

    public void addFifoi(List<Fio> fios,List<Material> materials) {
        putStorageMaterialMapper.addFifoi(fios);
        materialService.updateMaterSums(materials);
    }

    public Integer deleteFifois(String putMaterId) {
        return putStorageMaterialMapper.deleteFifois(putMaterId);
    }

    public Integer deleteMaterByPutId(String id) {
        return putStorageMaterialMapper.deleteMaterByPutId(id);
    }

    public Integer deleteMaterById(String id) {
        return putStorageMaterialMapper.deleteMaterById(id);
    }

    public void updateMaterMoney(StorageMaterial material) {
        putStorageMaterialMapper.updateMaterMoney(material);
    }

    public void updateMaterFifoio(Fio fio) {
        putStorageMaterialMapper.updateMaterFifoio(fio);
    }

    public Double getHistoryIdByMaterId(String materId) {
        return putStorageMaterialMapper.getHistoryIdByMaterId(materId);
    }

    public Fio getFioById(Double id) {
        return putStorageMaterialMapper.getFioById(id);
    }

    public Fio getFioByPutId(String putMaterId) {
        return putStorageMaterialMapper.getFioByPutId(putMaterId);
    }

    public Integer getOkMaterSum(String id) {
        return putStorageMaterialMapper.getOkMaterSum(id);
    }

    public List<StorageMaterial> getMaterialByCompany(Map<String, String> params) {
        return putStorageMaterialMapper.getMaterialByCompany(params);
    }
}
