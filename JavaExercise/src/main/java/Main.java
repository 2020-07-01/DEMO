import java.util.concurrent.*;

/**
 * @author :qiang
 * @date :2019/10/27 下午3:34
 * @description :
 * @other :
 */
public class Main {

    public static void main(String[] args) {

        BlockingQueue queue = new DelayQueue();
        /**
         * 默认工厂
         * 为线程池创建一组相同类型的线程
         * 主要声名线程名，
         * 是否为守护线程
         *
         */
        ThreadFactory threadFactory  = Executors.defaultThreadFactory();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,20,5000, TimeUnit.SECONDS,queue,threadFactory);


        Main main = new Main();
        main.test();
    }


    public void test() {

        Class c = this.getClass();
        System.out.println(c.getName());

        Class clazz = super.getClass();
        System.out.println(clazz.getName());
    }
}
