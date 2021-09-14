package thread;

/**
 * @ClassName : ThreadInterruptDemo
 * @Author : yq
 * @Date: 2021-09-05
 * @Description :
 */
public class ThreadInterruptDemo {

    Object object = new Object();

    public static void main(String[] args) {

         ThreadInterruptDemo main = new ThreadInterruptDemo();
         main.test();

    }

    public void test1(){
        Thread thread = new Thread();
        //中断线程，设置中断状态
        thread.interrupt();

        //判断线程是否可中断的，不清除中断位
        thread.isInterrupted();

        //判断线程是否可中断的，清除中断位
        thread.interrupted();
    }


    public void test(){

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程A启动......");
                try {
                    System.out.println("线程A睡眠10秒......");
                    Thread.currentThread().sleep(10 * 1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        threadA.start();

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程B启动......");
                System.out.println("线程A中断......");
                threadA.interrupt();
            }
        });
        threadB.start();
    }

}
