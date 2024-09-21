package com.xiaosong.api.service;

import com.alibaba.fastjson.JSON;
import com.xiaosong.api.exception.MyException;
import com.xiaosong.api.model.dto.todolist.TodoItemDTO;
import com.xiaosong.api.model.entity.SysUser;
import com.xiaosong.api.model.entity.todolist.TodoItem;
import com.xiaosong.api.model.vo.common.ResultCodeEnum;
import com.xiaosong.api.model.vo.todolist.TodoItemVO;
import com.xiaosong.api.model.vo.user.UserInfoVo;
import com.xiaosong.api.repository.system.SysUserRepository;
import com.xiaosong.api.repository.todolist.TodoItemSysUserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TodoListService {
    private TodoItemSysUserRepository todoItemSysUserRepository;
    private UserService userService;

    public List<TodoItemVO> getTodoList(String token) {
        UserInfoVo currentUserInfo = userService.getCurrentUserInfo(token);
        List<TodoItem> allById = todoItemSysUserRepository.findAllByUserId(currentUserInfo.getId());
        List<TodoItemVO> list = new ArrayList<>();
        allById.forEach(item ->
            list.add(TodoItemVO.builder()
                    .id(item.getId())
                    .description(item.getDescription())
                    .isDone(item.isDone())
                    .build())
        );

        return list;
    }

    public TodoItemVO creatTodoItem(String token, TodoItemDTO todoItemDTO) {
        SysUser userInfo = userService.getCurrentUser(token);
        TodoItem item = TodoItem.builder()
                .user(userInfo)
                .description(todoItemDTO.getDescription())
                .build();

        TodoItem save = todoItemSysUserRepository.save(item);
        return TodoItemVO.builder()
                .id(save.getId())
                .description(save.getDescription())
                .build();
    }


    public TodoItemVO updateTodoItem(String token, Long todoId, TodoItemDTO updateRequest) {
        SysUser userInfo = userService.getCurrentUser(token);
        Optional<TodoItem> byId = todoItemSysUserRepository.findById(todoId);
        if (byId.isPresent()){
            TodoItem todoItem = byId.get();
            if (!todoItem.getUser().getId().equals(userInfo.getId())) {
                throw new MyException(ResultCodeEnum.AUTHORIZATION_ERROR) ;
            }
            todoItem.setDescription(updateRequest.getDescription());
            todoItem.setDone(updateRequest.getDone());
            TodoItem save = todoItemSysUserRepository.save(todoItem);
            TodoItemVO vo = new TodoItemVO();
            BeanUtils.copyProperties(save, vo);
            return vo;
        }

        return null;
    }
}
