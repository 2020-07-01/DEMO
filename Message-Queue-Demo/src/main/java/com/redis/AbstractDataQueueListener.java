package com.redis;


/**
 * @ClassName : AbstractDataQueueListener
 * @Author : yq
 * @Date: 2021-05-25
 * @Description :
 */
public abstract class AbstractDataQueueListener<T> implements DataQueueListener {


    protected abstract int consume();

    /**
     * 监听数据
     */
    @Override
    public void listen() {
        while (true) {

            int count = consume();
            if (count < 1) {
                //timed-watting 状态
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
