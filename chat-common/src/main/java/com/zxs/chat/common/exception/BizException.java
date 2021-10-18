package com.zxs.chat.common.exception;

import com.zxs.chat.common.enums.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhanghua
 * @date 2021/10/15 15:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BizException extends RuntimeException{
    private int code;
    private String msg;

    public BizException(ResponseCode code,String msg){
        super(msg);
        this.code = code.getCode();
        this.msg = msg;
    }

    public BizException(ResponseCode code){
        super(code.getDesc());
        this.code = code.getCode();
        this.msg = code.getDesc();
    }
}
