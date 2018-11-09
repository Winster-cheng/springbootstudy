package com.mhc.bi.mapper.theadvisor;

import com.mhc.bi.domain.theadvisor.HueShell;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author baiyan
 * @date 2018/09/17
 * @description private int id;
 * private String name;
 * private String type;
 * private String input;
 * private String gmtCreate;
 * private String gmtModify;
 * private String executeRate;
 * private String executeTime;
 * private String shellName;
 * private String shellContent;
 * private String paraments;
 */

public interface HueShellMapper {
    @Select("select * from hueshell")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "execute_time", property = "executeTime"),
            @Result(column = "name", property = "name"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modify", property = "gmtModify"),
            @Result(column = "shellcontent", property = "shellContent"),
            @Result(column = "shellname", property = "shellName"),
            @Result(column = "type", property = "type"),
            @Result(column = "input", property = "input"),
            @Result(column = "output", property = "output"),
            @Result(column = "execute_rate", property = "executeRate"),
            @Result(column = "paraments", property = "paraments"),
            @Result(column = "is_history", property = "isHistory")
    })
    List<HueShell> getAll();

    @Select("select * from hueshell")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "execute_time", property = "executeTime"),
            @Result(column = "name", property = "name"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modify", property = "gmtModify"),
            @Result(column = "shellcontent", property = "shellContent"),
            @Result(column = "shellname", property = "shellName"),
            @Result(column = "type", property = "type"),
            @Result(column = "input", property = "input"),
            @Result(column = "output", property = "output"),
            @Result(column = "execute_rate", property = "executeRate"),
            @Result(column = "paraments", property = "paraments"),
            @Result(column = "is_history", property = "isHistory")
    })
    HueShell getAllTest();

    @Select("select * from hueshell where name=#{name}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "execute_time", property = "executeTime"),
            @Result(column = "name", property = "name"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modify", property = "gmtModify"),
            @Result(column = "shellcontent", property = "shellContent"),
            @Result(column = "shellname", property = "shellName"),
            @Result(column = "type", property = "type"),
            @Result(column = "input", property = "input"),
            @Result(column = "output", property = "output"),
            @Result(column = "execute_rate", property = "executeRate"),
            @Result(column = "paraments", property = "paraments"),
            @Result(column = "is_history", property = "isHistory")
    })
    HueShell selectByName(String name);

    @Select("select * from hueshell where name=#{name} and is_history=1")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "execute_time", property = "executeTime"),
            @Result(column = "name", property = "name"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modify", property = "gmtModify"),
            @Result(column = "shellcontent", property = "shellContent"),
            @Result(column = "shellname", property = "shellName"),
            @Result(column = "type", property = "type"),
            @Result(column = "input", property = "input"),
            @Result(column = "output", property = "output"),
            @Result(column = "execute_rate", property = "executeRate"),
            @Result(column = "paraments", property = "paraments"),
            @Result(column = "is_history", property = "isHistory")
    })
    HueShell selectAliveByName(String name);

    @Insert("insert into hueshell(name,input,output,execute_time,execute_rate,shellcontent,shellname,type,gmt_create,gmt_modify,paraments,is_history) values(#{hueShell.name},#{hueShell.input},#{hueShell.output},#{hueShell.executeTime},#{hueShell.executeRate},#{hueShell.shellContent},#{hueShell.shellName},#{hueShell.type},#{createTime},#{createTime},#{hueShell.paraments},#{hueShell.isHistory})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(@Param("hueShell") HueShell hueShell, @Param("createTime") String createTime);


    @Update("update hueshell set is_history=0 where id=#{id}")
    int updateHistory(HueShell hueShell);

    @Select("select * from hueshell where output=#{output} and is_history=1")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "execute_time", property = "executeTime"),
            @Result(column = "name", property = "name"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modify", property = "gmtModify"),
            @Result(column = "shellcontent", property = "shellContent"),
            @Result(column = "shellname", property = "shellName"),
            @Result(column = "type", property = "type"),
            @Result(column = "input", property = "input"),
            @Result(column = "output", property = "output"),
            @Result(column = "execute_rate", property = "executeRate"),
            @Result(column = "paraments", property = "paraments"),
            @Result(column = "is_history", property = "isHistory")
    })
    HueShell selectByOutput(String output);

}