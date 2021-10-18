package com.zxs.chat.bean.user;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhanghua
 * @date 2021/10/15 17:24
 */
@Data
public class UserBase {
    private Long id;
    private String userName;
    private String password;
    private Integer status;
    private String nickName;
    private String avatar;
    private String note;
    private LocalDateTime createTime;
    private LocalDateTime lastLoginTime;

}
