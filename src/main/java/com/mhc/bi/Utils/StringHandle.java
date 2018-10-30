package com.mhc.bi.Utils;

/**
 * @author baiyan
 * @date 2018/09/26
 * @description 字符串处理类
 */
public class StringHandle {

    public static String checkEnding(String msg,String end){
        if(msg.endsWith(end)) return msg;
        else return msg+end;
    }
}