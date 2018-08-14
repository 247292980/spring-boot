package com.lgp.springjpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @AUTHOR lgp
 * @DATE 2018/1/19 9:36
 * @DESCRIPTION
 **/
@Entity
public class Two {

    @Id
    @GeneratedValue
    private Long id;

    private Long name;

    @ManyToOne
    private One one;
}
