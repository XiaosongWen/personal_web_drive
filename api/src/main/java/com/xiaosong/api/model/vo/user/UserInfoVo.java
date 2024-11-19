package com.xiaosong.api.model.vo.user;

import lombok.Data;

import java.util.UUID;

@Data
public class UserInfoVo {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String avatar;
}
