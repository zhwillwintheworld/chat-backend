package com.zxs.chat.web.router;

import com.zxs.chat.common.exception.BizException;
import com.zxs.chat.web.controller.UserController;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.TimeoutHandler;

/**
 * @author zhanghua
 * @date 2021/10/15 14:18
 */
public class RouterHolder {
    private static Router router;
    public static void init(Vertx vertx){
        router = Router.router(vertx);
        Route route = router.route();
        route.handler(BodyHandler.create())
                .handler(TimeoutHandler.create(5000))
                .consumes("application/json")
                .produces("application/json");
        UserController.init();
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
