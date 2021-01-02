package designMode.monitor.common;

import java.util.EventObject;
import java.util.LinkedList;

/**
 * @ClassName : EventSource
 * @Author : yq
 * @Date: 2021-01-02
 * @Description : 事件源
 * 用来注册事件，并通知所有监听者
 */
public class EventSource {

    /**
     * 监听器维护列表
     */
    LinkedList<ApplicationListener> listenerLinkedList = new LinkedList<>();

    /**
     * 注册监听器
     *
     * @param applicationListener
     */
    public void addListener(ApplicationListener applicationListener) {
        listenerLinkedList.add(applicationListener);
    }

    /**
     * 删除监听器
     *
     * @param applicationListener
     */
    public void removeListener(ApplicationListener applicationListener) {
        listenerLinkedList.remove(applicationListener);
    }

    /**
     * 为所有的监听器通知监听的事件
     *
     * @param eventObject
     */
    public void notifyListener(EventObject eventObject) {
        for (ApplicationListener applicationListener : listenerLinkedList) {
            applicationListener.handler(eventObject);
        }
    }
}
