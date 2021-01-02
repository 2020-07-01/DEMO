package designMode.monitor;

import designMode.monitor.common.ApplicationPublisher;

/**
 * @ClassName : PublisherDemo
 * @Author : yq
 * @Date: 2021-01-02
 * @Description :
 */
public class PublisherDemo implements ApplicationPublisher<SendMessageEvent> {

    /**
     * 发布事件
     *
     * @param sendMessageEvent 事件
     */
    @Override
    public void publishEvent(SendMessageEvent sendMessageEvent) {
        System.out.println("发布事件......");
    }
}
