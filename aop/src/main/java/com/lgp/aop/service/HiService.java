package com.lgp.aop.service;

import org.springframework.stereotype.Service;

/**
 * @AUTHOR lgp
 * @DATE 2018/8/3 16:41
 * @DESCRIPTION
 **/
@Service
public class HiService {
    public void index(String s) {
        System.out.println("index");

    }

//    public void log() {
//        System.out.println("log");
//    }
}
