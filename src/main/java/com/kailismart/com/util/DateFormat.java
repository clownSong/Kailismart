package com.kailismart.com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.logging.SimpleFormatter;

/**
 * Created by Administrator on 2016-08-16.
 * 时间处理类
 */
public class DateFormat {
    public static SimpleDateFormat dateFormat = new SimpleDateFormat();

    /**
     * 获取最新的日期+时间
     * @return
     */
    public static String getDateTime(){
        dateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    public static String getDate(){
        dateFormat.applyPattern("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }

    public static String getDateForNumber(){
        dateFormat.applyPattern("yyyyMMdd");
        return dateFormat.format(new Date());
    }

    public static Date parseData(String data) {
        try {
            return dateFormat.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static String parseString(Date time) {
        dateFormat.applyPattern("yyyy-MM-dd");
        return dateFormat.format(time);
    }
}
