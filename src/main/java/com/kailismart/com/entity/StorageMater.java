package com.kailismart.com.entity;

/**
 * Created by Administrator on 2016-08-26.
 * 仓库中材料表 sdpm005
 */
public class StorageMater extends BaseEntity {
    private String storageId;   //仓库id      01
    private Material material;      //材料对象  02
    private Double sum = 0.0;         //数量        03
    private Double price = 0.0;       //平均单价      04
    private Double money = 0.0;       //总价        05
    private String pm00506 = "";     //不知名

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

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
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

    public String getPm00506() {
        return pm00506;
    }

    public void setPm00506(String pm00506) {
        this.pm00506 = pm00506;
    }
}
