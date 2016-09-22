package com.kailismart.com.entity;

/**
 * Created by Administrator on 2016-08-18.
 * 我的审批主对象  sdpo100
 */
public class MyApproveMain extends BaseEntity {
    private String po1000102 = "127013";           //  127013   02
    private String flowMessageId;       //【我的发起】ID号 03
    private String sendDate;            //发送时间  04
    private String name;                //办文名称  05
    private String title;               //办文主题  06
    private String frameCoding;         //窗口编号  07
    private String frameId;             //窗口主键id    08
    private byte type = 1;                //暂时不知作用，默认1  09
    private String staffName;           //发起人姓名 10
    private String approveAttachedId;   //审批表附表：po007表的id号  11
    private byte po10012 = 0;               //默认0，暂时不知作用

    public String getPo1000102() {
        return po1000102;
    }

    public void setPo1000102(String po1000102) {
        this.po1000102 = po1000102;
    }

    public String getFlowMessageId() {
        return flowMessageId;
    }

    public void setFlowMessageId(String flowMessageId) {
        this.flowMessageId = flowMessageId;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFrameCoding() {
        return frameCoding;
    }

    public void setFrameCoding(String frameCoding) {
        this.frameCoding = frameCoding;
    }

    public String getFrameId() {
        return frameId;
    }

    public void setFrameId(String frameId) {
        this.frameId = frameId;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getApproveAttachedId() {
        return approveAttachedId;
    }

    public void setApproveAttachedId(String approveAttachedId) {
        this.approveAttachedId = approveAttachedId;
    }

    public byte getPo10012() {
        return po10012;
    }

    public void setPo10012(byte po10012) {
        this.po10012 = po10012;
    }
}
