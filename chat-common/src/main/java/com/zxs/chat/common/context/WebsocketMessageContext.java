package com.zxs.chat.common.context;

import com.zxs.chat.common.domain.Chat;
import com.zxs.chat.common.domain.UserConnInfo;
import io.vertx.core.http.ServerWebSocket;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhanghua
 * @date 2021/10/8 15:27
 */
@Data
@AllArgsConstructor
public class WebsocketMessageContext {
    /**
     * 消息
     */
    private Chat.ChatMessage message;
    /**
     * 用户链接信息
     */
    private UserConnInfo connInfo;
    /**
     * websocket
     */
    private ServerWebSocket channel;


}
