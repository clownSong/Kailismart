package com.kailismart.com.entity;

import java.util.List;

/**
 * Created by Administrator on 2016-08-16.
 * 流程过程对象 sdpo020
 */
public class FlowCourse extends BaseEntity {
    private String flowId;      //流程id  02
    private int serial;      //过程序号     03
    private String name;        //过程名称  04
    private byte po02005 = 0;       //会签  05
    private int po02006 = 0;       //时间限制  06
    private byte po02007 = 0;       //安全验证  07
    private Integer pubPerson;  //自由选人  08：{0：否，1：是}
    private String po02009 = "0";           //短信审批  sdpo020b中为窗口主键id
    private byte po02010 = 0;           //立即发送
    private byte po02011 = 0;           //到期发送
    private int po02012 = 0;           //允许跳过
    private byte po02013 = 0;
    private int po02014 = 0;
    private int po02015 = 0;
    private int po02016 = 0;
    private int po02017 = 0;
    private String po02018 = "";
    private int po02019 = 0;
    private String po02020 = "";
    private String po02021 = "1";       //手机审批
    List<CoursePerson> personList;  //流程审批人员集合

    public byte getPo02005() {
        return po02005;
    }

    public void setPo02005(byte po02005) {
        this.po02005 = po02005;
    }

    public int getPo02006() {
        return po02006;
    }

    public void setPo02006(int po02006) {
        this.po02006 = po02006;
    }

    public byte getPo02007() {
        return po02007;
    }

    public void setPo02007(byte po02007) {
        this.po02007 = po02007;
    }

    public String getPo02009() {
        return po02009;
    }

    public void setPo02009(String po02009) {
        this.po02009 = po02009;
    }

    public byte getPo02010() {
        return po02010;
    }

    public void setPo02010(byte po02010) {
        this.po02010 = po02010;
    }

    public byte getPo02011() {
        return po02011;
    }

    public void setPo02011(byte po02011) {
        this.po02011 = po02011;
    }

    public int getPo02012() {
        return po02012;
    }

    public void setPo02012(int po02012) {
        this.po02012 = po02012;
    }

    public byte getPo02013() {
        return po02013;
    }

    public void setPo02013(byte po02013) {
        this.po02013 = po02013;
    }

    public int getPo02014() {
        return po02014;
    }

    public void setPo02014(int po02014) {
        this.po02014 = po02014;
    }

    public int getPo02015() {
        return po02015;
    }

    public void setPo02015(int po02015) {
        this.po02015 = po02015;
    }

    public int getPo02016() {
        return po02016;
    }

    public void setPo02016(int po02016) {
        this.po02016 = po02016;
    }

    public int getPo02017() {
        return po02017;
    }

    public void setPo02017(int po02017) {
        this.po02017 = po02017;
    }

    public String getPo02018() {
        return po02018;
    }

    public void setPo02018(String po02018) {
        this.po02018 = po02018;
    }

    public int getPo02019() {
        return po02019;
    }

    public void setPo02019(int po02019) {
        this.po02019 = po02019;
    }

    public String getPo02020() {
        return po02020;
    }

    public void setPo02020(String po02020) {
        this.po02020 = po02020;
    }

    public String getPo02021() {
        return po02021;
    }

    public void setPo02021(String po02021) {
        this.po02021 = po02021;
    }

    public List<CoursePerson> getPersonList() {
        return personList;
    }
    public void setPersonList(List<CoursePerson> personList) {
        this.personList = personList;
    }
    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPubPerson() {
        return pubPerson;
    }

    public void setPubPerson(Integer pubPerson) {
        this.pubPerson = pubPerson;
    }
}
