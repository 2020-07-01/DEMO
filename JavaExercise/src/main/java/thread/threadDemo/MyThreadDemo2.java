package thread.threadDemo;

/**
 * @author yq
 * @date 2020/8/20 19:54
 * 线程的停止
 * stop()方法会导致死锁，已停用
 */
public class MyThreadDemo2 {

    private static boolean flag = true;

    public static void main(String[] args) throws Exception {

        Thread userThread = new Thread(()->{
            int num = 0;
            for (int x = 0 ; x < 10 ; x++) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "正在运行、num = " + num++);
            }

        },"用户线程");

        Thread daemonThread = new Thread(()-> {
            for (int x = 0; x < Integer.MAX_VALUE; x++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "正在运行、x = " + x);
            }
        },"守护线程");

        daemonThread.setDaemon(true);
        userThread.start();
        daemonThread.start();

    }
}

