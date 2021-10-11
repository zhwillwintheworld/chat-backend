package com.zxs.chat.conn.redis;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.redis.client.RedisAPI;

/**
 * @author zhanghua
 * @date 2021/10/9 16:41
 */
public class RedisUtil {
    private static RedisAPI redis;
    private static RedisUtil util;

    public static RedisUtil getInstance(){
        if(util == null){
            util = new RedisUtil();
        }
        return util;
    }

    private RedisAPI getApi(){
        if(redis == null){
            redis = RedisAPI.api(RedisConnHolder.getConn());
        }
        return redis;
    }

    public Future<String> get(String key){
        Promise<String> promise = Promise.promise();
        getApi().get(key).onSuccess(res->{
            promise.complete(res.toString());
        });
        return promise.future();
    }

}
