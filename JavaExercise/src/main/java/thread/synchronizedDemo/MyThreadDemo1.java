package thread.synchronizedDemo;

/**
 * @author yq
 * @date 2020/8/20 18:54
 * 线程同步问题：卖票
 */
public class MyThreadDemo1 {
    public static void main(String[] args) {

        MyThread myThread = new MyThread();

        new Thread(myThread, "A").start();
        new Thread(myThread, "B").start();
        new Thread(myThread, "C").start();

    }
}

class MyThread implements Runnable {
    private int ticket = 10;

    @Override
    public void run() {
        while (sale()) {

        }
    }

    public synchronized boolean sale() {
        if (this.ticket > 0) {
            System.out.println(Thread.currentThread().getName() + "卖票，ticket = " + this.ticket--);
            return true;
        } else {
            System.out.println("***** 票已经卖光了 *****");
            return false;
        }
    }
}

