package leetcode.leetcode2021;

import javax.swing.text.SimpleAttributeSet;
import java.util.*;
import java.util.function.IntUnaryOperator;

/**
 * @ClassName : MainMarch
 * @Author : yq
 * @Date: 2021-03-01
 * @Description :
 */
public class MainMarch {

    /**
     * 61. 旋转链表
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {

        if (k <= 0 || head == null) {
            return head;
        }

        LinkedList<Integer> list = new LinkedList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        int startIndex = 0;
        int index = 0;
        if (list.size() >= k) {
            index = list.size() - k;
            startIndex = index;
        } else if (list.size() == 1) {
            index = 0;
            startIndex = 0;
        } else {
            int temp = k / list.size();
            k = k - list.size() * temp;
            index = k == 0 ? k : (list.size() - k);
            startIndex = index;
        }

        head = new ListNode();
        ListNode node = new ListNode(list.get(index));
        head.next = node;
        index++;
        while (index < list.size()) {
            ListNode temp = new ListNode(list.get(index));
            node.next = temp;
            node = temp;
            index++;
        }
        index = 0;
        while (index < startIndex) {
            ListNode temp = new ListNode(list.get(index));
            node.next = temp;
            node = temp;
            index++;
        }
        node.next = null;
        return head.next;
    }

    /**
     * 面试题 01.06. 字符串压缩
     *
     * @param S
     * @return
     */
    public String compressString(String S) {

        if (S == null || S.length() == 0) {
            return S;
        }
        StringBuilder stringBuilder = new StringBuilder();

        int index = 1;
        int count = 1;
        while (index < S.length()) {
            if (S.charAt(index - 1) != S.charAt(index)) {
                stringBuilder.append(S.charAt(index - 1)).append(count);
                count = 1;
            } else {
                count++;
            }
            index++;
        }
        stringBuilder.append(S.charAt(index - 1)).append(count);
        if (stringBuilder.length() >= S.length()) {
            return S;
        } else {
            return stringBuilder.toString();
        }
    }

    /**
     * 304. 二维区域和检索 - 矩阵不可变
     */
    class NumMatrix {
        int[][] matrix;

        /*public NumMatrix(int[][] matrix) {
            this.matrix = matrix;
        }
*/
        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for (int i = row1; i <= row2; i++) {
                for (int j = col1; j <= col2; j++) {
                    sum = sum + matrix[i][j];
                }
            }
            return sum;
        }

        /**
         * 前缀和优化法
         **/
        int[][] sums;

        public NumMatrix(int[][] matrix) {
            int m = matrix.length;
            if (m > 0) {
                int n = matrix[0].length;
                sums = new int[m][n + 1];
                for (int i = 0; i < m; i++) {
                    for (int j = 1; j < n; j++) {
                        sums[i][j + 1] = sums[i][j] + matrix[i][j];
                    }
                }
            }
        }

        public int sumRegion1(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for (int i = row1; i <= row2; i++) {
                sum = sum + (sums[i][col2 + 1] - sums[i][col1]);
            }
            return sum;
        }
    }

    /**
     * 338. 比特位计数
     * bitCount()
     *
     * @param num
     * @return
     */
    public int[] countBits(int num) {

        int[] res = new int[num + 1];

        for (int i = 0; i <= num; i++) {
            res[i] = Integer.valueOf(i).bitCount(i);
        }

        return res;
    }

    /**
     * 338. 比特位计数
     * 2的整数次幂二进制最高位数为1
     *
     * @param num
     * @return
     */
    public int[] countBits1(int num) {

        //标记上一个整数次幂
        int highBit = 0;
        int[] res = new int[num + 1];
        res[0] = 0;
        for (int i = 1; i <= num; i++) {
            //2的整数次幂
            if ((i & (i - 1)) == 0) {
                res[i] = 1;
                highBit = i;
            } else {
                res[i] = res[i - highBit] + 1;
            }
        }

        return res;
    }

    /**
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {

        //先对w进行递增排序
        //然后对h进行递减排序
        //忽略w,获取h的最长递增序列
        if (envelopes.length == 0) {
            return 0;
        }

        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o2[1] - o1[1];
                }
            }
        });
        int n = envelopes.length;
        int[] f = new int[n];
        Arrays.fill(f, 1);
        int ans = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (envelopes[j][1] < envelopes[i][1]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }

    /**
     * @param ops
     * @return
     */
    public int calPoints(String[] ops) {

        int index = 0;
        List<Integer> list = new LinkedList<>();
        list.add(Integer.valueOf(ops[0]));
        for (int i = 1; i < ops.length; i++) {
            if (ops[i].equals("+")) {
                list.add(list.get(index) + list.get(i - 1));
                index++;
            } else if (ops[i].equals("D")) {
                list.add(list.get(index) * 2);
                index++;
            } else if (ops[i].equals("C")) {
                list.remove(index);
                index--;
            } else {
                list.add(Integer.valueOf(ops[i]));
                index++;
            }
        }
        int count = 0;
        for (Integer item : list) {
            count = count + item;
        }
        return count;
    }


    int[][] f;
    List<List<String>> ret = new LinkedList<>();
    List<String> ans = new LinkedList<>();
    int n;

    /**
     * 131. 分割回文串
     * 官方解法
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {

        n = s.length();
        f = new int[n][n];
        dfs(s, 0);
        return ret;
    }

    private void dfs(String s, int i) {

        //最后一个元素
        if (i == n) {
            ret.add(new LinkedList<String>(ans));
            return;
        }

        for (int j = i; j < n; ++j) {
            //如果i到j是回文串
            if (isPalindrome(s, i, j) == 1) {
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }

    }

    /**
     * 0 : 未搜索
     * 1 : 是回文串
     * -1 : 不是回文串
     * 判断i到j是否未回文串
     *
     * @param s
     * @param i
     * @param j
     * @return
     */
    private int isPalindrome(String s, int i, int j) {
        //i到j是否为回文串
        if (f[i][j] != 0) {
            return f[i][j];
        }

        if (i >= j) {
            f[i][j] = 1;
        } else if (s.charAt(i) == s.charAt(j)) {
            f[i][j] = isPalindrome(s, i + 1, j - 1);
        } else {
            f[i][j] = -1;
        }
        return f[i][j];
    }


    /**
     * 判断是否递归优化方案
     */

    int[][] ft;

    /**
     * 通俗版本
     *
     * @param s
     * @return
     */
    public List<List<String>> partition1(String s) {

        int len = s.length();
        ft = new int[n][n];
        List<List<String>> lists = new LinkedList<>();

        if (len == 0) {
            return lists;
        }

        Deque<String> stack = new ArrayDeque<>();
        char[] charArray = s.toCharArray();

        /**
         * 解题思路：
         * 设置全局下标index
         * 从index开始
         * 判断index到i是否为回文串
         * 如果不是则判断index到i+1
         * 如果是回文串则判断,index = i，则判断下一个
         */
        dfs(charArray, 0, len, stack, lists);
        return lists;
    }


    /**
     * @param charArray
     * @param index
     * @param len
     * @param path
     * @param lists
     */
    private void dfs(char[] charArray, int index, int len, Deque<String> path, List<List<String>> lists) {

        //当递归到末尾时则此次划分符合要求
        if (len == index) {
            lists.add(new LinkedList<>(path));
            return;
        }

        for (int i = index; i < len; i++) {
            //判断字串是否为回文串
            if (!checkPalindrome(charArray, index, i)) {
                continue;
            }
            //如果是回文串则添加此字符串
            path.addLast(new String(charArray, index, i + 1 - index));
            //递归
            dfs(charArray, i + 1, len, path, lists);
            //回溯
            path.removeLast();
        }
    }

    /**
     * 判断是否为回文串
     *
     * @param charArray
     * @param left
     * @param right
     * @return
     */
    private boolean checkPalindrome(char[] charArray, int left, int right) {
        //此处采用动态规划或者记忆法进行优化
        while (left < right) {
            if (charArray[left] != charArray[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 优化方案
     *
     * @param charArray
     * @param left
     * @param right
     * @return
     */
    private boolean checkPalindrome1(char[] charArray, int left, int right) {

        //记忆优化法
        /**
         * 未判断 0
         * 是 1
         * 否 -1
         */

        if (ft[left][right] == 0) {

            //此处采用动态规划或者记忆法进行优化
            while (left < right) {
                if (charArray[left] != charArray[right]) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        } else if (ft[left][right] == -1) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * 503. 下一个更大元素 II
     * 暴力解法
     *
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {

        int length = nums.length;
        int[] res = new int[length];

        for (int i = 0; i < nums.length; i++) {
            int index = 0;
            int j = i + 1;
            while (index < length) {
                if (j < length) {
                    if (nums[j] > nums[i]) {
                        res[i] = nums[j];
                        break;
                    }
                    j++;
                    index++;
                } else if (j == length) {
                    j = 0;
                    if (nums[j] > nums[i]) {
                        res[i] = nums[j];
                        break;
                    }
                    j++;
                    index++;
                } else {
                    if (nums[j] > nums[i]) {
                        res[i] = nums[j];
                        break;
                    }
                    j++;
                    index++;
                }
            }
            if (index == length) {
                res[i] = -1;
            }
        }
        return res;
    }

    /**
     * 503. 下一个更大元素 II
     * 单调栈
     * 时间复杂度O(n)
     *
     * @param nums
     * @return
     */
    public int[] nextGreaterElements1(int[] nums) {

        int length = nums.length;
        int[] res = new int[length];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < length * 2; i++) {
            if (stack.isEmpty()) {
                stack.push(i % length);
            } else if (nums[i % length] > nums[stack.peek()]) {
                while (!stack.isEmpty() && nums[i % length] > nums[stack.peek()]) {
                    res[stack.pop()] = nums[i % length];
                }
                stack.push(i % length);
            } else {
                stack.push(i % length);
            }
        }
        return res;
    }

    /**
     * 395. 至少有 K 个重复字符的最长子串
     * 分治法 未理解
     *
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {

        int n = s.length();
        return dfs(s, 0, n - 1, k);
    }

    private int dfs(String s, int l, int r, int k) {
        //如果子串长度小于看，则返回
        if ((r - l + 1) < k) {
            return 0;
        }

        //存储元素出现的次数
        int[] cnt = new int[26];
        for (int i = l; i <= r; i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        //出现次数小于k的元素
        char split = 0;
        //判断元素出现的次数是否小于k
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0 && cnt[i] < k) {
                split = (char) (i + 'a');
                break;
            }
        }

        //如果没有元素，则到头
        if (split == 0) {
            return r - l + 1;
        }

        int i = l;
        int ret = 0;
        while (i <= r) {
            while (i <= r && s.charAt(i) == split) {
                i++;
            }
            if (i > r) {
                break;
            }
            int start = i;
            while (i <= r && s.charAt(i) != split) {
                i++;
            }

            int length = dfs(s, start, i - 1, k);
            ret = Math.max(ret, length);
        }
        return ret;
    }


    public static void main(String[] args) {

        for (int i = 0; i < 10; ++i) {
            System.out.println(i);
            return;
        }
    }

}

/**
 * 232. 用栈实现队列
 */
class MyQueue {

    Stack<Integer> stack1;
    Stack<Integer> stack2;

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
        stack1 = new Stack();
        stack2 = new Stack();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        if (stack1.empty()) {
            while (!stack2.empty()) {
                stack1.push(stack2.pop());
            }
        }
        stack1.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.empty()) {
            return -1;
        }
        return stack2.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.empty()) {
            return -1;
        }
        return stack2.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        if (stack1.empty() && stack2.empty()) {
            return true;
        } else {
            return false;
        }
    }


}

