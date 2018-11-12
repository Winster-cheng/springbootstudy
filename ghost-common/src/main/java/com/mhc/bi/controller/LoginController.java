package com.mhc.bi.controller;

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

    @GetMapping("/center")
    public String center() {
        return "index";
    }

    @ResponseBody
    @GetMapping("/loginValidate")
    public String loginValidate() {
        return "hello";
    }

}