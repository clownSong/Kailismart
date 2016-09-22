package com.kailismart.com.entity;

/**
 * Created by Administrator on 2016-08-06.
 */
public class Unit extends BaseEntity {
    private String name;        //单位名称
    private String remark;          //单位备注

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
}
