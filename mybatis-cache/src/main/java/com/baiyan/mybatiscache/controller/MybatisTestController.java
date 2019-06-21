package com.baiyan.mybatiscache.controller;

import com.baiyan.mybatiscache.dal.dao.UserMapper;
import com.baiyan.mybatiscache.dal.domain.User;
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
public class MybatisTestController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("getUser")
    public List<User> getUser() {
        try {
            List<User> userList = userMapper.getUsers();
            return userList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    //这里展示传入多个参数的情况
    @RequestMapping("insertWithMultipleParameters")
    public void insertWithMultipleParameters() {
        for (int i = 0; i < 1000000; i++) {
            User user = new User();
            user.setName("hello"+i);
            user.setPassword("hello"+i);
            userMapper.insertWithMultipleParameters(user, "20190624");
        }
    }

    @RequestMapping("selectCount")
    public void selectCount() {
            userMapper.selectCount();
    }
}
