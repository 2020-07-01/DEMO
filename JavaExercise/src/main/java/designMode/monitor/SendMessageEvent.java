package designMode.monitor;

import lombok.Data;

import java.util.EventObject;

/**
 * @ClassName : SendMessageEvent
 * @Author : yq
 * @Date: 2021-01-02
 * @Description : 发送短信事件
 */

public class SendMessageEvent extends EventObject {


    public SendMessageEvent(Object source) {
        super(source);
    }

    private String phone;

    private String name;

    private String messgae;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessgae() {
        return messgae;
    }

    public void setMessgae(String messgae) {
        this.messgae = messgae;
    }

    @Override
    public String toString() {
        return "SendMessageEvent{" +
                "phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", messgae='" + messgae + '\'' +
                '}';
    }
}
