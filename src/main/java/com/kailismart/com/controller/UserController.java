package com.kailismart.com.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.Apply;
import com.kailismart.com.entity.Project;
import com.kailismart.com.entity.Staff;
import com.kailismart.com.service.ApplyService;
import com.kailismart.com.service.ProjectService;
import com.kailismart.com.service.StaffService;
import com.kailismart.com.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2016-06-15.
 */
@Controller
public class UserController {
    @Autowired
    ApplyService applyService;
    @Autowired
    ProjectService projectService;
    @Autowired
    StaffService staffService;
    /*
    * 添加用户
    * */
    @RequestMapping("/index")
    @ResponseBody
    public List<Apply> addUser(){
        PageBounds bounds = new PageBounds(0,10);
        List applys = applyService.getApplyList(bounds);
        return applys;
    }
    @RequestMapping("/test")
    public String test(String name){
        return "/"+name;
    }
    /**
     * 根据项目ID获取项目对象
     * @param ID
     * @return
     */
    @RequestMapping("/project")
    @ResponseBody
    public Project getProjectByID(String ID){
        return projectService.getProjectByID(ID);
    }
}
