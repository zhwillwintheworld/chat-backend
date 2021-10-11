package com.zxs.chat.cache;

import com.zxs.chat.common.domain.UserConnInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhanghua
 * @date 2021/10/9 14:11
 */
public class ChatUserCacheHolder {
    private static Map<String, UserConnInfo> chatStorage;

    private static Map<String, UserConnInfo> getInfo(){
        if(chatStorage==null){
            chatStorage = new HashMap<>();
        }
        return chatStorage;
    }

    public static boolean containConn(String handleId){
        return getInfo().containsKey(handleId);
    }

    public static UserConnInfo getUserInfo(String handleId){
        return getInfo().get(handleId);
    }

}
