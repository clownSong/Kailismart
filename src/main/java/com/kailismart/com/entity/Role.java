package com.kailismart.com.entity;

import java.util.List;

/**
 * Created by Administrator on 2016-08-20.
 * 角色对象
 */
public class Role extends BaseEntity {
    private String coding;      //角色编码
    private String name;        //角色名称
    private List<Staff> staffList;  //角色职员集合

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }

    public Role() {
    }

    public Role(String coding, String name) {
        this.coding = coding;
        this.name = name;
    }

    public String getCoding() {
        return coding;
    }

    public void setCoding(String coding) {
        this.coding = coding;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
