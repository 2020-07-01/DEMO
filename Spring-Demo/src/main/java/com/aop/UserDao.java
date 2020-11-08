package com.aop;

import org.springframework.stereotype.Component;

/**
 * @ClassName : UserDao
 * @Author : yq
 * @Date: 2020-11-01
 * @Description :
 */
@Component
public class UserDao {

    public void query() {
        System.out.println("query db");
    }
}
