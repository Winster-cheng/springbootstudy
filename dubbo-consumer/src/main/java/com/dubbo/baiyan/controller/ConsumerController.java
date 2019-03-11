package com.dubbo.baiyan.controller;

import com.dubbo.api.domian.User;
import com.dubbo.baiyan.service.impl.UserDubboConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: peilongcheng
 * @Date: 2019/3/3 19:00
 * @Description:
 */
@RestController
@RequestMapping("/save")
public class ConsumerController {
    @Autowired
    UserDubboConsumerService userDubboConsumerService;

    @GetMapping("hello")
    public List<User> getList(){
        List a=userDubboConsumerService.getList();
        return a;
    }
}
