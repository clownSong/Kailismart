package com.kailismart.com.entity;

/**
 * Created by Administrator on 2016-08-23.
 * 入库单材料集合 sdpm027
 */
public class StorageMaterial extends BaseEntity {
    private String storageId;       //入库单id 01
    private Material material;      //材料对象  03 & 04
    private Double inSum = 1.0;           //系数   05
    private Double putSum;          //入库数量  06
    private Double price;           //单价（不含税）   07
    private Double money;           //金额（不含税）   08
    private Double taxPrice;        //含税单价      09
    private Double moneyTax;        //含税金额      10
    private Double taxMoney;        //税额        11
    private String proMaterId;      //采购订单材料表主键id:sdpm014-->pm01402,   12
    private String pm02713 = "";         //不知作用  13
    private Double pm02714 = 0.0;         //不知作用，默认 14 添加时无需
    private Double pm02715 = 0.0;         //不知作用，默认 15 添加时无需
    private Double pm02716 = 0.0;         //不知作用，默认 16 添加时无需
    private Double pm02717 = 0.0;         //不知作用，默认 17 添加时无需
    private String pm02718 = "";            //不知作用，     18
    private int pm02719 = 0;            //不知作用，默认0，添加时无需    19
    private String pm02720 = "";        //不知作用，默认"" 20
    private String pm02721 = "";        //不知作用，默认"" 21  添加时无需
    private Double pm02722 = 0.0;        //不知作用，默认0.0 22  添加时无需
    private String projectId = "";        //项目id  23
    private Project project;            //项目对象
    private String putDate;     //入库时间

    private Double tax;         //税率

    private String putNumber;       //入库单号

    public String getPutNumber() {
        return putNumber;
    }

    public void setPutNumber(String putNumber) {
        this.putNumber = putNumber;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public String getPutDate() {
        return putDate;
    }

    public void setPutDate(String putDate) {
        this.putDate = putDate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Double getInSum() {
        return inSum;
    }

    public void setInSum(Double inSum) {
        this.inSum = inSum;
    }

    public Double getPutSum() {
        return putSum;
    }

    public void setPutSum(Double putSum) {
        this.putSum = putSum;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(Double taxPrice) {
        this.taxPrice = taxPrice;
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

    public String getProMaterId() {
        return proMaterId;
    }

    public void setProMaterId(String proMaterId) {
        this.proMaterId = proMaterId;
    }

    public String getPm02713() {
        return pm02713;
    }

    public void setPm02713(String pm02713) {
        this.pm02713 = pm02713;
    }

    public Double getPm02714() {
        return pm02714;
    }

    public void setPm02714(Double pm02714) {
        this.pm02714 = pm02714;
    }

    public Double getPm02715() {
        return pm02715;
    }

    public void setPm02715(Double pm02715) {
        this.pm02715 = pm02715;
    }

    public Double getPm02716() {
        return pm02716;
    }

    public void setPm02716(Double pm02716) {
        this.pm02716 = pm02716;
    }

    public Double getPm02717() {
        return pm02717;
    }

    public void setPm02717(Double pm02717) {
        this.pm02717 = pm02717;
    }

    public String getPm02718() {
        return pm02718;
    }

    public void setPm02718(String pm02718) {
        this.pm02718 = pm02718;
    }

    public int getPm02719() {
        return pm02719;
    }

    public void setPm02719(int pm02719) {
        this.pm02719 = pm02719;
    }

    public String getPm02720() {
        return pm02720;
    }

    public void setPm02720(String pm02720) {
        this.pm02720 = pm02720;
    }

    public String getPm02721() {
        return pm02721;
    }

    public void setPm02721(String pm02721) {
        this.pm02721 = pm02721;
    }

    public Double getPm02722() {
        return pm02722;
    }

    public void setPm02722(Double pm02722) {
        this.pm02722 = pm02722;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
