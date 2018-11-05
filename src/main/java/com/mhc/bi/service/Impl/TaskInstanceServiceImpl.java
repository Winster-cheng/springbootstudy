package com.mhc.bi.service.Impl;

import com.mhc.bi.Utils.Algorithm;
import com.mhc.bi.Utils.GetTime;
import com.mhc.bi.common.ActionResult;
import com.mhc.bi.common.JDBC;
import com.mhc.bi.domain.theadvisor.JobPlan;
import com.mhc.bi.domain.theadvisor.TaskInstance;
import com.mhc.bi.mapper.theadvisor.TaskInstanceMapper;
import com.mhc.bi.service.JobPlanService;
import com.mhc.bi.service.TaskInstanceService;
import com.mhc.bi.vo.PageMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.*;

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
    public TaskInstance selectStartNode(String day) {
        return taskInstanceMapper.selectStartNode(day);
    }

    @Override
    public TaskInstance selectByTimeAndOutput(String day, String output) {
        return taskInstanceMapper.selectByTimeAndOutput(day, output);
    }

    @Override
    public int insertTaskInstance(TaskInstance taskInstances) {
        return taskInstanceMapper.insertIntoTaskInstance(taskInstances, GetTime.getTimeWithMysqlFormat());
    }

    @Override
    public void createTaskInstance() {
        TaskInstance taskInstance;
        List<JobPlan> list = jobPlanService.selectAll();
        String day = GetTime.getTimeStamp("yyyyMMdd", +1); //每天我们生成的是第二天的任务
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
                taskInstance.setShellName(jobPlan.getShellName());
                taskInstance.setOutput(jobPlan.getOutput() + "_" + time);
                taskInstance.setStatus(1);
                taskInstance.setExecuteTime(time);
                taskInstance.setExecuteDay(day);
                taskInstance.setParaments(jobPlan.getParaments());
                taskInstance.setOwner(jobPlan.getOwner());
                taskInstance.setType(jobPlan.getType());
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
    public List<TaskInstance> selectOutNode(String input, String day) {
        return taskInstanceMapper.selectOutNode(input, day);
    }

    @Override
    public int updateStatus(TaskInstance taskInstance) {
        return taskInstanceMapper.updateStatus(taskInstance, GetTime.getTimeStamp("yyyyMMddHHmmss"));
    }

    @Override
    public PageMessage select(int pageSize, int pageNo, String date, String fileName, int status, int timingSortType, int bussinessDateSortType, int startTimeSortType) {
        int totalCount = taskInstanceMapper.getTotalCount();
        PageMessage pageMessage = new PageMessage();
        pageMessage.setTotalPage(totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1);
        pageMessage.setPageNo(pageNo);
        pageMessage.setPageSize(pageSize);
        pageMessage.setTotalCount(totalCount);
        return null;
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
            JobPlan parentJobPlan = jobPlanService.selectJobPlanByOutput(in);
            if (Arrays.asList(parentJobPlan.getExecuteTime().split(",")).contains(time)) {//如果父节点包含子节点（taskinstance）的执行时间
                if (sb.toString().length() > 0) {
                    sb.append("," + parentJobPlan.getOutput() + "_" + time);
                } else {
                    sb.append(parentJobPlan.getOutput() + "_" + time);
                }
            } else {//如果父节点不包含子节点（taskinstance）的执行时间，我们取最近的时间
                if (sb.toString().length() > 0) {
                    sb.append("," + parentJobPlan.getOutput() + "_" + Algorithm.getSimulateNumber(Integer.parseInt(time), changeStringToInt(parentJobPlan.getExecuteTime().split(","))));
                } else {
                    sb.append(parentJobPlan.getOutput() + "_" + Algorithm.getSimulateNumber(Integer.parseInt(time), changeStringToInt(parentJobPlan.getExecuteTime().split(","))));
                }
            }
        }
        return sb.toString();
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


    //给接口3.2返回的List<TaskInstance>
    public List<TaskInstance> selectByPage(int pageSize, int pageNo, String date, String fileName, int[] status, String sortName, String sortType) {
        int start = (pageNo - 1) * pageSize;
//        taskInstanceService
        return null;
    }

    //输入id,返回父节点List
    public List<TaskInstance> getParentListById(int id) {
        List<TaskInstance> taskInstanceList = new ArrayList<>();
        String input = taskInstanceMapper.selectInputById(id);
        if (input == null) { //意味着这个节点是最高的，没有父节点
            return taskInstanceList;
        }
        String[] in = input.split(",");
        for (String i : in) {
            taskInstanceList.add(this.getTaskInstanceByInput(i));
        }
        return taskInstanceList;
    }


    //输入output,获取output=该值的对象
    public TaskInstance getTaskInstanceByInput(String output) {
        return taskInstanceMapper.getTaskInstanceByOutput(output);
    }


    //输入id,返回子节点List
    public List<TaskInstance> getChildrenListById(int id) {
        List<TaskInstance> taskInstanceList = new ArrayList<>();
        String output = taskInstanceMapper.selectOutputById(id);
        return getTaskInstanceByOutput(output);
    }


    //输入key,获取数据库中字段input=该值/包含该值的对象
    public List<TaskInstance> getTaskInstanceByOutput(String key) {
        List<TaskInstance> taskInstanceList = new ArrayList<>();
        List<TaskInstance> taskInstanceList2 = taskInstanceMapper.getTaskInstanceByInput(key);
        for (TaskInstance taskInstance : taskInstanceList2) { //对每个都进行筛选
            String in[] = taskInstance.getInput().split(",");
            for (String i : in) {
                if (i.equals(key)) {
                    taskInstanceList.add(taskInstance);
                    continue;
                }
            }
        }
        return taskInstanceList;
    }

    @Override
    public TaskInstance selectTaskInstanceById(int id) {
        return taskInstanceMapper.getTaskInstanceById(id);
    }

    @Override
    public int getTotalCountByDate(String date) {
        return taskInstanceMapper.getTotalCountByDate(date);
    }

    @Override
    public int getTotalCountByFileName(String name) {
        return taskInstanceMapper.getTotalCountByFileName(name);
    }

    @Override
    public int getTotalCountByStatus(int[] status) {
        StringBuffer sb = new StringBuffer();//拼接数组
        sb.append("(");
        int count = 0;
        for (int i : status) {
            if (count == 0) sb.append(" " + i);
            else sb.append("," + i);
            count++;
        }
        sb.append(")");

        JDBC jdbc = new JDBC();
        ActionResult actionResult = new ActionResult();
        try {
            count = jdbc.getTotalCountByStatus("select count(*) from taskinstance where status in " + sb.toString() + ";");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int getTotalCount() {
        return taskInstanceMapper.getTotalCount();
    }


}
