package com.kailismart.com.service;

import com.kailismart.com.entity.MaterOut;
import com.kailismart.com.entity.MaterOutChild;
import com.kailismart.com.entity.OutMaterChildHistory;

import java.util.List;

/**
 * Created by 宋正根 on 2016/9/1.
 * 出库单材料服务接口
 */
public interface OutMaterChildService {
    /**
     * 添加出库单材
     * @param child 出库单材料对象
     * @return 影响的行数
     */
    Integer addOutMater(MaterOutChild child);
    /**
     * 添加出库材料关系对象
     */
    Integer addOutMaterHistory(OutMaterChildHistory history);

    /**
     * 获取数据库中最大的主键
     * @return 最大的主键值
     */
    Double getMaxHistory();

    /**
     * 根据出库材料主键id，删除该关系
     * @param materOutId
     * @return
     */
    Integer deleteOutMaterHistory(String materOutId);

    /**
     * 更新出库材料记录信息
     * @param outMaterChildHistory 出库材料关系对象
     * @return 影响的行数
     */
    Integer updateOutMaterHistory(OutMaterChildHistory outMaterChildHistory);

    /**
     * 根据出库材料主键获取该关系对象
     * @param outMaterId 出库材料主键
     * @return 出库材料关系对象
     */
    OutMaterChildHistory getChildHistoryByMater(String outMaterId);

    /**
     * 更新出库单材料信息
     * @param child 出库单材料对象
     * @return 影响的行数
     */
    Integer updateMaterChild(MaterOutChild child);

    /**
     * 通过出库单id获取出库材料集合
     * @param outId 出库单id
     * @return 材料集合
     */
    List<MaterOutChild> getOutMatersByOutId(String outId);

    /**
     * 根据项目id获取出库材料集合
     * @param projectId 项目id
     * @return 出库材料集合
     */
    List<MaterOutChild> getOutMatersByProject(String projectId);
}
