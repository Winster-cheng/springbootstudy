package com.mhc.bi.service.Impl;

import com.mhc.bi.Utils.GetTime;
import com.mhc.bi.domain.ExecuteInstance;
import com.mhc.bi.mapper.ExecuteInstanceMapper;
import com.mhc.bi.service.ExecuteInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author baiyan
 * @date 2018/09/19
 * @description
 */
@Service
public class ExecuteInstanceImpl implements ExecuteInstanceService {
    @Autowired
    ExecuteInstanceMapper executeInstanceMapper;

    @Override
    public int insert(ExecuteInstance executeInstance) {

        return executeInstanceMapper.insert(executeInstance,GetTime.getTimeStamp("yyyyMMddHHmmss"));
    }
}
