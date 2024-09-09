package com.xiaosong.api.repository.system;

import com.xiaosong.api.model.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SysUserRepository extends JpaRepository<SysUser, Long> {
    List<SysUser> findSysUsersByEmail(String email);
}
