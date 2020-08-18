package leetcode.leetcode2020;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author yq
 * @date 2020/8/10 19:51
 */
public class MainAugust {


    /**
     * 数组中重复的数字
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(nums[i])) {
                return nums[i];
            } else {
                hashMap.put(nums[i], nums[i]);
            }

        }
        return -1;
    }

    /**
     * 数组中重复的数字
     * 下标为i的数字应该为i
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber1(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                if (nums[nums[i]] == nums[i]) {
                    return nums[i];
                } else {
                    int temp = nums[nums[i]];
                    nums[nums[i]] = nums[i];
                    nums[i] = temp;
                }
            }
        }
        return -1;
    }

    /**
     * 二维数组中的查找
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {

        if (matrix == null || matrix.length == 0) {
            return false;
        }

        if (matrix.length == 1 && matrix[0].length == 0) {
            return false;
        }

        int row = matrix.length;
        int col = matrix[0].length;

        if (matrix[0][0] > target || target > matrix[row - 1][col - 1]) {
            return false;
        }

        for (int i = 0; i < row; i++) {
            if (matrix[i][0] <= target && matrix[i][col - 1] >= target) {
                for (int k = 0; k < col; k++) {
                    if (matrix[i][k] == target) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 替换空格
     *
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        if (s == null) {
            return null;
        }
        if (!s.contains(" ")) {
            return s;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == ' ') {
                stringBuffer = stringBuffer.append("%20");
            } else {
                stringBuffer = stringBuffer.append(s.charAt(i));
            }
            i++;
        }

        return stringBuffer.toString();
    }


    /**
     * 替换空格
     *
     * @param s
     * @return
     */
    public String replaceSpace1(String s) {
        s.replace(" ", "%20");
        return s;
    }

    /**
     * 回文数
     * 不能直接将数字进行反转，否则有可能会出现数值越界
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {

        String string = Integer.toString(x);
        int i = 0;
        int size = string.length();
        while (i < string.length() / 2) {
            if (string.charAt(i) != string.charAt(size - i - 1)) {
                return false;
            }
            i++;
        }
        return true;
    }


    /**
     * 回文数
     *
     * @param x
     * @return
     */
    public boolean isPalindrome1(int x) {

        String oldString = new StringBuffer(Integer.toString(x)).toString();
        String newString = new StringBuffer(oldString).reverse().toString();
        if (oldString.equals(newString)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 回文数
     * 数学方法
     *
     * @param x
     * @return
     */
    public boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        }

        int p = x;
        int cur = 0;
        while (p != 0) {
            cur = cur * 10 + p % 10;
            p = p / 10;
        }

        return cur == x;
    }

    /**
     * 左旋转字符串
     *
     * @param s
     * @param n
     * @return
     */
    public String reverseLeftWords(String s, int n) {

        String string = s + s;
        return string.substring(n, s.length() + n);
    }


    /**
     * 合并两个排序的链表合并两个排序的链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode root = l1.val > l2.val ? new ListNode(l1.val) : new ListNode(l2.val);
        ListNode p = root;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                p = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                p = l2;
                l2 = l2.next;
            }
        }
        p.next = l1 != null ? l1 : l2;

        return root;
    }

    /**
     * 和为s的两个数字
     *
     * @param nums   递增排序的数组
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {

        int[] result = new int[2];
        if (nums == null || nums.length == 0) {
            return result;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            for (int k = i + 1; k < nums.length; k++) {
                if (nums[i] + nums[k] == target) {
                    result[0] = nums[i];
                    result[1] = nums[k];
                    return result;
                }
            }
        }
        return result;
    }

    /**
     * 和为s的两个数字
     *
     * @param nums   递增排序的数组
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {
        int[] result = new int[2];
        if (nums == null || nums.length == 0) {
            return result;
        }
        HashMap<Integer, Integer> hashMap = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.get(target - nums[i]) != null) {
                result[0] = nums[i];
                result[1] = target - nums[i];
                return result;
            } else {
                hashMap.put(target - nums[i], target - nums[i]);
            }
        }
        return result;
    }

    /**
     * 和为s的两个数字
     * 双指针法
     *
     * @param nums   递增排序的数组
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] == target) {
                return new int[]{nums[left], nums[right]};
            } else if (nums[left] + nums[right] > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[2];
    }

    int constant = 1000000007;

    /**
     * 斐波那契数列
     * 创建
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        int result = fib(n, new HashMap<Integer, Integer>());
        return result;
    }

    private int fib(int n, HashMap<Integer, Integer> hashMap) {

        if (n < 2) {
            return n;
        }

        if (hashMap.containsKey(n)) {
            return hashMap.get(n);
        }
        int fib1 = fib(n - 1, hashMap) % constant;
        hashMap.put(n - 1, fib1);
        int fib2 = fib(n - 2, hashMap) % constant;
        hashMap.put(n - 2, fib2);
        int result = (fib1 + fib2) % constant;
        hashMap.put(n, result);
        return result;

    }

    /**
     * 斐波那契数列
     * 非递归方式
     *
     * @param n
     * @return
     */
    public int fib1(int n) {
        if (n == 0) {
            return 0;
        }
        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;
        for (int i = 2; i < nums.length; i++) {
            nums[i] = nums[i - 1] + nums[i - 2];
            nums[i] = nums[i] % constant;
        }

        return nums[n];
    }

    /**
     * 两数相加
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode x = new ListNode(0);
        ListNode result = x;
        int temp = 0;
        while (l1 != null && l2 != null) {
            if ((l1.val + l2.val + temp) >= 10) {
                int newNum = (l1.val + l2.val + temp) % 10;
                ListNode node = new ListNode(newNum);
                result.next = node;
                result = result.next;
                temp = 1;
                l1 = l1.next;
                l2 = l2.next;
            } else {
                ListNode node = new ListNode(l1.val + l2.val + temp);
                result.next = node;
                result = result.next;
                temp = 0;
                l1 = l1.next;
                l2 = l2.next;
            }
        }

        if (l1 != null) {

            while (l1 != null) {
                if (l1.val + temp >= 10) {
                    int newNum = (l1.val + temp) % 10;
                    ListNode node = new ListNode(newNum);
                    result.next = node;
                    result = result.next;
                    temp = 1;
                    l1 = l1.next;
                } else {
                    ListNode node = new ListNode(l1.val + temp);
                    result.next = node;
                    result = result.next;
                    temp = 0;
                    l1 = l1.next;
                }
            }

        }

        if (l2 != null) {
            while (l2 != null) {
                if (l2.val + temp >= 10) {
                    int newNum = (l2.val + temp) % 10;
                    ListNode node = new ListNode(newNum);
                    result.next = node;
                    result = result.next;
                    temp = 1;
                    l2 = l2.next;
                } else {
                    ListNode node = new ListNode(l2.val + temp);
                    result.next = node;
                    result = result.next;
                    temp = 0;
                    l2 = l2.next;
                }
            }
        }

        if (temp == 1) {
            ListNode node = new ListNode(1);
            result.next = node;
            result = result.next;
        }

        return x.next;
    }

    /**
     * 无重复字符的最长子串
     * 借助额外空间
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int[] m = new int[128];
        int len = 0;
        for (int i = 0, j = 0; j < s.length(); j++) {
            //如果此字符重复则返回此字符出现的位置
            i = Math.max(m[s.charAt(j)], i);
            //获取最大长度
            len = Math.max(len, j - i + 1);
            //防止 aa的情况出现
            m[s.charAt(j)] = j + 1;
        }
        return len;

    }

    /**
     * 最长回文子串
     * 暴力解法
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {

        if (s.length() < 2) {
            return s;
        }
        int begin = 0;
        int maxLen = 1;//一个字符也是回文串
        char[] chars = s.toCharArray();
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (i - j + 1 > maxLen && isPalindrome(chars, j, i)) {
                    maxLen = i - j + 1;
                    begin = j;
                }
            }
        }

        return s.substring(begin, begin + maxLen);

    }

    //判断是否为回文串
    public boolean isPalindrome(char[] chars, int left, int right) {

        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 青蛙跳台阶问题
     * <p>
     * 递归法求解 采用hashmap进行优化
     *
     * @param n
     * @return
     */
    public int numWays(int n) {

        return numWays(n - 1, new HashMap<Integer, Integer>()) + numWays(n - 2, new HashMap<Integer, Integer>());
    }


    private int numWays(int n, HashMap<Integer, Integer> hashMap) {

        if (n < 2) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        if (hashMap.containsKey(n)) {
            return hashMap.get(n);
        }
        int fib1 = fib(n - 1, hashMap) % constant;
        hashMap.put(n - 1, fib1);
        int fib2 = fib(n - 2, hashMap) % constant;
        hashMap.put(n - 2, fib2);
        int result = (fib1 + fib2) % constant;
        hashMap.put(n, result);
        return result;

    }

    /**
     * 青蛙跳台阶问题
     * 转化为斐波那契求f(n)的值
     * 动态规划思想求解：将一个大问题划分为若干个子问题，但是各个子问题之间是相互依赖的
     *
     * @param n
     * @return
     */
    public int numWays1(int n) {
        if (n < 2) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        int[] db = new int[n + 1];
        db[0] = 1;
        db[1] = 1;
        db[2] = 2;
        for (int i = 2; i <= n; i++) {
            db[i] = (db[i - 1] + db[i - 2]) % constant;
        }
        return db[n] % constant;
    }


    /**
     * 有效的括号
     * O(n)
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {

        if (s.length() < 1) {
            return true;
        }

        /**
         * ASCII  [] {}     ()
         */

        Stack<Character> stack = new Stack<>();
        stack.push(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                continue;
            }
            if (stack.isEmpty()) {
                stack.push(s.charAt(i));
            } else {
                if (flag(stack.peek(), s.charAt(i))) {
                    stack.pop();
                } else {
                    stack.push(s.charAt(i));
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        }

        return false;
    }


    private boolean flag(char c1, char c2) {

        if (Math.abs(c1 - c2) == 1 || Math.abs(c1 - c2) == 2) {
            return true;
        }

        return false;
    }

    /**
     * 暴力解法
     * 进行有效括号的替换
     *
     * @param s
     * @return
     */
    public boolean isValid1(String s) {
        s.replace(" ", "");
        if (s.length() == 0) {
            return true;
        }
        boolean flag = true;
        while (flag) {

            if (s.contains("{}")) {
                s = s.replace("{}", "");
                continue;
            } else if (s.contains("()")) {
                s = s.replace("()", "");
            } else if (s.contains("[]")) {
                s = s.replace("[]", "");
            } else {
                flag = false;
            }
            System.out.println(s);
        }
        if (s.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断环形链表
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {

        Set set = new HashSet();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            } else {
                set.add(head);
                head = head.next;
            }
        }
        return false;
    }

    /**
     * 打印从1到最大的n位数
     * 大数越界，应该考虑使用String来表示
     *
     * @param n
     * @return
     */
    public int[] printNumbers(int n) {

        int max = (int) Math.pow(10, n);
        int[] result = new int[max];
        for (int i = 1; i < max; i++) {
            result[i - 1] = i;
        }
        return result;

    }

    /**
     * 109. 有序链表转换二叉搜索树
     * 分治法
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        TreeNode root = buildTree(head,null);
        return root;
    }

    public TreeNode buildTree(ListNode left,ListNode right){
        if(left == right){
            return null;
        }

        ListNode mid = getMid(left,right);
        TreeNode root = new TreeNode(mid.val);
        root.left = buildTree(left,mid);
        root.right = buildTree(mid.next,right);

        return root;
    }

    public ListNode getMid(ListNode left, ListNode right){
        //走两步和走一步
        ListNode fast = left;
        ListNode slat = left;
        while (fast != right && fast.next != right){
            fast = fast.next;
            fast = fast.next;
            slat = slat.next;
        }
        return slat;
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;

        }
    }


    /**
     * 节点
     */
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }


        /*int val;
        ListNode next;

       *//* ListNode(int x) {
            val = x;
        }*//*

        ListNode(int x) {
            val = x;
            next = null;
        }*/


    }

    public static void main(String[] args) {
        MainAugust main_august = new MainAugust();
        //int[][] mums = new int[][]{{1,   4,  7, 11, 15},{2,   5,  8, 12, 19},{3,   6,  9, 16, 22},{10, 13, 14, 17, 24},{18, 21, 23, 26, 30}};
        int[][] nums = new int[0][0];

        String s = "afasf";
        int[] array = new int[128];


        System.out.println(main_august.isValid1(" "));

    }
}


