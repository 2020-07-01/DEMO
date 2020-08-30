package sort_algorithm;

import java.util.Arrays;

/**
 * @ClassName : InsertSort
 * @Author : yq
 * @Date: 2020-08-30
 * @Description : 插入排序
 */
public class InsertSort {


    /**
     * 从小到大进行排序
     * @param nums
     */
    public static void  insertSort(int[] nums){
        if(nums == null || nums.length == 0){
            return;
        }
        for(int i = 1;i<nums.length;i++){
            for(int j = i;j>0;j--){
                if(nums[j] < nums[j-1]){
                    int temp = nums[j];
                    nums[j] = nums[j-1];
                    nums[j-1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arrays = new int[]{1,4,6,7,42,88,24324,43,43,4,123,2,44,36,657,435,3,4,46};
        insertSort(arrays);
        System.out.println(Arrays.toString(arrays));
    }
}
