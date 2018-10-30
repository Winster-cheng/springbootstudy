package com.mhc.bi.service.Impl;

import com.mhc.bi.Utils.GetTime;
import com.mhc.bi.domain.theadvisor.JobPlan;
import com.mhc.bi.mapper.theadvisor.JobPlanMapper;
import com.mhc.bi.service.JobPlanService;
import com.mhc.bi.vo.PageMessage;
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

    //对应接口文档2.1 搜索功能
    @Override
    public PageMessage select(int pageSize, int pageNo, String fileName, int orderByModifyTime) {
        int totalCount=jobPlanMapper.getNumbers();
        PageMessage pageMessage = new PageMessage();
        List<JobPlan> jobPlanList;
        if (fileName.equals("")) {
            if (orderByModifyTime == 1)
                jobPlanList = jobPlanMapper.getJobPlanListOrderByModifyAscLimit((pageNo - 1) * pageSize, pageSize);
            else if (orderByModifyTime == 2)
                jobPlanList = jobPlanMapper.getJobPlanListOrderByModifyDescLimit((pageNo - 1) * pageSize, pageSize);
            else
                jobPlanList = jobPlanMapper.getJobPlanListLimit((pageNo - 1) * pageSize, pageSize);
        } else {
            if (orderByModifyTime == 1) {
                jobPlanList = jobPlanMapper.getJobPlanListOrderByModifyNameLikeAscLimit(fileName, (pageNo - 1) * pageSize, pageSize);
            } else if (orderByModifyTime == 2) {
                jobPlanList = jobPlanMapper.getJobPlanListOrderByModifyNameLikeDescLimit(fileName, (pageNo - 1) * pageSize, pageSize);
            } else {
                jobPlanList = jobPlanMapper.selectByNameLike(fileName, (pageNo - 1) * pageSize, pageSize);
            }
        }
        pageMessage.setList(jobPlanList);
        pageMessage.setPageNo(pageNo);
        pageMessage.setPageSize(pageSize);
        pageMessage.setTotalPage(totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1);
        pageMessage.setTotalCount(totalCount);
        return pageMessage;
    }

    @Override
    public JobPlan selectJobPlan(String name) {
        return jobPlanMapper.selectJobPlan(name);
    }

}