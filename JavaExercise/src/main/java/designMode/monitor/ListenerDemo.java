package designMode.monitor;

import designMode.monitor.common.ApplicationListener;

/**
 * @ClassName : JavaDemo
 * @Author : yq
 * @Date: 2021-01-02
 * @Description : 监听发送短信事件
 */
public class ListenerDemo implements ApplicationListener<SendMessageEvent>{


    /**
     * 监听者监听到事件之后进行处理
     *
     * @param sendMessageEvent 事件
     */
    @Override
    public void handler(SendMessageEvent sendMessageEvent) {
        System.out.println("监听器监听到事件......" + sendMessageEvent.toString());
    }
}
