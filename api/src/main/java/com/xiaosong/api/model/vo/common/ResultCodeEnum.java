package com.xiaosong.api.model.vo.common;
import lombok.Getter;
@Getter
public enum ResultCodeEnum {
    SUCCESS(200 , "Success") ,
    LOGIN_ERROR(401 , "Invalid Login info"),
    AUTHORIZATION_ERROR(402 , "Not Authorized Operation"),
    SYSTEM_ERROR(500 , "您的网络有问题请稍后重试"),
    NODE_ERROR( 217, "该节点下有子节点，不可以删除"),
    DATA_ERROR(204, "数据异常"),
    ACCOUNT_STOP( 216, "账号已停用"),

    ;

    private final Integer code ;      // 业务状态码
    private final String message ;    // 响应消息

    ResultCodeEnum(Integer code, String message) {
        this.code = code ;
        this.message = message ;
    }
}
