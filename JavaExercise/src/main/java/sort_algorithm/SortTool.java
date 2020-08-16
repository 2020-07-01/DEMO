package sort_algorithm;

import java.util.Arrays;

/**
 * @author :qiang
 * @date :2019/10/17 上午10:28
 * @description :排序算法总结
 * @other :
 */
public class SortTool {


    /**
     * 排序的理解：无论是对象还是基本深数据类型，排序总归是基于某一东西进行排序，无论是数字还是String
     *
     * 目前存在的排序算法：
     * 1、冒泡排序
     * 2、插入排序
     * 3、
     */


    /**
     * 冒泡排序
     * 从第一个数开始，两两进行比较，将大的数放在前面
     * 稳定排序
     * 时间复杂度：n-n的平方
     *
     * @param nums
     */
    public static void bubblingSort(int[] nums) {
        /**
         * 优化：如果没有发生交换则已排好序，终止循环
         */
        boolean flag = true;
        for (int i = nums.length - 1; i > 0; i--) {
            flag = true;
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag) {
                return;
            }

        }
    }

    /**
     * 插入排序
     * 从第二个元素开始，将后面的元素一次插入到前面排好序的元素的指定位置
     * 稳定排序
     * 时间复杂度：n-n的平方
     *
     * @param nums
     */
    public static void insertSort1(int[] nums) {

        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            for (int j = i; j > 0; j--) {
                if (nums[j] > nums[j - 1]) {
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                }
            }
        }
    }

    /**
     * 选择排序
     * 从第一个数开始，往后依次遍历，寻找最小的数，然后与第一个数进行交换
     * 不稳定排序
     * 时间复杂度： n的平方
     *
     * @param nums
     */
    public static void selectSort(int[] nums) {

        for (int i = 0; i < nums.length - 1; i++) {
            int min = nums[i];
            int k = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < min) {
                    min = nums[j];
                    k = j;
                }
            }
            nums[k] = nums[i];
            nums[i] = min;

        }
    }


    /***
     * 归并排序
     * 先将数组进行分离，直到剩下一个元素，然后再依次进行有序合并，采用递归的思想
     * 时间复杂度nlogn
     * @param nums
     */
    public static int[] mergeSort(int[] nums){
        //一个元素不再分离
        if (nums.length < 2) {
            return nums;
        }
        int mid = nums.length / 2;
        int[] left = Arrays.copyOfRange(nums,0,mid);
        int[] right = Arrays.copyOfRange(nums,mid,nums.length);

        return merge(mergeSort(left),mergeSort(right));

    }

    /**
     * 将两段排好序的数组合并在一起
     * @param left
     * @param right
     * @return
     */
    public static int[] merge(int[] left,int[] right){
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length) {
                result[index] = right[j++];
            } else if (j >= right.length) {
                result[index] = left[i++];
            } else if (left[i] > right[j]) {
                result[index] = right[j++];
            } else {
                result[index] = left[i++];
            }
        }
        return result;
    }






    public static void main(String[] args) {

        int[] array = {89, 4, 20, 34, 655, 768, 6, 789, 0, 980, 2342, 2, 21, 1};

        int[] result = mergeSort(array);

        System.out.println(Arrays.toString(result));



    }


}
