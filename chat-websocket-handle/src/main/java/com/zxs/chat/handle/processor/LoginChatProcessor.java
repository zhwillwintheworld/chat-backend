package com.zxs.chat.handle.processor;

import com.zxs.chat.common.context.WebsocketMessageContext;
import com.zxs.chat.common.domain.Chat;
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
        Chat.Response response = Chat.Response.newBuilder()
                .setResponseType(Chat.ResponseType.LOGIN_VALUE)
                .setCode(ResponseCode.MISSING_LOGIN_REQUEST_BODY.getCode())
                .setInfo(ResponseCode.MISSING_LOGIN_REQUEST_BODY.getDesc())
                .setSuccess(false)
                .build();
        Chat.ChatMessage message = Chat.ChatMessage.newBuilder()
                .setHeadType(Chat.HeadType.RESPONSE)
                .setResponse(response)
                .setSeq(context.getMessage().getSeq())
                .build();
        if(Chat.LoginRequest.getDefaultInstance().equals(loginRequest)){
            context.getChannel().writeBinaryMessage(Buffer.buffer(message.toByteArray()));
            return;
        }
        String token = loginRequest.getToken();
        String uid = loginRequest.getUid();
        if(StringUtil.isNullOrEmpty(token)||StringUtil.isNullOrEmpty(uid)){
            context.getChannel().writeBinaryMessage(Buffer.buffer(message.toByteArray()));
            return;
        }
        RedisKey key = new RedisKey(RedisKeyCode.USER_TOKEN_SID,uid);
        RedisUtil.getInstance().getString(key).onSuccess(t->{
            if(token.equals(t)){

            }
        })



    }
}
