package twoPointers;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

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
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
     * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {

       /*
       目的是将nums1数组重置为有序数组,所以先创建数组3,并复制数组1
        */
        int[] nums3 = new int[m];
        System.arraycopy(nums1, 0, nums3, 0, m);


        int p1 = 0;
        int p2 = 0;

        // Set pointer for nums1
        int p = 0;

        // Compare elements from nums1_copy and nums2
        // and add the smallest one into nums1.
        while ((p1 < m) && (p2 < n)) {
            nums1[p++] = (nums3[p1] < nums2[p2]) ? nums3[p1++] : nums2[p2++];
        }


        //将数组3复制到数组1的后面
        if (p1 < m)
            System.arraycopy(nums3, p1, nums1, p1 + p2, m + n - p1 - p2);
        //将数组数组2复制到数组1的后面
        if (p2 < n)
            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
    }
}
