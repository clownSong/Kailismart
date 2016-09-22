package com.kailismart.com.service.impl;

import com.kailismart.com.entity.Storage;
import com.kailismart.com.entity.StorageMater;
import com.kailismart.com.entity.StorageMaterial;
import com.kailismart.com.mapper.StorageMapper;
import com.kailismart.com.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016-08-23.
 * 仓库服务接口实现类
 */
@Service("storageService")
public class StorageServiceImpl implements StorageService {
    @Autowired
    StorageMapper storageMapper;
    public Storage getStorageById(String id) {
        return storageMapper.getStorageById(id);
    }

    public List<Storage> getStorages() {
        return storageMapper.getStorages();
    }

    public Integer addStorageMaters(List<StorageMater> maters) {
        return storageMapper.addStorageMaters(maters);
    }

    public Integer addStorageMater(StorageMater mater) {
        return storageMapper.addStorageMater(mater);
    }

    public StorageMater getStorageMater(String StorageId,String materId) {
        return storageMapper.getStorageMater(StorageId,materId);
    }

    public Integer updateStorageMater(StorageMater mater) {
        return storageMapper.updateStorageMater(mater);
    }

    public List<StorageMater> getMaterByStorageId(String id) {
        return storageMapper.getMaterByStorageId(id);
    }

    public List<StorageMater> getMaterByStorageIds(String id) {
        return storageMapper.getMaterByStorageIds(id);
    }
}
