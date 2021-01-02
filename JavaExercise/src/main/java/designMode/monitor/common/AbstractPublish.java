package designMode.monitor.common;

import java.util.EventObject;

/**
 * @ClassName : AbstractPublish
 * @Author : yq
 * @Date: 2021-01-02
 * @Description :
 */
public class AbstractPublish<E extends EventObject> implements ApplicationPublisher{


    /**
     * 发布事件
     *
     * @param eventObject 事件
     */
    @Override
    public void publishEvent(EventObject eventObject) {

    }
}
