package com.kailismart.com.mapper;

import com.kailismart.com.entity.Count;
import com.kailismart.com.entity.MaterOut;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 宋正根 on 2016/8/30.
 * 出库单mapper
 */
@Repository
public interface OutMaterMapper {
    /**
     * 获取最新添加的出库单
     * @param staffName 职员名称
     * @return 出库单对象
     */
    MaterOut getNowOutMater(@Param("name") String staffName,@Param("data")String data);

    /**
     * 添加入库单到数据库
     * @param materOut 入库单对象
     * @return 影响的行数
     */
    Integer addOutMater(MaterOut materOut);

    /**
     * 获取指定时间内的出库单集合
     * @param params 开始和结束时间{start,end}
     * @return 出库单集合
     */
    List<MaterOut> getOutMaterList(Map<String,Object> params);

    /**
     * 获取领料人最多的20个集合
     * @return count对象集合{id,使用次数}
     */
    List<Count> getCountForStaff();

    /**
     * 更新出库单状态
     * @param materOut
     */
    void updateState(MaterOut materOut);

    /**
     * 听过出库单id获取出库单对象
     * @param outId 出库单id
     * @return 出库单对象
     */
    MaterOut getOutMaterById(String outId);

    /**
     * 通过项目id获取出库单集合
     * @param projectId 项目id
     * @return 出库单集合
     */
    List<MaterOut> getOutMaterByProjectId(String projectId);
}
