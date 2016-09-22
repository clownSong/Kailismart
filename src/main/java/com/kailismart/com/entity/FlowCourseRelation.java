package com.kailismart.com.entity;

/**
 * Created by Administrator on 2016-08-18.
 * 流程过程关系对象 sdpo020_Relation
 */
public class FlowCourseRelation extends BaseEntity {
    private String nextCourseId;      //下一个过程id     02
    private String currentId;           //当前过程id          03
    private String relation04 = "";          //默认空字符串，暂时不知作用
    private int relation05 = 1;         //默认为1，作用未知

    public String getNextCourseId() {
        return nextCourseId;
    }

    public void setNextCourseId(String nextCourseId) {
        this.nextCourseId = nextCourseId;
    }

    public String getCurrentId() {
        return currentId;
    }

    public void setCurrentId(String currentId) {
        this.currentId = currentId;
    }

    public String getRelation04() {
        return relation04;
    }

    public void setRelation04(String relation04) {
        this.relation04 = relation04;
    }

    public int getRelation05() {
        return relation05;
    }

    public void setRelation05(int relation05) {
        this.relation05 = relation05;
    }
}
