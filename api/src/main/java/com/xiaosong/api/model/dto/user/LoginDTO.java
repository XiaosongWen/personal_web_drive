package com.xiaosong.api.model.dto.user;

import lombok.Data;

@Data
public class LoginDTO {

    private String userEmail ;
    private String password ;

//    @Schema(description = "提交验证码")
//    private String captcha ;

//    @Schema(description = "验证码key")
//    private String codeKey ;
}
