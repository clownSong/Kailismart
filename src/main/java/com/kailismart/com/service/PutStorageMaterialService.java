package com.kailismart.com.service;

import com.kailismart.com.entity.Fio;
import com.kailismart.com.entity.Material;
import com.kailismart.com.entity.StorageMaterial;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-08-23.
 * 入库单材料服务接口
 */
public interface PutStorageMaterialService {
    /**
     * 添加入库单材料集合到数据库
     * @param storageMaterial 入库单材料集合
     * @return 影响的行数
     */
    int addMaterials(List<StorageMaterial> storageMaterial);

    /**
     * 通过入库单id获取材料集合
     * @param putId 入库单id
     * @return 材料集合
     */
    List<StorageMaterial> getMaterialByPutId(String putId);

    /**
     * 获取入库单材料关系表最大值
     * @return 最大值
     */
    Double getFifoiMax();

    void addFifoi(List<Fio> fios, List<Material> materials);


    /**
     * 删除入库材料记录
     * @param putMaterId 入库材料id
     * @return 影响的行数
     */
    Integer deleteFifois(String putMaterId);
    /**
     * 根据入库单id删除材料
     * @param id 入库单id
     * @return
     */
    Integer deleteMaterByPutId(String id);

    /**
     * 通过入库单材料id删除材料
     * @param id 入库单材料主键id
     * @return 影响的行数
     */
    Integer deleteMaterById(String id);

    /**
     * 更新入库单材料信息
     * @param material 入库单材料对象
     */
    void updateMaterMoney(StorageMaterial material);

    /**
     * 更新入库单材料关系表信息
     * @param fio 材料关系表对象
     */
    void updateMaterFifoio(Fio fio);

    /**
     * 获取还有库存的入库单材料集合
     * @param materId 材料id
     * @return
     */
    Double getHistoryIdByMaterId(String materId);

    /**
     * 通过fiOid获取fio对象
     * @param id fioid
     * @return fio对象
     */
    Fio getFioById(Double id);

    /**
     * 通过入库单材料id获取材料关系对象
     */
    Fio getFioByPutId(String putMaterId);

    /**
     * 获取入库单可出库材料的总数
     * @param id 入库单id
     * @return 可出库的材料总数
     */
    Integer getOkMaterSum(String id);

    /**
     * 根据供应单位id获取入库材料集合
     * @param params {company:单位id，start:开始时间，end：结束时间}
     * @return 入库材料集合
     */
    List<StorageMaterial> getMaterialByCompany(Map<String, String> params);
}
