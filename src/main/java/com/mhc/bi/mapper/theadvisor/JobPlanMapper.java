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
    @Insert("insert into jobplan(name,input,execute_rate,execute_time,output,paraments,gmt_create,gmt_modify,shellname) values(#{jobPlan.name},#{jobPlan.input},#{jobPlan.executeRate},#{jobPlan.executeTime},#{jobPlan.output},#{jobPlan.paraments},#{createTime},#{createTime},#{jobPlan.shellName})")
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
            @Result(column = "owner", property = "owner")
    })
    List<JobPlan> getAll();

    @Delete("delete from jobplan where name=#{name}")
    int delete(String name);

    @Update("update jobplan set  input=#{input},execute_rate=#{executeRate},execute_time=#{executeTime},output=#{output},paraments=#{paraments},gmt_create=#{gmtCreate}, gmt_modify=#{gmtModify}where name=#{name}")
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
}
