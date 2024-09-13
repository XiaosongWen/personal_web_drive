package com.xiaosong.api.model.dto.todolist;

import lombok.*;

@Data
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TodoItemDTO {
    private Long id;
    @NonNull
    private String description;
    private Boolean done;
}
