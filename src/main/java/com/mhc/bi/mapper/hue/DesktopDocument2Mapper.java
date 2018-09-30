package com.mhc.bi.mapper.hue;

import com.mhc.bi.domain.hue.DesktopDocument2;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

/**
 * @author baiyan
 * @date 2018/09/26
 * @description
 */
@Service
public interface DesktopDocument2Mapper {
    @Select("select * from hue2.desktop_document2 where name=#{name} and is_trashed=0;")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "owner_id", property = "ownerId"),
            @Result(column = "name", property = "name"),
            @Result(column = "description",property = "description"),
            @Result(column = "uuid",property = "uuid"),
            @Result(column = "type", property = "type"),
            @Result(column = "data", property = "data"),
            @Result(column = "extra",property = "extra"),
            @Result(column = "last_modified", property = "lastModified"),
            @Result(column = "version", property = "version"),
            @Result(column = "is_history", property = "isHistory"),
            @Result(column = "parent_directory_id",property = "parentDirectoryId"),
            @Result(column = "search",property = "search"),
            @Result(column = "is_managed",property = "isManaged"),
            @Result(column = "is_trashed",property = "isTrashed")
    })
    DesktopDocument2 selectByName(String name);

}
