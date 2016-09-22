package com.kailismart.com.entity;

import java.util.List;

/**
 * Created by Administrator on 2016-08-16.
 * 审批过程人员集合对象 sdpo002
 */
public class CoursePerson extends BaseEntity {
    private String flowId;      //流程id  02
    private  int serial;        //序号    03
    private int staffType;      //角色类型
    private String courseId;    //过程id  08
    private List<Staff> staff;        //审批人
    private String staffId;     //审批人Id 05,(若staffType为角色或别的类型，则他保存的是该角色主键)
    private int type;           //处理类型  09  {0：审批，1：知会};

    public List<Staff> getStaff() {
        return staff;
    }

    public void setStaff(List<Staff> staff) {
        this.staff = staff;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public int getStaffType() {
        return staffType;
    }

    public void setStaffType(int staffType) {
        this.staffType = staffType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

}
