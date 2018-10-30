package com.mhc.bi.controller;

import com.mhc.framework.support.session.auth.CurrentUserHolder;
import com.mhc.framework.support.session.base.User;
import net.sf.json.JSONObject;
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

<<<<<<< HEAD
    @ResponseBody
    @RequestMapping("/getUser")
    public User getUser() {
        User user = currentUserHolder.get(User.class);
        return user;
    }
}
=======
//    @ResponseBody
//    @RequestMapping("/getUser")
//    public Object getUser() {
//        User user = currentUserHolder.get(User.class);
//        return "当前用户为"+user.getName();
//    }
}

>>>>>>> 36b4825e22f5271ab90818fe5930c55a073648bf
