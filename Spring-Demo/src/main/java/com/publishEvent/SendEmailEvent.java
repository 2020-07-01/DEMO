package com.publishEvent;

import org.springframework.context.ApplicationEvent;

/**
 * @ClassName : SendEmailEvent
 * @Author : yq
 * @Date: 2020-12-06
 * @Description : 发送邮件
 * 场景描述：当用户注册成功后发布发送邮件的事件
 * 订阅方收到通知之后执行发送邮件
 */
public class SendEmailEvent extends ApplicationEvent {

    /**
     * 用户名
     */
    private String name;

    /**
     * 邮件地址
     */
    private String email;

    /**
     * @param source
     */
    public SendEmailEvent(Object source) {
        super(source);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SendEmailEvent{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
