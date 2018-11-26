package com.mhc.bi.Utils;

import com.mhc.bi.service.alert.DingDingAlert;

import java.text.ParseException;
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
     * @描述 根据格式和偏差值，返回日期
     * @参数 格式，偏差值
     * @返回值
     * @创建人 baiyan
     * @创建时间 2018/9/23
     * @修改人和其它信息
     */
    public static String getTimeStamp(String format, int deviation) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, deviation);
        Date date = calendar.getTime();
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
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH");//只有时分秒
        return sdf.format(date);
    }


    /**
     * @描述 mysql的datetime格式的当前时间
     * @参数e
     * @返回值
     * @创建人 baiyan
     * @创建时间 2018/9/27
     * @修改人和其它信息
     */
    public static String getTimeWithMysqlFormat() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }


    /**
     * @描述 输入时间，格式，加减数，返回时间+加减数
     * @参数 String time,String format,int count
     * @返回值 时间值
     * @创建人 baiyan
     * @创建时间 2018/11/5
     * @修改人和其它信息
     */
    public static String getTimeWithCount(String time, String format, int count) {
        try {
            SimpleDateFormat format1 = new SimpleDateFormat(format);
            Date oldDate = format1.parse(time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(oldDate);
            calendar.add(Calendar.DAY_OF_MONTH, count);
            return format1.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *@描述 替换参数专用时间方法，输入前端command中的时间参数，返回具体的时间值，比如${yyyyMMdd}，返回的是今天的天数-1，比如${yyyyMMdd-1}，返回的是今天的天数-2
     *@参数 时间格式参数
     *@返回值 具体值
     *@创建人  baiyan
     *@创建时间  2018/11/25
     *@修改人和其它信息
     */
    public static String getTimeForCommand(String format){
        int time=Integer.parseInt(format.replaceAll("\\s+","").replaceAll("yyyyMMdd",""));
        DingDingAlert.sendMsg("替换成为"+getTimeStamp("yyyyMMdd",time-1));
        return  getTimeStamp("yyyyMMdd",time-1);

    }

}