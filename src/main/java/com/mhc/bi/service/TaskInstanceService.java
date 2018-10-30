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

}
