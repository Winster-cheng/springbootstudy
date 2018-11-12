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

    @GetMapping("/center/**")
    public String center() {
        return "/center/index";
    }

    @GetMapping("/loginValidate")
    public String loginValidate() {
        return "/center/index";
    }

}