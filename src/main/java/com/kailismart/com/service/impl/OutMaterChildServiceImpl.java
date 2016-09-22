package com.kailismart.com.service.impl;

import com.kailismart.com.entity.MaterOut;
import com.kailismart.com.entity.MaterOutChild;
import com.kailismart.com.entity.OutMaterChildHistory;
import com.kailismart.com.mapper.OutMaterChildMapper;
import com.kailismart.com.service.OutMaterChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 宋正根 on 2016/9/1.
 */
@Service("outMaterChildService")
public class OutMaterChildServiceImpl implements OutMaterChildService {
    @Autowired
    OutMaterChildMapper outMaterChildMapper;
    public Integer addOutMater(MaterOutChild child) {
        return outMaterChildMapper.addOutMater(child);
    }

    public Integer addOutMaterHistory(OutMaterChildHistory history) {
        return outMaterChildMapper.addOutMaterHistory(history);
    }

    public Double getMaxHistory() {
        return outMaterChildMapper.getMaxHistory();
    }

    public Integer deleteOutMaterHistory(String materOutId) {
        return outMaterChildMapper.deleteOutMaterHistory(materOutId);
    }

    public Integer updateOutMaterHistory(OutMaterChildHistory outMaterChildHistory) {
        return outMaterChildMapper.updateOutMaterHistory(outMaterChildHistory);
    }

    public OutMaterChildHistory getChildHistoryByMater(String outMaterId) {
        return outMaterChildMapper.getChildHistoryByMater(outMaterId);
    }

    public Integer updateMaterChild(MaterOutChild child) {
        return outMaterChildMapper.updateMaterChild(child);
    }

    public List<MaterOutChild> getOutMatersByOutId(String outId) {
        return outMaterChildMapper.getOutMatersByOutId(outId);
    }

    public List<MaterOutChild> getOutMatersByProject(String projectId) {
        return outMaterChildMapper.getOutMatersByProject(projectId);
    }

}
