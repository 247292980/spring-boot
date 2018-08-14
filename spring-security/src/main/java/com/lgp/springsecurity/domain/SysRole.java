package com.lgp.springsecurity.domain;

import com.lgp.springsecurity.domain.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @AUTHOR lgp
 * @DATE 2018/1/17 14:50
 * @DESCRIPTION 角色表
 **/

@Entity
public class SysRole extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;
    /**
     * 现在已经不需要@column
     * 但column仍有绑定数据库的字段功能
     * Column（name=“role-name”）
    * */
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
