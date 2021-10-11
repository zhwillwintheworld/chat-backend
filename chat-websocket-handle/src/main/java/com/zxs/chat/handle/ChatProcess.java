package com.zxs.chat.handle;

import com.zxs.chat.common.context.WebsocketMessageContext;

/**
 * @author zhanghua
 * @date 2021/10/8 15:23
 */
public interface ChatProcess {
    /**
     *处理消息
     *@param context 消息上下文
     *@author zhanghua
     *@date 2021/10/8 16:26
     */
    void doProcess(WebsocketMessageContext context);
}
