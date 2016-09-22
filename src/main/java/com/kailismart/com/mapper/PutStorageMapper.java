package com.kailismart.com.mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.Procurement;
import com.kailismart.com.entity.PutStorage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-08-23.
 * 入库单mapper sdpm026
 */
@Repository
public interface PutStorageMapper {
    /**
     * 添加入库单到数据库
     *
     * @param storage 入库单对象
     * @return 影响的行数
     */
    Integer addStorage(PutStorage storage);

    /**
     * 根据时间获取入库单
     *
     * @param params 开始时间和结束时间
     * @return 入库单集合
     */
    List<PutStorage> getStorage(String params);

    /**
     * 更新入库单审核状态
     *
     * @param params {state:状态，id:入库单id，person:审核人员编号，date:审核日期}
     * @return 影响的行数
     */
    Integer updateApprove(Map<String,Object> params);

    /**
     * 通过入库单id获取入库单对象
     * @param id 入库单id
     * @return 入库单对象
     */
    PutStorage getStorageById(String id);

    /**
     * 通过指定的参数获取行数
     * @param params 开始时间和结束时间
     * @return 行数
     */
    int getCount(Map<String, Object> params);

    /**
     * 根据指定的参数获取入库单集合
     * @param bounds 分页对象
     * @param params 开始时间和结束时间
     * @return 入库单集合
     */
    List<PutStorage> getPutStorages(PageBounds bounds, Map<String, Object> params);

    /**
     * 更新入库单运杂费
     * @param storage 入库单对象
     * @return
     */
    Integer updatePutMixMoney(PutStorage storage);


    /**
     * 更新入库单税率
     * @param storage 入库单对象
     * @return
     */
    Integer updatePutTax(PutStorage storage);


    /**
     * 更新入库单税率
     * @param storage 入库单对象
     * @return
     */
    Integer updatePutRemark(PutStorage storage);


    /**
     * 更新入库时间
     * @param storage 入库单对象
     * @return
     */
    Integer updatePutDate(PutStorage storage);


    /**
     * 更新入库单编号
     * @param storage 入库单对象
     * @return
     */
    Integer updatePutSerial(PutStorage storage);

    /**
     * 删除入库单
     * @param id 入库单id
     * @return
     */
    Integer deletePut(String id);

    /**
     * 更新入库单信息
     * @param storage 入库单对象
     * @return 影响的行数
     */
    int updatePutMessage(PutStorage storage);

    /**
     * 获取所有入库单集合
     * @return
     */
    List<PutStorage> getPutAll();

    /**
     * 搜索入库单
     * @param str 入库单编号字符串
     * @return 入库单集合
     */
    List<PutStorage> seekPutStorage(String str);

    /**
     * 查看该入库单是否有过出库记录
     * @param id 入库单id
     * @return 入库单id
     */
    String isOut(String id);

    /**
     * 获取审核人员最新的单号
     * @param coding 审核人员编号
     * @return 入库单号
     */
    String getNowPutSerial(String coding);
}
