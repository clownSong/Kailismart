package com.kailismart.com.controller;

import com.kailismart.com.entity.Storage;
import com.kailismart.com.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016-08-23.
 * 仓库控制器
 */
@Controller
@RequestMapping("/managent")
public class StorageController {
    @Autowired
    StorageService storageService;      //仓库服务接口


    /**
     * 获取所有仓库集合
     * @return 仓库集合
     */
    @RequestMapping("/getStorages")
    @ResponseBody
    public List<Storage> getStorages(){
        return storageService.getStorages();
    }
}
