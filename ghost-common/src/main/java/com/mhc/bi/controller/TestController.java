package com.mhc.bi.controller;

import com.mhc.bi.domain.hue.DesktopDocument2;
import com.mhc.bi.exec.FlowControl;
import com.mhc.bi.service.DesktopDocument2Service;
import com.mhc.bi.service.alert.DingDingAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author baiyan
 * @date 2018/09/23
 * @description
 */
@Controller
public class TestController {
    @Autowired
    FlowControl flowControl;

    @Autowired
    DesktopDocument2Service desktopDocument2Service;


    @RequestMapping("/hello")
    public String hello() {
        return "index2";
    }

    @GetMapping("desktopdocument2")
    public DesktopDocument2 getDesktopDocument2(String name) {
        return desktopDocument2Service.getDesktopDocument2(name);
    }

    //钉钉手动接入
    @ResponseBody
    @GetMapping("dingding")
    public void sendDingDingMsg(String msg) {
        DingDingAlert.sendMsg(msg);
    }

}
