package com.baiyan.mybatisannotation.controller;

import com.baiyan.mybatisannotation.dal.dao.UserMapper;
import com.baiyan.mybatisannotation.dal.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/mybatis")
public class MybatisTestController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("getUser")
    public List<User> getUser() {
        try {
            List<User> userList = userMapper.getUsers2();
            return userList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    //这里展示传入多个参数的情况
    @RequestMapping("insertWithMultipleParameters")
    public void insertWithMultipleParameters() {
        User user=new User();
        user.setName("woniu");
        user.setPassword("woniu1234");
        userMapper.insertWithMultipleParameters(user,"20190325");
    }

}
