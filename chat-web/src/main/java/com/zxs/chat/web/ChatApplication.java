package com.zxs.chat.web;

import com.zxs.chat.websocket.server.WebsocketServerVerticle;
import io.vertx.core.Vertx;

/**
 * @author zhanghua
 * @date 2021/10/8 19:04
 */
public class ChatApplication {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new WebsocketServerVerticle());
    }
}
