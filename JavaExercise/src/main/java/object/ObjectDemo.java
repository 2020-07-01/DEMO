package object;

import lombok.Synchronized;
import org.openjdk.jol.info.ClassData;
import org.openjdk.jol.info.ClassLayout;

/**
 * @ClassName : ObjectDemo
 * @Author : yq
 * @Date: 2021-09-10
 * @Description :
 */
public class ObjectDemo {
    final static String className = "java";
    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getId());
        Student student = new Student();
        System.out.println(ClassData.parseInstance(student));
        //查看对象信息
        System.out.println(ClassLayout.parseInstance(student).toPrintable());

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    synchronized (student){
                        System.out.println("持有对象锁");
                        System.out.println(ClassLayout.parseInstance(student).toPrintable());
                    }
                }

            }
        }).start();


    }


    public void test1() throws InterruptedException {
        Student student = new Student();
        //查看对象信息
        System.out.println(ClassLayout.parseInstance(student).toPrintable());

        /*for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (student){
                        System.out.println("获取锁");
                        System.out.println(ClassLayout.parseInstance(student).toPrintable());
                    }
                }
            }).start();
        }*/

        synchronized (student){
            System.out.println("持有对象锁");
            System.out.println(ClassLayout.parseInstance(student).toPrintable());
        }

        Thread.sleep(1000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (student){
                    System.out.println("线程1获取锁:");
                    System.out.println(ClassLayout.parseInstance(student).toPrintable());
                }
            }
        }).start();

        Thread.sleep(1000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (student){
                    System.out.println("线程2获取锁:");
                    System.out.println(ClassLayout.parseInstance(student).toPrintable());
                }
            }
        }).start();

        Thread.sleep(1000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (student){
                    System.out.println("线程3获取锁:");
                    System.out.println(ClassLayout.parseInstance(student).toPrintable());
                }
            }
        }).start();
    }


}
