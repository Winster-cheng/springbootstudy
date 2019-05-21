package com.mhc.byrobotrecall.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fangrui
 * @date 2018/10/18
 * @description 方锐提供的json包装类
 */
@Getter
@Setter
@ToString
public class ActionResult<T> {
    private boolean flag;
    private boolean result;
    private Map<String, Object> dataMap;
    private List<T> list;
    private Object dataValue;
    private int code;
    private String message;

    public ActionResult() {
        this.flag = false;
        this.result = false;
        this.code = 500;
        this.dataMap = new HashMap<>();
        this.list = Collections.emptyList();
        this.message = "失败";
    }

    public ActionResult success() {
        this.flag = true;
        result = true;
        this.message = "成功";
        this.code = 200;
        return this;
    }

    public ActionResult success(String message) {
        this.flag = true;
        result = true;
        this.message = message;
        this.code = 200;
        return this;
    }

    public ActionResult fail() {
        this.flag = false;
        result = false;
        this.message = "失败";
        this.code = 500;
        return this;
    }

    public ActionResult fail(String message) {
        this.flag = false;
        result = false;
        this.message = message;
        this.code = 500;
        return this;
    }
}