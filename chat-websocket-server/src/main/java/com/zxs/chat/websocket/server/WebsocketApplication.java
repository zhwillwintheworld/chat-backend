package com.zxs.chat.websocket.server;

import com.hazelcast.config.Config;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

/**
 * @author zhanghua
 * @date 2021/10/11 10:32
 */
public class WebsocketApplication {
    public static void main(String[] args) {
        Config config = new Config();
        config.setClusterName("chat");

        ClusterManager mgr = new HazelcastClusterManager(config);
        VertxOptions options = new VertxOptions().setClusterManager(mgr);
        Vertx.clusteredVertx(options, res -> {
            if (res.succeeded()) {
                Vertx vertx = res.result();
                vertx.deployVerticle(new WebsocketServerVerticle());
            } else {
                System.out.println("集群启动失败");
            }
        });
    }
}
