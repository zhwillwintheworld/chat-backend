package com.zxs.chat.conn.redis;

import lombok.Getter;

/**
 * @author zhanghua
 * @date 2021/10/25 11:18
 */
@Getter
public enum RedisKeyCode {
    // redis key 值
    USER_TOKEN_SID(RedisTypeMode.STRING,RedisKeyMode.PARAM,RedisTtlMode.FIXED,RedisTtlCode.ONE_WEEK),
    ;


    private final RedisTypeMode typeMode;
    private final RedisKeyMode keyMode;
    private final RedisTtlMode ttlMode;
    private RedisTtlCode ttlCode;
    private Long ttlValue;

    RedisKeyCode(RedisTypeMode typeMode, RedisKeyMode keyMode, RedisTtlMode ttlMode, RedisTtlCode ttlCode) {
        this.typeMode = typeMode;
        this.keyMode = keyMode;
        this.ttlMode = ttlMode;
        this.ttlCode = ttlCode;
    }
}
