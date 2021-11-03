package com.zxs.chat.conn.redis;

/**
 * @author zhanghua
 * @date 2021/10/22 17:00
 */
public enum RedisTypeMode {
    // number 是用来实现incr decr
    NUMBER,
    // redis 基础类型
    STRING,
    HASH,
    SET,
    ZSET,
    LIST,
    STREAM,
    BITMAP,
    PUBSUB,
    GEO,
    HYPERLOGLOG
    ;
}
