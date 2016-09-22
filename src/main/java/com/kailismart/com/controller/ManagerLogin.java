package com.kailismart.com.controller;

import com.kailismart.com.entity.Staff;
import com.kailismart.com.service.StaffService;
import com.kailismart.com.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016-08-05.
 */
@Controller
public class ManagerLogin {
    @Autowired
    StaffService staffService;
    /**
     * 用户登录
     * @param staff
     * @return
     */
    @RequestMapping("/login")
    public String verify(Staff staff, HttpSession session){
        staff = staffService.login(staff.getName(),staff.getPasswd());
        if (staff != null){
            session.setMaxInactiveInterval(100000);
            session.setAttribute(Constant.SESSION_KEY,staff);       //保存用户信息到session中
        }else{
            return "/login";
        }
        return "redirect:/managent/getPage?pageName=managerIndex";
    }
}
