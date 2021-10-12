package com.zxs.chat.websocket.server;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.ServiceDiscoveryOptions;

/**
 * @author zhanghua
 * @date 2021/10/11 10:32
 */
public class WebsocketApplication {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new WebsocketServerVerticle());
        ServiceDiscovery.create(vertx, new ServiceDiscoveryOptions()
                .setBackendConfiguration(
                        new JsonObject()
                                .put("connectionString", "redis://wsy520@localhost:6379/0")
                                .put("key", "records")
                ));

    }
}
