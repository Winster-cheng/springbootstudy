package com.mhc.bi.mapper.hue;

import com.mhc.bi.domain.hue.DesktopDocument2;
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

    /*
    private int id;
    private int ownerId;
    private String name;
    private String description;
    private String uuid;
    private String type;
    private String data;
    private String extra;
    private Date lastModified;
    private int version;
    private int isHistory;
    private String parentDirectoryId;
    private String search;
    private int isManaged;
    private int isTrashed;
     */
    @Select("select * from hue2.desktop_document2 where is_trashed=0 and id>3")
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
    }
    )
    List<DesktopDocument2> getAllAlive();

    @Select("select id from hue2.desktop_document2 where parent_directory_id=#{parentId}")
    List<Integer> getChildrenList(int parentId);


    @Select("select search from hue2.desktop_document2 where id=#{id}")
    String getContent(int id);

    @Update("update hue2.desktop_document2 set search=#{content} where id=#{id}")
    Boolean updateContentById(@Param("id") int id, @Param("content") String content);

    @Select("select name from desktop_document2 where id=#{fileId}")
    String selectNameById(int fileId);

    @Select("select * from hue2.desktop_document2 where is_trashed=0 and id>3 and id=#{id}")
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
    }
    )
    DesktopDocument2 getById(int id);

    @Select("select * from hue2.desktop_document2 where is_trashed=0 and id>3 and parent_directory_id=#{id}")
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
    }
    )
    List<DesktopDocument2> getChildrenListById(int id);

    @Select("select name from hue2.desktop_document2 where is_trashed=0 and id=#{id}")
    String getNameById(int id);

}
