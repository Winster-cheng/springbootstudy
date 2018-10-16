package com.mhc.bi.mapper.theadvisor;

import com.mhc.bi.domain.theadvisor.TaskInstance;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author baiyan
 * @date 2018/09/14
 * @description private String name;
 * private String input;
 * private String output;
 * private String gmtCreate;
 * private String gmtModify;
 * private int status;
 * private String executeTime;
 * private String executeDay;
 * private String paraments;
 */
public interface TaskInstanceMapper {
    @Insert("insert into taskinstance(name,shellname,input,output,gmt_create,status,execute_time,execute_day,paraments) values(#{taskInstance.name},#{taskInstance.shellName},#{taskInstance.input},#{taskInstance.output},#{createTime},#{taskInstance.status},#{taskInstance.executeTime},#{taskInstance.executeDay},#{taskInstance.paraments})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertIntoTaskInstance(@Param("taskInstance") TaskInstance taskInstance, @Param("createTime") String createTime);

    @Select("select * from taskinstance")
    List<TaskInstance> selectAll();

    @Select("select * from taskinstance where execute_day=#{time}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "shellname", property = "shellName"),
            @Result(column = "input", property = "input"),
            @Result(column = "output", property = "output"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modify", property = "gmtModify"),
            @Result(column = "status", property = "status"),
            @Result(column = "execute_time", property = "executeTime"),
            @Result(column = "execute_day", property = "executeDay"),
            @Result(column = "paraments", property = "paraments")
    })
    List<TaskInstance> selectByExecuteTime(String time);


    @Select("select * from taskinstance where execute_day=#{day} and output=#{output}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "shellname", property = "shellName"),
            @Result(column = "input", property = "input"),
            @Result(column = "output", property = "output"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modify", property = "gmtModify"),
            @Result(column = "status", property = "status"),
            @Result(column = "execute_time", property = "executeTime"),
            @Result(column = "execute_day", property = "executeDay"),
            @Result(column = "paraments", property = "paraments")
    })    TaskInstance selectByTimeAndOutput(@Param("day") String day, @Param("output") String name);

    @Select("select * from taskinstance where execute_day=#{day} and output=#{name}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "shellname", property = "shellName"),
            @Result(column = "input", property = "input"),
            @Result(column = "output", property = "output"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modify", property = "gmtModify"),
            @Result(column = "status", property = "status"),
            @Result(column = "execute_time", property = "executeTime"),
            @Result(column = "execute_day", property = "executeDay"),
            @Result(column = "paraments", property = "paraments")
    })
    TaskInstance selectByTimeAndOutputName(@Param("day") String day, @Param("name") String name);

    @Select("select * from taskinstance where name=\"project_etl_start\" and execute_day=#{execute_day} ")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "shellname", property = "shellName"),
            @Result(column = "input", property = "input"),
            @Result(column = "output", property = "output"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modify", property = "gmtModify"),
            @Result(column = "status", property = "status"),
            @Result(column = "execute_time", property = "executeTime"),
            @Result(column = "execute_day", property = "executeDay"),
            @Result(column = "paraments", property = "paraments")
    })
    TaskInstance selectStartNode(String day);


    @Select("select * from taskinstance where input=#{input} and execute_day=#{day}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "shellname", property = "shellName"),
            @Result(column = "input", property = "input"),
            @Result(column = "output", property = "output"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modify", property = "gmtModify"),
            @Result(column = "status", property = "status"),
            @Result(column = "execute_time", property = "executeTime"),
            @Result(column = "execute_day", property = "executeDay"),
            @Result(column = "paraments", property = "paraments")
    })
    List<TaskInstance> selectOutNode(@Param("input") String input, @Param("day") String day);

    @Delete("delete from taskinstance where name=#{name}")
    int delete(TaskInstance taskInstance);

    @Update("update taskinstance set input=#{input},output=#{output},gmt_create=#{gmtCreate},status=#{status},execute_time=#{executeTime},execute_day=#{executeDay},paraments=#{paraments} where name=#{name}")
    int update(TaskInstance taskInstance, String gmtCreate);

    @Delete("delete from taskinstance")
    int deleteAll();

    @Update("update taskinstance set status=#{taskInstance.status},start_time=#{taskInstance.startTime},end_time=#{taskInstance.endTime},gmt_modify=#{gmtModify} where name=#{taskInstance.name} and execute_day=#{taskInstance.executeDay}")
    int updateStatus(@Param("taskInstance") TaskInstance taskInstance, @Param("gmtModify") String gmtModify);
}