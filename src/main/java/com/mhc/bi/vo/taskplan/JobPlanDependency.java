package com.mhc.bi.vo.taskplan;

import java.util.List;

/**
 * @author baiyan
 * @date 2018/10/31
 * @description 2.2接口使用 点击任务名返回依赖图
 */
public class JobPlanDependency {
    private int id;
    private String name;
    private boolean hasParent;
    private boolean hasChildren;
    List<Integer> parentList;
    List<Integer> childrenList;
}
