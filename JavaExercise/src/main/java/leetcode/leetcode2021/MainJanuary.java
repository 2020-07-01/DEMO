package leetcode.leetcode2021;

import javax.swing.tree.TreeNode;
import java.util.*;

/**
 * @ClassName : MainJanuary
 * @Author : yq
 * @Date: 2021-01-02
 * @Description :
 */
public class MainJanuary {


    int count = 0;

    /**
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays(int[] nums, int S) {
        calculate(nums, 0, 0, S);
        return count;
    }

    private void calculate(int[] nums, int i, int sum, int S) {
        if (i == nums.length) {
            if (S == sum) {
                count++;
            }
        } else {
            calculate(nums, i + 1, sum - nums[i], S);
            calculate(nums, i + 1, sum + nums[i], S);
        }
    }

    /**
     * 动态规划解法
     *
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays1(int[] nums, int S) {

        /**
         * dp[i][j] 表示前i个元素组成的方案数为j
         * 因此状态转移方程为
         * dp[i][j] = dp[i-1][j+nums[i]] + dp[i-1][j-nums[i]]
         */
        int[][] dp = new int[nums.length][2001];
        dp[0][nums[0] + 1000] = 1;
        dp[0][-nums[0] + 1000] = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[i - 1][sum + 1000] > 0) {
                    dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
                    dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
                }
            }
        }
        return S > 1000 ? 0 : dp[nums.length - 1][S + 1000];
    }


    /**
     * 0-1背包问题
     *
     * @param w
     * @param v
     * @param c 背包容量
     * @return
     */
    public int knapSack(int[] w, int[] v, int c) {
        int size = w.length;
        if (size == 0) {
            return 0;
        }

        int[][] dp = new int[size][c + 1];

        /**
         * dp[i][j] 表示前i个元素在j限时下的最大价值
         */
        /**
         * 初始化第一行
         * 仅考虑容量为c的背包放第0个物品的情况
         */
        for (int i = 0; i <= c; i++) {
            /**
             * 如果第i个物品的重量的小与c[i],则存放
             */
            dp[0][i] = w[i] <= i ? v[0] : 0;
        }

        //填充其他行和列
        for (int i = 1; i < size; i++) {
            for (int j = 0; j <= c; j++) {
                //将i-1个元素的价值复制给 i
                dp[i][j] = dp[i - 1][j];
                if (w[i] <= j) {
                    /**
                     * dp[i][j]当前元素/容量下的最大价值
                     * v[i] 当前元素的价值
                     * dp[i - 1][j - w[i]]前i个元素的最大价值
                     */
                    dp[i][j] = Math.max(dp[i][j], v[i] + dp[i - 1][j - w[i]]);
                }
            }
        }
        return dp[size - 1][c];
    }


    /**
     * 11. 盛最多水的容器
     * 双指针法求最大值
     * 时间复杂度O(n)
     *
     * @param height
     * @return
     */
    public int maxArea1(int[] height) {

        /**
         * 最大容量 = 两个指针距离 * 左右边界最小值
         */

        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left <= right) {
            maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return maxArea;
    }

    /**
     * 11. 盛最多水的容器
     * 时间复杂度(n2)
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {

        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = 0; j < height.length; j++) {
                maxArea = Math.max(maxArea, Math.min(height[i], height[j]) * Math.abs(i - j));
            }
        }
        return maxArea;
    }

    /**
     * 560. 和为K的子数组
     * 枚举法
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 0;
        int curSum;
        for (int i = 0; i < nums.length; i++) {
            curSum = 0;
            for (int j = i; j >= 0; j--) {
                curSum = curSum + nums[j];
                if (curSum == k) {
                    count++;
                }
            }
        }
        return count;
    }


    /**
     * 33. 搜索旋转排序数组
     * 迭代法
     * 时间复杂度O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            if (target == nums[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 33. 搜索旋转排序数组
     * 时间复杂度log2n
     *
     * @param nums
     * @param target
     * @return
     */
    public int search1(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            if (target == nums[left]) {
                return left;
            }
            if (target == nums[right]) {
                return right;
            }
            int mid = left + right >> 1;
            if (target == nums[mid]) {
                return mid;
            } else if (nums[mid] < nums[right]) {
                if (nums[mid] < target && target < nums[right]) {
                    //左侧无序，右侧有序
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (nums[left] < target && target < nums[mid]) {
                    //左侧有序，右侧无序
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 56. 合并区间
     * 时间复杂度O(log2n)
     * 空间复杂度O(log2n)
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {

        if (intervals == null || intervals.length == 0) {
            return intervals;
        }

        //先进行排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        /**
         * [[1,3],[2,6],[8,10],[15,18]]
         *  18,15,10,8,6,1
         */
        List<int[]> list = new LinkedList<>();

        for (int i = 0; i < intervals.length; i++) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            if (list.size() == 0 || list.get(list.size() - 1)[1] < left) {
                list.add(new int[]{left, right});
            } else {
                list.get(list.size() - 1)[1] = Math.max(right, list.get(list.size() - 1)[1]);
            }
        }
        return list.toArray(new int[list.size()][2]);
    }

    /**
     * 79. 单词搜索
     * 回溯法
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {

        /**
         * 思路:
         * 枚举法：从一个点触发
         * 向四个方向进行
         * 外层两个for循环遍历节点
         * 内部深度优先遍历
         */

        char[] words = word.toCharArray();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (check(board, words, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean check(char[][] board, char[] words, int i, int j, int index) {
        //边界值判断
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != words[index]) {
            return false;
        }
        //
        if (index == words.length - 1) {
            return true;
        }

        char temp = board[i][j];
        board[i][j] = '.';

        /**
         * 从上下左右四个方向找
         *  boolean res = check(board, words, i + 1, j, index + 1) || check(board, words, i - 1, j, index + 1) ||
         *             check(board, words, i, j + 1, index + 1) || check(board, words, i, j - 1, index + 1);
         */

        boolean res;
        //往右
        res = check(board, words, i, j + 1, index + 1);
        //往左
        res = res | check(board, words, i, j - 1, index + 1);
        //往下
        res = res | check(board, words, i + 1, j, index + 1);
        //往上
        res = res | check(board, words, i - 1, j + 1, index + 1);
        board[i][j] = temp;
        return res;
    }

    /**
     * 55. 跳跃游戏
     *
     * @param nums
     * 时间复杂度O(n)
     * @return
     */
    public boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= max) {
                max = Math.max(max, nums[i] + i);
                if (max >= nums.length - 1) {
                    return true;
                }
            }

        }
        return false;
    }



    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(1);
        linkedList.addFirst(100);
        linkedList.addFirst(10);
        Collections.sort(linkedList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println(linkedList);
    }
}
