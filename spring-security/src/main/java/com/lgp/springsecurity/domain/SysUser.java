package com.lgp.springsecurity.domain;

import com.lgp.springsecurity.domain.base.BaseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @AUTHOR lgp
 * @DATE 2018/1/17 14:50
 * @DESCRIPTION 用户表
 **/
@Entity
public class SysUser extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    /**
     * CascadeType.REFRESH：级联刷新，当多个用户同时作操作一个实体，为了用户取到的数据是实时的，
     * 在用实体中的数据之前就可以调用一下refresh()方法！
     * FetchType.EAGER：急加载,立即从数据库中加载
     */
    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<SysRole> roles;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public List<SysRole> getRoles() {
        return this.roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        List<SysRole> sysRoleList = this.getRoles();
        for (SysRole sysRole : sysRoleList) {
            grantedAuthorityList.add(new SimpleGrantedAuthority(sysRole.getName()));
        }
        return grantedAuthorityList;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
