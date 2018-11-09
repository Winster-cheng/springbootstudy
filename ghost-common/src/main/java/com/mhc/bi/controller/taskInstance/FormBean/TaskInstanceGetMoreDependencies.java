package com.mhc.bi.controller.taskInstance.FormBean;

import lombok.Data;

/**
 * @author baiyan
 * @date 2018/11/05
 * @description
 */
@Data
public class TaskInstanceGetMoreDependencies {
    private int taskInstanceId;
    private boolean isTop;

    public boolean getIsTop() {
        return this.isTop;
    }

    public void setIsTop(boolean isTop) {
        this.isTop=isTop;
    }
}
