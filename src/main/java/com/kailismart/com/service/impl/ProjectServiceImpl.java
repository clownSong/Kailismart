package com.kailismart.com.service.impl;

import com.kailismart.com.entity.Count;
import com.kailismart.com.entity.Project;
import com.kailismart.com.mapper.ProjectMapper;
import com.kailismart.com.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016-08-06.
 */
@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectMapper projectMapper;
    public Project getProjectByID(String ID) {
        return projectMapper.getProjectByID(ID);
    }

    public List<Project> getProjectByLaately() {
        return projectMapper.getProjectByLately(projectMapper.getOutPrijectMax());
    }

    public List<Count> getOutPrijectMax() {
        return projectMapper.getOutPrijectMax();
    }

    public List<Project> seek(String str){
        return projectMapper.seek(str);
    }


}
