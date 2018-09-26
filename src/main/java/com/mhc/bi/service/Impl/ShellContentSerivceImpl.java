package com.mhc.bi.service.Impl;

import com.mhc.bi.Utils.GetTime;
import com.mhc.bi.domain.ShellContent;
import com.mhc.bi.mapper.theadvisor.ShellContentMapper;
import com.mhc.bi.service.ShellContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author baiyan
 * @date 2018/09/14
 * @description
 */
@Service
public class ShellContentSerivceImpl implements ShellContentService {
    @Autowired
    private ShellContentMapper shellContentMapper;

    public int addShellContent(ShellContent content) {
        shellContentMapper.insertIntoShellContent(content, GetTime.getTimeStamp("yyyyMMddhhmm"));
        return content.getId();
    }

    @Override
    public int insertShellContent(ShellContent content) {
        return  shellContentMapper.insertIntoShellContent(content, GetTime.getTimeStamp("yyyyMMddhhmm"));
    }

    @Override
    public List<ShellContent> selectShellContent() {
        return shellContentMapper.getAll();
    }

    @Override
    public int updateShellContent(ShellContent content) {
        return shellContentMapper.update(content);
    }

    @Override
    public int deleteShellContent(ShellContent content) {
        return shellContentMapper.delete(content.getShellName());
    }

    @Override
    public ShellContent selectByName(String shellName) {
        return shellContentMapper.selectByName(shellName);
    }
}