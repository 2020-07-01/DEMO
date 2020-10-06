package sort_algorithm;

import java.util.Arrays;

/**
 * @ClassName : MergeSort
 * @Author : yq
 * @Date: 2020-08-30
 * @Description :归并排序
 */
public class MergeSort {

    public static int[] mergeSort(int[] nums){
        if(nums.length < 2){
            return nums;
        }
        int mid = nums.length / 2;
        int[] left = Arrays.copyOfRange(nums,0,mid);
        int[] right = Arrays.copyOfRange(nums,mid,nums.length);

        return merge(mergeSort(left),mergeSort(right));

    }

    public static int[] merge(int[] left,int[] right){
        int[] result = new int[left.length + right.length];
        for(int index = 0,i = 0,j = 0;index<result.length;index++){
            if(i>=left.length){
                result[index] = right[j++];
            }else if(j>=right.length){
                result[index] = left[i++];
            }else if(left[i] < right[j]){
                result[index] = left[i++];
            }else {
                result[index] = right[j++];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arrays = new int[]{1,4,6,7,42,88,24324,43,43,4,123,2,44,36,657,435,3,4,46};
        int[] result = mergeSort(arrays);
        System.out.println(Arrays.toString(result));
    }
}
