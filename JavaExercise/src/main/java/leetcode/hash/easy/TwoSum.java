package hash.easy;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author :qiang
 * @date :2019/10/29 下午6:55
 * @description :
 * @other :
 */

public class TwoSum {

    /**
     * 给定一个整数数组 nums 和一个目标值 target，
     * 请你在该数组中找出和为目标值的那 两个 整数，
     * 并返回他们的数组下标。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {

        /**
         * 穷举法：对于每一个元素同剩余的每一个元素进行相加然后比较
         * 时间复杂度为n的平法
         * 空间复杂度为1
         */
        int[] temp = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((nums[i] + nums[j]) == target) {
                    temp[0] = i;
                    temp[1] = j;
                    return temp;
                }
            }
        }

        return new int[]{-1, -1};
    }

    /**
     * 方法二：利用哈希表，遍历一次数组
     * 将遍历的每个元素存储在hashMap中，如果target - current 的值存在hashmap中则存储这样的一对值
     * 时间复杂度：n
     * 空间复杂度：1
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum1(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            if (hashMap.containsKey(target - nums[i])) {

                result[0] = hashMap.get(target - nums[i]);
                result[1] = i;
                return result;
            }

            hashMap.put(nums[i], i);

        }
        return new int[]{-1, -1};

    }

    public static void main(String[] args) {
        int[] array = {2, 7, 1, 5};
        System.out.println(Arrays.toString(twoSum(array, 6)));
    }
}
