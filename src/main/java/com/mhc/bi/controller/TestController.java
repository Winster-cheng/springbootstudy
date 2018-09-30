package com.mhc.bi.controller;

import com.mhc.bi.service.alter.DingDingAlert;
import com.mhc.bi.domain.hue.DesktopDocument2;
import com.mhc.bi.exec.FlowControl;
import com.mhc.bi.service.DesktopDocument2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author baiyan
 * @date 2018/09/23
 * @description
 */
@RestController
public class TestController {
    @Autowired
    FlowControl flowControl;

    @Autowired
    DesktopDocument2Service desktopDocument2Service;

    @Autowired
    DingDingAlert dingDingAlert;

    @GetMapping(value = "/hello")
    public int hello() {
        return 1;
    }

    @GetMapping("desktopdocument2")
    public DesktopDocument2 getDesktopDocument2(String name) {
        return desktopDocument2Service.getDesktopDocument2(name);
    }

    @GetMapping("dingding")
    public void sendDingDingMsg(String msg){
        dingDingAlert.sendMsg(msg);
    }
}
