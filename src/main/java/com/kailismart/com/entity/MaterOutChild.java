package com.kailismart.com.entity;

/**
 * Created by 宋正根 on 2016/8/30.
 * 出库单材料对象 sdpm021
 */
public class MaterOutChild extends BaseEntity {
    private String materOutId;      //出库单主表id
    private String outNumber;
    private Material material;      //材料对象 03
    private Double sum;             //领用数量  04
    private Double taxPrice;           //领用单价
    private Double taxMoney;           //领用金额
    private Double planPrice = 0.0;           //成本单价
    private String putMaterId = "";          //入库单材料id
    private String pm02109 = "";             //暂时不知作用
    private String pm02110 = "";             //暂时不知作用
    private byte pm02111 = 0;           //暂时不知作用，添加时无需，默认为0
    private String pm02112 = "";        //暂时不知作用，添加时无需
    private String outDate;             //出库时间

    public String getOutNumber() {
        return outNumber;
    }

    public void setOutNumber(String outNumber) {
        this.outNumber = outNumber;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public String getMaterOutId() {
        return materOutId;
    }

    public void setMaterOutId(String materOutId) {
        this.materOutId = materOutId;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(Double taxPrice) {
        this.taxPrice = taxPrice;
    }

    public Double getTaxMoney() {
        return taxMoney;
    }

    public void setTaxMoney(Double taxMoney) {
        this.taxMoney = taxMoney;
    }

    public Double getPlanPrice() {
        return planPrice;
    }

    public void setPlanPrice(Double planPrice) {
        this.planPrice = planPrice;
    }

    public String getPutMaterId() {
        return putMaterId;
    }

    public void setPutMaterId(String putMaterId) {
        this.putMaterId = putMaterId;
    }

    public String getPm02109() {
        return pm02109;
    }

    public void setPm02109(String pm02109) {
        this.pm02109 = pm02109;
    }

    public String getPm02110() {
        return pm02110;
    }

    public void setPm02110(String pm02110) {
        this.pm02110 = pm02110;
    }

    public byte getPm02111() {
        return pm02111;
    }

    public void setPm02111(byte pm02111) {
        this.pm02111 = pm02111;
    }

    public String getPm02112() {
        return pm02112;
    }

    public void setPm02112(String pm02112) {
        this.pm02112 = pm02112;
    }
}
