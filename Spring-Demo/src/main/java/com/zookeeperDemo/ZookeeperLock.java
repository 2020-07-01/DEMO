package com.zookeeperDemo;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName : ZookeeperLock
 * @Author : yq
 * @Date: 2021-01-24
 * @Description :
 */

@Slf4j
public class ZookeeperLock implements Lock {

    private static final String ZK_IP_POST = "";
    private static final String LOCK_NODE = "/LOCK";

    private ZkClient client = new ZkClient(ZK_IP_POST);

    //同时并发的线程数
    private static final int NUM = 10;
    private static CountDownLatch cdl = new CountDownLatch(NUM);
    private static Lock lock = new ReentrantLock();


    /**
     * 阻塞式加锁
     */
    @Override
    public void lock() {
        if (tryLock()) {
            return;
        }
        //阻塞 等待
        waitForLock();
        //再次加锁
        lock();
    }


    private void waitForLock() {

        IZkDataListener listener = new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {

            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                log.info("------------- get data delete event -----------");
                if (cdl != null) {
                    cdl.countDown();
                }
            }
        };

        //给节点加监听
        client.subscribeDataChanges(LOCK_NODE, listener);

        if (client.exists(LOCK_NODE)) {
            cdl = new CountDownLatch(1);
            try {
                cdl.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //给节点解除监听
        client.unsubscribeDataChanges(LOCK_NODE, listener);

    }


    /**
     * 非阻塞式加锁
     *
     * @return
     */
    @Override
    public boolean tryLock() {
        try {
            client.createPersistent(LOCK_NODE);
            return true;
        } catch (ZkNodeExistsException zkNodeExistsException) {
            return false;
        }
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        client.delete(LOCK_NODE);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }
}

