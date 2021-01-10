package dataStructure;

import java.util.*;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @ClassName : JavaDemo
 * @Author : yq
 * @Date: 2021-01-01
 * @Description :
 */
public class JavaDemo {

    public static void main(String[] args) {

        ArrayDeque arrayDeque = new ArrayDeque();


        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        concurrentLinkedQueue.add(1);
        concurrentLinkedQueue.remove();


        ConcurrentLinkedDeque concurrentLinkedDeque = new ConcurrentLinkedDeque();
        concurrentLinkedDeque.add(1);

    }

}
