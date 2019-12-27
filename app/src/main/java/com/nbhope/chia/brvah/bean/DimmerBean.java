package com.nbhope.chia.brvah.bean;

/**
 * @ClassName: DimmerBean
 * @Description: 作用描述
 * @Author: CHIA
 * @CreateDate: 2019/12/20
 */
public class DimmerBean {

    private String dimmerName;

    private String actionName;

    private boolean isSub;

    public boolean isSub() {
        return isSub;
    }

    public void setSub(boolean sub) {
        isSub = sub;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getDimmerName() {
        return dimmerName;
    }

    public void setDimmerName(String dimmerName) {
        this.dimmerName = dimmerName;
    }
}
