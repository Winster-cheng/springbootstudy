package com.mhc.bi.controller.taskInstance.FormBean;

import lombok.Data;

/**
 * @author baiyan
 * @date 2018/11/05
 * @description
 */
@Data
public class TaskInstanceSelect {
    private int pageSize;
    private int pageNo;
    private String date;
    private String fileName;
    private int[] status;
    private String sortName;
    private int sortType;
}
