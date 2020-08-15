package array.easy;


import java.util.Arrays;

/**
 * @author :qiang
 * @date :2019/10/2 下午4:54
 * @description :求和
 * @other :
 */
public class Sum {

    /**
     * 给定长度为 2n 的数组, 你的任务是将这些数分成 n 对,
     * 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从1 到 n 的 min(ai, bi) 总和最大。
     * 输入: [1,4,3,2] 输出: 4
     * 解释: n 等于 2, 最大总和为 4 = min(1, 2) + min(3, 4)A
     *
     * @param nums
     * @return
     */
    public static int arrayPairSum(int[] nums) {

        /**
         * 先将数组进行排序,然后从前到后,两两一对,将index为偶数的值相加
         */
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i = i + 2) {

            sum = sum + nums[i];
        }
        return sum;
    }

    /**
     * 求最大子序和
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {

        int result = nums[0];//保存最大的结果
        int sum = 0; //保存当前子序的和

        for (int i = 0; i < nums.length; i++) {
            if (sum < 0)//如果sum小于0，则从重新开始
            {
                sum = nums[i];
            } else {//如果大于0则继续添加
                sum = sum + nums[i];
            }
            result = Math.max(sum, result);//每一步更新最大的值
        }
        return result;
    }


    public static void main(String[] args) {

        int[] a = {1, 4, -3, 2};
        System.out.println(maxSubArray(a));
    }
}
