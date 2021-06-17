package com.redis;

import java.util.List;

/**
 * @ClassName : AbstractBatchHandlerListener
 * @Author : yq
 * @Date: 2021-05-25
 * @Description :
 */
public abstract class AbstractBatchHandlerListener<T> extends AbstractDataQueueListener<T> {

    protected int consume() {

        List<T> messages = pop();
        process(messages);
        return messages.size();
    }


    /**
     * 弹出数据
     *
     * @return
     */
    protected abstract List<T> pop();

    /**
     * 处理数据
     *
     * @param messages
     */
    protected abstract void process(List<T> messages);
}
