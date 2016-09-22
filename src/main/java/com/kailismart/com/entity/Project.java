package com.kailismart.com.entity;

/**
 * Created by Administrator on 2016-08-04.
 * 项目类
 */
public class Project extends BaseEntity {
    private String name;        //项目名称  pa00102
    private String managerName; //负责人   pa00112
    private String owner;       //业主单位ID    pa00105
    private String construction;    //建设单位  pa00158

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getConstruction() {
        return construction;
    }

    public void setConstruction(String construction) {
        this.construction = construction;
    }
}
