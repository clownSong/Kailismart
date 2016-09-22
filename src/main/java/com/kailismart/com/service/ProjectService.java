package com.kailismart.com.service;

import com.kailismart.com.entity.Count;
import com.kailismart.com.entity.Project;

import java.util.List;

/**
 * Created by Administrator on 2016-08-06.
 */
public interface ProjectService{
    /**
     * 根据项目ID获取项目对象
     * @param ID 项目ID
     * @return
     */
    Project getProjectByID(String ID);

    /**
     * 获取最近出库的20个项目
     * @return 最近出库的20个项目
     */
    List<Project> getProjectByLaately();


    /**
     * 获取出库最多的20个项目
     * @return 项目id总数和使用次数集合
     */
    List<Count> getOutPrijectMax();

    /**
     * 检索项目
     * @param str 检索字符串
     * @return 项目集合
     */
    public List<Project> seek(String str);
}
