package com.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @ClassName : Order
 * @Author : yq
 * @Date: 2021-10-02
 * @Description :
 */
@AllArgsConstructor
@Data
public class Order {

    private Integer id;

    private String orderNo;

    private String orderAddress;

    private Double totalPrice;

    private List<Product> productList;
}
