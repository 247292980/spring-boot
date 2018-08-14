package com.lgp.springsecurity.service;

import com.lgp.springsecurity.domain.SysUser;
import com.lgp.springsecurity.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @AUTHOR lgp
 * @DATE 2018/1/18 14:11
 * @DESCRIPTION
 **/
@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    SysUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser user = userRepository.findByUsername(s);
        if (null == user) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        System.out.println("s:" + s);
        System.out.println("username:"+user.getUsername()+" password:"+user.getPassword());
        return user;
    }
}
