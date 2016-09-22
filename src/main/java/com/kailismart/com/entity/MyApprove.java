package com.kailismart.com.entity;

/**
 * Created by Administrator on 2016-08-17.
 * 【我的审批】主表对象   sdpo006
 */
public class MyApprove extends BaseEntity {
    private String acceptDate;      //接收时间  02
    private String sendPerson;      //发送人id 03
    private String name;            //流程主题  04
    private String title;       //原单据标题 05
    private int po00606;        //数据库默认0，
    private int po00607;        //数据库默认0
    private String frameCoding;     //窗口编号  11
    private String frameId;     //窗口单据主id   12

    public String getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(String acceptDate) {
        this.acceptDate = acceptDate;
    }

    public String getSendPerson() {
        return sendPerson;
    }

    public void setSendPerson(String sendPerson) {
        this.sendPerson = sendPerson;
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

    public int getPo00606() {
        return po00606;
    }

    public void setPo00606(int po00606) {
        this.po00606 = po00606;
    }

    public int getPo00607() {
        return po00607;
    }

    public void setPo00607(int po00607) {
        this.po00607 = po00607;
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
}
