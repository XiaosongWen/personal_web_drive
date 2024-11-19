package com.xiaosong.api.repository.todolist;

import com.xiaosong.api.model.entity.todolist.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TodoItemSysUserRepository extends JpaRepository<TodoItem, UUID> {
    List<TodoItem> findAllByUserId(UUID userid);
}