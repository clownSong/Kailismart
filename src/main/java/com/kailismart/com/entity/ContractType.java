package com.kailismart.com.entity;

import com.kailismart.com.entity.BaseEntity;

/**
 * Created by Administrator on 2016-08-11.
 * 合同类型
 */
public class ContractType extends BaseEntity {
    private String name;        //类型名称
    private int serialNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }
}
