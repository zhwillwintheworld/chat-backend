package com.zxs.chat.push;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

/**
 * @author zhanghua
 * @date 2021/10/11 15:09
 */
public class PushVerticle extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        this.start();
        startPromise.complete();
    }
}
