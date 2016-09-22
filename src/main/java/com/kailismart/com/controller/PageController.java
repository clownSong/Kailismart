package com.kailismart.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016-08-05.
 */
@Controller
@RequestMapping("/managent")
public class PageController {
    public static final String PAGEKEY = "page";
    public static final String VALUES = "values";

    @RequestMapping("/getPage")
    public String getPage(String pageName){
        return "/"+pageName;
    }
}
