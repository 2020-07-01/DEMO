package com.sharding.demo.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * @ClassName : Order
 * @Author : yq
 * @Date: 2021-02-23
 * @Description :
 */
public class Order {

    /**
     * 订单流水ID
     */
    @Id
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 用户流水ID
     */
    @Column(name = "user_Id")
    private Long userId;

    /**
     * 订单编号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 订单创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
