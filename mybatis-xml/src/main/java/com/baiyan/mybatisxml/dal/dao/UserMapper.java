package com.baiyan.mybatisxml.dal.dao;

import com.baiyan.mybatisxml.dal.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: peilongcheng
 * @Date: 2019/3/20 10:04
 * @Description:
 */
@Mapper
public interface UserMapper {

    /**
     * 获取所有用户
     *
     * @return
     */
    List<User> getUsers();

    /**
     * 修改用户信息
     *
     * @param user
     */
    void update(User user);

    /**
     * 删除用户
     *
     * @param
     */
    void del(@Param("id")int id);

    /**
     * 新增一条用户信息
     *
     * @param user
     */
    void save(User user);

    //这里展示传入多个参数的情况
    void insertWithMultipleParameters(@Param("user") User user,@Param("createTime")String createTime);


}