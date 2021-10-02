package com.xxx.controller;

import com.bean.Product;
import com.xxx.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Product selectById(@PathVariable("id") Integer id, @RequestParam(value = "flag",required = false) String flag) {
        System.out.println(flag);
        return productService.findById(id);
    }
}
