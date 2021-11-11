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
public class BaseChatProcessor implements ChatProcess{
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
        String channelId = context.getChannel().textHandlerID();
        boolean login = ChatUserCacheHolder.containConn(channelId);
        Chat.ChatMessage message = context.getMessage();
        Chat.HeadType headType= message.getHeadType();
        if(login){
            context.setConnInfo(ChatUserCacheHolder.getUserInfo(channelId));
        }else{
            if(!Chat.HeadType.LOGIN_REQUEST.equals(headType)){
                Chat.Response response = Chat.Response.newBuilder()
                        .setResponseType(Chat.ResponseType.LOGIN_VALUE)
                        .setCode(ResponseCode.UN_LOGIN.getCode())
                        .setSuccess(false)
                        .build();
                Chat.ChatMessage chatMessage = Chat.ChatMessage.newBuilder()
                        .setHeadType(Chat.HeadType.RESPONSE)
                        .setResponse(response)
                        .setSeq(context.getMessage().getSeq())
                        .build();
                context.getChannel().writeBinaryMessage(Buffer.buffer(chatMessage.toByteArray()));
                return ;
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
