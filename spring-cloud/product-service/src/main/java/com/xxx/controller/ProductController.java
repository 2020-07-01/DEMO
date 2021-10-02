package com.xxx.controller;

import com.bean.Product;
import com.xxx.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : ProductApplication
 * @Author : yq
 * @Date: 2021-10-02
 * @Description :
 */
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(value = "/test")
    public String test(){
        return productService.test();
    }

    @GetMapping(value = "/{id}")
    public Product selectById(@PathVariable("id") Integer id) {
        return productService.findById(id);
    }
}
