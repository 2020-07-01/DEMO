package array.easy;

import java.util.Arrays;

/**
 * @author :qiang
 * @date :2019/10/19 下午10:50
 * @description :第三大数
 * @other :
 */
public class ThirdMax {

    /**
     * 给定一个非空数组，返回此数组中第三大的数。
     * 如果不存在，则返回数组中最大的数。
     * 要求算法时间复杂度必须是O(n)。
     *
     * @param nums
     * @return
     */
    public int thirdMax(int[] nums) {

        int one = nums[0];
        int two = Integer.MIN_VALUE;
        int three = Integer.MIN_VALUE;
        if (nums.length < 3) {

            return nums[0] > nums[1] ? nums[0] : nums[1];
        } else {


            for (int i = 0; i < nums.length; i++) {

                if (nums[i] == one || nums[i] == two || nums[i] == three) {
                    continue;
                }

                if (nums[i] > one) {
                    three = two;
                    two = one;
                    one = nums[i];
                }

                if (nums[i] > two && nums[i] < one) {

                    three = two;
                    two = nums[i];
                }
                if (nums[i] > three && nums[i] < two) {
                    three = nums[i];
                }
            }
        }


        return three;
    }

}
