package array.easy;

import java.util.Arrays;

/**
 * @author :qiang
 * @date :2019/10/14 下午10:25
 * @description :
 * @other :
 */
public class TureORFalse {

    /**
     * 给定一个整数数组，判断是否存在重复元素。
     * 如果任何值在数组中出现至少两次，函数返回 true。
     * 如果数组中每个元素都不相同，则返回 false。
     *
     * @param nums
     * @return
     */

    public boolean containsDuplicate(int[] nums) {

        /*
         * 先进行排序，如果后一个数字与前一个数字相同则返回false
         */
        if (nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return false;
        }
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1])
                return true;
        }
        return false;
    }
}
