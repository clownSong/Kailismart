package com.kailismart.com.service;

import com.kailismart.com.entity.Storage;
import com.kailismart.com.entity.StorageMater;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2016-08-23.
 * 仓库服务接口
 */
public interface StorageService {
    /**
     * 通过仓库id获取仓库对象
     * @param id 仓库id
     * @return 仓库对象
     */
    Storage getStorageById(String id);

    /**
     * 获取所有仓库集合
     * @return 仓库集合
     */
    List<Storage> getStorages();

    /**
     * 添加材料到指定的仓库中
     * @param maters 仓库材料对象集合
     * @return 影响的行数
     */
    Integer addStorageMaters(List<StorageMater> maters);

    /**
     * 添加材料到指定的仓库中
     * @param mater 仓库材料对象
     * @return 影响的行数
     */
    Integer addStorageMater(StorageMater mater);

    /**
     * 通过仓库id和材料编码获取仓库中材料对象
     * @return 仓库中材料对象
     */
    StorageMater getStorageMater(String StorageId,String materId);

    /**
     * 更新仓库中材料
     * @param mater
     * @return
     */
    Integer updateStorageMater(StorageMater mater);

    /**
     * 根据仓库id获取仓库中有库存的材料集合
     * @param id 仓库id
     * @return 材料集合
     */
    List<StorageMater> getMaterByStorageId(String id);

    /**
     * 根据仓库id获取该仓库中所有材料集合，包括库存为0的材料
     * @param id 仓库id
     * @return 仓库材料集合
     */
    List<StorageMater> getMaterByStorageIds(String id);
}
