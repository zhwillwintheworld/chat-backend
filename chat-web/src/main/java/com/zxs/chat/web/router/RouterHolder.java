package com.zxs.chat.web.router;

import com.zxs.chat.common.exception.BizException;
import com.zxs.chat.web.controller.UserController;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * @author zhanghua
 * @date 2021/10/15 14:18
 */
public class RouterHolder {
    private static Router router;
    private static Route route;
    public static void init(Vertx vertx){
        router = Router.router(vertx);
        route = router.route();
    }
    public static void start(){
        route.handler(BodyHandler.create());
        UserController.init();
        route.consumes("application/json")
                .produces("application/json")
                .failureHandler(ctx->{
                    Throwable t = ctx.failure();
                    JsonObject res = new JsonObject();
                    res.put("code",500);
                    res.put("msg",t.getMessage());
                    if(t instanceof BizException){
                        res.put("code",((BizException) t).getCode());
                    }
                    ctx.json(res);
                });





    }
    public static Router getRouter(){
        return router;
    }
    public static Route getRoute(){
        return  route;
    }

}
