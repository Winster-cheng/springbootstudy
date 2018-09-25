package com.mhc.bi.controller;

import com.mhc.bi.exec.FlowControl;
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
    @GetMapping(value = "/hello")
    public int hello(){
        return 1;
    }




}
