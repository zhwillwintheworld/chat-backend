package com.zxs.chat.handle.processor;

import com.zxs.chat.cache.ChatUserCacheHolder;
import com.zxs.chat.common.context.WebsocketMessageContext;
import com.zxs.chat.common.domain.Chat;
import com.zxs.chat.common.domain.UserConnInfo;
import com.zxs.chat.common.enums.ResponseCode;
import com.zxs.chat.conn.redis.RedisKey;
import com.zxs.chat.conn.redis.RedisKeyCode;
import com.zxs.chat.conn.redis.RedisUtil;
import com.zxs.chat.handle.ChatProcess;
import io.netty.util.internal.StringUtil;
import io.vertx.core.buffer.Buffer;

/**
 * @author zhanghua
 * @date 2021/10/9 17:50
 */
public class LoginChatProcessor implements ChatProcess {
    private static LoginChatProcessor processor;
    public static LoginChatProcessor getInstance(){
        if(processor == null){
            processor = new LoginChatProcessor();
        }
        return processor;
    }
    @Override
    public void doProcess(WebsocketMessageContext context) {
        Chat.ChatMessage chatMessage = context.getMessage();
        Chat.LoginRequest loginRequest = chatMessage.getLoginRequest();
        //封装返回值
        Chat.Response response = Chat.Response.newBuilder()
                .setResponseType(Chat.ResponseType.LOGIN_VALUE)
                .setCode(ResponseCode.MISSING_LOGIN_REQUEST_BODY.getCode())
                .setInfo(ResponseCode.MISSING_LOGIN_REQUEST_BODY.getDesc())
                .setSuccess(false)
                .build();
        // 返回结构
        Chat.ChatMessage message = Chat.ChatMessage.newBuilder()
                .setHeadType(Chat.HeadType.RESPONSE)
                .setResponse(response)
                .setSeq(context.getMessage().getSeq())
                .build();
        // 判断参数是否为空
        if(Chat.LoginRequest.getDefaultInstance().equals(loginRequest)){
            context.getChannel().writeBinaryMessage(Buffer.buffer(message.toByteArray()));
            return;
        }
        // 校验必要参数
        String token = loginRequest.getToken();
        String uid = loginRequest.getUid();
        if(StringUtil.isNullOrEmpty(token)||StringUtil.isNullOrEmpty(uid)){
            context.getChannel().writeBinaryMessage(Buffer.buffer(message.toByteArray()));
            return;
        }
        // 校验用户token
        RedisKey key = new RedisKey(RedisKeyCode.USER_TOKEN_SID,uid);
        RedisUtil.getInstance().getString(key).onSuccess(t->{
            // token 一致
            if(token.equals(t)){
                UserConnInfo connInfo = new UserConnInfo();
                connInfo.setToken(token);
                connInfo.setUserId(Long.parseLong(uid));
                // 标记用户为已登录
                ChatUserCacheHolder.setUserInfo(context.getChannel().textHandlerID(),connInfo);
                Chat.ChatMessage res = Chat.ChatMessage.newBuilder().setHeadType(Chat.HeadType.RESPONSE)
                        .setResponse(Chat.Response.newBuilder()
                                .setResponseType(Chat.ResponseType.LOGIN_VALUE)
                                .setCode(ResponseCode.SUCCESS.getCode())
                                .setInfo(ResponseCode.SUCCESS.getDesc())
                                .setSuccess(true)
                                .build())
                        .setSeq(context.getMessage().getSeq())
                        .build();
                context.getChannel().writeBinaryMessage(Buffer.buffer(res.toByteArray()));
            }else{
                Chat.ChatMessage res  = Chat.ChatMessage.newBuilder().setHeadType(Chat.HeadType.RESPONSE)
                        .setResponse(Chat.Response.newBuilder()
                                .setResponseType(Chat.ResponseType.LOGIN_VALUE)
                                .setCode(ResponseCode.TOKEN_WRONG.getCode())
                                .setInfo(ResponseCode.TOKEN_WRONG.getDesc())
                                .setSuccess(false)
                                .build())
                        .setSeq(context.getMessage().getSeq())
                        .build();
                context.getChannel().writeBinaryMessage(Buffer.buffer(res.toByteArray()));
            }
        });

    }
}
