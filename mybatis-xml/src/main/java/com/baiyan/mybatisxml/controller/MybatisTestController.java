//package com.baiyan.mybatisxml.controller;
//
//import com.baiyan.mybatisxml.dal.domain.User;
//import com.baiyan.mybatisxml.dal.dao.UserMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.Mapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @Auther: peilongcheng
// * @Date: 2019/3/19 16:53
// * @Description:
// */
//@RestController
//public class MybatisTestController {
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @GetMapping("getUser")
//    public List<User> getUser() {
//        try {
//            List<User> userList = userMapper.getUsers();
//            return userList;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return new ArrayList<>();
//    }
//    //这里展示传入多个参数的情况
//    @RequestMapping("insertWithMultipleParameters")
//    public void insertWithMultipleParameters() {
//        User user=new User();
//        user.setName("woniu");
//        user.setPassword("woniu1234");
//        userMapper.insertWithMultipleParameters(user,"20190325");
//    }
//
//}
