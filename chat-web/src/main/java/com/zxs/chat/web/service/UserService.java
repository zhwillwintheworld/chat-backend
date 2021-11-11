package com.zxs.chat.web.service;

import com.zxs.chat.bean.user.UserBase;
import com.zxs.chat.common.domain.Result;
import com.zxs.chat.common.enums.ResponseCode;
import com.zxs.chat.common.exception.BizException;
import com.zxs.chat.common.util.Md5Util;
import com.zxs.chat.conn.mysql.MysqlConnHolder;
import com.zxs.chat.conn.redis.RedisKey;
import com.zxs.chat.conn.redis.RedisKeyCode;
import com.zxs.chat.conn.redis.RedisUtil;
import com.zxs.chat.web.dao.user.UserBaseDao;
import io.vertx.core.Future;
import io.vertx.core.Promise;
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
        Integer platform = body.getInteger("platform");
        if(userName==null){
            throw new BizException(ResponseCode.PARAM_WRONG,"用户名不能为空");
        }
        if(password==null){
            throw new BizException(ResponseCode.PARAM_WRONG,"密码不能为空");
        }
        if(platform==null){
            throw new BizException(ResponseCode.PARAM_WRONG,"平台不能为空");
        }
        MysqlConnHolder.getClient()
                .getConnection()
                .compose(conn-> UserBaseDao.getInstance().getUserByUserName(conn,userName).eventually(k->conn.close()))
                .compose(ar -> validAndGetToken(ar,password,platform))
                .onSuccess(t->ctx.json(Result.ok(t)))
                .onFailure(ctx::fail);
    }

    private Future<String> validAndGetToken(UserBase user, String password, int platform){
        Promise<String> promise = Promise.promise();
        if(user==null){
            throw new BizException(ResponseCode.USER_NOT_EXIST);
        }
        if(!user.getPassword().equals(Md5Util.md5Encode(password))){
            throw new BizException(ResponseCode.PASSWORD_WRONG);
        }
        String token = Md5Util.md5Encode(user.getId() + "-" + System.currentTimeMillis());
        RedisKey tokenKey = new RedisKey(RedisKeyCode.USER_TOKEN_SID,user.getId());
        RedisUtil.getInstance().getString(tokenKey).onSuccess(x->{
            if(x!=null){
                // todo 发消息让其他链接关闭链接
            }
        });
        RedisUtil.getInstance().setString(tokenKey,token).onSuccess(k->promise.complete(token));
        return promise.future();
    }
}
