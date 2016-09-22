package com.kailismart.com.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.Procurement;
import com.kailismart.com.entity.PutStorage;
import com.kailismart.com.mapper.PutStorageMapper;
import com.kailismart.com.mapper.PutStorageMaterialMapper;
import com.kailismart.com.service.PutStorageMaterialService;
import com.kailismart.com.service.PutStorageService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-08-23.
 * 入库单服务实现类
 */
@Service("putStorageService")
public class PutStorageServiceImpl implements PutStorageService {
    @Autowired
    PutStorageMapper putStorageMapper;
    @Autowired
    PutStorageMaterialMapper putStorageMaterialMapper;
    @Autowired
    PutStorageMaterialService putStorageMaterialService;
    @Transactional
    public int addStorage(PutStorage storage) {
        Integer i = putStorageMapper.addStorage(storage);       //添加入库单到数据库
        if(i != null && i > 0){
            i += putStorageMaterialMapper.addMaterials(storage.getMaterialList());  //添加入库单材料到数据库
        }
        return i;
    }

    public List<PutStorage> getStorage(String params) {
        return putStorageMapper.getStorage(params);
    }

    public int updateApprove(Map<String,Object> params) {
        return putStorageMapper.updateApprove(params);
    }

    public PutStorage getStorageById(String id) {
        return putStorageMapper.getStorageById(id);
    }

    public int getCount(Map<String, Object> params) {
        return putStorageMapper.getCount(params);
    }

    public List<PutStorage> getPutStorages(PageBounds bounds, Map<String, Object> params) {
        return putStorageMapper.getPutStorages(bounds,params);
    }

    public Integer updatePutMixMoney(PutStorage storage) {
        return putStorageMapper.updatePutMixMoney(storage);
    }

    public Integer updatePutTax(PutStorage storage) {
        return putStorageMapper.updatePutTax(storage);
    }

    public Integer updatePutRemark(PutStorage storage) {
        return putStorageMapper.updatePutRemark(storage);
    }

    public Integer updatePutDate(PutStorage storage) {
        return putStorageMapper.updatePutDate(storage);
    }

    public Integer updatePutSerial(PutStorage storage) {
        return putStorageMapper.updatePutSerial(storage);
    }

    public Integer deletePut(String id) {
        if(putStorageMapper.deletePut(id) > 0){
            return putStorageMaterialService.deleteMaterByPutId(id);
        }
        return -1;
    }

    public int updatePutMessage(PutStorage storage) {
        return putStorageMapper.updatePutMessage(storage);
    }

    public List<PutStorage> getPutAll() {
        return putStorageMapper.getPutAll();
    }

    public List<PutStorage> seekPutStorage(String str) {
        return putStorageMapper.seekPutStorage(str);
    }

    public String isOut(String id) {
        return putStorageMapper.isOut(id);
    }

    public String getNowPutSerial(String coding) {
        return putStorageMapper.getNowPutSerial(coding);
    }
}
