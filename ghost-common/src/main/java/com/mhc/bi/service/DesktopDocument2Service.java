package com.mhc.bi.service;

import com.mhc.bi.domain.hue.DesktopDocument2;

import java.util.List;

/**
 * @author baiyan
 * @date 2018/09/27
 * @description
 */
public interface DesktopDocument2Service {
    public DesktopDocument2 getDesktopDocument2(String name);

    public List<DesktopDocument2> getAllAlive();

    public String getContent(int id);

    public boolean save(int id, String content);


    public String selectNameById(int id);

    public DesktopDocument2 getDesktopDocumentById(int Id);

    //输入id,返回它的子目录ID
    public List<Integer> getChildrenList(int id);

//    public List<DesktopDocument2>

    public String getNameById(int id);
}