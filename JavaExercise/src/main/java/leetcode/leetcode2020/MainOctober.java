package leetcode.leetcode2020;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * 面试题 08.01. 三步问题
     * 与楼梯两步走相同
     * dp[i] = dp[i-1]+dp[i-2]+dp[i-3]
     * 动态规划算法
     *
     * @param n
     * @return
     */
    public int waysToStep(int n) {

        if (n == 0) {
            return 1;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        if (n == 3) {
            return 4;
        }

        long one = 1;
        long two = 2;
        long three = 4;

        for (int i = 4; i <= n; i++) {
            long temp2 = two;
            long temp3 = three;
            three = (one + two + three) % 1000000007;
            one = temp2;
            two = temp3;
        }
        return (int) three;
    }

    /**
     * 345. 反转字符串中的元音字母
     * 双指针法 与快速排序法类似
     * 时间复杂度O(n)
     *
     * @param s
     * @return
     */
    public String reverseVowels(String s) {

        if (s == null || s.length() == 0) {
            return s;
        }

        HashMap hashMap = new HashMap();
        hashMap.put('a', 'a');
        hashMap.put('e', 'e');
        hashMap.put('i', 'i');
        hashMap.put('o', 'o');
        hashMap.put('u', 'u');
        hashMap.put('A', 'A');
        hashMap.put('E', 'E');
        hashMap.put('I', 'I');
        hashMap.put('O', 'O');
        hashMap.put('U', 'U');
        int start = 0;
        int end = s.length() - 1;
        char[] chars = s.toCharArray();
        while (start < end) {

            while (!hashMap.containsKey(chars[start]) && start < end) {
                start++;
            }

            while (!hashMap.containsKey(chars[end]) && start < end) {
                end--;
            }

            if (start < end) {
                char temp = chars[start];
                chars[start] = chars[end];
                chars[end] = temp;
            }
            end--;
            start++;

        }
        return String.valueOf(chars);
    }

    /**
     * 28. 实现 strStr()
     * 时间复杂度O(n) 会超时
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {

        if (haystack == null || haystack.length() == 0) {
            return -1;
        }
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        int index = 0;
        while (index < haystack.length() - needle.length() + 1) {

            //如果首字母相同则开始比较
            if (haystack.charAt(index) == needle.charAt(0)) {
                if (haystack.substring(index, index + needle.length()).equals(needle)) {
                    return index;
                }
            } else {
                index++;
            }
        }
        return -1;
    }

    /**
     * 28. 实现 strStr()
     * 时间复杂度O(n)
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr1(String haystack, String needle) {
        int L = needle.length(), n = haystack.length();
        int start = 0;
        while (start < n - L + 1) {
            if (haystack.substring(start, start + L).equals(needle)) {
                return start;
            }
            start++;
        }

        return -1;
    }

    /**
     * 28. 实现 strStr()
     * 暴力算法
     * 时间复杂度O((n-l)l)
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr2(String haystack, String needle) {

        int N = haystack.length();
        int L = needle.length();
        int left = 0;
        int right = left + L;

        while (left < N - L + 1) {
            right = left + L;
            //此处可使用for循环进行比较
            if (haystack.substring(left, right).equals(needle)) {
                return left;
            }
            left++;
        }
        return -1;
    }


    /**
     * 1491. 去掉最低工资和最高工资后的工资平均值
     * 一次遍历获取最大值和最小值
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     * @param salary
     * @return
     */
    public double average(int[] salary) {

        /**
         * 一趟遍历获取获取最大值和最小值
         * 并计算sum
         */
        int sum = 0;
        int max = salary[0];
        int min = salary[0];
        for (int i = 0; i < salary.length; i++) {

            sum = sum + salary[i];
            max = max > salary[i] ? max : salary[i];
            min = min < salary[i] ? min : salary[i];
        }
        BigDecimal bigDecimal = new BigDecimal(sum - max - min);

        bigDecimal.divide(new BigDecimal(salary.length - 2));
        return bigDecimal.setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * 976. 三角形的最大周长
     * 2，3，3，6 类似这种情况，直接舍弃最后一位
     *
     * @param A
     * @return
     */
    public int largestPerimeter(int[] A) {
        if (A.length == 3) {
            if (A[0] + A[1] > A[2] && A[0] + A[2] > A[1] && A[1] + A[2] > A[0]) {
                return A[0] + A[2] + A[1];
            } else {
                return 0;
            }
        }
        if (A.length < 3) {
            return 0;
        }
        /**
         * 冒泡排序
         */
        for (int i = A.length - 1; i >= 0; i--) {

            for (int j = 0; j < i; j++) {
                if (A[j] > A[j + 1]) {
                    int temp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = temp;
                }
            }
        }

        for (int i = A.length - 3; i >= 0; --i) {
            if (A[i] + A[i + 1] > A[i + 2]) {
                return A[i] + A[i + 1] + A[i + 2];
            }

        }

        return 0;
    }

    /**
     * 844. 比较含退格的字符串
     * 超出内存限制
     *
     * @param S
     * @param T
     * @return
     */
    public boolean backspaceCompare(String S, String T) {


        String string1 = new String();

        int s1 = S.length() - 1;
        while (s1 >= 0) {
            if (S.charAt(s1) == '#') {
                s1 = s1 - 1;
            } else {
                string1 = string1 + S.charAt(s1);
            }
            s1--;
        }

        String string2 = new String();
        int t2 = T.length() - 1;
        while (t2 >= 0) {
            if (T.charAt(t2) == '#') {
                t2 = t2 - 1;
            } else {
                string2 = string2 + T.charAt(t2);
            }
        }
        return string1.equals(string2);

    }

    /**
     * 844. 比较含退格的字符串
     * 未解决
     *
     * @param S
     * @param T
     * @return
     */
    public boolean backspaceCompare1(String S, String T) {

        int s = S.length() - 1;
        int t = T.length() - 1;

        while (s >= 0 && t >= 0) {
            while (S.charAt(s) == '#') {
                s = s - 2;
            }

            while (T.charAt(t) == '#') {
                t = t - 2;
            }

            if (S.charAt(s) != T.charAt(t)) {
                return false;
            }
        }

        if (s > 0 || t > 0) {
            return false;
        }
        return true;
    }


    /**
     * 1576. 替换所有的问号
     *
     * @param s
     * @return
     */
    public String modifyString(String s) {

        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '?') {
                char ahead = i == 0 ? ' ' : chars[i - 1];
                char behind = i == chars.length - 1 ? ' ' : chars[i + 1];

                char j = 'a';
                while (j == ahead || j == behind) {
                    j++;
                }
                chars[i] = j;
            }
        }
        return new String(chars);
    }


    /**
     * 面试题 17.10. 主要元素
     * 时间复杂度O(n)
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {

        int maxSum = 0;
        int item = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            if (maxSum < (hashMap.getOrDefault(nums[i], 0) + 1)) {
                maxSum = hashMap.getOrDefault(nums[i], 0) + 1;
                item = nums[i];
                hashMap.put(nums[i], maxSum);
            } else {
                hashMap.put(nums[i], hashMap.getOrDefault(nums[i], 0) + 1);
            }
        }

        if (maxSum > nums.length / 2) {
            return item;
        } else {
            return -1;
        }

    }

    /**
     * 1450. 在既定时间做作业的学生人数
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     * @param startTime
     * @param endTime
     * @param queryTime
     * @return
     */
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int sum = 0;
        int index = 0;
        while (index < startTime.length) {
            if (startTime[index] <= queryTime && endTime[index] >= queryTime) {
                sum++;
            }
        }
        return sum;
    }


    /**
     * 1512. 好数对的数目
     * 暴力算法时间复杂度O(n2)
     * 空间复杂度O(1)
     *
     * @param nums
     * @return
     */
    public int numIdenticalPairs(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j] && i < j) {
                    sum++;
                }
            }
        }
        return sum;
    }

    /**
     * 1512. 好数对的数目
     * 借用赋值空间
     * 计算公式：v(v-1)/2
     * 世家复杂度O(n)
     * 孔家复杂度O(n)
     *
     * @param nums
     * @return
     */
    public int numIdenticalPairs1(int[] nums) {

        Map<Integer, Integer> hashMap = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i], hashMap.getOrDefault(nums[i], 0) + 1);
        }
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            int temp = entry.getValue() * (entry.getValue() - 1) / 2;
            sum = sum + temp;
        }

        return sum;
    }

    public static void main(String[] args) {

        MainOctober mainOctober = new MainOctober();
        int[] array = new int[]{2, 1, 312, 312, 321, 3, 123, 213, 33, 54, 35, 6, 5676, 65, 3, 41, 2, 1, 3, 4, 54, 321, 2};
        int[] array1 = new int[]{2, 5, 8};


        double m = -0.00;
        System.out.println(m);
    }
}
