package com.mhc.bi.common;

import lombok.Data;

/**
 * @author baiyan
 * @date 2018/11/01
 * @description
 */
@Data
public class AddParm {
    private Integer pageSize;
    private Integer pageNo;
    private  String fileName;
    private Integer timeSortType;

}
