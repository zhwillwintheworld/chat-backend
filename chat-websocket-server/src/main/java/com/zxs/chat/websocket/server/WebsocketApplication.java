package com.zxs.chat.websocket.server;

import io.vertx.core.Vertx;

/**
 * @author zhanghua
 * @date 2021/10/11 10:32
 */
public class WebsocketApplication {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new WebsocketServerVerticle());

    }
}
