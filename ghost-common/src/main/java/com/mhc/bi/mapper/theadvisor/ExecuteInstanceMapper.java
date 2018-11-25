package com.mhc.bi.mapper.theadvisor;

import com.mhc.bi.domain.theadvisor.ExecuteInstance;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;


/**
 * @author baiyan
 * @date 2018/09/19
 * @description private int id;
 * private int parentId;
 * private String startTime;
 * private String endTime;
 * private String gmtCreate;
 * private String gmtModify;
 * private int status;
 * private String longLink;
 */
public interface ExecuteInstanceMapper {
    @Insert("insert into executeinstance (parent_id,start_time,end_time,gmt_create,gmt_modify,status,log_link) values(#{executeInstance.parentId},#{executeInstance.startTime},#{executeInstance.endTime},#{createTime},#{createTime},#{executeInstance.status},#{executeInstance.logLink})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(@Param("executeInstance") ExecuteInstance executeInstance, @Param("createTime") String createTime);
}