package com.zxs.chat.web;

import com.zxs.chat.conn.mysql.MysqlConnHolder;
import com.zxs.chat.conn.redis.RedisConnHolder;
import com.zxs.chat.web.router.RouterHolder;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;

/**
 * @author zhanghua
 * @date 2021/10/14 17:04
 */
public class WebVerticle extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        HttpServer server = vertx.createHttpServer();
        MysqlConnHolder.init(vertx);
        RedisConnHolder.init(vertx);
        RouterHolder.init(vertx);
        RouterHolder.start();
        server.requestHandler(RouterHolder.getRouter()).listen(8888);
        this.start();
        startPromise.complete();
    }
}
