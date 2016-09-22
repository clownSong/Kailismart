package com.kailismart.com.controller;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.kailismart.com.entity.*;
import com.kailismart.com.service.*;
import com.kailismart.com.util.Constant;
import com.kailismart.com.util.DateFormat;
import com.mysql.fabric.xmlrpc.base.Data;
import javafx.scene.input.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Administrator on 2016-08-23.
 * 入库单控制器
 */
@Controller
@RequestMapping("/managent")
public class PutStorageController {
    @Autowired
    PutStorageService putStorageService;            //入库单对象服务
    @Autowired
    ProcurementService procurementService;      //订单服务
    @Autowired
    ProcurementMaterService procurementMaterService;    //订单材料服务
    @Autowired
    PutStorageMaterialService putStorageMaterialService;        //入库单材料服务

    @Autowired
    MaterialService materialService;        //材料服务
    @Autowired
    StorageService storageService;      //仓库服务

    private static StringBuffer serialCount = new StringBuffer("");
    private static int serialLenth = 3;
    private static int serialCountNumber = 1;


    /**
     * 添加该订单中所有材料入库
     *
     * @return 影响的行数
     */
    @RequestMapping("/addPutStorage")
    @ResponseBody
    public int addPutStorage(@RequestBody PutStorage putStorage, HttpSession session) {

        Staff staff = (Staff) session.getAttribute(Constant.SESSION_KEY);   //获取当前用户
        putStorage.setPutDate(DateFormat.getDate());        //设置入库时间
        putStorage.setCreateDate(putStorage.getPutDate());  //设置表单创建时间
        putStorage.setPutPerson(staff.getCoding());     //设置入库人员
        putStorage.setPm02608(putStorage.getCreateDate());      //设置跟创建时间一样的列 08
        putStorage.setPm02610(staff.getCoding());       //设置制单人员编号
        //通过订单id获取材料集合，并计算入库的材料和数量
        List<ProMaterial> materialList = procurementMaterService.getNotMatersByProId(putStorage.getProId());

        //初始化订单对象，以便后续的状态更新
        Procurement procurement = new Procurement();
        procurement.setID(putStorage.getProId());

        //遍历采购订单中材料集合，并计算出入库数量
        ProMaterial material = null;    //订单材料对象
        StorageMaterial storageMaterial = null;     //入库单材料对象
        List<StorageMaterial> materials = new ArrayList<StorageMaterial>();     //初始化入库单材料集合
        putStorage.setID(UUID.randomUUID().toString());         //生成入库单id
        double putSums = 0.0;
        for (int i = 0; i < materialList.size(); i++) {
            material = materialList.get(i);
            double tempSum = material.getSum() - material.getInSum();   //入库数量=采购数量-已入库数量
            double moneyTax = tempSum * material.getPriceTax();       //计算出含税金额
            if (tempSum > 0) {    //如果该材料采购数量减去入库入量还大于0,则再次入库该材料
                storageMaterial = new StorageMaterial();
                storageMaterial.setID(UUID.randomUUID().toString());        //生成该入库材料的id


                storageMaterial.setPutSum(tempSum);                      //设置入库数量
                storageMaterial.setTaxPrice(material.getPriceTax());        //设置含税单价
                storageMaterial.setPrice(material.getPrice());          //设置不含税单价
                storageMaterial.setMoneyTax(moneyTax);        //设置含税金额
                storageMaterial.setMoney((tempSum * material.getPrice()));          //设置不含税金额
                storageMaterial.setTaxMoney((storageMaterial.getMoneyTax() - storageMaterial.getMoney()));        //税额=含税金额-不含税金额

                storageMaterial.setMaterial(material.getMaterial());        //设置材料对象
                storageMaterial.getMaterial().setUnit(material.getUnit());  //设置材料单位
                storageMaterial.setProMaterId(material.getID());            //设置采购订单材料主键id
                storageMaterial.setProjectId(material.getProjectId());      //设置项目id


                storageMaterial.setStorageId(putStorage.getID());           //设置入库单id
                materials.add(storageMaterial);     //添加入库材料对象到集合中
                material.setInSum((tempSum + material.getInSum()));     //设置当前订单材料的入库数量=原来已入库数量+当前入库数量，以便后面的更新状态和入库数量
                material.setInDate(DateFormat.getDate());       //设置订单材料的入库时间
                putSums += tempSum;     //记录此次入库总数
            }
        }
        putStorage.setMaterialList(materials);      //设置该入库单材料集合
        if (materialList.size() <= 0) {
            return -1;
        }
//        添加入库单到数据库
        Integer state = putStorageService.addStorage(putStorage);
//        更新采购订单状态
        return updateProState(materialList, procurement, state, putSums);
    }

    /**
     * 选择入库
     *
     * @param putStorage 入库主对象
     * @return 影响的行数
     */
    @RequestMapping("/addPutStorageSelect")
    @ResponseBody
    public int addPutStorageSelect(@RequestBody PutStorage putStorage, HttpSession session) {
        //取出入库材料集合
        List<StorageMaterial> materials = putStorage.getMaterialList();
        if (materials == null || materials.size() <= 0) {
            return -1;
        }
        Staff staff = (Staff) session.getAttribute(Constant.SESSION_KEY);   //获取当前用户
        putStorage.setPutDate(DateFormat.getDate());        //设置入库时间
        putStorage.setCreateDate(putStorage.getPutDate());  //设置表单创建时间
        putStorage.setPutPerson(staff.getCoding());     //设置入库人员
        putStorage.setPm02608(putStorage.getCreateDate());      //设置跟创建时间一样的列 08
        putStorage.setPm02610(staff.getCoding());       //设置制单人员编号
        putStorage.setID(UUID.randomUUID().toString());     //生成入库单id

        //通过订单材料主键id获取材料，并添加到集合中，以便后续对采购订单的更新
        List<ProMaterial> materialList = new ArrayList<ProMaterial>();

//        入库单材料引用
        StorageMaterial putMaterial = null;

        //初始化订单对象，以便后续的状态更新
        Procurement procurement = new Procurement();

        //设置入库单id
        procurement.setID(putStorage.getProId());
        double putSums = 0.0;
        for (int i = 0; i < materials.size(); i++) {
            putMaterial = materials.get(i);
            ProMaterial material = procurementMaterService.getMatersById(putMaterial.getProMaterId());     //获取订单材料对象

            putMaterial.setID(UUID.randomUUID().toString());        //生成该入库材料的id
            putMaterial.setStorageId(putStorage.getID());           //设置入库单id

            material.setInDate(DateFormat.getDate());       //设置订单材料的入库时间
            material.setInSum(material.getInSum()+putMaterial.getPutSum());     //设置本次入库数量 = 以前的入库数量 + 本次的入库数量
            materialList.add(material);     //添加订单材料到集合中

            //记录此次入库总数
            putSums += putMaterial.getPutSum();
        }


        if (materialList.size() <= 0) {
            return -1;
        }
//        添加入库单到数据库
        Integer state = putStorageService.addStorage(putStorage);

//        更新采购订单状态
        return updateProState(materialList, procurement, state, putSums);

//        System.out.println("采购订单id："+putStorage.getProId());
//        System.out.println("供应单位id："+putStorage.getCompany().getID());
//        System.out.println("仓库id："+putStorage.getStorage().getID());
//        System.out.println("税率："+putStorage.getTax());
//        System.out.println("材料集合数量："+putStorage.getMaterialList().size());
//        List<StorageMaterial> materials = putStorage.getMaterialList();
//        for (int i = 0;i < materials.size();i++){
//            StorageMaterial material = materials.get(i);
//            System.out.println("入库数量："+material.getPutSum());
//            System.out.println("应该入库数量："+material.getInSum());
//            System.out.println("不含税单价："+material.getPrice());
//            System.out.println("含税单价："+material.getTaxPrice());
//            System.out.println("不含税金额："+material.getMoney());
//            System.out.println("含税金额："+material.getMoneyTax());
//            System.out.println("税额："+material.getTaxMoney());
//            System.out.println("材料id："+material.getMaterial().getID());
//            System.out.println("材料单位id："+material.getMaterial().getUnit().getID());
//            System.out.println("项目id："+material.getProjectId());
//            System.out.println("采购订单材料表id："+material.getProMaterId());
//        }
    }

    /**
     * 更新采购订单状态
     *
     * @param materialList 采购订单材料集合
     * @param procurement  采购订单对象
     * @param putSum       此次入库总数
     * @param state        是否更新
     * @return 3"部分入库，4:完全入库
     */
    private int updateProState(List<ProMaterial> materialList, Procurement procurement, Integer state, double putSum) {
        if (state > 0) {
            Map<String, BigDecimal> sums = procurementMaterService.getCount(procurement.getID());     //获取该采购订单材料总数和已入库总数

            BigDecimal putSums = sums.get(Constant.PUTSUM);
            putSums = putSums.add(new BigDecimal(putSum));        //入库总数 = 原来入库数量 + 此次入库数量;
//            如果入库总数量 >= 采购数量，更新采购订单状态为全部入库
            if (putSums.compareTo(sums.get(Constant.YSUM)) == 0 || putSums.compareTo(sums.get(Constant.YSUM)) == 1) {
                procurementService.updatePutState(Constant.STATE_4, procurement.getID(), materialList);    //更新订单状态为完全入库,并更新材料的入库数量和入库时间
                return Constant.STATE_4;
            } else if (sums.get(Constant.PUTSUM).compareTo(new BigDecimal(0)) != -1) { //如果入库数量大于0
                procurementService.updatePutState(Constant.STATE_3, procurement.getID(), materialList);    //更新订单状态为部分入库，并更新材料的入库数量和入库时间
                return Constant.STATE_3;
            }
        }
        return -1;
    }

    /**
     * 获取入库订单集合
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return 订单集合（json）
     */
    @RequestMapping("/getPutList")
    @ResponseBody
    public List<PutStorage> getProcurements(String start, String end, int index) {
        Map<String, Object> params = new HashMap();
        if ("null".equals(start)) {
            return putStorageService.getPutAll();
        }
        params.put("start", start);
        params.put("end", end);
        params.put("type",null);
        PageBounds bounds = new PageBounds(index, putStorageService.getCount(params));
//        订单集合
        return putStorageService.getPutStorages(bounds, params);
    }

    /**
     * 根据入库单id获取入库材料集合
     *
     * @param putId 入库单id
     * @return 材料集合
     */
    @RequestMapping("/getMaterByPutId")
    @ResponseBody
    public List<StorageMaterial> getMaterByPutId(String putId) {
        for (StorageMaterial test:putStorageMaterialService.getMaterialByPutId(putId)){
            System.out.println("材料id:"+test.getMaterial().getID());
            System.out.println("材料名称:"+test.getMaterial().getName());
        }
        return putStorageMaterialService.getMaterialByPutId(putId);
    }


    /**
     * 根据入库单id获取可出库的材料
     */
    @RequestMapping("/getOkMaterByPutId")
    @ResponseBody
    public List<StorageMaterial> getNotMaterByPutId(String putId) {
        List<StorageMaterial> materials = putStorageMaterialService.getMaterialByPutId(putId);
        StorageMaterial material = null;
        Fio fio = null;
        for (int i = 0;i < materials.size();i++){
            material = materials.get(i);
            fio = putStorageMaterialService.getFioByPutId(material.getID());
            if(fio.getPutSum() > fio.getIo06()){       //如果入库数量大于出库数量，该材料可出库
                material.setInSum(fio.getPutSum() - fio.getIo06());
            }else{
                materials.remove(i);        //删除该材料，不许出库，因为已经出库过了
            }

        }
        return materials;
    }


    /**
     * 审核入库单
     *
     * @return 审核后的状态
     */
    @RequestMapping("/approvePut")
    @ResponseBody
    public PutStorage approvePut(@RequestBody PutStorage storage, HttpSession session) {
//        获取入库单材料集合
        List<StorageMaterial> materials = putStorageMaterialService.getMaterialByPutId(storage.getID());
//        初始化材料记录集合
        List<Fio> fios = new ArrayList<Fio>();     //审核后添加关系集合到数据库中
        List<Material> materials1 = new ArrayList<Material>();
        List<StorageMaterial> storageMaterials = new ArrayList<StorageMaterial>();
//        获取材料记录集合中最大值
        Double max = putStorageMaterialService.getFifoiMax();
//        材料记录集合临时对象
        Fio fio = null;
//        更新入库单是的参数
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", storage.getID());        //设置入库单id
        StorageMater mater = null;
        //                入库单材料对象
        StorageMaterial material = null;
        if (storage.getApproveType() == 0) {   //审核
            params.put("state", Constant.STATE_1);           //设置审批状态
            params.put("date", DateFormat.getDate());        //设置审批时间
            params.put("person", ((Staff) session.getAttribute(Constant.SESSION_KEY)).getCoding());        //设置审批人员编号
            for (int i = 0; i < materials.size(); i++) {
                material = materials.get(i);

                fio = new Fio();
                fio.setID01(++max);
                fio.setMaterId(material.getMaterial().getID());      //设置材料id
                fio.setPutSum(material.getPutSum());     //设置入库数量
                fio.setDate(DateFormat.getDateTime());   //设置操作时间
                fio.setPutPrice(material.getTaxPrice());        //设置单价
                fio.setPutMoney(material.getMoneyTax());       //设置金额
                fio.setPutMaterId(material.getID());       //设置入库单材料id

                material.getMaterial().setStorageSum(material.getPutSum());
                materials1.add(material.getMaterial());     //添加材料对象到集合中，以便后续对材料库存的更新

                mater = storageService.getStorageMater(storage.getStorage().getID(), material.getMaterial().getID());
                //如果仓库中没有该材料，则添加
                if (mater == null) {
                    mater = new StorageMater();
                    mater.setStorageId(storage.getStorage().getID());      //设置仓库id
                    mater.setMaterial(material.getMaterial());      //设置材料对象
                    mater.setSum(material.getPutSum());     //设置库存数量
                    mater.setPrice(material.getPrice());    //设置价格
                    mater.setPrice(material.getPutSum() * material.getPrice());       //设置总金额
                    storageService.addStorageMater(mater);      //添加该仓库材料到sdpm005表中
                } else {      //直接更新仓库中材料
                    mater.setSum(mater.getSum() + material.getPutSum());      //设置价格
                    mater.setMoney(mater.getMoney() + material.getMoneyTax());    //设置总金额=库存金额 + 入库的金额
                    if (mater.getSum() >= 0) {
                        mater.setPrice(mater.getMoney() / mater.getSum());        //设置最新单价
                    } else {
                        mater.setPrice(0.0);        //设置最新单价
                    }
                    Material temp = material.getMaterial();
                    temp.setStorageSum(temp.getStorageSum()+material.getPutSum());    //设置审核后的材料库存数量

                    storageService.updateStorageMater(mater);       //更新仓库中材料
                    materialService.updateMaterSum(temp);           //更新材料库存数量
                }
                //添加到集合中
                fios.add(fio);
            }
            putStorageMaterialService.addFifoi(fios, materials1);        //添加材料入库记录到表中，并更新材料库存
            if ("".equals(storage.getPutSerial()) || storage.getPutSerial() == null) {
                //获取该职员今日最新入库的单号
                Staff staff = (Staff)session.getAttribute(Constant.SESSION_KEY);
                String putSerial = putStorageService.getNowPutSerial(staff.getCoding());
                if(null == putSerial){
                    putSerial = getSerial("");
                }else{
                    putSerial = getSerial(putSerial);
                }
                storage.setPutSerial(staff.getName() +"-"+putSerial);        //生成入库单号
                putStorageService.updatePutSerial(storage); //更新入库单序号
            }
            storage.setApproveType(Constant.STATE_1);       //设置状态，返回给客户端
        } else {  //反审核
            //判断该订单是否有出库记录,有过出库记录不予反审核
            String isOut = putStorageService.isOut(storage.getID());        //查看该入库单是否有出库记录
            if(isOut != null){
                storage.setApproveType(Constant.STATE_2);       //该订单已出库
                return storage;
            }

            params.put("state", Constant.STATE_0);           //设置审批状态
            params.put("date", "");        //设置审批时间
            params.put("person", "");        //设置审批人员编号
            for (int i = 0; i < materials.size(); i++) {
                material = materials.get(i);        //获取入库单材料对象


                putStorageMaterialService.deleteFifois(materials.get(i).getID());       //删除记录
                Material temp = material.getMaterial();
                temp.setStorageSum(temp.getStorageSum()-material.getPutSum());    //设置反审核后的材料库存数量

                //获取库存中材料对象
                mater = storageService.getStorageMater(storage.getStorage().getID(), material.getMaterial().getID());

                mater.setSum(mater.getSum() - material.getPutSum());      //设置库存数量
                mater.setMoney(mater.getMoney() - material.getMoneyTax());    //设置总金额=库存金额 + 入库的金额
                if (mater.getSum() > 0) {
                    mater.setPrice(mater.getMoney() / mater.getSum());        //设置最新单价
                } else {
                    mater.setPrice(0.0);        //设置最新单价
                }
                storageService.updateStorageMater(mater);       //更新仓库中材料
                materialService.updateMaterSum(temp);       //更新材料库存数量
            }
            storage.setApproveType(Constant.STATE_0);       //设置状态，返回给客户端
        }
        putStorageService.updateApprove(params);
        return storage;
    }

    /**
     * 生成入库单序号
     *
     * @return
     * @param putSerial
     */
    public String getSerial(String putSerial) {
        String temp = putSerial + "";
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
     * 删除入库单
     * @return 状态码
     */
    public int deletePutStorage(String id){

        return -1;
    }

    /**
     * 更新入库单信息
     * @return 影响的行数
     */
    @RequestMapping("updatePutMessage")
    @ResponseBody
    int updatePutStarogemessage(@RequestBody PutStorage storage){
        return putStorageService.updatePutMessage(storage);
    }

    /**
     * 更新入库单税率
     * @param storage 入库单对象
     * @return 影响的行数
     */
    @RequestMapping("/updatePutTax")
    @ResponseBody
    int updatePutTax(@RequestBody PutStorage storage){
//        获取入库单材料集合
        List<StorageMaterial> materials = putStorageMaterialService.getMaterialByPutId(storage.getID());
        StorageMaterial material = null;
        for (int i = 0;i < materials.size();i++){
            material = materials.get(i);
            material.setMoney(material.getMoneyTax()/(1+storage.getTax()/100));        //设置不含税金额 = 含税总金额 / (1 + 税率/100)
            material.setPrice(material.getMoney()/material.getPutSum());        //设置不含税单价
            material.setTaxMoney(material.getMoneyTax() - material.getMoney());     //设置税额 = 含税金额 - 不含税金额
            putStorageMaterialService.updateMaterMoney(material);       //更新材料信息
        }
        //更新入库单税率信息
        return putStorageService.updatePutTax(storage);
    }

    /**
     * 搜索入库单
     * @param str 搜索字符串
     * @param start 开始时间
     * @param end 结束时间
     * @return 入库单集合
     */
    @RequestMapping("/seekPutStorage")
    @ResponseBody
    public List<PutStorage> seekStorage(String str,String start,String end){
        List<PutStorage> storages = null;
        if(null == str || "".equals(str)){
            Map<String,Object> params = new HashMap();
            params.put("start", start);
            params.put("end", end);
            params.put("type",1);       //已审核类型
            storages = putStorageService.getPutStorages(new PageBounds(0,ApplyController.PAGESIZE),params);
        }else{
            storages = putStorageService.seekPutStorage(str);
        }

        for (int i = 0;i < storages.size();i++){
            //获取该订单可出库的材料总数
            Integer sum = putStorageMaterialService.getOkMaterSum(storages.get(i).getID());
            if(sum == null || sum <= 0){        //没有材料的入库单过滤掉
                storages.remove(i);
            }
        }
        return storages;
    }

    /**
     * 根据供应单位id获取该供应单位入库材料
     * @param companyId 供应单位id
     * @return 入库材料集合
     */
    @RequestMapping("/getMaterByCompany")
    @ResponseBody
    public List<StorageMaterial> getStorageMaterByCompany(String companyId,String start,String end){
        List<StorageMaterial> storageMaters = null;
        Map<String,String> params = new HashMap<String, String>();
        params.put("company",companyId);    //供应单位id
        params.put("start",start);      //开始时间
        params.put("end",end);          //结束时间
        storageMaters = putStorageMaterialService.getMaterialByCompany(params);
        return storageMaters;
    }
}
