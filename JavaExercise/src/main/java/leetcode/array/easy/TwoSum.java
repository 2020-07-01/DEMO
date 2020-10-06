package array.easy;

import java.util.Arrays;

/**
 * @author :qiang
 * @date :2019/11/2 下午2:05
 * @description :求数组中和为目标数的数字
 * @other :
 */
public class TwoSum {
    /**
     * 给定一个已按照升序排列的有序数组，找到两个数使得它们相加之和等于目标数。
     * 返回的下标值（index1 和 index2）不是从零开始的。
     * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
     *
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum(int[] numbers, int target) {

        /**
         * 最常用的方法:两层for循环
         */
        int[] result = new int[2];

        if (numbers[0] >= target) {
            result[0] = -1;
            result[1] = -1;
        }

        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    result[0] = i + 1;
                    result[1] = j + 1;
                    return result;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 54, 65,};
        System.out.println(Arrays.toString(twoSum(array, 5)));
    }
}
