package com.zxs.chat.conn.redis;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.redis.client.Redis;
import io.vertx.redis.client.RedisConnection;
import io.vertx.redis.client.RedisOptions;

/**
 * @author zhanghua
 * @date 2021/10/9 16:14
 */
public class RedisConnHolder {
    private static final int MAX_RECONNECT_RETRIES = 16;

    private static final RedisOptions OPTIONS = new RedisOptions();
    private static RedisConnection client;
    private static Vertx vertx;

    public static void init(Vertx holder) {
        vertx = holder;
        OPTIONS.setConnectionString("redis://wsy520@127.0.0.1:6379/1")
                .setMaxPoolSize(2)
                .setMaxWaitingHandlers(32);
        createRedisClient()
                .onSuccess(conn -> {
                    client = conn;
                    System.out.println("链接redis成功");
                });
    }
    public static RedisConnection getConn(){
        return client;
    }

    private static Future<RedisConnection> createRedisClient() {
        Promise<RedisConnection> promise = Promise.promise();
        Redis.createClient(vertx, OPTIONS)
                .connect()
                .onSuccess(conn -> {
                    conn.exceptionHandler(e -> {
                        attemptReconnect(0);
                    });
                    promise.complete(conn);
                });

        return promise.future();
    }


    private static void attemptReconnect(int retry) {
        if (retry <= MAX_RECONNECT_RETRIES) {
            // retry with backoff up to 10240 ms
            long backoff = (long) (Math.pow(2, Math.min(retry, 10)) * 10);
            vertx.setTimer(backoff, timer -> {
                createRedisClient()
                        .onFailure(t -> attemptReconnect(retry + 1));
            });
        }
    }
}
