package com.kailismart.com.mapper;

import com.kailismart.com.entity.Fio;
import com.kailismart.com.entity.StorageMaterial;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-08-23
 * 入库单材料mapper.
 */
@Repository
public interface PutStorageMaterialMapper {
    /**
     * 添加入库单材料集合到数据库
     * @param storageMaterial 入库单材料集合
     * @return 影响的行数
     */
    Integer addMaterials(@Param("materials") List<StorageMaterial> storageMaterial);

    /**
     * 通过入库单id获取材料集合
     * @param putId 入库单id
     * @return 材料集合
     */
    List<StorageMaterial> getMaterialByPutId(String putId);

    /**
     * 获取入库材料关系表中最大的值
     * @return 最大的值
     */
    Double getFifoiMax();

    /**
     * 审核通过后添加入库材料到记录表中
     * @return 影响的行数
     */
    Integer addFifoi(@Param("fios") List<Fio> fios);

    /**
     * 删除入库材料记录
     * @param putMaterId 入库材料id
     * @return 影响的行数
     */
    Integer deleteFifois(@Param("id") String putMaterId);

    /**
     * 根据入库单id删除材料
     * @param id 入库单id
     * @return 影响的行数
     */
    Integer deleteMaterByPutId(String id);

    /**
     * 根据入库单材料主键id删除单个材料
     * @param id 入库单材料主键id
     * @return 影响得到行数
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
     * @param materId
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
     * \通过入库单材料id获取入库单材料关系表对象
     * @param putMaterId 入库单材料id
     * @return 材料关系对象
     */
    Fio getFioByPutId(String putMaterId);

    /**
     * 获取入库单可出库的材料总数
     * @param id 入库单id
     * @return 可出库材料总数
     */
    Integer getOkMaterSum(String id);

    /**
     * 根据供应单位id获取入库材料集合
     * @param params {company:单位id,start:开始时间，end：结束时间}
     * @return 入库材料集合
     */
    List<StorageMaterial> getMaterialByCompany(Map<String, String> params);
}
