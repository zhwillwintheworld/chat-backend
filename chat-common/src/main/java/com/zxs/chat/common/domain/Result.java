package com.zxs.chat.common.domain;

import com.zxs.chat.common.enums.ResponseCode;
import io.vertx.core.json.JsonObject;

/**
 * @author zhanghua
 * @date 2021/11/4 17:13
 */
public class Result {
    public static <T> JsonObject ok(T t){
        JsonObject json = new JsonObject();
        json.put("code", ResponseCode.SUCCESS.getCode());
        json.put("msg",ResponseCode.SUCCESS.getDesc());
        json.put("data",t);
        return json;
    }
    public static JsonObject ok(){
        JsonObject json = new JsonObject();
        json.put("code", ResponseCode.SUCCESS.getCode());
        json.put("msg",ResponseCode.SUCCESS.getDesc());
        return json;
    }
}
