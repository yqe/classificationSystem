package com.nju.classification.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description: 作用描述
 * @Author: qianen.yin
 * @CreateDate: 2019-12-30 23:46
 */
public class DateUtils {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static SimpleDateFormat sdfInDay = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat sdfInHour = new SimpleDateFormat("yyyy-MM-dd HH");
    private static SimpleDateFormat hour = new SimpleDateFormat("HH");
    private static Calendar c = Calendar.getInstance();

    public static String getToday(){
        return sdf.format(new Date());
    }

    public static String getTodayInDay(){
        return sdfInDay.format(new Date());
    }

    public static String getTodayInHour(){
        return sdfInHour.format(new Date());
    }

    public static String getNowHour(){
        return hour.format(new Date());
    }

    public static String getDayWeekAgo(){
        c.setTime(new Date());
        c.add(Calendar.DATE, - 7);
        Date d = c.getTime();
        return sdfInDay.format(d);
    }

    public static String getDayMonthAgo(){
        c.setTime(new Date());
        c.add(Calendar.MONTH, - 1);
        Date d = c.getTime();
        return sdfInDay.format(d);
    }

    public static String getDaySeasonAgo(){
        c.setTime(new Date());
        c.add(Calendar.MONTH, - 3);
        Date d = c.getTime();
        return sdfInDay.format(d);
    }
}

