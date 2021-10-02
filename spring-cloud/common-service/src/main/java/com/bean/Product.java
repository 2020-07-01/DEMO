package com.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName : Product
 * @Author : yq
 * @Date: 2021-10-02
 * @Description :
 */
@AllArgsConstructor
@Data
public class Product {


    private Integer id;

    private String productName;

    private Integer productNum;

    private Double productPrice;

}
