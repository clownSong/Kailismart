package com.kailismart.com.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016-08-02.
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 616445466042862696L;
    private String ID;      //对象ID

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
