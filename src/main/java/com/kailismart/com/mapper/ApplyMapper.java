package com.kailismart.com.mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.Apply;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-08-02.
 * 采购申请单对象mapper
 */
@Repository("applyMapper")
public interface ApplyMapper {
    /**
     * 获取采购生清单集合
     * @return
     */
    List<Apply> getApplyList(PageBounds bounds);
    /**
     * 根据指定日期获取采购申请单
     *
     * @param bounds 分页对象
     * @param map    开始日期、结束日期；
     * @return
     */
    List<Apply> getApplysByDate(PageBounds bounds, @Param("map") Map map);
    /**
     * 更新申请单状态
     *
     * @param params state 0：未采购，1：部分采购，2：全部采购 * appId 申请单id
     * @return 影响的行数
     */
    int updateState(Map<String,Object> params);
}
