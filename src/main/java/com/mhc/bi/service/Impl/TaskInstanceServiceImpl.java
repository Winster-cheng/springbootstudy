package com.mhc.bi.service.Impl;

import com.mhc.bi.Utils.GetTime;
import com.mhc.bi.domain.JobPlan;
import com.mhc.bi.domain.TaskInstance;
import com.mhc.bi.mapper.theadvisor.TaskInstanceMapper;
import com.mhc.bi.service.JobPlanService;
import com.mhc.bi.service.TaskInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author baiyan
 * @date 2018/09/16
 * @description
 */
@Service
public class TaskInstanceServiceImpl implements TaskInstanceService {

    @Autowired
    TaskInstanceMapper taskInstanceMapper;

    @Autowired
    private JobPlanService jobPlanService;

    @Autowired
    private TaskInstanceService taskInstanceService;


    @Override
    public int updateTaskInstance(TaskInstance taskInstances) {
        return taskInstanceMapper.update(taskInstances, new SimpleDateFormat("yyyyMMddhhmm").format(new Date()));
    }

    @Override
    public int deleteTaskInstance(TaskInstance taskInstance) {
        return taskInstanceMapper.delete(taskInstance);
    }

    @Override
    public List<TaskInstance> selectAll() {
        return taskInstanceMapper.selectAll();
    }

    @Override
    public List<TaskInstance> selectStartNode(String day) {
        return taskInstanceMapper.selectStartNode(day);
    }

    @Override
    public TaskInstance selectByTimeAndName(String day, String name) {
        return taskInstanceMapper.selectByTimeAndName(day, name);
    }

    @Override
    public int insertTaskInstance(TaskInstance taskInstances) {
        return taskInstanceMapper.insertIntoTaskInstance(taskInstances, GetTime.getTimeStamp("yyyyMMddhhmm"));
    }

    @Override
    public void createTaskInstance() {
        TaskInstance taskInstance;
        System.out.println("Strating create TaskInstance！！！");
        List<JobPlan> list = jobPlanService.selectAll();
        String day = new SimpleDateFormat("yyyyMMdd").format(new Date());
        for (JobPlan jobPlan : list) {
            //每个时间点都生成一个执行实例
            for (String time : jobPlan.getExecuteTime().split(",")) {
                taskInstance = new TaskInstance();
                taskInstance.setName(jobPlan.getName() + "_" + time);
                try {
                    //如果不存在父节点就不设置
                    if (jobPlan.getInput() != null)
                        taskInstance.setInput(getTaskInstanceInput(jobPlan, time));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (jobPlan.getOutput() != null)
                    taskInstance.setOutput(jobPlan.getOutput() + "_" + time);
                taskInstance.setStatus(1);
                taskInstance.setExecuteTime(time);
                taskInstance.setExecuteDay(day);
                taskInstance.setParaments(jobPlan.getParaments());
                taskInstanceService.insertTaskInstance(taskInstance); //注入到数据库中
            }
        }
    }

    @Override
    public void deleteAll() {
        taskInstanceMapper.deleteAll();
    }


    @Override
    public List<TaskInstance> selectByExecuteTime(String time) {
        return taskInstanceMapper.selectByExecuteTime(time);
    }

    @Override
    public List<TaskInstance> selectOutNode(String input,String day) {
        return taskInstanceMapper.selectOutNode(input,day);
    }

    @Override
    public int updateStatus(TaskInstance taskInstance) {
        return taskInstanceMapper.updateStatus(taskInstance,GetTime.getTimeStamp("yyyyMMddHHmmss"));
    }

    /**
     * @描述 输入JobPlan，返回这个任务对应的TaskInstance的input ，比如taskinstance 中name=task_2,input可能为task1_1或者task_2
     * @参数 JobPlan
     * @返回值 String input;
     * @创建人 baiyan
     * @创建时间 2018/9/21
     * @修改人和其它信息
     */
    public String getTaskInstanceInput(JobPlan jobPlan, String time) throws Exception {
        StringBuffer sb = new StringBuffer();
        String[] inputs;
        inputs = jobPlan.getInput().split(",");
        for (String in : inputs) {  //遍历父节点，每个父节点生成相应的父节点_hour
            JobPlan parentJobPlan = jobPlanService.selectJobPlan(in);
            System.out.println("parentJobPlan.getExecuteTime(" + in + "):" + parentJobPlan.getExecuteTime());
            if (Arrays.asList(parentJobPlan.getExecuteTime().split(",")).contains(time)) {//如果父节点包含子节点（taskinstance）的执行时间
                if (sb.toString().length() > 0) {
                    sb.append("," + parentJobPlan.getName() + "_" + time);
                } else {
                    sb.append(parentJobPlan.getName() + "_" + time);
                }
            } else {//如果父节点不包含子节点（taskinstance）的执行时间，我们取最近的时间
                if (sb.toString().length() > 0) {
                    sb.append("," + parentJobPlan.getName() + "_" + getCloseTime(changeStringToInt(parentJobPlan.getExecuteTime().split(",")), Integer.parseInt(time)));
                } else {
                    sb.append(parentJobPlan.getName() + "_" + getCloseTime(changeStringToInt(parentJobPlan.getExecuteTime().split(",")), Integer.parseInt(time)));
                }
            }
        }
        System.out.println("getTaskInstanceInput返回值是" + sb.toString());
        return sb.toString();
    }

    /**
     * @描述 输入a数组，数字b,一直遍历a,直到返回和b最近的数字（<=b）
     * @参数 int[] a,int b
     * @返回值 int c(c<=2)
     * @创建人 baiyan
     * @创建时间 2018/9/21
     * @修改人和其它信息
     */
    public int getCloseTime(int[] a, int b) throws Exception {

        for (int g : a) {
            if (g == b) {
                return g;
            } else {
                return getCloseTime(a, b--);
            }
        }
        throw new Exception("在a数组找不到小于d的值，这个判断记得移到提交环节");
    }

    /**
     * @描述 转String数组为int数组
     * @参数 String[]
     * @返回值 int[]
     * @创建人 baiyan
     * @创建时间 2018/9/21
     * @修改人和其它信息
     */
    public int[] changeStringToInt(String s[]) {
        int length = s.length;
        int[] r = new int[length];
        for (int j = 0; j < length; j++) {
            r[j] = Integer.parseInt(s[j]);
        }
        return r;
    }


    /**
     * @描述 把input和name倒过来生成Map<input:name>，就成了我们要的Map<任务名:对应的output>
     * @参数 List<JobPlan> list
     * @返回值 Map<任务名:对应的output>
     * @创建人 baiyan
     * @创建时间 2018/9/17
     * @修改人和其它信息
     */
    public HashMap<String, String> getOutput(List<JobPlan> list) {
        HashMap<String, String> map = new HashMap();
        for (JobPlan jobPlan : list) {
            if (map.get(jobPlan.getInput()) == null)
                map.put(jobPlan.getInput(), jobPlan.getName());
            else {
                String before = map.get(jobPlan.getInput());
                map.put(jobPlan.getInput(), before + "," + jobPlan.getName());
            }
        }
        return map;
    }
}
