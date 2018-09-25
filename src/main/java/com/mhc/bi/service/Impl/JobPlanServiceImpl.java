package com.mhc.bi.service.Impl;

import com.mhc.bi.Utils.GetTime;
import com.mhc.bi.domain.JobPlan;
import com.mhc.bi.mapper.JobPlanMapper;
import com.mhc.bi.service.JobPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return jobPlanMapper.update(jobPlan);
    }

    @Override
    public int delete(JobPlan jobPlan) {
        return jobPlanMapper.delete(jobPlan.getName());
    }

    @Override
    public int insert(JobPlan jobPlan) {
        return jobPlanMapper.insertIntoJobPlan(jobPlan,GetTime.getTimeStamp("yyyyMMddHHmmss"));
    }

    @Override
    public JobPlan selectJobPlan(String name) {
        return jobPlanMapper.selectJobPlan(name);
    }
}