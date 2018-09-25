package com.mhc.bi.service;

import com.mhc.bi.domain.HueShell;

import java.util.List;

/**
 * @author baiyan
 * @date 2018/09/17
 * @description
 */

public interface HueShellService  {
    public List<HueShell> selectAll();
    public int insert(HueShell hueShell);
    public HueShell selectByName(String name);
    public String submit(String name);
}
