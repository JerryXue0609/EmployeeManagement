package com.lokey.student.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by HJYIN on 14-11-26.
 */
public class DateUtil {

    public static String getNowDateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now_date = simpleDateFormat.format(new Date());
        return now_date;
    }

    /**
     * 比较时间的大小（按照日期比较）
     */
    public static boolean compareDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = simpleDateFormat.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dt1);
            calendar.add(Calendar.YEAR, 1);
            dt1 = calendar.getTime();
            Date dt2 = new Date();
            if (dt1.getTime() >= dt2.getTime()) {
                return true;
            } else if (dt1.getTime() < dt2.getTime()) {
                return false;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getYearLater(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dt1 = null;
        try {
            dt1 = simpleDateFormat.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dt1);
            calendar.add(Calendar.YEAR, 1);
            dt1 = calendar.getTime();
            String time = simpleDateFormat.format(dt1);
            return  time;
        } catch (ParseException e) {
            e.printStackTrace();
        }
         return "";
    }

    public static boolean compareNowDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = simpleDateFormat.parse(date);
            Date dt2 = new Date();
            if (dt1.getTime() >= dt2.getTime()) {
                return true;
            } else if (dt1.getTime() < dt2.getTime()) {
                return false;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getNowTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now_date = simpleDateFormat.format(new Date());
        return now_date;
    }

    public long secondDiff(String startTime, String endTime) {
        //按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long diffvalue = 0;
        try {
            Date start = simpleDateFormat.parse(startTime);
            Date end = simpleDateFormat.parse(endTime);
            diffvalue = (start.getTime() - end.getTime()) / 1000;//除以1000是为了转换成秒
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            return diffvalue;
        }
    }

    public static String getDayTime(long date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
        String now_date = simpleDateFormat.format(new Date(date));
        return now_date;
    }

    public static String getSMSDate(long date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日 HH:mm");
        String now_date = simpleDateFormat.format(new Date(date));
        return now_date;
    }

    //得到日期
    public static String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String now_date = simpleDateFormat.format(new Date());
        return now_date;
    }

    //得到日期
    public static String getMouth() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String now_date = simpleDateFormat.format(new Date());
        return now_date;
    }
    //得到日期
    public static String getDate(Integer num) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String now_date = simpleDateFormat.format(new Date());
        now_date = getDateAfter2(now_date,num*7);
        return now_date;
    }

    //得到时间
    public static String getTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        String now_date = simpleDateFormat.format(new Date());
        return now_date;
    }

    //根据日期取得星期几

    /**
     * Mon
     * Tue
     * Wed
     * Thu
     * Fri
     * Sat
     * Sun
     * @param date
     * @return
     */
    public static String getWeek(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("E", Locale.US);
        String week = sdf.format(date);
        return week;
    }

    /**
     * 得到几天前的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static String getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(now.getTime());
    }

    /**
     * 得到几天前的时间
     *
     * @param d
     * @param
     * @return
     */
    public static String getMouBefore(Date d, int mouth) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.MONTH, now.get(Calendar.MONTH) - mouth);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        return simpleDateFormat.format(now.getTime());
    }

    /**
     * 得到几天后的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static String getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(now.getTime());
    }

    /**
     * 得到几天后的时间2
     *
     * @param d
     * @param day
     * @return
     */
    public static String getDateAfter2(String d, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar now = Calendar.getInstance();//实例化Celendar对象
        now.setTime(date);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return sdf.format(now.getTime());
    }


    /**
     * 比较时间的大小 按照完整的时间
     * @return
     */
    public static  int compateAppointDate(String date1,String date2){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date dt1 = simpleDateFormat.parse(date1);
            Date dt2 = simpleDateFormat.parse(date2);
            if (dt1.getTime() >= dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //得到往前七天
    public static List<String> getSevenDayAgo() {
        List<String> reDate = new ArrayList<>();
        Date date = new Date();
        for(int i=0;i<7;i++) {
            String dateString = DateUtil.getDateBefore(date, i);
            reDate.add(dateString);
        }
        return  reDate;
    }
    //得到近6个月
    public static List<String> getSixMouthAgo() {
        List<String> reDate = new ArrayList<>();
        Date date = new Date();
        for(int i=0;i<6;i++) {
            String dateString = DateUtil.getMouBefore(date, i);
            reDate.add(dateString);
        }
        return  reDate;
    }
    //计算得到7天之后的日期
    public static  List<String> getSevenDayAfter(){
        List<String> reDate = new ArrayList<>();
        Date date = new Date();
        for(int i=0;i<7;i++){
            String dateString = DateUtil.getDateAfter(date,i);
            reDate.add(dateString);
        }
        return  reDate;
    }
   //将字符串转换成日期格式
    public  static  Date parseString(String time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date =simpleDateFormat.parse(time);
        }catch (Exception e){
            e.printStackTrace();
        }
        return date;
    }
}
