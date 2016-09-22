package com.kailismart.com.entity;

/**
 * Created by 宋正根 on 2016/9/1.
 * 出库单材料表关系表 sdfifoo
 */
public class OutMaterChildHistory {
    private Double ID;          //自增主键  01
    private String materId;     //材料id  02
    private String dateTime;        //添加时间  03
    private Double outSum;          //出库数量  04
    private Double RQty05 = 0.0;          //暂时不知作用  05
    private Double price;           //单价        06
    private Double money;           //总价        07
    private String outMaterChildId;     //出库单材料表主键  08
    private byte type = 20;         //类型    09
    private Double fifoiId;         //sdfifoi主键id


    public Double getID() {
        return ID;
    }

    public void setID(Double ID) {
        this.ID = ID;
    }

    public String getMaterId() {
        return materId;
    }

    public void setMaterId(String materId) {
        this.materId = materId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Double getOutSum() {
        return outSum;
    }

    public void setOutSum(Double outSum) {
        this.outSum = outSum;
    }

    public Double getRQty05() {
        return RQty05;
    }

    public void setRQty05(Double RQty05) {
        this.RQty05 = RQty05;
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

    public String getOutMaterChildId() {
        return outMaterChildId;
    }

    public void setOutMaterChildId(String outMaterChildId) {
        this.outMaterChildId = outMaterChildId;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public Double getFifoiId() {
        return fifoiId;
    }

    public void setFifoiId(Double fifoiId) {
        this.fifoiId = fifoiId;
    }
}
