package thread.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @ClassName : TimeThreadPool
 * @Author : yq
 * @Date: 2021-03-14
 * @Description :
 */
public class TimeThreadPoolExecutor extends ThreadPoolExecutor {

    public TimeThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }


    ThreadLocal threadLocal = new ThreadLocal();


    /**
     * 任务执行之前
     * 可进行执行前数据记录
     * <p>
     * 此方法执行时，线程池中的某个线程正在执行
     *
     * @param t 正在执行的线程
     * @param r
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        //任务详情
        System.out.println(Thread.currentThread().getName());

        threadLocal.set(t.getName());
    }


    /**
     * 任务执行之后
     * 前后数据对比并作分析
     *
     * @param r
     * @param t
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);

        threadLocal.get();
        //清理 否则会内存溢出
        threadLocal.remove();
    }

    @Override
    protected void terminated() {
        super.terminated();
    }

    public static TimeThreadPoolExecutor create(String threadPoolName) {
        ArrayBlockingQueue queue = new ArrayBlockingQueue(100);


        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat(threadPoolName + "-%d").build();

        TimeThreadPoolExecutor threadPoolExecutor = new TimeThreadPoolExecutor(50, 100, 1000 * 60 * 5, TimeUnit.MILLISECONDS, queue, threadFactory);


        return threadPoolExecutor;
    }


    public static void main(String[] args) {

        /**
         * 线程池核心数量的配置：
         * IO密集型-磁盘IO/网络IO：2N
         * CPU密集型：N+1
         */


        TimeThreadPoolExecutor threadPoolExecutor = TimeThreadPoolExecutor.create("pool");
        /**
         * 监控：
         * 活动线程数量/核心线程数量
         * 活动线程数量/最大线程数量
         * 被拒绝过的线程的数量
         */
        threadPoolExecutor.getCompletedTaskCount();
        threadPoolExecutor.getLargestPoolSize();
        threadPoolExecutor.getQueue().size();
        threadPoolExecutor.getTaskCount();

        for (int i = 0; i < 10; i++) {

            threadPoolExecutor.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
