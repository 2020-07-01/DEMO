package com.cacheframework.autoload;

import java.util.Comparator;

/**
 * @ClassName : AutoLoadQueueSortType
 * @Author : yq
 * @Date: 2021-05-04
 * @Description : 自动加载队列排序算法
 */
public enum AutoLoadQueueSortType {
    /**
     * 默认顺序
     */
    NONE(0, null),

    OLDEST_FIRST(1, new AutoLoadOldestComparator());

    private final Integer id;

    private final Comparator<AutoLoadTO> comparator;

    AutoLoadQueueSortType(Integer id, Comparator<AutoLoadTO> comparator) {
        this.id = id;
        this.comparator = comparator;
    }

    public Comparator<AutoLoadTO> getComparator() {
        return comparator;
    }

}
