package com.zxs.chat.common.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhanghua
 * @date 2021/10/8 16:17
 */
@Data
public class
UserConnInfo {
    private String token;
    private String location;
    private String workId;
    private String platform;
    private LocalDateTime loginTime;
    private Long userId;

}
