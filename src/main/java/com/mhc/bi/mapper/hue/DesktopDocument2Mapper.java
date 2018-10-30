package com.mhc.bi.mapper.hue;

import com.mhc.bi.domain.hue.DesktopDocument2;
import com.mhc.bi.vo.File;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author baiyan
 * @date 2018/09/26
 * @description
 */
@Service
public interface DesktopDocument2Mapper {
    @Select("select * from hue2.desktop_document2 where name=#{name} and is_trashed=0;")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "owner_id", property = "ownerId"),
            @Result(column = "name", property = "name"),
            @Result(column = "description", property = "description"),
            @Result(column = "uuid", property = "uuid"),
            @Result(column = "type", property = "type"),
            @Result(column = "data", property = "data"),
            @Result(column = "extra", property = "extra"),
            @Result(column = "last_modified", property = "lastModified"),
            @Result(column = "version", property = "version"),
            @Result(column = "is_history", property = "isHistory"),
            @Result(column = "parent_directory_id", property = "parentDirectoryId"),
            @Result(column = "search", property = "search"),
            @Result(column = "is_managed", property = "isManaged"),
            @Result(column = "is_trashed", property = "isTrashed")
    })
    DesktopDocument2 selectByName(String name);

    @Select("select type,id,name,parent_directory_id from hue2.desktop_document2 where is_trashed=0")
    @Results({
            @Result(column = "type", property = "type"),
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "parent_directory_id", property = "parent")
    }
    )
    List<File> getDirectory();

    @Select("select id from hue2.desktop_document2 where parent_directory_id=#{parentId}")
    List<Integer> getChildrenList(int parentId);


    @Select("select search from hue2.desktop_document2 where id=#{id}")
    @Results({
            @Result(column = "search", property = "content"),
    })
    File getContent(int id);

    @Update("update hue2.desktop_document2 set search=#{content} where id=#{id}")
    Boolean updateContentById(@Param("id") int id,@Param("content") String content);

    @Select("select name from desktop_document2 where id=#{fileId}")
    String selectNameById(int fileId);
}
