package com.mhc.bi.vo.taskplan;

import java.util.List;

/**
 * @author baiyan
 * @date 2018/10/30
 * @description 这个类用来提供给 2.2 返回依赖图 2.3点击+号使用
 */
public class JobPlanView {
    private int id;
    private String name;
    private boolean hasParent;
    private boolean hasChildre;
    private List<Integer> parentList;
    private List<Integer> childrenList;
    private  boolean isTop;
    private  List<JobPlanView> list;

}
