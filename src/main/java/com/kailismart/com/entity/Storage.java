package com.kailismart.com.entity;

/**
 * Created by Administrator on 2016-08-23.
 * 仓库主对象 sdpm004
 */
public class Storage extends BaseEntity{
    private String name;        //仓库名称  02
    private Staff staff;        //仓库管理员 04
    private String createDate;  //创建时间  06

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
