package thread.threadpool;

import java.util.concurrent.*;

/**
 * @ClassName : ThreadPoolExecuorFactory
 * @Author : yq
 * @Date: 2021-03-13
 * @Description :
 * 1.不同业务接口之间线程池相互隔离
 * 2.线程池的管理
 * 3.线程池参数动态配置
 * 4.线程池性能监控
 */
public class ThreadPoolFactory {

    /**
     * Spring环境下可做线程池工厂的初始化工作
     * 每种场景下的线程池可做差异化参数初始化
     */

    private ConcurrentHashMap ThreadPoolMap = new ConcurrentHashMap(1 << 4);

    /**
     * 线程池应该做统一管理，统一做创建销毁维护，每个业务接口只能创建一个线程池
     *
     * @param threadPollName
     * @return
     */
    public ThreadPoolExecutor create(String threadPollName) {

        ArrayBlockingQueue queue = new ArrayBlockingQueue(100);

        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,10,1000 * 60 * 5, TimeUnit.MILLISECONDS,queue);
        return threadPoolExecutor;
    }


    //TODO 定时任务检测线程池队列中任务数量


}
