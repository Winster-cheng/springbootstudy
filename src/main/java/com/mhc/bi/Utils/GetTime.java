package com.mhc.bi.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author baiyan
 * @date 2018/09/19
 * @description
 */
public class GetTime {

    /**
     * @描述
     * @参数
     * @返回值
     * @创建人 baiyan
     * @创建时间 2018/9/19
     * @修改人和其它信息
     */
    public static String getTimeStamp(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    public static String getTimeStamp(String format, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     *@描述 根据格式和偏差值，返回日期
     *@参数  格式，偏差值
     *@返回值
     *@创建人  baiyan
     *@创建时间  2018/9/23
     *@修改人和其它信息
     */
    public static String getTimeStamp(String format, int deviation) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, deviation);
        Date date=calendar.getTime();
        return simpleDateFormat.format(date);
    }

    /**
     * @描述 获取day天后的时间
     * @参数 format:时间格式 day:天数
     * @返回值 day天后的时间
     * @创建人 baiyan
     * @创建时间 2018/9/21
     * @修改人和其它信息
     */
    public static String getBeforeTimeStamp(String format, int day) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        date = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * @描述 获取当前的小时数
     * @参数
     * @返回值 int hour
     * @创建人 baiyan
     * @创建时间 2018/9/21
     * @修改人和其它信息
     */
    public static String getCurrentHour() {
        Date date =new Date();
        SimpleDateFormat sdf =new SimpleDateFormat("HH");//只有时分秒
        return sdf.format(date);
    }
}