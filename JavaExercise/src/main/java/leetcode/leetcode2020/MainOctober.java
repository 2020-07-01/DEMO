package leetcode.leetcode2020;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;

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

    /**
     * 1365. 有多少小于当前数字的数字
     * 排序 + 映射
     * 时间复杂度 O(nlogn)
     * 空间复杂度 O(n)
     * <p>
     * 1.暴力算法
     * 2.计数排序
     *
     * @param nums
     * @return
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {

        int[] temp = new int[nums.length];
        System.arraycopy(nums, 0, temp, 0, nums.length);
        //排序nLogn
        Arrays.sort(nums);
        //遍历数组 n
        int[] arrays = new int[nums.length];
        arrays[0] = 0;
        HashMap<Integer, Integer> hashMap = new HashMap();
        hashMap.put(nums[0], 0);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                arrays[i] = arrays[i - 1];
                hashMap.put(nums[i], arrays[i]);
            } else {
                arrays[i] = i;
                hashMap.put(nums[i], arrays[i]);
            }
        }
        for (int i = 0; i < temp.length; i++) {
            temp[i] = hashMap.get(temp[i]);
        }

        return temp;
    }


    /**
     * 1365. 有多少小于当前数字的数字
     * 计数排序思想
     * 1.找到最大值
     * 2.计数每个元素出现的次数
     * 3.遍历通，计算小于当前元素的个数（dp）
     * 4.获取小于当前元素的元素个数
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param nums
     * @return
     */
    public int[] smallerNumbersThanCurrent1(int[] nums) {

        if (nums == null || nums.length == 0) {
            return nums;
        }
        //获取最大值
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max = max > nums[i] ? max : nums[i];
        }

        int[] bucket = new int[max + 1];

        for (int i = 0; i < nums.length; i++) {
            bucket[nums[i]] = bucket[nums[i]] + 1;
        }
        int temp = bucket[0];
        bucket[0] = 0;

        for (int i = 1; i < bucket.length; i++) {
            int temp1 = bucket[i];
            bucket[i] = temp + bucket[i - 1];
            temp = temp1;
        }

        for (int i = 0; i < nums.length; i++) {

            nums[i] = bucket[nums[i]];
        }
        return nums;
    }


    /**
     * 80. 删除排序数组中的重复项 II
     * <p>
     * 双指针法+原地删除
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {

        /**
         * 1.借助HashMap
         * 2.暴力解法
         * 3.计数法
         */
        int left = 0;
        int right = 2;

        int sum = nums.length;
        while (right < sum) {
            if (nums[left] == nums[right]) {
                System.arraycopy(nums, right + 1, nums, right, nums.length - right - 1);
                sum--;
            } else {
                left++;
                right++;
            }
        }

        return sum;
    }


    /**
     * 80. 删除排序数组中的重复项 II
     * 原地覆盖
     *
     * @param nums
     * @return
     */
    public int removeDuplicates1(int[] nums) {

        int i = 0;
        for (int n : nums) {
            if (i < 2 || n > nums[i - 2]) {
                nums[i++] = n;
            }
        }
        return i;

    }


    /**
     * 287. 寻找重复数
     * 1.暴力解法 时间复杂度O(n2) 空间复杂度O(1)
     * 2.HashMap 时间复杂度O(n) 空间复杂度O(n)
     * 3.快慢指针法
     * HashMap
     * 时间复杂度O(1)
     * 空间复杂度O(n)
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {

        int repeatNum = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.get(nums[i]) != null) {//判断时间复杂度O(1)
                return nums[i];
            } else {
                hashMap.put(nums[i], nums[i]);
            }
        }

        return repeatNum;
    }

    /**
     * 287. 寻找重复数
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     * @param nums
     * @return
     */
    public int findDuplicate1(int[] nums) {

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }
        return -1;

    }

    /**
     * 287. 寻找重复数
     * 快慢指针法
     * 将数组看作一个链表
     *
     * @param nums
     * @return
     */
    public int findDuplicate2(int[] nums) {

        int left = 0;
        int right = 0;
        while (left != right) {
            left = nums[left];
            right = nums[nums[right]];
        }
        //left = right时 存在环

        left = 0;
        while (left != right) {
            left = nums[left];
            right = nums[right];
        }
        return left;
    }

    /**
     * 剑指 Offer 47. 礼物的最大价值
     * 二维数据组问题
     * 动态规划思想
     * 时间复杂度O(m*n)
     * 空间复杂度O(1)
     *
     * @param grid
     * @return
     */
    public int maxValue(int[][] grid) {

        int row = grid.length;
        int col = grid[0].length;

        for (int i = 0; i < row; i++) {

            for (int j = 0; j < col; j++) {

                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) {
                    grid[i][j] = grid[i][j] + grid[i][j - 1];
                } else if (j == 0) {
                    grid[i][j] = grid[i][j] + grid[i - 1][j];
                } else {
                    grid[i][j] = grid[i][j] + Math.max(grid[i - 1][j], grid[i][j - 1]);
                }
            }
        }

        return grid[row - 1][col - 1];
    }

    /**
     * 面试题 17.11. 单词距离
     * 双指针法
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     * @param words
     * @param word1
     * @param word2
     * @return
     */
    public int findClosest(String[] words, String word1, String word2) {

        if (words == null || words.length == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int min = words.length;
        while (left < words.length && right < words.length) {
            while (left < words.length && !words[left].equals(word1)) {
                left++;
            }

            while (right < words.length && !words[right].equals(word2)) {
                right++;
            }
            if (left < words.length && right < words.length) {
                if (left > right) {
                    min = Math.min(min, (left - right));
                    right++;
                } else {
                    min = Math.min(min, (right - left));
                    left++;
                }
            } else {
                return min;
            }
        }

        return min;
    }

    /**
     * 86. 分隔链表
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {

        ListNode listNode1 = new ListNode(0);
        ListNode left = listNode1;

        ListNode listNode2 = new ListNode(0);
        ListNode right = listNode2;

        while (head != null) {
            if (head.val < x) {
                ListNode temp = new ListNode(head.val);
                left.next = temp;
                left = left.next;
            } else {
                ListNode temp = new ListNode(head.val);
                right.next = temp;
                right = right.next;
            }
            head = head.next;
        }

        /**
         * 在不创建新的节点时
         * 设置哑节点
         * 将最后一个节点的next节点指向null
         * 防止环形链表的出现
         */

        left.next = listNode2.next;
        return listNode1.next;
    }


    /**
     * 面试题 02.04. 分割链表
     * 双指针原地修改
     * 此题目示例答案与预期值不一样
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partition1(ListNode head, int x) {

        ListNode listNode1 = new ListNode(0);
        ListNode left = listNode1;

        ListNode listNode2 = new ListNode(0);
        ListNode right = listNode2;

        while (head != null) {
            if (head.val < x) {
                left.next = head;
                left = left.next;
            } else {
                right.next = head;
                right = right.next;
            }
            head = head.next;
        }

        /**
         * 在不创建新的节点时
         * 设置哑节点
         * 将最后一个节点的next节点指向null
         * 防止环形链表的出现
         */
        right.next = null;
        left.next = listNode2.next;
        return listNode1.next;
    }

    /**
     * 142. 环形链表 II
     * 哈希表法
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {

        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return head;
            } else {
                set.add(head);
            }
            head = head.next;
        }
        return null;
    }


    /**
     * 142. 环形链表 II
     * 慢指针法
     * 在任何时刻，快指针走过的路程为慢指针的两倍
     * 当快慢指针相遇时
     * 此时快指针在环内有可能已走过多圈
     * 定义第三个节点从头开始，与满指针遍历，当他们相遇时为入环点
     *
     * @param head
     * @return
     */
    public ListNode detectCycle1(ListNode head) {

        //慢指针
        ListNode node1 = head;
        //快指针
        ListNode node2 = head;

        while (node2 != null) {
            node1 = node1.next;
            if (node2.next != null) {
                node2 = node2.next.next;
            } else {
                return null;
            }

            if (node1 == node2) {
                //寻找入口点
                ListNode p = head;
                while (p != node1) {
                    p = p.next;
                    node1 = node1.next;
                }
                return p;
            }
        }
        return null;
    }


    /**
     * 75. 颜色分类
     * 计数排序法
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param nums
     */
    public void sortColors(int[] nums) {

        int[] bucket = new int[3];

        for (int i = 0; i < nums.length; i++) {
            bucket[nums[i]] = bucket[nums[i]] + 1;
        }

        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            int temp = bucket[i];
            while (temp > 0) {
                nums[index++] = i;
                temp--;
            }
        }
    }

    /**
     * 75. 颜色分类
     * 两次遍历数组
     * 将0排在前面
     * 将2排在后面
     * 原地修改数组
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     * @param nums
     */
    public void sortColors1(int[] nums) {

        int left = 0;
        int right = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[left];
                nums[left] = temp;
                left++;
            }
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == 2) {
                int temp = nums[i];
                nums[i] = nums[right];
                nums[right] = temp;
                right--;
            }
        }
    }

    /**
     * 75. 颜色分类
     * 三指针一次遍历
     * 待优化
     *
     * @param nums
     */
    public void sortColors2(int[] nums) {

        int index = 0;
        int left = 0;
        int right = nums.length - 1;

        while (index < nums.length) {
            if (nums[index] == 0) {
                int temp = nums[index];
                nums[index] = nums[left];
                nums[left++] = temp;
            } else if (nums[index] == 2) {
                int temp = nums[index];
                nums[index] = nums[right];
                nums[right--] = temp;
            } else {
                index++;
            }
            if (right < index) {
                return;
            }
        }
    }

    /**
     * 974. 和可被 K 整除的子数组
     *
     * @param A
     * @param K
     * @return
     */
    public int subarraysDivByK(int[] A, int K) {

        Map<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(0, 1);
        int sum = 0;
        int ans = 0;
        for (int i = 0; i < A.length; i++) {
            sum = sum + A[i];
            int modulus = (sum % K + K) % K;
            int same = hashMap.getOrDefault(modulus, 0);
            ans = ans + same;
            hashMap.put(modulus, same + 1);
        }

        return ans;
    }


    /**
     * 1351. 统计有序矩阵中的负数
     * 暴力算法
     * 时间复杂度O(n)
     *
     * @param grid
     * @return
     */
    public int countNegatives(int[][] grid) {

        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] < 0) {
                    sum++;
                }
            }
        }

        return sum;

    }

    /**
     * 1351. 统计有序矩阵中的负数
     * 利用二分查找  找到第一个小于0的索引
     *
     * @param grid
     * @return
     */
    public int countNegatives1(int[][] grid) {

        int sum = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                //最后一个元素大于0
                if (grid[i][grid[i].length - 1] >= 0) {
                    break;//跳出循环
                }
                //第一个元素小于0
                if (grid[i][j] < 0) {
                    sum = sum + grid[i].length;
                    break;
                }
                //第一个元素大于0.最后一个元素小于0
                //找到第一个小于0的索引
                if (grid[i][j] >= 0 && grid[i][grid[i].length - 1] < 0) {
                    int left = j;
                    int right = grid[i].length - 1;
                    while (left < right) {

                        int mid = left + (right - left) / 2;

                        if (grid[i][mid] >= 0) {
                            left = mid + 1;
                        } else {
                            //前一个不是负数
                            if (grid[i][mid - 1] >= 0) {
                                left = mid;
                                break;
                            }
                            right = mid;
                        }
                    }

                    sum = sum + (grid[i].length - left);
                    break;
                }
            }
        }
        return sum;
    }


    /**
     * 739. 每日温度
     * 暴力解法
     * 时间复杂度O(n2)
     * 空间复杂度O(n)
     *
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {

        int[] reslut = new int[T.length];
        HashMap<Integer, Integer> hashMap = new HashMap();

        for (int i = T.length - 1; i >= 0; i--) {
            int cur = T[i];
            int j = i + 1;
            while (j < T.length) {
                if (T[j] > cur) {
                    reslut[i] = j - i;
                    break;
                }
                j++;
            }

            if (j == T.length) {
                reslut[i] = 0;
            }
        }
        return reslut;
    }

    /**
     * 739. 每日温度
     * 此种方法时间超过限制
     *
     * @param T
     * @return
     */
    public int[] dailyTemperatures1(int[] T) {

        int[] reslut = new int[T.length];
        HashMap<Integer, Integer> hashMap = new HashMap();

        for (int i = T.length - 1; i >= 0; i--) {
            int cur = T[i];
            int j = i + 1;
            while (j < T.length) {

                if (T[j] > cur) {
                    reslut[i] = j - i;
                    break;
                } else {
                    if (hashMap.get(T[j]) == 0) {
                        reslut[i] = 0;
                        break;
                    } else {
                        j++;
                    }
                }
            }
            if (j == T.length) {
                reslut[i] = 0;
            }
            hashMap.put(T[i], j - i);
        }
        return reslut;
    }


    /**
     * 面试题 01.07. 旋转矩阵
     * 旋转90度
     * 使用辅助数组
     * aNew[i][j] = a[a.length-1-j][i]
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {

        int n = matrix.length;
        int[][] matrix_new = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix_new[j][n - i - 1] = matrix[i][j];
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix[i][j] = matrix_new[i][j];
            }
        }

    }


    /**
     * 771. 宝石与石头
     * <p>
     * 时间复杂度O(m+n)
     * 空间复杂度O(m)
     *
     * @param J
     * @param S
     * @return
     */
    public int numJewelsInStones(String J, String S) {

        //此处可使用HashSet减少空间
        HashMap<Character, String> hashMap = new HashMap();

        int index = 0;
        while (index < J.length()) {
            hashMap.put(J.charAt(index), "true");
            index++;
        }

        index = 0;
        int sum = 0;
        while (index < S.length()) {
            if (hashMap.containsKey(S.charAt(index))) {
                sum++;
            }
            index++;
        }
        return sum;
    }

    /**
     * 771. 宝石与石头
     * 时间复杂度O(n)
     *
     * @param J
     * @param S
     * @return
     */
    public int numJewelsInStones1(String J, String S) {

        int sum = 0;
        int index = 0;
        while (index < S.length()) {
            if (J.contains(String.valueOf(S.charAt(index)))) {
                sum++;
            }
            index++;
        }
        return sum;
    }

    /**
     * 面试题 10.05. 稀疏数组搜索
     * 退化为线性表
     *
     * @param words
     * @param s
     * @return
     */
    public int findString(String[] words, String s) {

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(s)) {
                return i;
            }
        }
        return -1;

    }

    /**
     * 69. x 的平方根
     * 二分法
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {

        if (x == 0) {
            return 0;
        }
        if (x == 1) {
            return 1;
        }

        int left = 0;
        int right = x;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (Math.pow(mid, 2) > x) {
                if (Math.pow((mid - 1), 2) < x) {
                    return mid - 1;
                }
                right = mid;
            } else if (Math.pow(mid, 2) < x) {
                if (Math.pow((mid + 1), 2) > x) {
                    return mid;
                }
                left = mid;
            } else {
                return mid;
            }
        }
        //负数
        return -1;
    }

    /**
     * 69. x 的平方根
     * <p>
     * 优化后的二分查找法
     *
     * @param x
     * @return
     */
    public int mySqrt1(int x) {
        if (x == 0) {
            return 0;
        }
        if (x == 1) {
            return 1;
        }
        int left = 0;
        int right = x;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (Math.pow(mid, 2) <= x) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    /**
     * 718. 最长重复子数组
     * 暴力解法
     * 遍历A
     * 再依次遍历B
     * 比较最长公共前缀
     * <p>
     * 超出时间限制
     * 时间复杂度O(n3)
     *
     * @param A
     * @param B
     * @return
     */
    public int findLength(int[] A, int[] B) {

        int max = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int k = 0;
                while ((i + k) < A.length && (j + k) < B.length) {
                    if (A[i + k] == B[j + k]) {
                        k++;
                    } else {
                        break;
                    }
                }
                max = Math.max(max, k);
            }
        }
        return max;
    }


    /**
     * 718. 最长重复子数组
     * 动态规划算法
     * 时间复杂度O(m*n)
     *
     * @param A
     * @param B
     * @return
     */
    public int findLength1(int[] A, int[] B) {

        int[][] dp = new int[A.length + 1][B.length + 1];
        int max = 0;
        int a = A.length;
        int b = B.length;
        for (int i = a - 1; i >= 0; i--) {

            for (int j = b - 1; j >= 0; j--) {
                /**
                 * 二维数组存储dp结果
                 * 从后往前遍历
                 */
                dp[i][j] = A[i] == B[j] ? (dp[i + 1][j + 1] + 1) : 0;
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }


    /**
     * 718. 最长重复子数组
     * 滑动窗口
     * 时间复杂度O((m+n) * min(m,n))
     *
     * @param A
     * @param B
     * @return
     */
    public int findLength2(int[] A, int[] B) {

        int max = 0;
        /**
         * A依次与B对齐的最大公共值
         * B依次与A对齐的最大公共值
         */

        //A依次与B对齐
        for (int i = 0; i < B.length; i++) {
            int k = 0;
            int length = Math.min(A.length, (B.length - i));
            for (int p = 0; p < length; p++) {
                if (A[p] == B[i + p]) {
                    k++;
                } else {
                    k = 0;
                }
                max = Math.max(max, k);
            }

        }

        //B依次与A对齐
        for (int i = 0; i < A.length; i++) {

            int k = 0;
            int length = Math.min(B.length, (A.length - i));
            for (int p = 0; p < length; p++) {
                if (B[p] == A[i + p]) {
                    k++;
                } else {
                    k = 0;
                }
                max = Math.max(max, k);
            }
        }

        return max;
    }


    /**
     * 454. 四数相加 II
     * Map
     * 时间复杂度O(n2)
     *
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {

        HashMap<Integer, Integer> map = new HashMap();

        int sum = 0;
        for (int i = 0; i < A.length; i++) {

            for (int j = 0; j < B.length; j++) {
                map.put(A[i] + B[j], map.getOrDefault(A[i] + B[j], 0) + 1);
            }
        }

        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                int temp = -(C[i] + D[j]);
                if (map.containsKey(temp)) {
                    sum = sum + map.get(temp);
                }
            }
        }
        return sum;
    }


    /**
     * 445. 两数相加 II
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();

        while (l1 != null) {
            stringBuilder1.append(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            stringBuilder2.append(l2.val);
            l2 = l2.next;
        }

        BigInteger bigInteger = new BigInteger(stringBuilder1.toString());

        BigInteger result = bigInteger.add(new BigInteger(stringBuilder2.toString()));
        String string = result.toString();

        Integer index = 0;
        ListNode node = new ListNode(0);
        ListNode cur = node;
        while (index < string.length()) {

            ListNode temp = new ListNode(string.charAt(index) - '0');
            cur.next = temp;
            cur = cur.next;
            index++;
        }

        return node.next;
    }


    /**
     * 445. 两数相加 II
     * 辅助栈法
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {


        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        ListNode cur = null;
        int mod = 0;
        while (!stack1.empty() && !stack2.empty()) {
            Integer i = (stack1.peek() + stack2.peek() + mod) % 10;
            mod = (stack1.pop() + stack2.pop() + mod) / 10;

            ListNode temp = new ListNode(i);
            temp.next = cur;
            cur = temp;
        }

        while (!stack1.empty()) {
            Integer i = (stack1.peek() + mod) % 10;
            mod = (stack1.pop() + mod) / 10;

            ListNode temp = new ListNode(i);

            temp.next = cur;
            cur = temp;
        }

        while (!stack2.empty()) {
            Integer i = (stack2.peek() + mod) % 10;
            mod = (stack2.pop() + mod) / 10;

            ListNode temp = new ListNode(i);
            temp.next = cur;
            cur = temp;
        }

        if (mod != 0) {
            ListNode temp = new ListNode(mod);
            temp.next = cur;
            cur = temp;
        }
        return cur;
    }

    /**
     * 228. 汇总区间
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums) {

        List<String> list = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return list;
        }

        int left = nums[0];
        for (int i = 1; i < nums.length; i++) {

            if (nums[i] != (nums[i - 1] + 1)) {
                if (left != nums[i - 1]) {
                    String string = left + "->" + nums[i - 1];
                    list.add(string);
                    left = nums[i];
                } else {
                    list.add(String.valueOf(left));
                    left = nums[i];
                }
            }
        }
        int n = nums.length - 1;
        if (left != nums[n]) {
            String string = left + "->" + nums[n];
            list.add(string);
        } else {
            list.add(String.valueOf(left));
        }

        return list;
    }

    /**
     * 228. 汇总区间
     * 双指针法
     *
     * @param nums
     * @return
     */
    public List<String> summaryRanges1(int[] nums) {

        List<String> list = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return list;
        }

        int left = 0;
        int right = 1;
        int pre = left;
        while (right < nums.length) {
            if (nums[right] != (nums[pre] + 1)) {
                if (nums[left] != nums[pre]) {
                    String string = nums[left] + "->" + nums[pre];
                    list.add(string);
                    left = right;
                    pre = right;
                } else {
                    list.add(String.valueOf(nums[left]));
                    left = right;
                    pre = right;
                }
            } else {
                pre = right;
            }
            right++;
        }

        if (nums[left] != nums[right]) {
            String string = nums[left] + "->" + nums[pre];
            list.add(string);
        } else {
            list.add(String.valueOf(nums[left]));
        }
        return list;
    }


    /**
     * 849. 到最近的人的最大距离
     * 数0的个数
     *
     * @param seats
     * @return
     */
    public int maxDistToClosest(int[] seats) {

        int pre = -1;
        int max = 1;

        for (int i = 0; i < seats.length; i++) {

            if (seats[i] == 1) {
                if (pre >= 0) {
                    max = Math.max(max, (i - pre) / 2);
                } else {
                    max = i;
                }

                pre = i;
            }
        }

        max = Math.max(max, (seats.length - 1 - pre));

        return max;
    }


    /**
     * 面试题 10.02. 变位词组
     * 排序方式结局唯一key问题
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();
        for (String string : strs) {

            char[] bin = string.toCharArray();
            Arrays.sort(bin);
            map.computeIfAbsent(String.valueOf(bin), unused -> new ArrayList<>()).add(string);

        }
        return new ArrayList<>(map.values());
    }


    /**
     * 面试题 16.02. 单词频率
     */
    class WordsFrequency {
        HashMap<String, Integer> dictionary = new HashMap();
        public WordsFrequency(String[] book) {
            for (String string : book) {
                dictionary.put(string, dictionary.getOrDefault(string, 0));
            }
        }

        public int get(String word) {
            return dictionary.getOrDefault(word,0);
        }
    }

    public static void main(String[] args) {

        MainOctober mainOctober = new MainOctober();
        int[] array = new int[]{0, 1, 2, 4, 5, 7, 8, 9, 10};
        int[] array1 = new int[]{3, 2, 1, 4, 7};

        String[] strings = new String[]{"looked", "just", "like", "her", "brother"};

        int[][] arrays3 = new int[][]{{4, 3, 3, 1, 1}, {1, 0, 0, -1, -1}, {-2, -2, -2, -2, -3}, {-2, -2, -2, -3, -3}, {-3, -3, -3, -3, -3}};

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);

        System.out.println(mainOctober.summaryRanges1(array));
    }
}
