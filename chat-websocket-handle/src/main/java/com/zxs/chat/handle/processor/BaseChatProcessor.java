package com.zxs.chat.handle.processor;

import com.zxs.chat.cache.ChatUserCacheHolder;
import com.zxs.chat.common.context.WebsocketMessageContext;
import com.zxs.chat.common.domain.Chat;
import com.zxs.chat.common.enums.ResponseCode;
import com.zxs.chat.handle.ChatProcess;
import io.vertx.core.buffer.Buffer;

/**
 * @author zhanghua
 * @date 2021/10/8 16:38
 */
public class BaseChatProcessor implements ChatProcess {
    private static BaseChatProcessor instance;

    public static BaseChatProcessor getInstance(){
        if(instance==null){
            instance = new BaseChatProcessor();
        }
        return  instance;
    }

    @Override
    public void doProcess(WebsocketMessageContext context) {
        // 校验是否登录
        boolean login = ChatUserCacheHolder.containConn(context.getHandleId());
        Chat.ChatMessage message = context.getMessage();
        Chat.HeadType headType= message.getHeadType();
        if(login){
            context.setConnInfo(ChatUserCacheHolder.getUserInfo(context.getHandleId()));
        }else{
            if(!Chat.HeadType.LOGIN_REQUEST.equals(headType)){
                Chat.Response.Builder builder = Chat.Response.newBuilder();
                builder.setResponseType(Chat.ResponseType.LOGIN_VALUE);
                builder.setCode(ResponseCode.UN_LOGIN.getCode());
                builder.setSuccess(false);
                context.getSocket().writeBinaryMessage(Buffer.buffer(builder.build().toByteArray()));
                return;
            }
        }
        switch (headType){
            case LOGIN_REQUEST:
                LoginChatProcessor.getInstance().doProcess(context);
                break;
            case LOGOUT_REQUEST:
                break;
            case MESSAGE_REQUEST:
                break;
            case KEEPALIVE_REQUEST:
                break;
            default:
        }

    }
}
