package dataStructure;

import java.util.*;

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

        PriorityQueue priorityQueue = new PriorityQueue();

        priorityQueue.add(100);
        priorityQueue.add(32);
        priorityQueue.add(43);
        priorityQueue.add(2);
        priorityQueue.add(324);
        priorityQueue.add(6);
        priorityQueue.remove(5);

        System.out.println(priorityQueue.peek());
        System.out.println(priorityQueue.peek());


        PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue();
        priorityBlockingQueue.add(4);

    }
}
