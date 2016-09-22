package com.kailismart.com.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.Material;
import com.kailismart.com.entity.Storage;
import com.kailismart.com.entity.StorageMater;
import com.kailismart.com.service.MaterialService;
import com.kailismart.com.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 宋正根 on 2016/8/31.
 */
@Controller
@RequestMapping("/managent")
public class MaterialController {
    @Autowired
    MaterialService materialService;        //材料服务
    @Autowired
    StorageService storageService;          //仓库服务

    /**
     * 获取材料集合，{1：有库存的材料，0：全部，分页对象}
     *
     * @param type  获取的材料类型
     * @param index 页码
     * @param str   检索材料
     * @return
     */
    @RequestMapping("/seekMaterialList")
    @ResponseBody
    public List<Material> getMaterIal(String type, int index, String str) {
        PageBounds bounds = new PageBounds(index, ApplyController.PAGESIZE);
        //获取材料集合
        Map<String,Object> params = new HashMap();
        params.put("index",bounds);
        params.put("type",type);
        params.put("str",str);
        List<Material> materials = materialService.getMaterialByType(params);
        return materials;
    }

    /**
     * 根据仓库id获取该仓库中材料集合
     * @param id 仓库id
     * @return 材料集合
     */
    @RequestMapping("/getMaterByStorage")
    @ResponseBody
    public List<StorageMater> getMaterByStirage(String id){
        return storageService.getMaterByStorageId(id);
    }
}
