package com.zxs.chat.conn.redis;

import com.zxs.chat.common.util.DateUtil;

import java.time.ZoneOffset;

/**
 * @author zhanghua
 * @date 2021/10/25 11:11
 */
public enum RedisTtlCode {
    // redis 过去策略
    FOREVER,
    ONE_DAY,
    THREE_DAY,
    ONE_WEEK,
    ONE_MONTH,
    THIS_WEEK_END,
    THIS_DAY_END,
    THIS_MONTH_END,
    ;
    public static long getTtl(RedisTtlCode code){
        long ttl = -1;
        long now = System.currentTimeMillis();
        long target;
        switch (code){
            case ONE_DAY:
                ttl = 86400;
                 break;
            case THREE_DAY:
                ttl = 3 * 86400;
                break;
            case ONE_WEEK:
                ttl = 7 * 86400;
                break;
            case ONE_MONTH:
                ttl = 30 * 86400;
                break;
            case THIS_DAY_END:
                target = DateUtil.getTomorrowStart().toInstant(ZoneOffset.of("+8")).toEpochMilli();
                ttl = (target-now)/1000;
                break;
            case THIS_WEEK_END:
                target = DateUtil.getNextWeekStart().toInstant(ZoneOffset.of("+8")).toEpochMilli();
                ttl = (target-now)/1000;
                break;
            case THIS_MONTH_END:
                target = DateUtil.getNextMonthStart().toInstant(ZoneOffset.of("+8")).toEpochMilli();
                ttl = (target-now)/1000;
                break;
            case FOREVER:
            default:
                break;
        }
        return ttl;
    }
}
