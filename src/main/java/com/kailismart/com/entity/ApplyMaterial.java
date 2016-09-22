package com.kailismart.com.entity;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2016-08-02.
 * 申请单中材料对象
 */
public class ApplyMaterial extends Material {
    private String applyID;                  //采购申请单ID
    private int serialNumber;            //序号
    private String applyDate = "";                //需求日期
    private Double sum;        //申请数量
    private String remark = "";      //材料备注
    private String cnfParam = "";        //配置参数
    private Double notSum;          //未采购数量
    private String major;           //主键ID
    private Double ySum;            //已采购数量 {08}
    private Apply apply;            //申请单对象

    public Apply getApply() {
        return apply;
    }

    public void setApply(Apply apply) {
        this.apply = apply;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Double getySum() {
        return ySum;
    }

    public void setySum(Double ySum) {
        this.ySum = ySum;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public Double getNotSum() {
        return notSum;
    }

    public void setNotSum(Double notSum) {
        this.notSum = notSum;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getApplyID() {
        return applyID;
    }

    public void setApplyID(String applyID) {
        this.applyID = applyID;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCnfParam() {
        return cnfParam;
    }

    public void setCnfParam(String cnfParam) {
        this.cnfParam = cnfParam;
    }
}
