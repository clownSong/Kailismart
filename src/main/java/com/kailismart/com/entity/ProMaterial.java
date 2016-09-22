package com.kailismart.com.entity;

/**
 * Created by Administrator on 2016-08-12.
 * 采购订单材料对象 sdpm014
 */
public class ProMaterial extends ApplyMaterial {
    private String proId;       //订单id           01     服务端赋值
    private String primentId;          //主键id      02
    private Double price;       //单价（不含税）    07     客户端 no
    private Double money;       //金额（不含税）    08     客户端 no
    private Double priceTax;    //单价（含税）      14    客户端  ok
    private Double moneyTax;       //金额（含税）   18    客户端  ok
    private Double taxMoney;        //税额          16    客户端  no
    private Double inSum = 0.00;           //入库数量      11  客户端
    private String inDate = "";           //入库时间      12  客户端
    private String projectId;       //项目id        22    客户端 ok
    private String dhDate;          //到货日期      10      客户端 ok
    private Double pm01405 = 1.000000;         //暂时不知作用
    private Material material;      //材料对象

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Double getPm01405() {
        return pm01405;
    }

    public void setPm01405(Double pm01405) {
        this.pm01405 = pm01405;
    }

    public String getPrimentId() {
        return primentId;
    }

    public void setPrimentId(String primentId) {
        this.primentId = primentId;
    }

    public Double getMoneyTax() {
        return moneyTax;
    }

    public void setMoneyTax(Double moneyTax) {
        this.moneyTax = moneyTax;
    }

    public Double getTaxMoney() {
        return taxMoney;
    }

    public void setTaxMoney(Double taxMoney) {
        this.taxMoney = taxMoney;
    }

    public String getDhDate() {
        return dhDate;
    }

    public void setDhDate(String dhDate) {
        this.dhDate = dhDate;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPriceTax() {
        return priceTax;
    }

    public void setPriceTax(Double priceTax) {
        this.priceTax = priceTax;
    }

    public Double getInSum() {
        return inSum;
    }

    public void setInSum(Double inSum) {
        this.inSum = inSum;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "ProMaterial{" +
                "proId='" + proId + '\'' +
                ", price=" + price +
                ", money=" + money +
                ", priceTax=" + priceTax +
                ", moneyTax=" + moneyTax +
                ", taxMoney=" + taxMoney +
                ", inSum=" + inSum +
                ", projectId='" + projectId + '\'' +
                ", dhDate='" + dhDate + '\'' +
                '}';
    }
}
