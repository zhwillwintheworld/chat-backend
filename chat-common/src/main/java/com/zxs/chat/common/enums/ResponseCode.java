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
    SUCCESS(1,"成功"),
    // 基础错误
    PARAM_WRONG(105,"参数错误"),
    //socket相关
    UN_LOGIN(1001,"未登录"),
    MISSING_LOGIN_REQUEST_BODY(1002,"缺少登录请求体"),
    TOKEN_WRONG(1003,"token错误"),
    // web相关
    USER_NOT_EXIST(2001,"用户不存在"),
    PASSWORD_WRONG(2002,"密码错误"),

    ;
    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
