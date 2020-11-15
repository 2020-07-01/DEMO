package com.demo.service.impl;

import com.demo.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Priority;

/**
 * @ClassName : UserServiceImpl
 * @Author : yq
 * @Date: 2020-10-16
 * @Description :
 */
@Priority(value = 1)
@Primary
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {


    private String desc = "this is demo";

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 获取当前类的描述信息
     */
    @Override
    public String getUserServiceDesc() {

        return this.desc;
    }

}
