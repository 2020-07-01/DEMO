package com.xxx.service;

import com.bean.Product;
import org.springframework.stereotype.Service;

/**
 * @ClassName : ProductService
 * @Author : yq
 * @Date: 2021-10-02
 * @Description :
 */
@Service
public class ProductService {

    public String test(){
        return "product service";
    }

    public Product findById(Integer id) {
        return new Product(id, "冰箱", 1, 26666D);
    }
}
