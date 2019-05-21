package com.mhc.byrobotrecall.web.controller;

import com.mhc.byrobotrecall.common.ActionResult;
import com.mhc.byrobotrecall.common.DingDingAlert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @Auther: peilongcheng
 * @Date: 2019/5/8 14:58
 * @Description:
 */
@RestController
@RequestMapping("/openapi/isvCallback")
public class RecallController {
    @PostMapping(value = "/getCallBackMsg")
    @ResponseBody
    public String isvCallBack(HttpServletRequest request) {
        DingDingAlert.sendMsg("调用了回调接口");
        System.out.println("调用了回调接口");
        ActionResult actionResult=new ActionResult();
        StringBuffer data = new StringBuffer();
        String line = null;
        BufferedReader reader = null;
        try {
            reader = request.getReader();
            while (null != (line = reader.readLine())) {
                data.append(line);
            }
        } catch (IOException e) {
            actionResult.fail(e.getMessage());
            DingDingAlert.sendMsg("出错啦"+e.getMessage());
            System.out.println("出错啦");
            return "failure";
        } finally {
            System.out.println("获取的消息"+data.toString());
            DingDingAlert.sendMsg("isv回调测试成功");
        }
        return "success";
        }

    }
