package com.zxs.chat.conn.mysql;

import io.vertx.core.Vertx;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;

/**
 * @author zhanghua
 * @date 2021/10/14 17:40
 */
public class MysqlConnHolder {
    private static MySQLPool client;

    public static void init(Vertx vertx){
        MySQLConnectOptions connectOptions = new MySQLConnectOptions()
                .setPort(3306)
                .setHost("127.0.0.1")
                .setDatabase("chat_user")
                .setUser("root")
                .setPassword("zs520")
                .setUseAffectedRows(true)
                .setReconnectAttempts(2)
                .setReconnectInterval(1000);
        // Pool options
        PoolOptions poolOptions = new PoolOptions()
                .setMaxSize(5);
        // Create the pooled client
        client = MySQLPool.pool(vertx, connectOptions, poolOptions);
    }

    public static MySQLPool getClient(){
        return client;
    }
}
