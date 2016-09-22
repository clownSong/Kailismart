package com.kailismart.com.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.Apply;
import com.kailismart.com.mapper.ApplyMapper;
import com.kailismart.com.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-06-20.
 */
@Service("applyService")
public class ApplyServiceImpl implements ApplyService {
    @Autowired
    private ApplyMapper applyMapper;
    public List<Apply> getApplyList(PageBounds bounds) {
        return applyMapper.getApplyList(bounds);
    }

    public List<Apply> getApplysByDate(PageBounds bounds, Map map) {
        return applyMapper.getApplysByDate(bounds,map);
    }

    public void updateState(String s, int state) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("state",state);  //状态put到参数中
        params.put("appId",s);      //申请单id put到参数中
        applyMapper.updateState(params);
    }
}
