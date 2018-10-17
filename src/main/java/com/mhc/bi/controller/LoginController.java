package com.mhc.bi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mhc.framework.support.session.anno.NoAuth;
import com.subaru.common.util.AES;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;

/**
 * @author baiyan
 * @date 2018/10/16
 * @description
 */
@Controller
public class LoginController {

    @RequestMapping("/")
    public String start() {
        return "Login";
    }

    @RequestMapping("/center")
    public String center() {
        return "index";
    }


    @RequestMapping("/loginValidate")
    public String validateView(ModelMap model, HttpSession session, @RequestParam("ct") String ct) {
        try {
            AES aes = null;
            aes = new AES("N5H4PYAFYMTjZtHy");
            String plainText = new String(aes.decrypt(Base64.decodeBase64(ct)), "UTF-8");
            JSONObject jsonObject = JSON.parseObject(plainText);
            String ticket = jsonObject.getString("ticket");
            String unionId = jsonObject.getString("unionId");
            String appId = "255792592814370816";
        } catch (Exception ignored) {
        }
        return "index";
    }

}

