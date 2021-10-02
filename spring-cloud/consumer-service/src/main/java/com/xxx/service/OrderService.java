package com.xxx.service;

import com.bean.Order;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.util.Arrays;

/**
 * @ClassName : OrderService
 * @Author : yq
 * @Date: 2021-10-02
 * @Description :
 */
@Service
public class OrderService {

    @Autowired
    private ProductService productService;

    public Order selectOrderById(Integer id) {
        return new Order(id, "order-001", "中国", 2666D, Arrays.asList(productService.selectProductById(1)));
    }
}
