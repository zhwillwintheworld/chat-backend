package com.zxs.chat.conn.redis;

import lombok.Data;

/**
 * @author zhanghua
 * @date 2021/10/22 16:53
 */
@Data
public class RedisKey {
    private RedisTypeMode type;
    private String key;
    private Long ttl;

    public RedisKey(RedisKeyCode code,Object... params){
        RedisKeyMode keyMode = code.getKeyMode();
        StringBuilder keyName = new StringBuilder(code.name());
        if(RedisKeyMode.PARAM.equals(keyMode)){
            for(Object param:params){
                keyName.append(":").append(param);
            }
        }
        this.key = keyName.toString();
        RedisTtlMode ttlMode = code.getTtlMode();
        if(RedisTtlMode.DYNAMIC.equals(ttlMode)){
            this.ttl = code.getTtlValue()==null?-1:code.getTtlValue();
        }else{
            this.ttl = RedisTtlCode.getTtl(code.getTtlCode());
        }
        this.type = code.getTypeMode();
    }

}
