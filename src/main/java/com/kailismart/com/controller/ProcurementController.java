package com.kailismart.com.controller;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.*;
import com.kailismart.com.service.*;
import com.kailismart.com.util.Constant;
import com.kailismart.com.util.DateFormat;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2016-08-08.
 * 采购订单控制器
 */
@Controller
@RequestMapping("/managent")
public class ProcurementController {
    @Autowired
    ProcurementService procurementService;  //订单服务
    @Autowired
    ProcurementMaterService procurementMaterService;    //订单材料服务
    @Autowired
    ApplyMaterialService applyMaterialService;  //申请单材料服务
    @Autowired
    ApplyService applyService;  //申请单服务
    @Autowired
    FlowService flowService;        //流程对象服务
    @Autowired
    FlowCourseService flowCourseService;      //流程过程服务
    @Autowired
    FlowHistoryService flowHistoryService;  //流程记录对象
    @Autowired
    CompanyService companyService;      //单位服务对象
    @Autowired
    ContractService contractService;        //合同服务

    /**
     * 通过采购订单id获取采购订单对象
     *
     * @param pId 采购订单id
     * @return 采购订单对象
     */
    @RequestMapping("/getProById")
    @ResponseBody
    public Procurement getProcurementById(String pId) {
        return procurementService.getProcurementById(pId);
    }

    /**
     * 获取订单集合
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return 订单集合（json）
     */
    @RequestMapping("/getProList")
    @ResponseBody
    public List<Procurement> getProcurements(String start, String end, int index) {
        Map<String, Object> params = new HashMap();
        if ("null".equals(start)) {
            return procurementService.getProcurementAll();
        }
        params.put("start", start);
        params.put("end", end);
        PageBounds bounds = new PageBounds(index, procurementService.getCount(params));
//        订单集合
        return procurementService.getProcurements(bounds, params);
    }

    /**
     * 更新订单
     *
     * @param procurement 订单对象
     * @return 是否更新成功 {0：否，1：是}
     */
    @RequestMapping("/updatePro")
    @ResponseBody
    public String updateProcurement(Procurement procurement, HttpSession session) {
        //设置修改人员名称
        procurement.setVoucherCoding(((Staff) session.getAttribute(Constant.SESSION_KEY)).getName());

        if (procurement.getID() == null || "".equals(procurement.getID())) {
            return "-1";
        } else if (procurementService.updateProcurement(procurement) != -1) {
            Contract contract = contractService.getContractById(procurement.getContract().getID());
            if (contract != null) {
                return companyService.getCompanyById(procurement.getCompany().getID()).getName() + "," + contract.getName();
            }
            return companyService.getCompanyById(procurement.getCompany().getID()).getName() + ",";
        }
        return "-1";
    }

    /**
     * 删除订单
     *
     * @param pId 订单id号
     * @return 删除成功？（0：否，1：是）
     */
    @RequestMapping("/dltPro")
    @ResponseBody
    public int deleteProcurement(String pId) {
        return 0;
    }


    @RequestMapping("/test")
    @ResponseBody
    public List<Flow> test() {
        return flowService.getFlowByFrameCoding(Constant.FRAME_CODING_PROCUREMENT);
    }

    /**
     * 添加采购订单
     *
     * @param applyy      申请单数组
     * @param procurement 订单对象
     * @param session     session对象
     * @return
     */
    @RequestMapping(value = "/addProcurement", method = RequestMethod.POST)
    public String addPro(Procurement procurement, String[] applyy, HttpSession session) {
        Staff staff = (Staff) session.getAttribute(Constant.SESSION_KEY);
        if (null == staff) {
            return "login";
        } else {
            procurement.setVoucherCoding(staff.getCoding());
            procurement.setVoucherName(staff.getName());
        }

        if (procurement.getStaff() == null) {
            procurement.setStaff(staff);
        }

        if (procurement.getRemark() == null) {
            procurement.setRemark("");
        }
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        procurement.setPmDate(date);    //设置采购日期
        procurement.setFqDate(date);    //设置发起时间
        procurement.setVoucherDate(date);   //设置制单时间
        procurement.setID(UUID.randomUUID().toString());    //生成采购订单id号
        procurementService.addProcurement(procurement, applyy, procurement.getFlow());
        return "redirect:/managent/getPage?pageName=procurement/pmList";
    }

    /**
     * 通过订单id获取该订单所有材料
     *
     * @param id 订单id
     * @return 材料集合
     */
    @RequestMapping("/getProMaterial")
    @ResponseBody
    public List<ProMaterial> getProMaterialByProId(String id) {
        return procurementMaterService.getProMatersByProId(id);
    }


    /**
     * 通过订单id获取该订单未入库的材料
     *
     * @param id 订单id
     * @return 未入库材料集合
     */
    @RequestMapping("/getNotProMaterial")
    @ResponseBody
    public List<ProMaterial> getNotProMaterialByProId(String id) {
        return procurementMaterService.getNotMatersByProId(id);
    }
}
