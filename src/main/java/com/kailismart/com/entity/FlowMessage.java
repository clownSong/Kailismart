package com.kailismart.com.entity;

/**
 * Created by Administrator on 2016-08-16.
 * 流程发起后，消息主表【我的发起】 sdpo003
 */
public class FlowMessage extends BaseEntity {
    private String startDate;   //发起时间  02
    private String staffId;     //发起人id        03
    private String title;       //发起标题        04
    private String content;     //发起内容        05
    private String frameCoding; //窗口编号        06
    private String frameId;     //窗口对象主键id  07 如：采购订单主ID
    private int state = 0;          //当前流程状态    08 {1:审批中，4：取消}
    private int po00309 = 0;        //默认0，作用未知
    private int po00310 = 0;        //默认0，作用未知
    private String histroryId;      //流程记录id    sdpo001_History表主键
    private int po00312 = 0;            //默认0，作用未知
    private String date;            //某个时间字段    13
    private String po00314 = "";         //默认空字符串，作用未知   14
    private String sql;             //sql语句，目前位置    15
    private String frameColumn;     //对象主键id列名称     16
    private String po00317 = "";         //默认空字符串，作用未知   17

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getPo00309() {
        return po00309;
    }

    public void setPo00309(int po00309) {
        this.po00309 = po00309;
    }

    public int getPo00310() {
        return po00310;
    }

    public void setPo00310(int po00310) {
        this.po00310 = po00310;
    }

    public String getHistroryId() {
        return histroryId;
    }

    public void setHistroryId(String histroryId) {
        this.histroryId = histroryId;
    }

    public int getPo00312() {
        return po00312;
    }

    public void setPo00312(int po00312) {
        this.po00312 = po00312;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPo00314() {
        return po00314;
    }

    public void setPo00314(String po00314) {
        this.po00314 = po00314;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getFrameColumn() {
        return frameColumn;
    }

    public void setFrameColumn(String frameColumn) {
        this.frameColumn = frameColumn;
    }

    public String getPo00317() {
        return po00317;
    }

    public void setPo00317(String po00317) {
        this.po00317 = po00317;
    }
}
