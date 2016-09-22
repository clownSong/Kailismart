package com.kailismart.com.entity;

/**
 * Created by Administrator on 2016-08-06.
 * 部门实体对象
 */
public class Section extends BaseEntity {
    private String name;        //部门名称
    private String parentID;    //上级部门ID
    private String managerID;   //部门主管ID
    private String tel;         //联系电话
    private String address;     //办公地点
    private String remark;      //部门备注
    private String coding;      //部门编码

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCoding() {
        return coding;
    }

    public void setCoding(String coding) {
        this.coding = coding;
    }
}
