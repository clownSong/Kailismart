package com.kailismart.com.service.impl;

import com.kailismart.com.entity.Section;
import com.kailismart.com.mapper.SectionMapper;
import com.kailismart.com.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 宋正根 on 2016/8/31.
 * 部门服务实现
 */
@Service("sectionService")
public class SectionServiceImpl implements SectionService {
    @Autowired
    SectionMapper sectionMapper;
    public Section getSection(String coding) {
        return sectionMapper.getSevtionByID(coding);
    }
}
