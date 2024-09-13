package com.xiaosong.api.model.vo.todolist;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TodoItemVO {
    private Long id;
    private String description;
    private boolean isDone;
}
