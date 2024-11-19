package com.xiaosong.api.controller;

import com.xiaosong.api.model.dto.user.LoginDTO;
import com.xiaosong.api.model.vo.common.Result;
import com.xiaosong.api.model.vo.common.ResultCodeEnum;
import com.xiaosong.api.model.vo.user.LoginVo;
import com.xiaosong.api.model.vo.user.UserInfoVo;
import com.xiaosong.api.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public Result<LoginVo> login(@RequestBody LoginDTO loginDto) {
        LoginVo loginVo = userService.login(loginDto);
        return Result.build( ResultCodeEnum.SUCCESS, loginVo);
    }
    @GetMapping(value = "/logout")
    public Result<LoginVo> logout(@RequestHeader(value = "token") String token) {
        userService.logout(token);
        return Result.build( ResultCodeEnum.SUCCESS, null);
    }
    @PostMapping(value = "/register")
    public Result<String> register() {
        userService.register();
        return Result.<String>build( ResultCodeEnum.SUCCESS, "create user success");
    }
    @GetMapping(value = "/info")
    public Result<UserInfoVo> getCurrentUser(@RequestHeader(value = "token", defaultValue = "") String token){
        UserInfoVo userInfoVo = userService.getCurrentUserInfo(token) ;
        return Result.build(ResultCodeEnum.SUCCESS, userInfoVo);
    }
}
