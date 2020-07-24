package com.edu.pet.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    /*
    日期格式
    */
    private final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /*
    日期对象
     */
    private static DateFormat dateFormat;

    static {
        if (dateFormat == null) dateFormat = new SimpleDateFormat(DateUtils.DEFAULT_DATE_FORMAT);
    }

    //获取当前日期和时间字符串
    public static String getDateTime(){
        Date date=new Date();
        java.sql.Date sqlDate=new java.sql.Date(date.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(sqlDate);
    }
    /**
     * 日期转字符
     * Date->yyyy-MM-dd
     */
    public static String dateConvertString(Date currentDate) {
        String dateString = null;
        if (currentDate != null) {
            dateString = dateFormat.format(currentDate);
        }
        return dateString;
    }
}
