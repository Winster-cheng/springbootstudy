package com.dubbo.baiyan.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.api.domian.User;
import com.dubbo.api.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserDubboConsumerService {

    @Reference(version = "1.0.0")
    UserService userService;

    public List<User> getList() {
        return userService.getUserAddressList();
    }
}
