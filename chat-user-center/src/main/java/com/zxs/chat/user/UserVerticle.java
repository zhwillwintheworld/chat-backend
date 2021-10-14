package com.zxs.chat.user;

import com.zxs.chat.conn.redis.RedisConnHolder;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

/**
 * 用户顶点
 * @author zhanghua
 * @date 2021/10/14 16:07
 */
public class UserVerticle extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        // 创建redis 连接
        RedisConnHolder.init(vertx);
//        ServiceDiscovery.create(vertx, new ServiceDiscoveryOptions()
//                .setBackendConfiguration(
//                        new JsonObject()
//                                .put("connectionString", "redis://wsy520@localhost:6379/0")
//                                .put("key", "records")
//               ));
        this.start();
        startPromise.complete();
    }
}
