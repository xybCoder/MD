package com.xybcoder.baseactivity.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by dell on 2016/5/14.
 */
public class DateUtil {

    /**
     *
     * @param s
     * @param format
     * @return
     *
     */
    public static Date strToDate(String s, String format) {
        // yyyy-MM-dd HH:mm
        Date d = new Date();
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            d = dateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     *
     * @param date
     * @return
     */
    public static String dateToStr1(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return dateFormat.format(date);
    }

    /**
     *
     * @param date
     * @return
     */
    public static String dateToStr2(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String str = dateFormat.format(date);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return str;
    }

    /**
     *
     * @param date
     * @return
     */
    public static String dateToStr3(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm");
        String str = dateFormat.format(date);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return str;
    }

    /**
     *
     * @param date
     * @param add
     * @return
     */
    public static String datetoStr4(Date date, int add) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, add);
        return dateToStr1(calendar.getTime());
    }


    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getNowDateString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    /**
     *
     * @param date
     * @return
     */
    public static Date getLastdayDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     *
     * @param date
     * @return
     */
    public static Date getNextdayDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     *
     * @param one
     * @param another
     * @return
     */
    public static boolean isTheSameDay(Date one, Date another) {
        Calendar _one = Calendar.getInstance();
        _one.setTime(one);
        Calendar _another = Calendar.getInstance();
        _another.setTime(another);
        int oneDay = _one.get(Calendar.DAY_OF_YEAR);
        int anotherDay = _another.get(Calendar.DAY_OF_YEAR);

        return oneDay == anotherDay;
    }
}
