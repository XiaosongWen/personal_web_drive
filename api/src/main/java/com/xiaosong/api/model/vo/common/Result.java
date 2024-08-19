package com.xiaosong.api.model.vo.common;
import lombok.*;

@Data
@Builder
public class Result<T> {
//    @Schema(description = "业务状态码")
    private Integer code;

//    @Schema(description = "响应消息")
    private String message;

//    @Schema(description = "业务数据")
    private T data;

    public static <T> Result<T> build(Integer code, T body, String message) {
         return Result.<T>builder()
                .code(code)
                .message(message)
                .data(body)
                .build();
    }

    public static <T> Result<T> build(ResultCodeEnum resultCodeEnum, T body) {
        return build(resultCodeEnum.getCode(), body, resultCodeEnum.getMessage()) ;
    }
}
