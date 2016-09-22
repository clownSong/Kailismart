package com.kailismart.com.entity;

import java.util.List;

/**
 * Created by Administrator on 2016-08-08.
 * 采购订单对象
 */
public class Procurement extends BaseEntity {
    private Staff staff;        //采购职员  20
    private String pmDate;      //采购日期  02
    private String pmNumber;    //订单编号  01
    private Double tax;         //税率      16
    private Company company;        //供应单位对象    04
    private String voucherDate;     //制单时间  08
    private String fqDate;          //发起时间  09
    private String voucherName;     //制单人员  10
    private String voucherCoding;       //制单人员编码 10
    private City city;            //货运地址  06
    private String auditName;       //审核人员 13
    private byte state;          //审核状态 14   {0：未审核，1已审核}
    private Contract contract;        //合同对象
    private List<ProMaterial> material;     //采购订单材料
    private String remark;          //备注
    private String pm01326 = "";         //不能为null,数据库默认插入""字符串用
    private String pm01312 = "";         //不能为null,数据库默认插入""字符串用
    private byte putState;          //入库状态{0：未入库，3：部分入库，4：完全入库}
    private Flow flow;              //流程对象

    public byte getPutState() {
        return putState;
    }

    public void setPutState(byte putState) {
        this.putState = putState;
    }

    public Flow getFlow() {
        return flow;
    }

    public void setFlow(Flow flow) {
        this.flow = flow;
    }

    public String getPm01312() {
        return pm01312;
    }

    public void setPm01312(String pm01312) {
        this.pm01312 = pm01312;
    }

    public String getPm01326() {
        return pm01326;
    }

    public void setPm01326(String pm01326) {
        this.pm01326 = pm01326;
    }

    public String getVoucherCoding() {
        return voucherCoding;
    }

    public void setVoucherCoding(String voucherCoding) {
        this.voucherCoding = voucherCoding;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFqDate() {
        return fqDate;
    }

    public void setFqDate(String fqDate) {
        this.fqDate = fqDate;
    }

    public List<ProMaterial> getMaterial() {
        return material;
    }

    public void setMaterial(List<ProMaterial> material) {
        this.material = material;
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getPmDate() {
        return pmDate;
    }

    public void setPmDate(String pmDate) {
        this.pmDate = pmDate;
    }

    public String getPmNumber() {
        return pmNumber;
    }

    public void setPmNumber(String pmNumber) {
        this.pmNumber = pmNumber;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(String voucherDate) {
        this.voucherDate = voucherDate;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    @Override
    public String toString() {
        return "采购订单{" +
                "职员=" + staff +
                ", pmDate='" + pmDate + '\'' +
                ", pmNumber='" + pmNumber + '\'' +
                ", tax=" + tax +
                ", company=" + company +
                ", voucherDate='" + voucherDate + '\'' +
                ", voucherName='" + voucherName + '\'' +
                ", city='" + city + '\'' +
                ", auditName='" + auditName + '\'' +
                ", state=" + state +
                ", contract=" + contract +
                ", material=" + material +
                '}';
    }
}
