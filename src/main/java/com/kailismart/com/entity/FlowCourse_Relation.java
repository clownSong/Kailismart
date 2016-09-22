package com.kailismart.com.entity;

/**
 *
 * 流程过程关系记录对象 sdpo020b_Relation
 */
public class FlowCourse_Relation extends FlowCourseRelation {
    private String flowHistoryId;       //流程记录id
    public String getFlowHistoryId() {
        return flowHistoryId;
    }

    public void setFlowHistoryId(String flowHistoryId) {
        this.flowHistoryId = flowHistoryId;
    }
}
