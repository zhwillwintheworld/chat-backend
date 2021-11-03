package com.zxs.chat.common.util;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

/**
 * @author zhanghua
 * @date 2021/10/25 11:47
 */
public class DateUtil {
    public static LocalDateTime getTomorrowStart(){
        LocalDateTime time = LocalDateTime.now();
        return time.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
    }
    public static LocalDateTime getNextWeekStart(){
        LocalDateTime time = LocalDateTime.now();
        DayOfWeek dayOfWeek = time.getDayOfWeek();
        int plus = 0;
        switch (dayOfWeek){
            case MONDAY:
                plus = 7;
                break;
            case TUESDAY:
                plus = 6;
                break;
            case WEDNESDAY:
                plus = 5;
                break;
            case THURSDAY:
                plus = 4;
                break;
            case FRIDAY:
                plus = 3;
                break;
            case SATURDAY:
                plus = 2;
                break;
            case SUNDAY:
                plus = 1;
                break;
            default:
        }
        return time.plusDays(plus).withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    public static LocalDateTime getNextMonthStart(){
        LocalDateTime time = LocalDateTime.now();
        return time.plusMonths(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
    }


}
