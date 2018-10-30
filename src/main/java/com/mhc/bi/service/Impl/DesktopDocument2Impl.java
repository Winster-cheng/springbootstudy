package com.mhc.bi.service.Impl;

import com.mhc.bi.domain.hue.DesktopDocument2;
import com.mhc.bi.vo.File;
import com.mhc.bi.mapper.hue.DesktopDocument2Mapper;
import com.mhc.bi.service.DesktopDocument2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    //获取所有id>3的目录，并且如果是文件，那么file.type改成file
    @Override
    public List<File> getDirectory() {
        List<File> directoryList = desktopDocument2Mapper.getDirectory();
        List<File> removeList = new ArrayList<>();
        for (File file : directoryList) {
            if (file.getId() > 3)
                file.setChildren(desktopDocument2Mapper.getChildrenList(file.getId()));
            else
                removeList.add(file);
            if (file.getType() != "directory") file.setType("file");

        }
        directoryList.removeAll(removeList);
        return directoryList;
    }

    //TODO 现在默认都是hiveshell类型
    @Override
    public File getContent(int id) {
        File file = desktopDocument2Mapper.getContent(id);
        file.setType("hiveshell");
        return file;
    }

    @Override
    public boolean save(int id, String content) {
        return desktopDocument2Mapper.updateContentById(id,content);
    }


}
