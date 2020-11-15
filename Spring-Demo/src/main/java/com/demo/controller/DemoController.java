package com.demo.controller;

import com.factoryBean.BeanService;
import com.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DemoController {


    @Autowired
    UserServiceImpl userService;

    @Autowired(required = false)
    BeanService beanService;




    @GetMapping("userName")
    public String getUserName() {

        String userName = userService.getUserServiceDesc();

        return userName;
    }


    @GetMapping("factoryBeanService")
    public String testFactoryBeanService() {
        try {
            /* FactoryBeanServiceImpl o = (FactoryBeanServiceImpl)factoryBeanService.getObject();*/
            beanService.testFactoryBean();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }


}
