package com.kailismart.com.entity;

/**
 * Created by Administrator on 2016-08-16.
 * 审批流程消息表 sdpo004
 */
public class FlowApprove extends BaseEntity {
    private String flowMessageId;    //流程消息主表id  02
    private String courseId;        //过程id  03
    private String staffId;         //发送人id 04
    private String acceptStaffId;   //接收人id 05
    private String accrptDate = "";      //发送时间  06      添加时不用
    private String readDate = "";        //已读时间  07      添加时不用
    private String approveDate = "";     //审批时间  08      添加时不用
    private int po00409 = 0;            //默认0，作用未知  09
    private String content;         //审批内容  10      添加时不用
    private int approveState = 0;        //操作状态 11  {0：未读，1：已读，3：同意，5:知会未读，6：知会已读，7：驳回}    添加时默认为0:：未读
    private int po00412 = 0;            //未知标识，12 默认：0
    private String date;                //某个时间列 13
    private byte po00414;            //审批人员序号
    private String po00415;         // 审批步骤序号
    private byte po00416 = 0;           //作用未知  默认0；    16
    private String acceptDate;       //发送时间 17
    private String po00418Id;       //我的审批表附表id     sdpo007:po00701
    private byte po00419;           //默认0   添加时无需
    private byte po00420;           //默认0   添加时无需
    private String po00421 = "";         //默认空字符串，不知作用

    public String getFlowMessageId() {
        return flowMessageId;
    }

    public void setFlowMessageId(String flowMessageId) {
        this.flowMessageId = flowMessageId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getAcceptStaffId() {
        return acceptStaffId;
    }

    public void setAcceptStaffId(String acceptStaffId) {
        this.acceptStaffId = acceptStaffId;
    }

    public String getAccrptDate() {
        return accrptDate;
    }

    public void setAccrptDate(String accrptDate) {
        this.accrptDate = accrptDate;
    }

    public String getReadDate() {
        return readDate;
    }

    public void setReadDate(String readDate) {
        this.readDate = readDate;
    }

    public String getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate;
    }

    public int getPo00409() {
        return po00409;
    }

    public void setPo00409(int po00409) {
        this.po00409 = po00409;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getApproveState() {
        return approveState;
    }

    public void setApproveState(int approveState) {
        this.approveState = approveState;
    }

    public int getPo00412() {
        return po00412;
    }

    public void setPo00412(int po00412) {
        this.po00412 = po00412;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public byte getPo00414() {
        return po00414;
    }

    public void setPo00414(byte po00414) {
        this.po00414 = po00414;
    }

    public String getPo00415() {
        return po00415;
    }

    public void setPo00415(String po00415) {
        this.po00415 = po00415;
    }

    public byte getPo00416() {
        return po00416;
    }

    public void setPo00416(byte po00416) {
        this.po00416 = po00416;
    }

    public String getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(String acceptDate) {
        this.acceptDate = acceptDate;
    }

    public String getPo00418Id() {
        return po00418Id;
    }

    public void setPo00418Id(String po00418Id) {
        this.po00418Id = po00418Id;
    }

    public byte getPo00419() {
        return po00419;
    }

    public void setPo00419(byte po00419) {
        this.po00419 = po00419;
    }

    public byte getPo00420() {
        return po00420;
    }

    public void setPo00420(byte po00420) {
        this.po00420 = po00420;
    }

    public String getPo00421() {
        return po00421;
    }

    public void setPo00421(String po00421) {
        this.po00421 = po00421;
    }
}
