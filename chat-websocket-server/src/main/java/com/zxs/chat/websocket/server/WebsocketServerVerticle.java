package com.zxs.chat.websocket.server;

import com.google.protobuf.InvalidProtocolBufferException;
import com.zxs.chat.common.context.WebsocketMessageContext;
import com.zxs.chat.common.domain.Chat;
import com.zxs.chat.conn.redis.RedisConnHolder;
import com.zxs.chat.handle.processor.BaseChatProcessor;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;

/**
 * @author zhanghua
 * @date 2021/9/22 16:52
 */

public class WebsocketServerVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        HttpServerOptions options = new HttpServerOptions().setMaxWebSocketFrameSize(1000000);
        HttpServer server = vertx.createHttpServer(options);
        RedisConnHolder.init(vertx);
        server.webSocketHandler(serverWebSocket -> {
            // 接收到消息
            String handleId = serverWebSocket.textHandlerID();
            System.out.println(handleId+"接入");
            serverWebSocket.handler(buffer -> {
                try{
                    Chat.ChatMessage message = Chat.ChatMessage.parseFrom(buffer.getBytes());
                    WebsocketMessageContext messageContext = new WebsocketMessageContext(message,null,handleId,serverWebSocket);
                    BaseChatProcessor processor = BaseChatProcessor.getInstance();
                    processor.doProcess(messageContext);
                }catch (InvalidProtocolBufferException e){
                    serverWebSocket.close();
                    System.out.println(handleId+"协议出错");
                }
            });
            // 关闭链接
            serverWebSocket.closeHandler(k->{
                System.out.println(handleId+"链接断开");
            });

        });
        server.listen(8889);
        startPromise.complete();
        System.out.println("启动成功");



    }


}
