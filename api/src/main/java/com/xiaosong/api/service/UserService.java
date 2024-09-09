package com.xiaosong.api.service;

import com.alibaba.fastjson.JSON;
import com.xiaosong.api.exception.MyException;
import com.xiaosong.api.model.dto.user.LoginDTO;
import com.xiaosong.api.model.entity.SysUser;
import com.xiaosong.api.model.vo.common.ResultCodeEnum;
import com.xiaosong.api.model.vo.user.LoginVo;
import com.xiaosong.api.model.vo.user.UserInfoVo;
import com.xiaosong.api.repository.system.SysUserRepository;
import lombok.AllArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class UserService {

    private SysUserRepository sysUserRepository ;
    private RedisTemplate<String , String> redisTemplate ;

    public LoginVo login(LoginDTO loginDTO) {

        // 根据用户名查询用户
        List<SysUser> sysUsers = sysUserRepository.findSysUsersByEmail(loginDTO.getUserEmail());
        if(sysUsers.isEmpty()) {
            throw new MyException(ResultCodeEnum.LOGIN_ERROR) ;
        }
        SysUser user = sysUsers.get(0);

        // 验证密码是否正确
        String inputPassword = loginDTO.getPassword();
        String md5InputPassword = DigestUtils.md5DigestAsHex(inputPassword.getBytes());
        if(!md5InputPassword.equals(user.getPassword())) {
            throw new MyException(ResultCodeEnum.LOGIN_ERROR) ;
        }

        // 生成令牌，保存数据到Redis中
        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set("user:login:" + token , JSON.toJSONString(user) , 30 , TimeUnit.MINUTES);

        // 构建响应结果对象
        LoginVo loginVo = new LoginVo() ;
        loginVo.setToken(token);
        loginVo.setRefresh_token("");

        // 返回
        return loginVo;
    }

    public void logout(String token){
        redisTemplate.delete("user:login:" + token) ;

    }
    public void register(){
        String md5InputPassword = DigestUtils.md5DigestAsHex("00000".getBytes());
        SysUser user = SysUser.builder()
                .firstName("xiaosong")
                .lastName("wen")
                .password(md5InputPassword)
                .email("xiaosong.wen@gmail.com")
                .build();
        try {
            sysUserRepository.save(user);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public UserInfoVo getCurrentUserInfo(String token){
        String userInfoJSON = redisTemplate.opsForValue().get("user:login:" + token);
        if(userInfoJSON == null) {
            throw new MyException(ResultCodeEnum.LOGIN_ERROR) ;
        }
        SysUser userInfo = JSON.parseObject(userInfoJSON , SysUser.class) ;
        UserInfoVo userInfoVo = new UserInfoVo();
        assert userInfo != null;
        BeanUtils.copyProperties(userInfo, userInfoVo);
        return userInfoVo ;
    }
}
