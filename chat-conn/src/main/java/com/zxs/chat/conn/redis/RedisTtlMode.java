package com.zxs.chat.conn.redis;

/**
 * @author zhanghua
 * @date 2021/10/22 17:39
 */
public enum RedisTtlMode {
    //
    // 固定值，比如一天 一周 一月
    FIXED,
    // 自动类型 由用户设置
    DYNAMIC,
    // 到某个时间段结束
    END,



    ;
}
