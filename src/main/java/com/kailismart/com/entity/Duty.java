package com.kailismart.com.entity;

/**
 * Created by Administrator on 2016-08-20.
 * 职务对象 po014
 */
public class Duty extends BaseEntity {
    private String name;        //职务名称
    private String po01403;       //上级职务主管？
    private String parent;      //上级职务
    private String coding;      //职务编码，此编码非彼编码，不能作为id主键

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPo01403() {
        return po01403;
    }

    public void setPo01403(String po01403) {
        this.po01403 = po01403;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getCoding() {
        return coding;
    }

    public void setCoding(String coding) {
        this.coding = coding;
    }
}
