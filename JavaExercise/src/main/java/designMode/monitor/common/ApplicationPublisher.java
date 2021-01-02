package designMode.monitor.common;

import java.util.EventObject;

/**
 * @ClassName : Publisher
 * @Author : yq
 * @Date: 2021-01-02
 * @Description : 事件发布者
 */
public interface ApplicationPublisher<E extends EventObject> {

    /**
     * 发布事件
     *
     * @param e 事件
     */
    void publishEvent(E e);

}
