package sort_algorithm;

import java.util.Arrays;

/**
 * @ClassName : SelectSort
 * @Author : yq
 * @Date: 2020-08-30
 * @Description : 选择排序
 */
public class SelectSort {

    /**
     *
     * @param nums
     */
    public static void selectSort(int[] nums){
        if(nums == null || nums.length == 0){
            return;
        }
        for(int i = 0;i<nums.length-1;i++){
            int index = i; //索引
            for(int j = i+1;j<nums.length;j++){
                if(nums[j] < nums[index]){
                    index = j;
                }
            }
            int temp = nums[index];
            nums[index] = nums[i];
            nums[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arrays = new int[]{1, 14, 6, 7, 42, 88, 24324, 143, 43, 64, 123, 2, 44, 36, 657, 435, 3, 4, 46};
        selectSort(arrays);
        System.out.println(Arrays.toString(arrays));
    }
}
