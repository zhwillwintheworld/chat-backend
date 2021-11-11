package com.zxs.chat.web.router;

import com.zxs.chat.common.exception.BizException;
import com.zxs.chat.web.controller.UserController;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.TimeoutHandler;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhanghua
 * @date 2021/10/15 14:18
 */
public class RouterHolder {
    private static Router router;
    public static void init(Vertx vertx){
        router = Router.router(vertx);

        router.route()
                // 处理post参数
                .handler(BodyHandler.create())
                // 设置超时设置
                .handler(TimeoutHandler.create(5000))
                // 设置content-type
                .consumes("application/json")
                .produces("application/json");

        // 解决跨域问题
        Set<HttpMethod> allowedMethods = new HashSet<>();
        allowedMethods.add(HttpMethod.GET);
        allowedMethods.add(HttpMethod.POST);
        allowedMethods.add(HttpMethod.OPTIONS);
        router.route().handler(CorsHandler.create("*").allowedMethods(allowedMethods));

        UserController.init();

        // 错误拦截器
        router.errorHandler(500,ctx->{
            Throwable t = ctx.failure();
            t.printStackTrace();
            JsonObject json = new JsonObject();
            json.put("code",500);
            json.put("msg","内部服务错误");
            if(t instanceof BizException){
                json.put("code",((BizException) t).getCode());
                json.put("msg",t.getMessage());
            }
            ctx.json(json);
        });
    }

    public static Router getRouter(){
        return router;
    }
}
