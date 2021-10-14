package com.zxs.chat.user;

import io.vertx.core.Vertx;

/**
 * @author zhanghua
 * @date 2021/10/14 16:07
 */
public class UserApplication {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new UserVerticle());
    }
}
