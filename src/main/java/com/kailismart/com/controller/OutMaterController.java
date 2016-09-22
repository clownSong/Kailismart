package com.kailismart.com.controller;

import com.kailismart.com.entity.*;
import com.kailismart.com.service.*;
import com.kailismart.com.util.Constant;
import com.kailismart.com.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.util.resources.CalendarData;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by 宋正根 on 2016/8/30.
 * 出库控制器
 */
@Controller
@RequestMapping("/managent")
public class OutMaterController {
    @Autowired
    OutMaterService outMaterService;        //出库单服务
    @Autowired
    OutMaterChildService outMaterChildService;      //出库单材料服务
    @Autowired
    PutStorageMaterialService putStorageMaterialService;        //入库单材料服务
    @Autowired
    StorageService storageService;      //仓库服务
    @Autowired
    MaterialService materialService;        //材料服务

    private static StringBuffer serialCount = new StringBuffer("");
    private static int serialLenth = 3;
    private static int serialCountNumber = 1;

    /**
     * 跳转到出库单页面，并带一个出库对象
     *
     * @return 页面
     */
    @RequestMapping("/toOutPage")
    public String toOutPage(Model model, HttpSession session) {
        int i = 0;
        MaterOut materOut = null;
        Calendar calendarData = Calendar.getInstance();
        String data = DateFormat.getDate();
        while (materOut == null) {
            materOut = outMaterService.getNowOutMater(((Staff) session.getAttribute(Constant.SESSION_KEY)).getCoding(), data);
            if (i == 10) {
                break;
            }
            calendarData.setTime(DateFormat.parseData(data));
            i++;
            calendarData.add(Calendar.DAY_OF_MONTH, -i);
            data = DateFormat.parseString(calendarData.getTime());
        }
        model.addAttribute("outObject", materOut);
        return "putStorage/materOut";
    }

    /**
     * 添加出库单
     * @param materOut 出库单对象
     * @param session 登录用户
     * @return
     */
    @RequestMapping("/addOutMater")
    public String addOutMater(MaterOut materOut, HttpSession session) {
        materOut.setStaff(((Staff) session.getAttribute(Constant.SESSION_KEY)));     //设置制单人员
        materOut.setDate(DateFormat.getDate());         //设置制单时间
        materOut.setID(UUID.randomUUID().toString());           //生成出库单id号

        if(materOut.getOutNumber() == null || "".equals(materOut.getOutNumber())){
            MaterOut outTemp = outMaterService.getNowOutMater(materOut.getStaff().getCoding(),DateFormat.getDate());
            if(outTemp == null){
                materOut.setOutNumber(generNumber(""));
            }else{
                materOut.setOutNumber(generNumber(outTemp.getOutNumber()));
            }
        }

        outMaterService.addOutMater(materOut);      //添加入库单和入库单材料到数据库

        approveOutMater(materOut,session);      //审核出库单
        return "putStorage/outList";
    }

    /**
     * 审核||反审核   出库单
     *
     * @return
     */
    @RequestMapping("/approveOut")
    @ResponseBody
    public int approveOut(String outId, HttpSession session) {
        MaterOut out = outMaterService.getOutMaterById(outId);
        out.setMaterOuts(outMaterChildService.getOutMatersByOutId(outId));
        if(out == null || out.getMaterOuts().size() <= 0){
            return -1;
        }

        return approveOutMater(out,session);
    }

    private Integer approveOutMater(MaterOut materOut, HttpSession session){
        List<MaterOutChild> childList = materOut.getMaterOuts();
        OutMaterChildHistory history = null;
        MaterOutChild child = null;
        Fio fio = null;
        try {
            if (materOut.getState() == Constant.STATE_0) {    //未审核
                Double maxId = outMaterChildService.getMaxHistory();    //获取出库材料关系表中最大的id
                for (int i = 0; i < childList.size(); i++) {
                    history = new OutMaterChildHistory();
                    child = childList.get(i);

                    history.setID(++maxId);     //设置出库单材料关系表主键id
                    history.setMaterId(child.getMaterial().getID());        //设置材料id
                    history.setDateTime(DateFormat.getDateTime());      //设置出库时间
                    history.setOutSum(child.getSum());      //设置出库数量
                    history.setPrice(child.getTaxPrice());      //设置单价
                    history.setMoney(child.getTaxMoney());      //设置金额
                    history.setOutMaterChildId(child.getID());      //设置出库单材料表主键
                    if (child.getPutMaterId() == null || "".equals(child.getPutMaterId())) {
                        fio = putStorageMaterialService.getFioById(putStorageMaterialService.getHistoryIdByMaterId(history.getMaterId()));        //通过材料id获取入库单材料关系对象
                    } else {
                        fio = putStorageMaterialService.getFioByPutId(child.getPutMaterId());
                    }
                    if (fio != null) {
                        history.setFifoiId(fio.getID01());           //设置入库单材料关系表主键


                        //如果出库数量大于入库单材料记录数量 或者 以前的出库数量+当前的出库数量大于 入库总数，那么忽略掉剩下的出库数量
                        if (history.getOutSum() > fio.getPutSum() || (fio.getIo06()+history.getOutSum()) > fio.getPutSum()) {
                            fio.setIo06(fio.getPutSum());       //设置入库单材料关系为入库数量
                        } else {
                            fio.setIo06((fio.getIo06()+history.getOutSum()));       //设置出库数量到入库单材料表中
                        }
                        //更新入库单材料单关系的出库数量
                        putStorageMaterialService.updateMaterFifoio(fio);
                    } else {      //忽略，不更新
                        history.setFifoiId(0.0);
                    }
                    //更新仓库中材料的库存数量和材料主表的库存数量
                    StorageMater mater = storageService.getStorageMater(materOut.getStorage().getID(), history.getMaterId());
                    mater.setSum(mater.getSum() - history.getOutSum());     //设置库存数量 = 库存数量 - 出库数量
                    mater.setMoney(mater.getSum() * mater.getPrice());      //是指总金额
                    storageService.updateStorageMater(mater);

                    //更新材料库的库存数量
                    Material material = materialService.getMaterialByID(history.getMaterId());        //获取材料对象
                    material.setStorageSum(material.getStorageSum() - history.getOutSum());       //设置库存数量 = 原来的数量 - 出库数量
                    materialService.updateMaterSum(material);
                    //添加出库单材料关系对象到数据库
                    outMaterChildService.addOutMaterHistory(history);
                }

                materOut.setState(Constant.STATE_1);        //设置状态为已审核
                materOut.setApproveStaff((Staff) session.getAttribute(Constant.SESSION_KEY));             //设置审核人员
                materOut.setApproveDate(DateFormat.getDate());      //设置审核日期
                //更新出库单状态为已审核
                outMaterService.updateState(materOut);
            } else {      //已审核

                for (int i = 0; i < childList.size(); i++) {
                    child = childList.get(i);

                    history = outMaterChildService.getChildHistoryByMater(child.getID());        //获取出库单关系记录的对象


                    //获取入库单材料关系对象，并更新出库字段
                    fio = putStorageMaterialService.getFioById(history.getFifoiId());
                    if(fio != null){
                        if(history.getOutSum() > fio.getIo06()){
                            fio.setIo06(0.0);       //设置已出库数量为0
                        }else{
                            fio.setIo06(fio.getPutSum() - history.getOutSum());     //已出库数量 = 已出库数量 - 当前出库数量
                        }
    //                    更新入库单材料关系表已出库的数量
                        putStorageMaterialService.updateMaterFifoio(fio);
                    }else{      //暂时忽略掉

                    }
                    //更新仓库中材料的库存数量和材料主表的库存数量
                    StorageMater mater = storageService.getStorageMater(materOut.getStorage().getID(), history.getMaterId());
                    mater.setSum(mater.getSum() + history.getOutSum());     //设置库存数量 = 库存数量 - 出库数量
                    mater.setMoney(mater.getSum() * mater.getPrice());      //是指总金额
                    storageService.updateStorageMater(mater);

                    //更新材料库的库存数量
                    Material material = materialService.getMaterialByID(history.getMaterId());        //获取材料对象
                    material.setStorageSum(material.getStorageSum() + history.getOutSum());       //设置库存数量 = 原来的数量 - 出库数量
                    materialService.updateMaterSum(material);
                    //删除出库单关系对象
                    outMaterChildService.deleteOutMaterHistory(child.getID());

                }

                materOut.setState(Constant.STATE_0);        //设置状态为未审核
                Staff staff = new Staff();
                staff.setCoding("");
                materOut.setApproveStaff(staff);             //设置反审核人员
                materOut.setApproveDate("");      //设置反审核日期
                //更新出库单状态为已审核
                outMaterService.updateState(materOut);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return -1;
        }
        return 1;
    }

    /**
     * 根据时间获取出库单集合
     * @param start 开始时间
     * @param end 结束时间
     * @return 出库单集合
     */
    @RequestMapping("/getMaterOuts")
    @ResponseBody
    public List<MaterOut> geMaterOut(String start,String end){
        Map<String,Object> params = new HashMap();
        params.put("start",start);
        params.put("end",end);
        return outMaterService.getOutMaterList(params);
    }

    /**
     * 通过出库单id获取出库单材料
     * @param outId 出库单id
     * @return 出库单材料集合
     */
    @RequestMapping("/getChildByOutId")
    @ResponseBody
    public List<MaterOutChild> getChildByOutId(String outId){
        return outMaterChildService.getOutMatersByOutId(outId);
    }

    /**
     * 生成出库单序号
     *
     * @return
     * @param outSerial 上一个出库单号
     */
    public String generNumber(String outSerial) {
        String temp = outSerial + "";
        try {
            temp = temp.substring(temp.length()-serialLenth,temp.length());
            serialCountNumber = Integer.parseInt(temp);
            serialCountNumber++;
            for (int i = 0;i < (serialLenth - ((serialCountNumber+"").length()));i++){
                serialCount.append("0");
            }
            serialCount.append(serialCountNumber);
            temp = DateFormat.getDateForNumber() + serialCount.toString();
            serialCount.delete(0,serialCount.length());
        } catch (Exception e) {
            //订单号生成失败
            for (int i = 0;i < (serialLenth - ((serialCountNumber+"").length()));i++){
                serialCount.append("0");
            }
            serialCount.append(serialCountNumber);
            temp = DateFormat.getDateForNumber() + serialCount.toString();
            serialCount.delete(0,serialCount.length());
        }
        return temp;
    }

    /**
     * 根据项目id获取该项目领用的材料集合
     * @param projectId 项目id
     * @return 出库材料集合
     */
    @RequestMapping("/getMaterOutChildByProject")
    @ResponseBody
    public List<MaterOutChild> getmaterOutCildByProject(String projectId){
        return outMaterChildService.getOutMatersByProject(projectId);
    }


}
