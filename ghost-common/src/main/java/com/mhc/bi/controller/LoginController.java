package com.mhc.bi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author baiyan
 * @date 2018/10/16
 * @description
 */
@Controller
public class LoginController {


    @Autowired
    CurrentUserHolder currentUserHolder;

    @GetMapping("/center/**")
    public String center() {
        return "/center/index";
    }

    @ResponseBody
    @GetMapping("/loginValidate")
    public String loginValidate() {
        User user = currentUserHolder.get(User.class);
        if ( null == user){
            return "登陆失败！";
        }
        return user.getName();
    }

}