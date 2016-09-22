package com.kailismart.com.mapper;

import com.kailismart.com.entity.Count;
import com.kailismart.com.entity.Project;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016-08-05.
 * 项目mapper
 */
@Repository
public interface ProjectMapper {
    Project getProjectByID(String ID);

    /**
     * 获取最近出库的20个项目
     * @return
     */
    List<Project> getProjectByLately(@Param("list") List<Count> counts);

    /**
     * 获取出库最多的20个项目
     * @return 项目id总数和使用次数集合
     */
    List<Count> getOutPrijectMax();


    /**
     * 检索项目集合
     * @param str 检索字符串
     * @return 检索到的集合
     */
    List<Project> seek(@Param("str") String str);
}
