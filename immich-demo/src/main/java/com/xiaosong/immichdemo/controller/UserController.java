package com.xiaosong.immichdemo.controller;

import com.xiaosong.immichdemo.model.entity.User;
import com.xiaosong.immichdemo.model.vo.AssetVO;
import com.xiaosong.immichdemo.model.vo.UserVO;
import com.xiaosong.immichdemo.service.AssetService;
import com.xiaosong.immichdemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/user")
public class UserController {
    private final UserService userService;
    private final AssetService assetService;
    @GetMapping(value = "/list")
    public List<UserVO> list() {
        return userService.getAll();
    }

    @GetMapping(value = "/{userEmail}/{page}")
    public List<AssetVO> getImg(@PathVariable String userEmail, @PathVariable int page) {

        UserVO byEmail = userService.getByEmail(userEmail);

        return assetService.getAssetByOwnerId(byEmail, page);
    }
    @PostMapping("/migrate/{userEmail}")
    public String triggerJob(@PathVariable String userEmail) {
        UserVO byEmail = userService.getByEmail(userEmail);
        assetService.migrate(byEmail);
        System.out.println("Job triggered asynchronously");
        return "Job triggered asynchronously";
    }
    @PostMapping("/restructure")
    public String restructure() {
        assetService.restructure();
        return "Job triggered asynchronously";
    }
}

