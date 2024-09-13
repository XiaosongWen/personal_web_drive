package com.xiaosong.api.repository.todolist;

import com.xiaosong.api.model.entity.todolist.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoItemSysUserRepository extends JpaRepository<TodoItem, Long> {
    List<TodoItem> findAllByUserId(Long userid);
}