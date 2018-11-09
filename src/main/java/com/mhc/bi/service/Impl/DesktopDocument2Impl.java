package com.mhc.bi.service.Impl;

import com.mhc.bi.domain.hue.DesktopDocument2;
import com.mhc.bi.mapper.hue.DesktopDocument2Mapper;
import com.mhc.bi.service.DesktopDocument2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author baiyan
 * @date 2018/09/27
 * @description
 */
@Service
public class DesktopDocument2Impl implements DesktopDocument2Service {
    @Autowired
    DesktopDocument2Mapper desktopDocument2Mapper;

    @Override
    public DesktopDocument2 getDesktopDocument2(String name) {
        return desktopDocument2Mapper.selectByName(name);
    }

    //获取所有的没被抛弃的desktopdocument2对象
    @Override
    public List<DesktopDocument2> getAllAlive() {
        return desktopDocument2Mapper.getAllAlive();
    }

    @Override
    public boolean save(int id, String content) {
        return desktopDocument2Mapper.updateContentById(id, content);
    }


    @Override
    public String getContent(int id) {
        return desktopDocument2Mapper.getContent(id);
    }

    @Override
    public List<Integer> getChildrenList(int id) {
        return desktopDocument2Mapper.getChildrenList(id);
    }

    @Override
    public String getNameById(int id) {
        return desktopDocument2Mapper.getNameById(id);
    }

    @Override
    public String selectNameById(int id) {
        return desktopDocument2Mapper.selectNameById(id);
    }

    @Override
    public DesktopDocument2 getDesktopDocumentById(int Id) {

        return null;
    }


}
