package com.kailismart.com.controller;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.Apply;
import com.kailismart.com.entity.ApplyMaterial;
import com.kailismart.com.mapper.ApplyMaterialMapper;
import com.kailismart.com.model.AppMaterModel;
import com.kailismart.com.service.ApplyMaterialService;
import com.kailismart.com.service.ApplyService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2016-08-06.
 * 采购申请单控制器
 */
@Controller
@RequestMapping("/managent")
public class ApplyController {
    public static final int PAGESIZE = 200;
    @Autowired
    ApplyService applyService;
    @Autowired
    ApplyMaterialService applyMaterialService;
    @RequestMapping("/applyPage")
    public ModelAndView applyPage(@RequestParam(name="index",required=false) int index){
        ModelAndView mav = new ModelAndView("/apply/applyList");
        Map<String,String> map = new HashMap<String, String>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-7);
        map.put("start",new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
        map.put("end",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
//        System.out.println(map.toString());
        /*for (Apply apply : applyService.getApplysByDate(new PageBounds(index,PAGESIZE),map)) {
            System.out.println("申请单id："+apply.getID());
            for (ApplyMaterial applyMaterial:applyMaterialService.getApplyMaterials(apply.getID())) {
                if (applyMaterial != null){
                    System.out.println(applyMaterial.getName());
                }
            }
        }*/
        mav.addObject("applyList",applyService.getApplysByDate(new PageBounds(index,PAGESIZE),map));
        return mav;
    }
    @RequestMapping("/applyByJson")
    @ResponseBody
    public List<Apply> getApplys(){
        PageBounds bounds = new PageBounds(0,PAGESIZE);
        return applyService.getApplyList(bounds);
    }


    /**
     * 根据申请单ID获取材料集合
     * @param applyId 申请单ID
     * @return 采购申请单材料集合
     */
    @RequestMapping("/material")
    @ResponseBody
    public List<ApplyMaterial> getMaterialByApply(@Param("applyId") String applyId){
        return applyMaterialService.getApplyMaterials(applyId);
    }


    /**
     * 申请单中选择材料后跳转到添加订单页面中
     * @param materials 材料模型集合
     * @param model model传递对象
     * @return 采购订单添加页面
     */
    @RequestMapping("/toProByMaters")
    public String toProByAppMaters(AppMaterModel materials, Model model){
        model.addAttribute("maters",materials.getMaterial());
        return "procurement/pmAdd2";
    }
}
