package com.lgp.springjpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @AUTHOR lgp
 * @DATE 2018/1/19 9:36
 * @DESCRIPTION
 **/
@Entity
public class One {

    @Id
    @GeneratedValue
    private Long id;
    private Long name;

//    @OneToOne(cascade = CascadeType.ALL)
//    private Two two;

//    @OneToMany
//    private Set<Two> two;

//    @ManyToMany
//    private Set<Two> two;
}
