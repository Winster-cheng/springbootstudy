package com.mhc.bi.controller;

import com.mhc.framework.support.session.auth.CurrentUserHolder;
import com.mhc.framework.support.session.base.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author baiyan
 * @date 2018/10/16
 * @description
 */
@Controller
public class LoginController {

//    @Autowired
//    CurrentUserHolder currentUserHolder;

//    @RequestMapping("/")
//    public String start() {
//        return "Login";
//    }

    @GetMapping("/center/**")
    public String center() {
        return "/center/index";
    }

//    @ResponseBody
//    @RequestMapping("/getUser")
//    public Object getUser() {
//        User user = currentUserHolder.get(User.class);
//        return "当前用户为"+user.getName();
//    }
}

