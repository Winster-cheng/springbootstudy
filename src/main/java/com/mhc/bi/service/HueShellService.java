package com.mhc.bi.service;

import com.mhc.bi.domain.theadvisor.HueShell;

import java.util.List;

/**
 * @author baiyan
 * @date 2018/09/17
 * @description
 */

public interface HueShellService {
    public List<HueShell> selectAll();

    public int insert(HueShell hueShell);

    public HueShell selectByName(String name);

    public boolean submit(String name);

    public HueShell selectAliveByName(String name);

    /**
     * @描述 如果这个对象存在了，那么把旧对象的is_history改成0，这个版本的改成1
     * @参数
     * @返回值
     * @创建人 baiyan
     * @创建时间 2018/9/27
     * @修改人和其它信息
     */
    public int insertOrAddNewVersion(HueShell hueShell);


    /**
     *@描述 根据output和is_history=1查询hueshell对象
     *@参数 output
     *@返回值 HueShell
     *@创建人  baiyan
     *@创建时间  2018/9/27
     *@修改人和其它信息
     */
    public HueShell selectByOutput(String name);

    /**
     *@描述 返回HueShell的所有一级目录
     *@参数
     *@返回值
     *@创建人  baiyan
     *@创建时间  2018/10/22
     *@修改人和其它信息
     */
    public List<String>  getDirectory();
}
