package com.zxs.chat.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

/**
 * @author zhanghua
 * @date 2021/10/14 17:04
 */
public class WebVerticle extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        this.start();
        startPromise.complete();
    }
}
