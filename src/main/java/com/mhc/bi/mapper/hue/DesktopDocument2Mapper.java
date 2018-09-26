package com.mhc.bi.mapper.hue;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

/**
 * @author baiyan
 * @date 2018/09/26
 * @description
 */
@Service
public interface DesktopDocument2Mapper {
    @Select("select search from hue2.desktop_document2 where name=#{name} and is_trashed=0;")
    String getSearch(String name);

}
