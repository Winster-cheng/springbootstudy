package com.baiyan.mybatisxml.controller;

import com.baiyan.mybatisxml.dal.domain.User;
import com.baiyan.mybatisxml.dal.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: peilongcheng
 * @Date: 2019/3/19 16:53
 * @Description:
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/mybatis")
public class MybatisTestController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("getUser")
    public List<User> getUser() {
        List<User> userList=userMapper.getUsers();
        return userList;
    }
}
