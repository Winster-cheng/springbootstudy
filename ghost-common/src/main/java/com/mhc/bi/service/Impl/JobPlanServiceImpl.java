package com.mhc.bi.service.Impl;

import com.mhc.bi.Utils.GetTime;
import com.mhc.bi.domain.theadvisor.HueShell;
import com.mhc.bi.domain.theadvisor.JobPlan;
import com.mhc.bi.mapper.theadvisor.JobPlanMapper;
import com.mhc.bi.service.JobPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baiyan
 * @date 2018/09/16
 * @description
 */
@Service
public class JobPlanServiceImpl implements JobPlanService {

    @Autowired
    JobPlanMapper jobPlanMapper;

    @Override
    public List<JobPlan> selectAll() {
        return jobPlanMapper.getAll();
    }

    @Override
    public int update(JobPlan jobPlan) {
        jobPlan.setGmtModify(GetTime.getTimeWithMysqlFormat());
        return jobPlanMapper.update(jobPlan);
    }

    @Override
    public int delete(JobPlan jobPlan) {
        return jobPlanMapper.delete(jobPlan.getName());
    }

    @Override
    public int insert(JobPlan jobPlan) {
        return jobPlanMapper.insertIntoJobPlan(jobPlan, GetTime.getTimeWithMysqlFormat());
    }


    @Override
    public JobPlan selectJobPlanByOutput(String output) {
        return jobPlanMapper.selectJobPlanByOutput(output);
    }

    //获取JobPlan的总条数
    @Override
    public int getNumbers() {
        return jobPlanMapper.getNumbers();
    }

    @Override
    public int getNumbersByName(String name) {
        return jobPlanMapper.getNumbersByName(name);
    }


    @Override
    public String getOutputById(int id) {
        return jobPlanMapper.getOutputById(id);
    }

    @Override
    public String getInputById(int id) {
        return jobPlanMapper.getInputById(id);
    }

    @Override
    public List<Integer> getParentIdByJobPlan(JobPlan jobPlan) {
        return getParentIdById(jobPlan.getId());
    }

    @Override
    public List<Integer> getParentIdById(int id) {
        String x = jobPlanMapper.getInputById(id);
        if (x == null) return new ArrayList<>();
        String parents[] = x.split(",");
        List<Integer> list = new ArrayList<>();
        for (String p : parents) {
            list.add(jobPlanMapper.getParentId(p));
        }
        return list;
    }

    @Override
    public List<Integer> getChildrenIdByJobPlan(JobPlan jobPlan) {
        return getChildrenIdById(jobPlan.getId());
    }

    //输入id，返回这个JobPlan的子节点的id
    @Override
    public List<Integer> getChildrenIdById(int id) {
        List<JobPlan> jobPlanList = this.getChildrenList(id);
        List<Integer> jobPlanIdList = new ArrayList<>();
        for (JobPlan jobPlan : jobPlanList) {
            jobPlanIdList.add(jobPlan.getId());
        }
        return jobPlanIdList;
    }

    @Override
    public JobPlan getJobPlanById(int id) {
        return jobPlanMapper.getJobPlanById(id);
    }

    @Override
    public boolean isExit(String name) {
        if (this.selectJobPlan(name) != null) return true;
        return false;
    }

    @Override
    public JobPlan getJobPlanFromHueShell(HueShell hueShell) {
        return new JobPlan(hueShell.getName(), hueShell.getInput(), hueShell.getShellName().replaceAll(".bi", ""), hueShell.getOutput(), hueShell.getExecuteRate(), hueShell.getExecuteTime(), hueShell.getParaments(), GetTime.getTimeStamp("yyyyMMdd"), hueShell.getOwner(), hueShell.getType());

    }

    //输入id,返回父节点列表
    @Override
    public List<JobPlan> getParentList(int id) {
        List<JobPlan> jobPlanList = new ArrayList<>();
        String input = this.getInputById(id);
        if (!(input == null)) { //考虑到最顶上到节点
            String[] in = input.split(",");
            JobPlan jobPlan;
            for (String o : in) {
                jobPlan = jobPlanMapper.getJobPlanByOutput(o);
                jobPlanList.add(jobPlan);
            }
        }
        return jobPlanList;
    }

    @Override
    public List<JobPlan> selectJobPlanListByPage(int pageSize, int pageNo, String fileName, int orderByModifyTime) {
        int start = (pageNo - 1) * pageSize;
        List<JobPlan> jobPlanList;
        if (fileName.equals("")) {
            if (orderByModifyTime == 1)
                //把JobPlanList包装成JobPlanViewList
                jobPlanList = jobPlanMapper.getJobPlanListOrderByModifyAscLimit(start, pageSize);
            else if (orderByModifyTime == 2)
                jobPlanList = jobPlanMapper.getJobPlanListOrderByModifyDescLimit(start, pageSize);
            else
                jobPlanList = jobPlanMapper.getJobPlanListLimit(start, pageSize);
        } else {
            if (orderByModifyTime == 1) {
                jobPlanList = jobPlanMapper.getJobPlanListOrderByModifyNameLikeAscLimit(fileName, start, pageSize);
            } else if (orderByModifyTime == 2) {
                jobPlanList = jobPlanMapper.getJobPlanListOrderByModifyNameLikeDescLimit(fileName, start, pageSize);
            } else {
                jobPlanList = jobPlanMapper.selectByNameLike(fileName, start, pageSize);
            }
        }
        return jobPlanList;
    }

    @Override
    public JobPlan selectJobPlan(String name) {
        return jobPlanMapper.selectJobPlan(name);
    }

    //输入ID，返回子节点JobPlan列表
    @Override
    public List<JobPlan> getChildrenList(int id) {
        List<JobPlan> childrenList = new ArrayList<>();
        String output = jobPlanMapper.getOutputById(id);
        List<JobPlan> jobPlanList = jobPlanMapper.getPossibleChildrenList(output);
        for (JobPlan jobPlan : jobPlanList) {
            String input[] = jobPlan.getInput().split(",");
            for (String in : input) {
                if (in.equals(output)) {
                    childrenList.add(jobPlan);
                    continue;
                }
            }
        }
        return childrenList;
    }


}