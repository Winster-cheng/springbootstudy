package com.mhc.bi.service;

import com.mhc.bi.domain.TaskInstance;

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

    public List<TaskInstance> selectStartNode(String time);

    public TaskInstance selectByTimeAndName(String day, String name);

    public void createTaskInstance();

    public void deleteAll();

    public List<TaskInstance> selectByExecuteTime(String day);

    public List<TaskInstance> selectOutNode(String input, String day);

    public int updateStatus(TaskInstance taskInstance);
}
