package leetcode.leetcode2020;

/**
 * @ClassName : MainOctober
 * @Author : yq
 * @Date: 2020-10-01
 * @Description : 10月份
 */
public class MainOctober {


    /**
     * 已通过题目 139
     * 提交未通过题目 7
     * 未开始题目 1667
     * 提交总数 460
     * 通过的提交 227
     * 提交通过率 49.35%
     */


    /**
     * 70. 爬楼梯
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        /**
         * f(x) = f(x-1) + f(x-2)
         */
        int f1 = 1;
        int f2 = 2;
        int sum = 0;
        for (int i = 3; i <= n; i++) {
            sum = f1 + f2;
            f1 = f2;
            f2 = sum;
        }

        return sum;
    }

    /**
     * 746. 使用最小花费爬楼梯
     * 遍历数组+动态规划
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {

        int first = 0;
        int second = 0;
        for (int i = 0; i < cost.length;i++) {
            int temp = second;
            second = Math.min(cost[i] + first,second + cost[i]);

            first = temp;
        }

        return Math.min(first,second);
    }


    public static void main(String[] args) {

        MainOctober mainOctober = new MainOctober();
        int[] array = new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        int[] array1 = new int[]{10,15,20};
        System.out.println(mainOctober.minCostClimbingStairs(array1));
    }
}
