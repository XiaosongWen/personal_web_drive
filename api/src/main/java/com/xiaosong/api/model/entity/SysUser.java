package com.xiaosong.api.model.entity;

import com.xiaosong.api.model.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@Builder
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
@NoArgsConstructor
@Setter
@Table(name  = "sys_user")
public class SysUser extends BaseEntity {
    @NonNull
    @Column(name = "first_name")
    private String firstName;
    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @NonNull
    @Column(name = "password")
    private String password;

    @NonNull
    @Column(name = "email")
    private String email;

    @Column(name = "avatar")
    private String avatar;
}
