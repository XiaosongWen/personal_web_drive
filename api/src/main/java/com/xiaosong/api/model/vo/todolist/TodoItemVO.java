package com.xiaosong.api.model.vo.todolist;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoItemVO {
    private Long id;
    private String description;
    private boolean isDone;
    private Date createdTime;
    private Date lastUpdatedTime;
}
