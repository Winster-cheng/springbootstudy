package com.mhc.bi.mapper.theadvisor;

import com.mhc.bi.domain.theadvisor.JobPlan;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author baiyan
 * @date 2018/09/14
 * @description
 */
public interface JobPlanMapper {
    @Insert("insert into jobplan(name,input,execute_rate,execute_time,output,paraments,gmt_create,gmt_modify,shellname,type) values(#{jobPlan.name},#{jobPlan.input},#{jobPlan.executeRate},#{jobPlan.executeTime},#{jobPlan.output},#{jobPlan.paraments},#{createTime},#{createTime},#{jobPlan.shellName},#{jobPlan.type})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertIntoJobPlan(@Param("jobPlan") JobPlan jobPlan, @Param("createTime") String creatTime);

    @Select("select * from jobplan")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "input", property = "input"),
            @Result(column = "execute_rate", property = "executeRate"),
            @Result(column = "execute_time", property = "executeTime"),
            @Result(column = "output", property = "output"),
            @Result(column = "paraments", property = "paraments"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modify", property = "gmtModify"),
            @Result(column = "owner", property = "owner"),
            @Result(column = "type",property = "type")
    })
    List<JobPlan> getAll();

    @Delete("delete from jobplan where name=#{name}")
    int delete(String name);

    @Update("update jobplan set shellname=#{shellName},input=#{input},execute_rate=#{executeRate},execute_time=#{executeTime},output=#{output},paraments=#{paraments},gmt_create=#{gmtCreate}, gmt_modify=#{gmtModify},owner=#{owner},type=#{type} where name=#{name}")
    int update(JobPlan jobplan);

    @Select("select * from jobplan where name=#{name}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "input", property = "input"),
            @Result(column = "execute_rate", property = "executeRate"),
            @Result(column = "execute_time", property = "executeTime"),
            @Result(column = "output", property = "output"),
            @Result(column = "paraments", property = "paraments"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modify", property = "gmtModify"),
            @Result(column = "owner", property = "owner")
    })
    JobPlan selectJobPlan(String name);

    @Select("select * from jobplan where output=#{output}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "input", property = "input"),
            @Result(column = "execute_rate", property = "executeRate"),
            @Result(column = "execute_time", property = "executeTime"),
            @Result(column = "output", property = "output"),
            @Result(column = "paraments", property = "paraments"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modify", property = "gmtModify"),
            @Result(column = "owner", property = "owner")
    })
    JobPlan selectJobPlanByOutput(String output);


    //下面3个select负责查询name=null的时候jobplan的jobplan列表
    @Select("select * from jobplan order by gmt_modify asc limit #{start},#{pageSize}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "input", property = "input"),
            @Result(column = "execute_rate", property = "executeRate"),
            @Result(column = "execute_time", property = "executeTime"),
            @Result(column = "output", property = "output"),
            @Result(column = "paraments", property = "paraments"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modify", property = "gmtModify"),
            @Result(column = "owner", property = "owner")
    })
    List<JobPlan> getJobPlanListOrderByModifyAscLimit(@Param("start") int start, @Param("pageSize") int pageSize);

    @Select("select * from jobplan order by gmt_modify desc limit #{start},#{pageSize}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "input", property = "input"),
            @Result(column = "execute_rate", property = "executeRate"),
            @Result(column = "execute_time", property = "executeTime"),
            @Result(column = "output", property = "output"),
            @Result(column = "paraments", property = "paraments"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modify", property = "gmtModify"),
            @Result(column = "owner", property = "owner")
    })
    List<JobPlan> getJobPlanListOrderByModifyDescLimit(@Param("start") int start, @Param("pageSize") int pageSize);

    @Select("select * from jobplan  limit #{start},#{pageSize}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "input", property = "input"),
            @Result(column = "execute_rate", property = "executeRate"),
            @Result(column = "execute_time", property = "executeTime"),
            @Result(column = "output", property = "output"),
            @Result(column = "paraments", property = "paraments"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modify", property = "gmtModify"),
            @Result(column = "owner", property = "owner")
    })
    List<JobPlan> getJobPlanListLimit(@Param("start") int start, @Param("pageSize") int pageSize);


    //下面3个select负责查询name！=null的时候jobplan的jobplan列表
    @Select("select * from jobplan where name like concat('%', #{name}, '%') limit #{start},#{pageSize}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "input", property = "input"),
            @Result(column = "execute_rate", property = "executeRate"),
            @Result(column = "execute_time", property = "executeTime"),
            @Result(column = "output", property = "output"),
            @Result(column = "paraments", property = "paraments"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modify", property = "gmtModify"),
            @Result(column = "owner", property = "owner")
    })
    List<JobPlan> selectByNameLike(@Param("name") String name, @Param("start") int start, @Param("pageSize") int pageSize);

    @Select("select * from jobplan where name like concat('%', #{name}, '%') order by gmt_modify asc limit #{start},#{pageSize}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "input", property = "input"),
            @Result(column = "execute_rate", property = "executeRate"),
            @Result(column = "execute_time", property = "executeTime"),
            @Result(column = "output", property = "output"),
            @Result(column = "paraments", property = "paraments"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modify", property = "gmtModify"),
            @Result(column = "owner", property = "owner")
    })
    List<JobPlan> getJobPlanListOrderByModifyNameLikeAscLimit(@Param("name") String name, @Param("start") int start, @Param("pageSize") int pageSize);

    @Select("select * from jobplan where name like concat('%', #{name}, '%')  order by gmt_modify desc limit #{start},#{pageSize}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "input", property = "input"),
            @Result(column = "execute_rate", property = "executeRate"),
            @Result(column = "execute_time", property = "executeTime"),
            @Result(column = "output", property = "output"),
            @Result(column = "paraments", property = "paraments"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modify", property = "gmtModify"),
            @Result(column = "owner", property = "owner")
    })
    List<JobPlan> getJobPlanListOrderByModifyNameLikeDescLimit(@Param("name") String name, @Param("start") int start, @Param("pageSize") int pageSize);


    //查询总条数
    @Select("select count(*) from jobplan")
    int getNumbers();

    //查询包含某个字段的总条数
    @Select("select count(*) from jobplan where name like concat('%', #{name}, '%')")
    public int getNumbersByName(String name);

    //输入output，返回可能input包含该output的子节点列表(主要是input包含多个的时候，like不好判断)
    @Select("select * from jobplan where input like concat('%', #{name}, '%')")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "input", property = "input"),
            @Result(column = "execute_rate", property = "executeRate"),
            @Result(column = "execute_time", property = "executeTime"),
            @Result(column = "output", property = "output"),
            @Result(column = "paraments", property = "paraments"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modify", property = "gmtModify"),
            @Result(column = "owner", property = "owner")
    })
    public List<JobPlan> getPossibleChildrenList(String name);


    //根据List<output>查询所有在该output列表的jobplan
    @Select("select * from jobplan where output in #{outputList}")
    public JobPlan getListByOutput(List<String> outputList);

    //根据output查询ID
    @Select("select id from jobplan where output=#{name}")
    public int getParentId(String name);

    //根据ID获取output
    @Select("select output from jobplan where id=#{id}")
    public String getOutputById(int id);

    //根据ID获取iutput
    @Select("select input from jobplan where id=#{id}")
    public String getInputById(int id);

    //根据output获取JobPlan对象
    @Select("select * from jobplan where output=#{output}")
    public JobPlan getJobPlanByOutput(String output);

    @Select("select * from jobplan where id=#{id}")
    public JobPlan getJobPlanById(int id);
}
