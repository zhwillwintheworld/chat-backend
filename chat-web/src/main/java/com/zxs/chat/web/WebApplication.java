package com.zxs.chat.web;

import io.vertx.core.Vertx;

/**
 * @author zhanghua
 * @date 2021/10/8 19:04
 */
public class WebApplication {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new WebVerticle());
    }
}
