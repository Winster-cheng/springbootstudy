package com.mhc.bi.common;

/**
 * @author baiyan
 * @date 2018/09/19
 * @description 状态枚举类
 */
public enum Status {
    COMMITTED, Waited, RUNNING, SUCCESSED, FAIULRED;

    public static Status getStatus(int statusCode) {
        switch (statusCode) {
            case 1:
                return COMMITTED;
            case 2:
                return Waited;
            case 3:
                return RUNNING;
            case 4:
                return SUCCESSED;
            case 5:
                return FAIULRED;
            default:
                System.out.println("这里做一个异常抛出");
                return null;
        }
    }
    }
