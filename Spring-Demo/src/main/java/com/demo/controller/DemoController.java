package com.demo.controller;


import com.demo.service.UserServiceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName :DemoController
 * @Author : yq
 * @Date: 2020-10-16
 * @Description :
 */
@RestController
@RequestMapping("demo")
public class DemoController implements InitializingBean {

    private String name;

    @Bean
    public void setName() {
        this.name = "name";
        System.out.println("初始化name.....");
    }

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/userName")
    public String getUserName() {

        String userName = userService.getUserServiceDesc();

        return userName;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化controller.....");
    }


}
