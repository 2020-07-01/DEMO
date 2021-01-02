package designMode.observer.demo1;


import java.util.Observable;

/**
 * @ClassName : WatcherEd
 * @Author : yq
 * @Date: 2021-01-02
 * @Description : 被观察者
 * 被观察者里面维护了观察者集合
 * 当被观察者发生变化时，观察者会通知所有被观察者
 */
public class WatchEd extends Observable {

    @Override
    public void notifyObservers(Object arg) {

        /**
         * 为了避免并发冲突，设置了 changed 标志位 changed=true，则当前线程可以通知所有观察者，内部同步块会设置为false；
         * 通知过程中，正在新注册的和撤销的无法通知到
         */
        super.setChanged();

        /**
         * 事件触发，通知所有感兴趣的观察者
         */
        super.notifyObservers(arg);
    }

}
