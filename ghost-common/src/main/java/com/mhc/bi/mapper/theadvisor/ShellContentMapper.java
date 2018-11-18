package com.mhc.bi.mapper.theadvisor;

import com.mhc.bi.domain.theadvisor.ShellContent;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author baiyan
 * @date 2018/09/14
 * @description 脚本名称

private int id;
private String shellName;
private String shellContent;
private String shellType;
private String gmtCreate;
private String gmtModify;
 */
public interface ShellContentMapper {

    @Insert("insert into shellcontent(shellname,shelltype,shellcontent,gmt_create,gmt_modify) values(#{shellContent.shellName},#{shellContent.shellType},#{shellContent.shellContent},#{createTime},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertIntoShellContent(@Param("shellContent") ShellContent shellContent, @Param("createTime") String createTime);

    @Select("select * from shellcontent")
    @Results({
            @Result(column = "shellname", property = "shellName"),
            @Result(column = "shelltype", property = "shellType"),
            @Result(column = "shellcontent", property = "shellContent"),
            @Result(column = "gmt_modify",property = "gmtModify"),
            @Result(column = "gmt_create",property = "gmtCreate")
    })
    List<ShellContent> getAll();

    @Delete("delete from shellcontent where shellname=#{shellnName}")
    int delete(String shellName);

    @Update("update shellcontent set  shellcontent=#{shellContent.shellContent},shelltype=#{shellContent.shellType},gmt_modify=#{shellContent.gmtModify} where shellname=#{shellContent.shellName}")
    int update(@Param("shellContent")ShellContent shellcontent);

    @Select("select * from shellcontent where shellname=#{shellname}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "shellname", property = "shellName"),
            @Result(column = "shelltype", property = "shellType"),
            @Result(column = "shellcontent", property = "shellContent"),
            @Result(column = "gmt_modify",property = "gmtModify"),
            @Result(column = "gmt_create",property = "gmtCreate")
    })
    ShellContent selectByName(String shellname);

}