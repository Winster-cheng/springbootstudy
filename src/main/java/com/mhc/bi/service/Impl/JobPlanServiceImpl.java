package com.mhc.bi.service.Impl;

import com.mhc.bi.Utils.GetTime;
import com.mhc.bi.domain.theadvisor.JobPlan;
import com.mhc.bi.mapper.theadvisor.JobPlanMapper;
import com.mhc.bi.service.JobPlanService;
import com.mhc.bi.vo.PageMessage;
import com.mhc.bi.vo.taskplan.JobPlanView;
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
      return   jobPlanMapper.getNumbersByName(name);
    }

    //对应接口文档2.1 搜索功能
        @Override
        public List<JobPlan> selectJobPlanListByPage ( int pageSize, int pageNo, String fileName,int orderByModifyTime){
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
        public JobPlan selectJobPlan (String name){
            return jobPlanMapper.selectJobPlan(name);
        }

    }