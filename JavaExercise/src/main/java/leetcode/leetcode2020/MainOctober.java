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
        for (int i = 0; i < cost.length; i++) {
            int temp = second;
            second = Math.min(cost[i] + first, second + cost[i]);

            first = temp;
        }

        return Math.min(first, second);
    }


    /**
     * 面试题 02.02. 返回倒数第 k 个节点
     * 双指针算法
     * 时间复杂度O(n)
     *
     * @param head
     * @param k
     * @return
     */
    public int kthToLast(ListNode head, int k) {

        if (head == null) {
            return -1;
        }

        if (k < 1) {
            return -1;
        }

        int right = 1;
        ListNode node = head;
        while (right <= k && head != null) {
            head = head.next;
            right++;
        }

        while (head != null) {
            head = head.next;
            node = node.next;
        }

        return node.val;
    }


    /**
     * 面试题 10.01. 合并排序的数组
     * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
     * 双指针法
     * 时间复杂度O(m+n)
     * 空间复杂度O(m+n)
     *
     * @param A
     * @param m
     * @param B
     * @param n
     */
    public void merge(int[] A, int m, int[] B, int n) {

        int[] C = new int[A.length];

        int a = 0;
        int b = 0;
        int c = 0;
        while (a < m || b < n) {
            if (a < m && b < n) {
                if (A[a] < B[b]) {
                    C[c] = A[a];
                    a++;
                } else {
                    C[c] = B[b];
                    b++;
                }
                c++;
            } else if (a == m) {
                C[c] = B[b];
                b++;
                c++;
            } else {
                C[c] = A[a];
                a++;
                c++;
            }

        }
        System.arraycopy(C, 0, A, 0, m + n);
    }


    /**
     * 面试题 10.01. 合并排序的数组
     * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
     * 从后往前进行遍历
     * 时间复杂度O(m+n)
     * 空间复杂度O(m+n)
     *
     * @param A
     * @param m
     * @param B
     * @param n
     */
    public void merge1(int[] A, int m, int[] B, int n) {

        while (m > 0 && n > 0) {
            /**
             * 从后往前遍历元素，将值较大者放在最后
             */
            A[m + n - 1] = A[m - 1] > B[n - 1] ? A[--m] : B[--n];
        }
        /**
         * 如果n > 0;则说还有元素未合并
         */
        while (n > 0) {
            A[n] = B[--n];
        }
    }


    public static void main(String[] args) {

        MainOctober mainOctober = new MainOctober();
        int[] array = new int[]{1, 2, 3, 0, 0, 0};
        int[] array1 = new int[]{2, 5, 8};

        mainOctober.merge(array, 3, array1, 3);

        //System.out.println(mainOctober.minCostClimbingStairs(array1));
    }
}
