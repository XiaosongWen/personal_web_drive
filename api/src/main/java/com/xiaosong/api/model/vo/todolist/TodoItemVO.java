package com.xiaosong.api.model.vo.todolist;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoItemVO {
    private UUID id;
    private String description;
    private boolean isDone;
    private Date createdTime;
    private Date lastUpdatedTime;
}
