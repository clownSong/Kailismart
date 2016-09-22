package com.kailismart.com.service.impl;

import com.kailismart.com.entity.Count;
import com.kailismart.com.entity.MaterOut;
import com.kailismart.com.entity.MaterOutChild;
import com.kailismart.com.mapper.OutMaterMapper;
import com.kailismart.com.service.OutMaterChildService;
import com.kailismart.com.service.OutMaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by 宋正根 on 2016/8/30.
 */
@Service("outMaterService")
public class OutMaterServiceImpl implements OutMaterService {
    @Autowired
    OutMaterMapper outMaterMapper;
    @Autowired
    OutMaterChildService outMaterChildService;
    public MaterOut getNowOutMater(String staffName,String data) {
        return outMaterMapper.getNowOutMater(staffName,data);
    }

    public Integer addOutMater(MaterOut materOut) {
        //添加出库单主对象到数据库  sdpm020
        int state = outMaterMapper.addOutMater(materOut);
        List<MaterOutChild> materOutChildren = materOut.getMaterOuts();
        if(state > 0){
            //添加出库单材料到数据库   sdpm021
            MaterOutChild child = null;
            for (int i = 0;i < materOutChildren.size();i++){
                child = materOutChildren.get(i);
                child.setID(UUID.randomUUID().toString());      //生成材料主键id
                child.setMaterOutId(materOut.getID());                      //设置出库单主表id
                outMaterChildService.addOutMater(child);            //添加出库单到材料库 sdpm021表中
            }

        }
        return state;
    }

    public List<MaterOut> getOutMaterList(Map<String, Object> params) {
        return outMaterMapper.getOutMaterList(params);
    }

    public List<Count> getCountForStaff(){
        return outMaterMapper.getCountForStaff();
    }

    public void updateState(MaterOut materOut) {
        outMaterMapper.updateState(materOut);
    }

    public MaterOut getOutMaterById(String outId) {
        return outMaterMapper.getOutMaterById(outId);
    }

    public List<MaterOut> getOutMaterByProjectId(String projectId) {
        return outMaterMapper.getOutMaterByProjectId(projectId);
    }

}
