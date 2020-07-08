package com.test.iso8601transfer;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class DateTools {

    public static DateTimeFormatter isoformat =  DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    public static DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");


//    public static String ISO2String(String str){
////        DateTime dateTime = isoformat.parseDateTime(str);
////        return dateTime.toString(format);
//        //下面的返回也对，上面的方法最终也是调用print方法
////        return format.print(dateTime);
//    }

    public static long ISO2second(String str){
        return isoformat.parseMillis(str);
    }

    public static String getNowDate(){
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.toString(isoformat)+"+08:00";
    }

    public static void main(String[] args) {
//        String str = "2019-11-20T10:25:23.050+08:00";
        System.out.println(getNowDate());
    }


}
