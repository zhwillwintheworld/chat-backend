package com.zxs.chat.web.service;

import com.zxs.chat.common.enums.ResponseCode;
import com.zxs.chat.common.exception.BizException;
import com.zxs.chat.conn.mysql.MysqlConnHolder;
import com.zxs.chat.web.dao.user.UserBaseDao;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

/**
 * @author zhanghua
 * @date 2021/10/15 14:41
 */
public class UserService {
    private static UserService service;
    public static UserService getInstance(){
        if(service==null) {
            service = new UserService();
        }
        return  service;
    }
    public void login(RoutingContext ctx){
        JsonObject body = ctx.getBodyAsJson();
        String userName = body.getString("userName");
        String password = body.getString("password");
        if(userName==null){
            throw new BizException(ResponseCode.PARAM_WRONG,"用户名不能为空");
        }
        if(password==null){
            throw new BizException(ResponseCode.PARAM_WRONG,"密码不能为空");
        }
        MysqlConnHolder.getClient()
                .getConnection()
                .compose(conn-> UserBaseDao.getInstance().getUserByUserName(conn,userName).eventually(k->conn.close()))
                .onSuccess(ar -> {
                    if(ar==null){
                        ctx.fail(new BizException(ResponseCode.USER_NOT_EXIST));
                    }else if(!ar.getPassword().equals(password)){
                        ctx.fail(new BizException(ResponseCode.PASSWORD_WRONG));
                    }else{
                        ctx.json(new JsonObject().put("token","wsl yyds"));
                    }
                });
    }
}
