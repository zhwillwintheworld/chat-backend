package com.zxs.chat.conn.redis;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.redis.client.RedisAPI;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanghua
 * @date 2021/10/9 16:41
 */
public class RedisUtil {
    private static final RedisAPI REDIS = RedisAPI.api(RedisConnHolder.getConn());
    private static RedisUtil util;

    public static RedisUtil getInstance(){
        if(util == null){
            util = new RedisUtil();
        }
        return util;
    }


    public Future<String> getString(RedisKey key){
        Promise<String> promise = Promise.promise();
        REDIS.get(key.getKey()).onSuccess(res->{
            promise.complete(res.toString());
        });
        return promise.future();
    }

    public Future<Void> setString(RedisKey key,String val){
        Promise<Void> promise = Promise.promise();
        long ttl = key.getTtl();
        List<String> args = new ArrayList<>();
        args.add(key.getKey());
        args.add(val);
        if(ttl>-1){
            args.add("ex");
            args.add(ttl+"");
        }
        REDIS.set(args).onSuccess(k-> promise.complete());
        return promise.future();
    }




}
