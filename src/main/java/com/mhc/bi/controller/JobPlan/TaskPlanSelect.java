package com.mhc.bi.controller.JobPlan;

import lombok.Data;

/**
 * @author baiyan
 * @date 2018/11/01
 * @description formbean
 */
@Data
public class TaskPlanSelect {
    private Integer pageSize;
    private Integer pageNo;
    private  String fileName;
    private Integer timeSortType;

}
