package com.kailismart.com.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.*;
import com.kailismart.com.mapper.ProcurementMapper;
import com.kailismart.com.service.*;
import com.kailismart.com.util.Constant;
import com.kailismart.com.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2016-08-08.
 * 采购订单服务对象实现类
 */
@Service("procurementService")
public class ProcurementServiceImpl implements ProcurementService {
    @Autowired
    ProcurementMapper procurementMapper;
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
    FlowMessageService flowMessageService;      //流程消息发起主对象
    @Autowired
    FlowApproveService flowApproveService;      //流程步骤审批服务
    @Autowired
    MyApproveService myApproveService;      //【我的审批】服务
    @Autowired
    CoursePersonAttachedService coursePersonAttachedService;        //【审批流程步骤主记录】
    @Autowired
    MyApproveMainService myApproveMainService;          //【我的审批】超级主表
    @Autowired
    FlowCourseRelationService flowCourseRelationService;        //步骤关系服务
    @Autowired
    RoleService roleService;            //角色服务
    @Autowired
    DutyService dutyService;            //职务服务

    @Transactional
    public void addProcurement(Procurement procurement, String[] applyy, Flow flow) {
//            添加采购订单
        procurementMapper.addProcurement(procurement);
//            添加采购订单材料
        for (ProMaterial proMaterial : procurement.getMaterial()) {
            proMaterial.setPrimentId(UUID.randomUUID().toString()); //生成材料id
            //更新申请单已采购数量{1:申请单材料id，该材料采购数量}
            applyMaterialService.updateMaterProSum(proMaterial.getMajor(), proMaterial.getSum());
        }
        //添加订单材料集合到数据库
        procurementMaterService.addMater(procurement.getMaterial(), procurement.getID());
//        获取申请单材料总数，并改变申请单状态
        if (applyy != null) {
            for (int i = 0; i < applyy.length; i++) {
                //获取已采数量和该申请单材料总数量
                if ("undefined".equals(applyy[i]) || applyy[i] == null || "".equals(applyy[i])) {
                    continue;
                }
                try {
                    Map<String, BigDecimal> sums = applyMaterialService.getMaterSums(applyy[i]);
//                如果已采购数量大于等于申请单材料总数时改变申请单状态为全部采购
                    if (sums.get("ySum").compareTo(sums.get("applySum")) == 0 || sums.get("ySum").compareTo(sums.get("applySum")) == 1) {
                        applyService.updateState(applyy[i], Constant.STATE_ALL_APPLY);  //全部采购
                    } else if (sums.get("ySum").compareTo(new BigDecimal(0)) == 1) {
                        applyService.updateState(applyy[i], Constant.STATE_PART_APPLY);  //部分采购
                    }
                } catch (Exception e) {
                    //申请单id为null的异常，目前未发现bug在哪
                }
            }
        }
            /*
            发起流程
             */
//            添加流程记录对象到数据库
        Map<String, String> folws = flowService.getFlorderARemarkAName(flow.getID());
        FlowHistory history = new FlowHistory(folws.get("name"), folws.get("remark"), folws.get("folder"), Constant.FRAME_CODING_PROCUREMENT);
        history.setID(UUID.randomUUID().toString());    //生成记录表id
        flowHistoryService.addHistory(history);     //添加记录到数据库中
//            String folder = flowService.getFlowFolder(flow.getID());
//          创建审批消息对象
        FlowMessage flowMessage = new FlowMessage();
        flowMessage.setID(UUID.randomUUID().toString());    //生成主键id
        flowMessage.setTitle(procurement.getPmNumber());    //设置消息标题
        flowMessage.setContent(procurement.getRemark());                 //设置消息内容
        flowMessage.setFrameCoding(Constant.FRAME_CODING_PROCUREMENT);  //设置为采购订单窗口编号
        flowMessage.setFrameColumn(Constant.FRAME_COLUMN);          //设置数据库主字段
        flowMessage.setFrameId(procurement.getID());                //设置采购订单id号
        flowMessage.setSql(Constant.PM2SQL_15306);                  //设置兼容PM2程序的sql语句
        flowMessage.setDate(DateFormat.getDateTime());              //设置未知的时间
        flowMessage.setStartDate(DateFormat.getDateTime());         //设置开始时间
        flowMessage.setHistroryId(history.getID());                 //设置流程记录ID
        flowMessage.setState(1);                                    //设置发起后的状态为1：审批中
        flowMessage.setStaffId(procurement.getStaff().getID());     //设置职员id号
        flowMessageService.addFlowMessage(flowMessage);             //添加消息对象到数据库
        //创建【我的审批】超级对象 sdpo100
        MyApproveMain approveMain = new MyApproveMain();

        approveMain.setTitle("审批：" + procurement.getPmNumber());  //创建审批标题
        approveMain.setName(procurement.getPmNumber());         //办文名称
        approveMain.setFrameCoding(Constant.FRAME_CODING_PROCUREMENT);  //设置窗口编号
        approveMain.setFrameId(procurement.getID());            //设置窗口id号

        MyApprove approve = new MyApprove();    //实例化【我的审批】对象
        approve.setAcceptDate(DateFormat.getDateTime());        //设置接收时间
        approve.setTitle(procurement.getPmNumber());        //设置审批标题
        approve.setName(procurement.getStaff().getName() + ":" + procurement.getPmNumber() + "采购订单审批");     //设置办文名称
        approve.setFrameCoding(Constant.FRAME_CODING_PROCUREMENT);  //设置窗口代码
        approve.setFrameId(procurement.getID());            //设置采购订单id
        approve.setSendPerson(procurement.getStaff().getID());  //设置发起人id
        FlowApprove flowApprove = new FlowApprove();        //【我的发起附表】
        //获取该流程中所有的步骤
        List<FlowCourse> course = flowCourseService.getFlowCourseByFlow(flow.getID());
        FlowCourse flowCourse = null;
        MyApproveAttached attached = new MyApproveAttached();       //实例化【我的审批附表】对象
        List<List<FlowCourse_Relation>> courseRelations = new ArrayList<List<FlowCourse_Relation>>();

        //            遍历所有步骤，添加到sdpo002b表中，并添加第一步骤到sdpo003表中
        boolean isAdd100 = false;
        for (int i = 0; i < course.size(); i++) {
            flowCourse = course.get(i);         //获取步骤对象
            flowCourse.setFlowId(history.getID());      //设置主流程记录id
            flowCourse.setPo02009(procurement.getID());     //设置窗口主键id
            //根据当前步骤id，获取步骤关系对象
            List<FlowCourse_Relation> relations = flowCourseRelationService.getRelationByCourseId(flowCourse.getID());
            courseRelations.add(relations);             //添加过程关系对象到集合中
            List<CoursePerson> coursePerson = flowCourse.getPersonList();   //获取步骤中所有人员集合

            for (int x = 0; x < coursePerson.size(); x++) {        //遍历步骤中所有审批和知会人员
                CoursePerson person = coursePerson.get(x);
                CoursePersonAttached personAttached = new CoursePersonAttached();
                personAttached.setFrameId(procurement.getID());     //设置窗口id
                personAttached.setCourseId(flowCourse.getID());     //设置过程id
                personAttached.setCoursePersonId(person.getID());     //设置过程人员主键id
                personAttached.setType(person.getType());         //设置类型
                personAttached.setSerial(person.getSerial());     //设置审批人员序号
                personAttached.setStaffId(person.getStaffId());     //设置审批人员id
                personAttached.setFlowHistoryId(history.getID());     //设置流程记录表id
                personAttached.setB04(person.getStaffType());           //设置审批人员类型
                coursePersonAttachedService.addAttached(personAttached);        //添加审批步骤队列到数据库
                /**
                 * 【我的审批】对象
                 */
                if (i == 0 && person.getType() == 0) { //第一位添加到sdpo003表中,并且只有类型等于0，及为审批人员才添加
                    List<Staff> staffList = new ArrayList<Staff>();     //防止空指针异常
                    switch (person.getStaffType()) {
                        case 0:     //部门
                            break;
                        case 1:     //职务
//                        根据职务编码获取职员集合
                            staffList = dutyService.getStaffByDuty(person.getStaffId());
                            break;
                        case 2:     //审批人
                            /**
                             * 设置职员id，并添加到集合中
                             */
                            staffList = new ArrayList<Staff>();
                            Staff temp = new Staff();
                            temp.setID(person.getStaffId());
                            staffList.add(temp);
                            break;
                        case 3:     //角色
                            //根据角色编码获取职员集合
                            staffList = roleService.getStaffByRoleCoding(person.getStaffId());
                            break;
                        case 4:     //上级领导
                            break;
                        case 5:     //部门主管
                            break;
                        case 6:     //发起人
                            break;
                        default:
//                        数据获取失败
                            break;
                    }
//                    遍历审批角色中人员集合，并添加到数据库中
                    for (int n = 0; n < staffList.size(); n++) {
                        Staff temp = staffList.get(n);
                        approve.setID(UUID.randomUUID().toString());        //生成审批消息id
                        attached.setID(UUID.randomUUID().toString());       //生成【我的审批附表】主键id
                        attached.setSendPersong(procurement.getStaff().getID());  //设置发送人员
                        attached.setAcceptDate("");    //设置接收时间
                        attached.setApproveId(approve.getID());             //设置【我的发起附表】主表id
                        attached.setApprovePerson(temp.getID()); //设置审批人员

                        flowApprove.setID(UUID.randomUUID().toString());        //生成消息主键id
                        flowApprove.setStaffId(procurement.getStaff().getID());      //设置发送人员id
                        flowApprove.setCourseId(person.getCourseId());          //设置过程id
                        flowApprove.setFlowMessageId(flowMessage.getID());          //设置流程消息主表id
                        flowApprove.setAcceptStaffId(temp.getID());      //设置审批人id
                        flowApprove.setAcceptDate(DateFormat.getDateTime());                //设置发送时间
                        flowApprove.setPo00415("" + person.getSerial());       //设置审批过程序号
                        flowApprove.setPo00414((byte) i);                                   //设置审批人序号
                        flowApprove.setDate("2016-08-08 08:08:08");         //设置不知名的时间列
                        flowApprove.setAccrptDate(approve.getAcceptDate());   //设置发送时间
                        myApproveService.addMyApprove(approve, attached);     //添加【我的审批附表 sdpo006】和【我的审批附表 sdpo007】消息到数据库
                        flowApprove.setPo00418Id(attached.getID());         //设置【我的审批附表】id
                        flowApproveService.addApprove(flowApprove);         //添加【我的发起sdpo004】和发起消息附表【sdpo004_AllRecord】审批消息到数据库
                        //添加我的发起显示关系对象到数据库
                        flowApproveService.addPrintShow(new Sdpo004PrintShow(UUID.randomUUID().toString(), flowApprove.getID(), (byte) 1));

                        //【我的审核】超级对象
                        approveMain.setID(UUID.randomUUID().toString());        //生成id主键
                        approveMain.setSendDate("2016-08-18");       //设置接收时间
                        approveMain.setApproveAttachedId(attached.getID());         //设置sdpo007表id
                        approveMain.setFlowMessageId(flowMessage.getID());          //设置【我的发起】消息对象主键id
                        approveMain.setStaffName(procurement.getStaff().getName());       //设置发起人姓名
                        myApproveMainService.addApproveMain(approveMain);       //添加【我的审批】超级对象到数据库
                    }
                }
            }
            flowCourseService.addFlowCourseBInstance(flowCourse);       //添加当前流程第一步   过程到sdoi020b_Instance表中
            flowCourseService.addFlowCourseB(flowCourse);       //添加过程到sdpo020b记录表中
        }
        /**
         * 遍历关系对象集合，并添加到当前流转sdpo020b_Relation关系表中
         */
        for (int i = 0; i < courseRelations.size(); i++) {
            List<FlowCourse_Relation> temp = courseRelations.get(i);
            for (int x = 0; x < temp.size(); x++) {
                FlowCourse_Relation relation = temp.get(x);
                relation.setFlowHistoryId(history.getID());     //设置流程记录id
                flowCourseRelationService.addRelationB(relation);
            }
        }
        approve.setID(UUID.randomUUID().toString());        //再次生成新的uuid
        myApproveService.addMyApproveFirst(approve);        //添加到数据库
    }

    public Procurement getProcurementById(String pId) {
        return procurementMapper.getProcurementById(pId);
    }

    public List<Procurement> getProcurements(PageBounds bounds, Map params) {
        return procurementMapper.getProcurements(bounds, params);
    }

    public int updateProcurement(Procurement procurement) {
        return procurementMapper.updateProcurement(procurement);
    }

    public int deleteProcurement(String pId) {
        return 0;
    }

    public Integer getCount(Map<String, Object> params) {
        return procurementMapper.getCount(params);
    }

    public void updatePutState(byte b, String proId, List<ProMaterial> materials) {
        procurementMaterService.updatePutSum(materials);        //更新订单材料入库数量集合
        procurementMapper.updatePutState(b, proId);  //更新订单状态
    }

    public List<Procurement> getProcurementAll() {
        return procurementMapper.getProcurementAll();
    }
}
