package com.xiaosong.api.repository.system;

import com.xiaosong.api.model.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface SysUserRepository extends JpaRepository<SysUser, UUID> {
    List<SysUser> findSysUsersByEmail(String email);
}
