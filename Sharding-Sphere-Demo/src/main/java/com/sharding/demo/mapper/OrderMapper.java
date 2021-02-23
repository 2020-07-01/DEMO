package com.sharding.demo.mapper;

import com.sharding.demo.entity.Order;
import org.springframework.stereotype.Repository;

/**
 * @ClassName : UserMapper
 * @Author : yq
 * @Date: 2021-02-22
 * @Description :
 */
@Repository
public interface OrderMapper {

    void insert(Order order);
}
