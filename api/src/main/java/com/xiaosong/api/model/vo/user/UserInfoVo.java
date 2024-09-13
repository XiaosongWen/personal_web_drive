package com.xiaosong.api.model.vo.user;

import lombok.Data;

@Data
public class UserInfoVo {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String avatar;
}
