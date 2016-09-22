package com.kailismart.com.entity;

import java.util.List;

/**
 * Created by 宋正根 on 2016/8/30.
 * 材料出库单主对象 sdpm020
 */
public class MaterOut extends BaseEntity {
    private String outDate;     //领料日期  02
    private String outNumber;       //领料单编号 03
    private Project project;     //项目对象 ，领料项目 04
    private String pm02005 = "1";     //任务代码，没有任务为1
    private Storage storage;        //仓库对象 ，发出仓库 06
    private Section section;        //部门对象,领料部门  07
    private Company company;        //领料单位对象    08
    private String remark = "";      //领料单备注 09
    private String pm02010 = "";    //暂时不知作用    10
    private String date;            //制单日期，最后修改时间   11
    private String pm02012 = "";         //暂时不知作用    12
    private Staff staff;        //制单人   13
    private String pm02014 = "";     //暂时不知作用    14
    private String putId = "";           //入库单id,添加时可以为空字符串 15
    private String approveDate = "";     //审核时间 16
    private String approvePersonCoding = "";     //审核人id    17
    private Staff approveStaff;             //审核人对象
    private String pm02018 = "";        //暂时不知作用    18
    private int state;              //审核状态{0：未审核，1：已审核}
    private byte pm02020;       //暂时不知作用，添加时无需，数据库默认为0
    private String outPersonId = "";     //领料人id     21
    private Staff outPerson;        //领料人对象
    private List<MaterOutChild> materOuts;  //出库材料集合

    public List<MaterOutChild> getMaterOuts() {
        return materOuts;
    }

    public void setMaterOuts(List<MaterOutChild> materOuts) {
        this.materOuts = materOuts;
    }

    public Staff getApproveStaff() {
        return approveStaff;
    }

    public void setApproveStaff(Staff approveStaff) {
        this.approveStaff = approveStaff;
    }

    public String getOutPersonId() {
        return outPersonId;
    }

    public void setOutPersonId(String outPersonId) {
        this.outPersonId = outPersonId;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public String getOutNumber() {
        return outNumber;
    }

    public void setOutNumber(String outNumber) {
        this.outNumber = outNumber;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getPm02005() {
        return pm02005;
    }

    public void setPm02005(String pm02005) {
        this.pm02005 = pm02005;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPm02010() {
        return pm02010;
    }

    public void setPm02010(String pm02010) {
        this.pm02010 = pm02010;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPm02012() {
        return pm02012;
    }

    public void setPm02012(String pm02012) {
        this.pm02012 = pm02012;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getPm02014() {
        return pm02014;
    }

    public void setPm02014(String pm02014) {
        this.pm02014 = pm02014;
    }

    public String getPutId() {
        return putId;
    }

    public void setPutId(String putId) {
        this.putId = putId;
    }

    public String getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate;
    }

    public String getApprovePersonCoding() {
        return approvePersonCoding;
    }

    public void setApprovePersonCoding(String approvePersonCoding) {
        this.approvePersonCoding = approvePersonCoding;
    }

    public String getPm02018() {
        return pm02018;
    }

    public void setPm02018(String pm02018) {
        this.pm02018 = pm02018;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public byte getPm02020() {
        return pm02020;
    }

    public void setPm02020(byte pm02020) {
        this.pm02020 = pm02020;
    }

    public Staff getOutPerson() {
        return outPerson;
    }

    public void setOutPerson(Staff outPerson) {
        this.outPerson = outPerson;
    }
}
