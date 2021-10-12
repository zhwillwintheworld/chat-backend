package com.zxs.chat.config;

import io.vertx.core.json.JsonObject;

/**
 * @author zhanghua
 * @date 2021/10/12 17:45
 */
public class LocalConfigHolder {
    private static JsonObject config;

    public static JsonObject getConfig(){
        if(config==null){
            config = new JsonObject();
        }
        return config;
    }
}
