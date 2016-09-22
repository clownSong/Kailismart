package com.kailismart.com.controller;

import com.kailismart.com.entity.Project;
import com.kailismart.com.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 宋正根 on 2016/8/30.
 * 项目控制器
 */
@Controller
@RequestMapping("/managent")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @RequestMapping("/getProjectBySeek")
    @ResponseBody
    public List<Project> getProjectByLately(String str) {
        List<Project> projects = null;
        if (null == str || "".equals(str)) {
            projects = projectService.getProjectByLaately();
        }else{
            projects = projectService.seek(str);
        }
        return projects;
    }
}
