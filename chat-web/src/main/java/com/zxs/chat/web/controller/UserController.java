package com.zxs.chat.web.controller;

import com.zxs.chat.web.router.RouterHolder;
import com.zxs.chat.web.service.UserService;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Route;

/**
 * @author zhanghua
 * @date 2021/10/15 14:38
 */
public class UserController {
    public static void init(){
        Route route =  RouterHolder.getRoute();
        route.method(HttpMethod.POST).path("/user/login").handler(x-> UserService.getInstance().login(x));



    }
}
