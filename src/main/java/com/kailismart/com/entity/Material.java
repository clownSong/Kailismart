package com.kailismart.com.entity;

/**
 * Created by Administrator on 2016-08-02.
 * 材料对象
 */
public class Material extends BaseEntity {
    private String folder;              //材料目录ID
    private String name;           //材料名称
    private String brand;           //品牌
    private String producingArea;    //产地
    private String property;        //材料属性
    private String model;       //型号
    private Double planPrice;   //计划单价
    private Double costPrice;      //成本单价
    private String remark;      //备注
    private Unit unit;        //材料单位对象  {}
    private String unitName;        //材料单位名称
    private String coding;          //材料编码
    private Double storageSum;      //库存数量

    public Double getStorageSum() {
        return storageSum;
    }

    public void setStorageSum(Double storageSum) {
        this.storageSum = storageSum;
    }

    public String getCoding() {
        return coding;
    }

    public void setCoding(String coding) {
        this.coding = coding;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProducingArea() {
        return producingArea;
    }

    public void setProducingArea(String producingArea) {
        this.producingArea = producingArea;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPlanPrice() {
        return planPrice;
    }

    public void setPlanPrice(Double planPrice) {
        this.planPrice = planPrice;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }
}
