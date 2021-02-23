package com.sharding.demo.controller;

import com.sharding.demo.entity.Order;
import com.sharding.demo.mapper.OrderMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName : ShardingSphereController
 * @Author : yq
 * @Date: 2021-02-22
 * @Description :
 */

@RestController
@RequestMapping(value = "/test")
public class ShardingSphereController {

    @Resource
    private OrderMapper userMapper;

    @RequestMapping(value = "/insertUser")
    public void insert() {

        Order order = new Order();

        order.setOrderId(1L);
        order.setOrderNo("12321312");
        order.setUserId(2L);
        userMapper.insert(order);
    }


}
