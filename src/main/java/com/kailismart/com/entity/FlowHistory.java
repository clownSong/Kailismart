package com.kailismart.com.entity;

/**
 * Created by Administrator on 2016-08-16.
 * 流程使用记录对象
 */
public class FlowHistory extends BaseEntity {
    private String name;        //流程名称
    private String remark;      //流程说明
    private String folderCoding;    //流程目录编号
    private String frameCoding;     //窗体代码

    /**
     * 默认的口燥函数
     */
    public FlowHistory(){

    }

    public FlowHistory(String name, String remark, String folderCoding, String frameCoding) {
        this.name = name;
        this.remark = remark;
        this.folderCoding = folderCoding;
        this.frameCoding = frameCoding;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFolderCoding() {
        return folderCoding;
    }

    public void setFolderCoding(String folderCoding) {
        this.folderCoding = folderCoding;
    }

    public String getFrameCoding() {
        return frameCoding;
    }

    public void setFrameCoding(String frameCoding) {
        this.frameCoding = frameCoding;
    }
}
