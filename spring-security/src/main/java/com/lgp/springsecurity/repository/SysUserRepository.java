package com.lgp.springsecurity.repository;

import com.lgp.springsecurity.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @AUTHOR lgp
 * @DATE 2018/1/18 14:09
 * @DESCRIPTION
 **/

public interface SysUserRepository extends JpaRepository<SysUser, Long> {
    SysUser findByUsername(String username);
}
