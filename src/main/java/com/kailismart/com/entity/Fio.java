package com.kailismart.com.entity;

/**
 * Created by Administrator on 2016-08-26.
 * 入库单审核通过后的记录表，sdfifoi
 */
public class Fio extends BaseEntity{
    private Double ID01;     //主键id   01
    private String materId;     //材料编码|id   02
    private String date;        //添加时间  2016-08-26 09:44:30.253 03
    private Double putSum;       //入库数量 04
    private Double io05 = 0.0;       //暂时不知 05
    private Double io06 = 0.0;       //暂时不知 06
    private Double putPrice;       //入库单价 (含税)   07
    private Double putMoney;        //入库金额 (含税) 08
    private String putMaterId;        //入库单材料表id 09
    private byte type = 30;          //类型,30：采购订单入库，60：库存盘点入库

    public Double getIo06() {
        return io06;
    }

    public void setIo06(Double io06) {
        this.io06 = io06;
    }

    public Double getIo05() {
        return io05;
    }

    public void setIo05(Double io05) {
        this.io05 = io05;
    }

    public Double getPutSum() {
        return putSum;
    }

    public void setPutSum(Double putSum) {
        this.putSum = putSum;
    }

    public Double getPutPrice() {
        return putPrice;
    }

    public void setPutPrice(Double putPrice) {
        this.putPrice = putPrice;
    }

    public Double getPutMoney() {
        return putMoney;
    }

    public void setPutMoney(Double putMoney) {
        this.putMoney = putMoney;
    }

    public String getPutMaterId() {
        return putMaterId;
    }

    public void setPutMaterId(String putMaterId) {
        this.putMaterId = putMaterId;
    }

    public Double getID01() {
        return ID01;
    }

    public void setID01(Double ID01) {
        this.ID01 = ID01;
    }

    public String getMaterId() {
        return materId;
    }

    public void setMaterId(String materId) {
        this.materId = materId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }
}
