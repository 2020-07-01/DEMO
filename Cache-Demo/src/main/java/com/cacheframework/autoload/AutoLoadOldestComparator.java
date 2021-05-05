package com.cacheframework.autoload;

import java.util.Comparator;

/**
 * @ClassName : AutoLoadOldestComparator
 * @Author : yq
 * @Date: 2021-05-04
 * @Description : 排序算法，越接近过期时间，越耗时的排在前面
 */
public class AutoLoadOldestComparator implements Comparator<AutoLoadTO> {

    @Override
    public int compare(AutoLoadTO o1, AutoLoadTO o2) {
        return 0;
    }
}
