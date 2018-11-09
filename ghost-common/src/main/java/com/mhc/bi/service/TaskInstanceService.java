package com.mhc.bi.service;

import com.mhc.bi.domain.theadvisor.TaskInstance;
import com.mhc.bi.vo.PageMessage;

import java.util.List;

/**
 * @author baiyan
 * @date 2018/09/14
 * @description
 */
public interface TaskInstanceService {
    public int insertTaskInstance(TaskInstance taskInstance);

    public int updateTaskInstance(TaskInstance taskInstance);

    public int deleteTaskInstance(TaskInstance taskInstance);


    public List<TaskInstance> selectAll();

    public TaskInstance selectStartNode(String time);

    public TaskInstance selectByTimeAndOutput(String day, String output);

    public void createTaskInstance();

    public void deleteAll();

    public List<TaskInstance> selectByExecuteTime(String day);

    public List<TaskInstance> selectOutNode(String input, String day);

    public int updateStatus(TaskInstance taskInstance);

    public PageMessage select(int pageSize, int pageNo, String date, String fileName, int status, int timingSortType, int bussinessDateSortType, int startTimeSortType);

    public List<TaskInstance> getParentListById(int id);

    public TaskInstance getTaskInstanceByInput(String output);

    public List<TaskInstance> getChildrenListById(int id);

    public List<TaskInstance> getTaskInstanceByOutput(String key);

    public TaskInstance selectTaskInstanceById(int id);

    //获得总条数-时间筛选
    public int getTotalCountByDate(String date);

    //获得总条数-名称筛选
    public int getTotalCountByFileName(String name);

    //获得总条数-状态数组筛选
    public int getTotalCountByStatus(int[] status);

    //获得总条数-无任何筛选条件
    public int getTotalCount();

    //输入,返回父节点列表,注意这里要加一个execute_day判断
    public List<Integer> getParentIdByTaskInstance(int id);

    //输入ID，返回子节点列表，注意这里要加一个execute_day判断
    public List<Integer> getChildrenIdByTaskInstance(int id);
}
