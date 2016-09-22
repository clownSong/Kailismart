package com.kailismart.com.entity;

/**
 * Created by Administrator on 2016-08-17.
 * 审批过程人员关系表 sdpo002b
 */
public class CoursePersonAttached extends BaseEntity {
    private String coursePersonId;        //流程过程审批人员对象主键ID    b01
    private String flowHistoryId;   //流程记录表id   b02
    private int serial;                //审批人序号 serial  po00203=b03
    private int b04 = 2;                //审批角色类型，审批人员类型
    private String staffId;         //审批人ID     b05
    private String b06 = "";        //作用未知      b06
    private int b07 = 0;            //默认为0，作用未知 b07
    private String courseId;        //过程id      b08
    private int type;                //1：知会，0：审批   b09
    private byte b10 = 0;               //作用未知，默认为0，添加时无需   po00209=b10
    private String frameId;     //窗口id号 b11
    private String b12 = "0";             //作用未知，默认为0，添加时无需   b12
    private byte b13 = 1;             //作用未知，默认空字符串   b10

    public String getCoursePersonId() {
        return coursePersonId;
    }

    public void setCoursePersonId(String coursePersonId) {
        this.coursePersonId = coursePersonId;
    }

    public String getFlowHistoryId() {
        return flowHistoryId;
    }

    public void setFlowHistoryId(String flowHistoryId) {
        this.flowHistoryId = flowHistoryId;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public int getB04() {
        return b04;
    }

    public void setB04(int b04) {
        this.b04 = b04;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getB06() {
        return b06;
    }

    public void setB06(String b06) {
        this.b06 = b06;
    }

    public int getB07() {
        return b07;
    }

    public void setB07(int b07) {
        this.b07 = b07;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public byte getB10() {
        return b10;
    }

    public void setB10(byte b10) {
        this.b10 = b10;
    }

    public String getFrameId() {
        return frameId;
    }

    public void setFrameId(String frameId) {
        this.frameId = frameId;
    }

    public String getB12() {
        return b12;
    }

    public void setB12(String b12) {
        this.b12 = b12;
    }

    public byte getB13() {
        return b13;
    }

    public void setB13(byte b13) {
        this.b13 = b13;
    }
}
