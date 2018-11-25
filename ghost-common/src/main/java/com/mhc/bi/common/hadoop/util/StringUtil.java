package com.mhc.bi.common.hadoop.util;

import com.mhc.bi.common.hadoop.constant.RollsroyceConstant;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaoshuai on 16/7/28.
 */
public class StringUtil extends StringUtils {
    
    /**
     * 获取第一个非月末日期
     * @param bizDates
     * @return
     */
    public static String getFirstNotMonthEnd(List<String> bizDates) throws ParseException {
        String bizDate;
        for (int i = 0, len = bizDates.size(); i < len - 1; i++) {
            bizDate = bizDates.get(i);
            if (!isMonthEndBizDate(bizDate)) {
                return bizDate;
            }
        }
        return null;
    }

    /**
     * 获取第一个非星期日期
     * @param bizDates
     * @param weekday
     * @return
     */
    public static String getFirstNotWeekday(List<String> bizDates, int weekday) throws ParseException {
        String bizDate;
        for (int i = 0, len = bizDates.size(); i < len - 1; i++) {
            bizDate = bizDates.get(i);
            if (!isWeekday(bizDate, weekday)) {
                return bizDate;
            }
        }
        return null;
    }
    
    public static boolean isWeekday(Date date, int weekday) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dateWeekday = calendar.get(Calendar.DAY_OF_WEEK);
        return dateWeekday == weekday;
    }
    
    public static boolean isMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE , 1);
        return calendar.get(Calendar.DAY_OF_MONTH) == 1;
    }
    
    public static boolean isMonthEnd(String bizDate, String dateFormat) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = sdf.parse(bizDate);
        return isMonthEnd(date);
    }

    public static boolean isWeekday(String bizDate, String dateFormat, int weekday) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = sdf.parse(bizDate);
        return isWeekday(date, weekday);
    }
    
    public static boolean isMonthEndBizDate(String bizDate) throws ParseException {
        return isMonthEnd(bizDate, RollsroyceConstant.DATE_FORMAT);
    }

    public static boolean isWeekday(String bizDate, int weekday) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(RollsroyceConstant.DATE_FORMAT);
        Date date = sdf.parse(bizDate);
        return isWeekday(date, weekday);
    }
    
}
