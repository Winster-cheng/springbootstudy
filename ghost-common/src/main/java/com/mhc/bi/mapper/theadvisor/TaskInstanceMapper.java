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
    @Insert("insert into taskinstance(name,shellname,input,output,gmt_create,gmt_modify,status,execute_time,execute_day,paraments,type) values(#{taskInstance.name},#{taskInstance.shellName},#{taskInstance.input},#{taskInstance.output},#{createTime},#{createTime},#{taskInstance.status},#{taskInstance.executeTime},#{taskInstance.executeDay},#{taskInstance.paraments},#{taskInstance.type})")
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
            @Result(column = "paraments", property = "paraments"),
            @Result(column = "type", property = "type"),
            @Result(column = "owner",property = "owner"),
            @Result(column = "startTime",property = "start_time"),
            @Result(column = "endTime",property = "end_time")
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
            @Result(column = "paraments", property = "paraments"),
            @Result(column = "type", property = "type"),
            @Result(column = "owner", property = "owner")})
    TaskInstance selectByTimeAndOutput(@Param("day") String day, @Param("output") String name);

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
            @Result(column = "paraments", property = "paraments"),
            @Result(column = "type", property = "type"),
            @Result(column = "owner",property = "owner")
    })
    TaskInstance selectByTimeAndOutputName(@Param("day") String day, @Param("name") String name);

    @Select("select * from taskinstance where name=\"sync_all\" and execute_day=#{execute_day} ")
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
            @Result(column = "paraments", property = "paraments"),
            @Result(column = "type", property = "type"),
            @Result(column = "owner",property = "owner"),
            @Result(column = "startTime",property = "start_time"),
            @Result(column = "endTime",property = "end_time")
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
            @Result(column = "paraments", property = "paraments"),
            @Result(column = "type", property = "type"),
            @Result(column = "owner",property = "owner"),
            @Result(column = "startTime",property = "start_time"),
            @Result(column = "endTime",property = "end_time")
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

    @Select("select count(id) from taskinstance")
    int getTotalCount();

    @Select("select * from taskinstance where output=#{output}")
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
            @Result(column = "paraments", property = "paraments"),
            @Result(column = "type", property = "type"),
            @Result(column = "owner",property = "owner"),
            @Result(column = "startTime",property = "start_time"),
            @Result(column = "endTime",property = "end_time")
    })
    TaskInstance getTaskInstanceByOutput(String output);

    @Select("select output from taskinstance where id=#{id}")
    String selectOutputById(int id);

    @Select("select input from taskinstance where id=#{id}")
    String selectInputById(int id);

    @Select("select * from taskinstance where input like concat('%', #{key}, '%')")
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
            @Result(column = "paraments", property = "paraments"),
            @Result(column = "type", property = "type"),
            @Result(column = "owner",property = "owner"),
            @Result(column = "startTime",property = "start_time"),
            @Result(column = "endTime",property = "end_time")
    })
    List<TaskInstance> getTaskInstanceByInput(String key);

    @Select("select * from taskinstance where id=#{id}")
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
            @Result(column = "paraments", property = "paraments"),
            @Result(column = "type", property = "type"),
            @Result(column = "owner",property = "owner"),
            @Result(column = "startTime",property = "start_time"),
            @Result(column = "endTime",property = "end_time")
    })
    TaskInstance getTaskInstanceById(int id);

    @Select("select count(*) from  taskinstance where execute_day=#{date}")
    public int getTotalCountByDate(String date);

    @Select("select count(*) from taskinstance where name like  concat('%', #{name}, '%')")
    public int getTotalCountByFileName(String name);


    @Select("${_parameter}")
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
            @Result(column = "paraments", property = "paraments"),
            @Result(column = "type", property = "type"),
            @Result(column = "owner",property = "owner"),
            @Result(column = "startTime",property = "start_time"),
            @Result(column = "endTime",property = "end_time")
    })
    public List<TaskInstance> executeDefineSql(String sql);


    @Select("${_parameter}")
    public int getTotalCountByStatus(String sql);
}