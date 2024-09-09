package com.xiaosong.api.exception;


import com.xiaosong.api.model.vo.common.ResultCodeEnum;
import lombok.Data;

@Data
public class MyException extends RuntimeException {

    private Integer code ;          // 错误状态码
    private String message ;        // 错误消息

    private ResultCodeEnum resultCodeEnum ;     // 封装错误状态码和错误消息

    public MyException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum ;
        this.code = resultCodeEnum.getCode() ;
        this.message = resultCodeEnum.getMessage();
    }

    public MyException(Integer code , String message) {
        this.code = code ;
        this.message = message ;
    }

}