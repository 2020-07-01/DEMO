package com.xxx.service;

import com.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName : ProductService
 * @Author : yq
 * @Date: 2021-10-02
 * @Description :
 */
@FeignClient(name = "product-service")
public interface ProductService {

    @GetMapping(value = "product-service/product/test")
    String test();

    @GetMapping(value = "product-service/product/{id}")
    Product selectProductById(@PathVariable("id") Integer id);

}
