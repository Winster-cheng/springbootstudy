package com.mhc.bi.service;

import com.mhc.bi.domain.hue.DesktopDocument2;
import com.mhc.bi.vo.File;

import java.util.List;

/**
 * @author baiyan
 * @date 2018/09/27
 * @description
 */
public interface DesktopDocument2Service {
    public DesktopDocument2 getDesktopDocument2(String name);

    public List<File> getDirectory();

    public File getContent(int id);

    public boolean save(int id,String content);
}