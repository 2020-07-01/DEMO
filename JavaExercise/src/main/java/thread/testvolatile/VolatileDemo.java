package thread.testvolatile;

/**
 * @ClassName : VolatileDemo
 * @Author : yq
 * @Date: 2020-09-16
 * @Description : volatile
 */
public class VolatileDemo {

    /**
     * 创建匿名线程，当flag为true时循环打印“flag为true”
     * 当主线程改变flag的值为false时，此值没有刷新到主内存中
     * 匿名线程会一直执行下去
     */
    static boolean flag = true;
    static volatile boolean flag1 = true;

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("flag为true");
                }
            }
        }).start();

        //改变值
        flag = false;

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag1) {
                    System.out.println("flag1为true");
                }
            }
        }).start();
        try {
            Thread.sleep(1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //改变值
        flag1 = false;
    }





}



