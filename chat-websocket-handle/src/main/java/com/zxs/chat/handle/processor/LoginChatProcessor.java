package com.zxs.chat.handle.processor;

import com.zxs.chat.common.context.WebsocketMessageContext;
import com.zxs.chat.handle.ChatProcess;

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


    }
}
