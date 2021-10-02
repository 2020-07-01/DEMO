package com.xxx.controller;

import com.bean.Order;
import com.xxx.service.OrderService;
import com.xxx.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : ConsumerController
 * @Author : yq
 * @Date: 2021-10-02
 * @Description :
 */
@RequestMapping(value = "/consumer")
@RestController
public class ConsumerController {

    @Autowired
    ProductService productService;

    @GetMapping(value = "/test")
    public String test(){
        return "consumer service";
    }

    @GetMapping(value = "/test2")
    public String test2(){
        return productService.test();
    }

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/{id}")
    public Order selectOrderById(@PathVariable("id") Integer id) {
        return orderService.selectOrderById(id);
    }
}
