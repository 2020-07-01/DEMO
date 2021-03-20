package thread.lock;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestCondition_Lock {


    final Lock lock = new ReentrantLock();

    final Condition notFull = lock.newCondition();

    final Condition notEmpty = lock.newCondition();


    final Object[] items = new Object[100];


    int putptr, takepptr, count;

    /**
     * 生产者
     * 当队列未满时生产
     *
     * @param x
     */
    @SneakyThrows
    public void put(Object x) {

        lock.lock();
        try {

            while (count == items.length) {
                //阻塞生产线程
                //队列已满，等待，直到not full时继续生产W
                //队列满了，此时not full 阻塞
                //此时当前需要完全释放锁
                //释放锁之前先要获取锁，否则当前节点释放锁失败
                notFull.await();
            }
            items[putptr] = x;
            if (++putptr == items.length) {
                putptr = 0;
            }
            //生产成功，发通知，此时消费者线程条件队列的头节点进入阻塞队列的尾部
            //此时不为空，唤醒消费者线程
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }
}
