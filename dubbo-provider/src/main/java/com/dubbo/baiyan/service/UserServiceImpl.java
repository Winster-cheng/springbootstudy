package com.dubbo.baiyan.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.api.domian.User;
import com.dubbo.api.service.UserService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Service(version = "1.0.0")
@Component
public class UserServiceImpl implements UserService {
    @Override
    public List<User> getUserAddressList() {
        ArrayList list = new ArrayList();
        list.add(new User(new Integer(1),"张三","男",36,"杭州市"));
        list.add(new User(new Integer(2),"李四","男",34,"南京市"));
        list.add(new User(3,"王五","男",39,"上海市"));
        return list;
    }

}
