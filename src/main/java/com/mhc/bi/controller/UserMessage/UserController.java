package com.mhc.bi.controller.UserMessage;

import com.mhc.bi.common.ActionResult;
import com.mhc.bi.service.JobPlanService;
import com.mhc.bi.service.TaskInstanceService;
import com.mhc.framework.support.session.auth.CurrentUserHolder;
import com.mhc.framework.support.session.base.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author baiyan
 * @date 2018/10/22
 * @description 返回用户相关信息
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    CurrentUserHolder currentUserHolder;

    @Autowired
    JobPlanService jobPlan;

    @Autowired
    TaskInstanceService taskInstanceService;

    @GetMapping("getUser")
    public Object getUser() {
        ActionResult actionResult = new ActionResult();
        try {
            List l = new ArrayList();
            User user = currentUserHolder.get(User.class);
            actionResult.setDataValue(user);
            actionResult.success("查询用户成功");
        } catch (Exception e) {
            e.printStackTrace();
            actionResult.fail("查询用户信息失败");
        } finally {
        }
        return actionResult;
    }
}