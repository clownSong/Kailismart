package com.kailismart.com.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.Section;
import com.kailismart.com.entity.Staff;
import com.kailismart.com.service.OutMaterService;
import com.kailismart.com.service.SectionService;
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
 * Created by Administrator on 2016-08-10.
 * 职员控制器
 */
@Controller
@RequestMapping("/managent")
public class StaffController {

    @Autowired
    StaffService staffService;
    @Autowired
    OutMaterService outMaterService;

    @Autowired
    SectionService sectionService;
    @RequestMapping("/proStaff")
    @ResponseBody
    public List<Staff> getProStaff(){
        return staffService.getProStaff();
    }

    /**
     * 根据字符串搜索职员
     * @return 职员集合
     */
    @RequestMapping("/seekStaff")
    @ResponseBody
    public List<Staff> seekStaff(String str){
        if(null == str || "".equals(str)){
            return staffService.getStaffByCount(outMaterService.getCountForStaff());
        }else{
            return staffService.seek(str);
        }
    }

    /**
     * 根据部门编码获取部门对象
     * @param coding 部门编码
     * @return 部门对象
     */
    @RequestMapping("/getSectionByCoding")
    @ResponseBody
    public Section getSectionByCoding(String coding){
        return sectionService.getSection(coding);
    }
}
