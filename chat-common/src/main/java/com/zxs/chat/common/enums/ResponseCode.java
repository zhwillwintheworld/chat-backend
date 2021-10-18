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
    // 基础错误
    PARAM_WRONG(105,"参数错误"),
    //
    UN_LOGIN(1001,"未登录"),
    USER_NOT_EXIST(1002,"用户不存在"),
    PASSWORD_WRONG(1003,"密码错误"),
    ;
    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
