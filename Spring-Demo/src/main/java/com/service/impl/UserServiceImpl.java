package com.service.impl;

import com.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @ClassName : UserServiceImpl
 * @Author : yq
 * @Date: 2020-10-16
 * @Description :
 */
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
