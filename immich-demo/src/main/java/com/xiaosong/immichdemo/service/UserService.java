package com.xiaosong.immichdemo.service;

import com.xiaosong.immichdemo.model.entity.User;
import com.xiaosong.immichdemo.model.vo.UserVO;
import com.xiaosong.immichdemo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserVO> getAll(){
        List<User> all = userRepository.findAll();
        List<UserVO> userVOs = new ArrayList<>();
        for (User user : all) {
            UserVO userVO = new UserVO();
            userVO.setId(user.getId());
            userVO.setEmail(user.getEmail());
            userVOs.add(userVO);
        }
        return userVOs;
    }
    public UserVO getByEmail(String email){
        User user = userRepository.findUsersByEmail(email);
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setEmail(user.getEmail());
        return userVO;
    }
}
