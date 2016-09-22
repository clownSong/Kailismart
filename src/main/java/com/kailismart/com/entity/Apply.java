package com.kailismart.com.entity;

import java.sql.Date;
import java.util.List;

/**
 * Created by Administrator on 2016-08-02.
 * 材料采购申请单
 */
public class Apply extends BaseEntity {
    private String projectID;       //项目ID
    private Project project;         //项目对象
    private String serialNumber;    //申请单编号
    private byte audit;              //审核标志，{false 0：未审核， true 1：审核通过}
    private String prepareDate;       //制单时间,原数据库中为varchar(10)
    private String staffCoding;         //员工编号，数据库中长度为20 pj00421
    private Staff staff;            //员工对象
    private String remark;          //申请单备注，长度为：255
    private String unitID;          //单位ID，长度40
    private int isTax;              //是否抵税；{0：否，1：是}
    private String date;              //申请日期,原数据库中为varchar(10)
    private String address;         //地址ID号，长度40
    private Section section;        //申请部门
    private int state;              //采购状态  {08（0：未采购，1：部分采购，2全部采购）}
    private List<ApplyMaterial> applyMaterialList;      //申请单材料
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    public List<ApplyMaterial> getApplyMaterialList() {
        return applyMaterialList;
    }

    public void setApplyMaterialList(List<ApplyMaterial> applyMaterialList) {
        this.applyMaterialList = applyMaterialList;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public byte getAudit() {
        return audit;
    }

    public void setAudit(byte audit) {
        this.audit = audit;
    }
    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }


    public String getStaffCoding() {
        return staffCoding;
    }

    public void setStaffCoding(String staffCoding) {
        this.staffCoding = staffCoding;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUnitID() {
        return unitID;
    }

    public void setUnitID(String unitID) {
        this.unitID = unitID;
    }

    public int getIsTax() {
        return isTax;
    }

    public void setIsTax(int isTax) {
        this.isTax = isTax;
    }

    public String getPrepareDate() {
        return prepareDate;
    }

    public void setPrepareDate(String prepareDate) {
        this.prepareDate = prepareDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
