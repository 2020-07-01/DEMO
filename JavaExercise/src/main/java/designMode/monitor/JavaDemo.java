package designMode.monitor;

import designMode.monitor.common.ApplicationPublisher;
import designMode.monitor.common.EventSource;


/**
 * @ClassName : JavaDemo
 * @Author : yq
 * @Date: 2021-01-02
 * @Description :
 */
public class JavaDemo {


    public static void main(String[] args) {


        /**
         * 1.创建事件
         * 2.发布事件
         * 3.注册监听器并监听事件
         * 4.监听到事件并响应
         */
        SendMessageEvent sendMessageEvent = new SendMessageEvent("发送短息");
        PublisherDemo publisherDemo = new PublisherDemo();
        publisherDemo.publishEvent(sendMessageEvent);



        EventSource eventSource = new EventSource();

        ListenerDemo listenerDemo = new ListenerDemo();
        eventSource.addListener(listenerDemo);
        eventSource.notifyListener(sendMessageEvent);

        /**
         * 存在的问题：
         * 存在类型的事件，监听听器只监听自己感兴趣的事件
         */
    }
}
