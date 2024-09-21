package com.xiaosong.api.controller;

import com.xiaosong.api.model.dto.todolist.TodoItemDTO;
import com.xiaosong.api.model.vo.common.Result;
import com.xiaosong.api.model.vo.common.ResultCodeEnum;
import com.xiaosong.api.model.vo.todolist.TodoItemVO;
import com.xiaosong.api.model.vo.user.LoginVo;
import com.xiaosong.api.model.vo.user.UserInfoVo;
import com.xiaosong.api.service.TodoListService;
import com.xiaosong.api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/todolist")
public class TodoListController {
    private final TodoListService todoListService;

    @GetMapping(value = "/list")
    public Result<List<TodoItemVO>> list(@RequestHeader(value = "token") String token) {
        List<TodoItemVO> todoList = todoListService.getTodoList(token);
        return Result.build( ResultCodeEnum.SUCCESS, todoList);
    }

    @PostMapping(value = "/create")
    public Result<TodoItemVO> create(@RequestHeader(value = "token") String token,
                                           @RequestBody TodoItemDTO item) {
        TodoItemVO todoItem = todoListService.creatTodoItem(token, item);
        return Result.build( ResultCodeEnum.SUCCESS, todoItem);
    }
    @PutMapping("/{todoId}")
    public Result<TodoItemVO> update(
            @RequestHeader(value = "token") String token,
            @PathVariable Long todoId,
            @RequestBody TodoItemDTO updateRequest) {
        TodoItemVO updatedTodo = todoListService.updateTodoItem(token, todoId, updateRequest);
        return Result.build( ResultCodeEnum.SUCCESS, updatedTodo);
    }
}
