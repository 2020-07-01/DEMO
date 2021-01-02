package designMode.monitor.common;

import java.util.EventListener;
import java.util.EventObject;

/**
 * @ClassName : Listener
 * @Author : yq
 * @Date: 2021-01-02
 * @Description : 事件监听者
 */
public interface ApplicationListener<E extends EventObject> extends EventListener {

    /**
     * 监听者监听到事件之后进行处理
     *
     * @param e 事件
     */
    void handler(E e);


}

