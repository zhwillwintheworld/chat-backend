package com.zxs.chat.common.enums;

import com.zxs.chat.common.interfaces.BaseEnum;
import lombok.Getter;

/**
 * @author zhanghua
 * @date 2021/10/9 17:38
 */
@Getter
public enum ResponseCode implements BaseEnum {
    // 描述
    UN_LOGIN(1001,"未登录"),
    ;
    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
