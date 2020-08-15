package array.easy;

import java.util.Arrays;

/**
 * @author :qiang
 * @date :2019/10/1 下午3:51
 * @description :合并两个有序数组
 * @other :
 */
public class MergeArrays {

    /**
     * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中
     * 使得 num1 成为一个有序数组
     * m和n分别为数组的元素的数量
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        /*
         方法一：先合并之后再进行排序
         */
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }


}
