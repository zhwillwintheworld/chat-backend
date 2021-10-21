package com.zxs.chat.web.controller;

import com.zxs.chat.web.router.RouterHolder;
import com.zxs.chat.web.service.UserService;
import io.vertx.ext.web.Router;

/**
 * @author zhanghua
 * @date 2021/10/15 14:38
 */
public class UserController {
    public static void init(){
        Router router = RouterHolder.getRouter();
        UserService userService = UserService.getInstance();
        // 登录接口
        router.post("/user/login").handler(userService::login);



    }
}
