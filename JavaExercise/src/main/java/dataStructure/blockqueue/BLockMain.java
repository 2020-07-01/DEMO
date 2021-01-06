package dataStructure.blockqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName : BLockMain
 * @Author : yq
 * @Date: 2021-01-05
 * @Description : 延迟队列
 */
public class BLockMain {

    public static void main(String[] args) {
        DelayQueue<Message> delayQueue = new DelayQueue();

        delayQueue.add(new Message("短信1", 1000 * 10L));
        delayQueue.add(new Message("短信2", 1000 * 20L));

        try {
            while (delayQueue.size() > 0) {
                System.out.println(delayQueue.take().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 预定酒店或者订餐服务
 * 订餐成功后60秒后发送短信给客户
 */
class Message implements Delayed {

    private Long start = System.currentTimeMillis();

    private String name;

    private Long time;

    Message(String name, Long time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    /**
     * 获取剩余延期时间
     * 延期时间 - 当前时间
     *
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert((start + time) - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }


    /**
     * 比较大小
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        Message message = (Message) o;
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - message.getDelay(TimeUnit.MILLISECONDS));
    }
}
