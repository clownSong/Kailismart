package com.kailismart.com.entity;

/**
 * Created by Administrator on 2016-08-15.
 * 流程对象实体
 */
public class Flow extends BaseEntity {
    private String name;        //流程名称
    private String remark;      //流程说明
    private String folder;      //流程目录id
    private Integer isPub;      //是否公开流程
    private byte condition;     //必要条件
    private String frameCoding; //窗体代码
    private String pubFlow;     //是否自由流程
    private String templement;  //模板id
    private byte type;          //关联类型{2:关联模板，3.无关联，0：关联窗体}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getIsPub() {
        return isPub;
    }

    public void setIsPub(Integer isPub) {
        this.isPub = isPub;
    }

    public byte getCondition() {
        return condition;
    }

    public void setCondition(byte condition) {
        this.condition = condition;
    }

    public String getFrameCoding() {
        return frameCoding;
    }

    public void setFrameCoding(String frameCoding) {
        this.frameCoding = frameCoding;
    }

    public String getPubFlow() {
        return pubFlow;
    }

    public void setPubFlow(String pubFlow) {
        this.pubFlow = pubFlow;
    }

    public String getTemplement() {
        return templement;
    }

    public void setTemplement(String templement) {
        this.templement = templement;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }
}
