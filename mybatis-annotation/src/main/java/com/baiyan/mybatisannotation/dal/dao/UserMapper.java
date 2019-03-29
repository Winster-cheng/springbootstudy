package com.baiyan.mybatisannotation.dal.dao;

import com.baiyan.mybatisannotation.dal.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: peilongcheng
 * @Date: 2019/3/20 10:04
 * @Description:
 */
@Repository
public interface UserMapper {
    /**
     * 获取所有用户
     *
     * @return
     */
    @Select("select * from user")
    @Results(id = "userMap", value = {
            @Result(column = "id", property = "id", javaType = Integer.class),
            @Result(column = "name", property = "name", javaType = String.class),
            @Result(column = "password", property = "password", javaType = String.class),
            @Result(column="create_time",property="createTime",javaType = String.class)
    })
    List<User> getUsers();



    @Select("select * from user")
//    @ResultMap("userMap")
    //这里用来表示统一映射关系，避免每个sql都要写一遍@Results,可以参考@Results——resultMap,注意 @Results必须要在一个方法里面被用过才可以被引用
    @ResultMap("userMap")
    List<User> getUsers2();

    //这里展示传入多个参数的情况
    @Insert("insert into user (name,password,create_time) values(#{user.name},#{user.password},#{createTime}")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")//这里用来返回id
    void insertWithMultipleParameters(@Param("user") User user, @Param("createTime") String createTime);


}