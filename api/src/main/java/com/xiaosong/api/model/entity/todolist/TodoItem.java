package com.xiaosong.api.model.entity.todolist;

import com.xiaosong.api.model.entity.SysUser;
import com.xiaosong.api.model.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@Builder
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
@NoArgsConstructor
@Setter
@Table(name  = "todo_item")
public class TodoItem extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_todo_item"), nullable = false)
    private SysUser user;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "is_done", nullable = false)
    private boolean isDone;

}
