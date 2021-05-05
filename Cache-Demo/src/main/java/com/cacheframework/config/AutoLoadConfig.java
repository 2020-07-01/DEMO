package com.cacheframework.config;

import com.cacheframework.autoload.AutoLoadQueueSortType;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @ClassName : AutoLoadConfig
 * @Author : yq
 * @Date: 2021-04-27
 * @Description :
 */
@Data
@Component
public class AutoLoadConfig {

    /**
     * 命名空间
     */
    private String namespace;

    /**
     * 处理自动加载队列的线程数量
     */
    private Integer threadCnt = 10;

    private boolean printSlowLog = true;

    /**
     * Processing Map的初始大小
     */
    private int processingMapSize = 512;

    private AutoLoadQueueSortType sortType = AutoLoadQueueSortType.NONE;

    private boolean checkFromCacheBeforeLoad = false;

    /**
     * 自动加载队列中允许存放的最大容量
     */
    private int maxElement = 20000;

    /**
     * 是否启用DataLoader对象池
     */
    private boolean dataLoaderPooled = true;

    /**
     * 单个线程中执行自动加载的时间间隔
     */
    private int autoLoadPeriod = 50;


    /**
     * 自动加载耗时
     */
    private int loadUseTimeForAutoLoad1 = 10;

    /**
     * 自动加载耗时
     */
    private int loadUseTimeForAutoLoad2 = 200;

    /**
     * 加载数据重试次数，默认值为1：
     */
    private int loadDataTryCnt = 1;

}
