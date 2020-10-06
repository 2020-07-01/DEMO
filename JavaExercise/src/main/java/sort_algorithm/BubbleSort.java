package sort_algorithm;

import java.util.Arrays;

/**
 * @ClassName : BubbleSort
 * @Author : yq
 * @Date: 2020-08-30
 * @Description : 冒泡排序算法
 */
public class BubbleSort {

    /**
     * 从小到大
     * 从后往前遍历
     * @param nums
     */
    public static void bubblingSort(int[] nums) {

        for (int i = nums.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                //当前面的数大于后面的数时才两两交换
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 从小到大
     * @param nums
     */
    public static void bubblingSort2(int[] nums) {

        for(int i = 0;i<nums.length-1;i++){
            for(int j = 0;j<nums.length-1-i;j++){
                if(nums[j] > nums[j+1]){
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }

            }
        }
    }


    /**
     * 从小到大
     * 优化
     *
     * @param nums
     */
    public static void bubblingSort1(int[] nums) {
        boolean flag = true;
        for (int i = nums.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag == true) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        int[] arrays = new int[]{1, 14, 6, 7, 42, 88, 24324, 143, 43, 64, 123, 2, 44, 36, 657, 435, 3, 4, 46};
        bubblingSort2(arrays);
        System.out.println(Arrays.toString(arrays));
    }
}
