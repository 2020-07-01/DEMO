package leetcode.leetcode2021;

import callback_function.example2.Main;
import dataStructure.list.Link;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName : MainJune
 * @Author : yq
 * @Date: 2021-06-01
 * @Description :
 */
public class MainJune {


    /**
     * 461. 汉明距离
     * 不同的位置的数目
     *
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {

        /**
         * bitCount:二进制数中值为1的位数个数
         */
        //异或：相同为1 相异为0
        return Integer.bitCount(x ^ y);

    }

    /**
     * 914. 卡牌分组
     * 统计次数 求公约数
     *
     * @param deck
     * @return
     */
    public boolean hasGroupsSizeX(int[] deck) {
        if (deck == null || deck.length == 1) {
            return false;
        }

        Map<Integer, Integer> map = new HashMap<>();

        Arrays.sort(deck);
        int minValue = deck[0];
        for (Integer item : deck) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }
        int minCount = map.get(minValue);
        boolean p = true;
        int i = 2;

        while (p) {
            boolean f = true;
            for (Integer item : map.values()) {
                if (item % i != 0) {
                    f = false;
                }
            }
            if (f) {
                return true;
            } else {
                i = i + 1;
                if (i > minCount) {
                    return false;
                }
            }
        }
        return true;
    }


    int ans = 0;

    /**
     * 494. 目标和
     *
     * @param nums
     * @param t
     * @return
     */
    public int findTargetSumWays(int[] nums, int t) {
        dfs(nums, t, 0, 0);
        return ans;
    }

    public void dfs(int[] nums, int t, int index, int current) {
        if (index == nums.length) {
            ans = ans + (current == t ? 1 : 0);
            return;
        }
        dfs(nums, t, index + 1, current + nums[index]);
        dfs(nums, t, index + 1, current - nums[index]);
    }

    /**
     * 279. 完全平方数
     * 动态规划
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {

        int[] dp = new int[n + 1];

        for (int i = 0; i <= n; i++) {

            dp[i] = i;//最差情况 设为最大值

            for (int j = 1; i - j * j >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n];
    }


    /**
     * 前提不重复
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {

        int start = 0;
        for (int i = 0; i < nums.length - 1; i++) {

            if (nums[i] > nums[i + 1]) {
                start = i;
                break;
            }
        }

        int end = 0;
        for (int i = nums.length - 1; i > 0; i--) {

            if (nums[i] < nums[i - 1]) {
                end = i;
                break;
            }
        }
        return end - start == 0 ? 0 : end - start + 1;
    }


    /**
     * 581. 最短无序连续子数组
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarray1(int[] nums) {

        int[] snums = nums.clone();
        Arrays.sort(snums);
        int start = snums.length;
        int end = 0;
        for (int i = 0; i < snums.length; i++) {
            if (snums[i] != nums[i]) {
                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }
        return (end - start) >= 0 ? (end - start + 1) : 0;
    }

    int max = 0;

    /**
     * 最大路径长度
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return max;
        }
        dfs(root);
        return max;
    }

    public int dfs(TreeNode node) {
        if (node.left == null && node.right == null) {
            return 0;
        }
        int leftSize = node.left == null ? 0 : dfs(node.left) + 1;
        int rightSize = node.right == null ? 0 : dfs(node.right) + 1;
        max = Math.max(max, leftSize + rightSize);
        return Math.max(leftSize, rightSize);
    }

    /**
     * 最大路径和
     * dfs
     *
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs1(root);
        return max;
    }

    private int dfs1(TreeNode node) {
        if (node.left == null && node.right == null) {
            return 0;
        }

        int leftSize = node.left == null ? 0 : (dfs1(node.left)) + 1;
        if (leftSize > 0 && node.val != node.left.val) {
            leftSize = 0;
        }
        int rightSize = node.right == null ? 0 : (dfs1(node.right)) + 1;
        if (rightSize > 0 && node.right.val != node.val) {
            rightSize = 0;
        }
        max = Math.max(rightSize + leftSize, max);
        return Math.max(leftSize, rightSize);
    }


    /**
     * 438. 找到字符串中所有字母异位词
     * 超时
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> res = new LinkedList<>();
        int left = 0;
        int right = p.length();
        while (right <= s.length()) {
            if (check(p, s.substring(left, right))) {
                res.add(left);
            }
            left++;
            right++;
        }
        return res;
    }

    private boolean check(String target, String string) {

        if (target.length() != string.length()) {
            return false;
        }
        int index = 0;
        while (index < string.length()) {
            char c = string.charAt(index);
            String s = String.valueOf(c);
            target = target.replaceFirst(s, "");
            index++;
        }

        return target.trim().equals("");
    }

    /**
     * 438. 找到字符串中所有字母异位词
     * 统计字母出现的词频
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams1(String s, String p) {

        List<Integer> res = new LinkedList<>();

        int n = s.length();
        int m = p.length();
        if (n < m) {
            return res;
        }
        int[] pCnt = new int[26];
        int[] sCnt = new int[26];
        for (int i = 0; i < m; i++) {
            pCnt[p.charAt(i) - 'a']++;
            sCnt[s.charAt(i) - 'a']++;
        }
        if (Arrays.equals(pCnt, sCnt)) {
            res.add(0);
        }
        for (int i = m; i < n; i++) {
            //第一个词的词频减一
            sCnt[s.charAt(i - m) - 'a']--;
            //当前词频+1
            sCnt[s.charAt(i) - 'a']++;
            if (Arrays.equals(sCnt, pCnt)) {
                res.add(i - m + 1);
            }
        }
        return res;
    }

    /**
     * 146. LRU 缓存机制
     * <p>
     * linkedHashMap 源码
     */
    class LRUCache extends LinkedHashMap<Integer, Integer> {
        private int capacity;

        public LRUCache(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }

    List<String> res = new ArrayList<>();

    /**
     * 22. 括号生成
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        if (n <= 0) {
            return res;
        }
        getParenthesis("", n, n);
        return res;
    }

    public void getParenthesis(String string, int left, int right) {
        //增加括号 减少n

        if (left == 0 && right == 0) {
            res.add(string);
        }
        if (left == right) {
            getParenthesis(string + "(", left - 1, right);
        } else if (left < right) {
            if (left > 0) {
                getParenthesis(string + "(", left - 1, right);
            }
            getParenthesis(string + ")", left, right - 1);
        }
    }


    /**
     * 64. 最小路径和
     * 动态规划
     * 有相似题型
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;

        int[][] dp = new int[row][col];
        dp[0][0] = grid[0][0];

        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < col; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    public static void main(String[] args) {

        MainJune main = new MainJune();
        int[] nums = new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3};
        System.out.println(main.findAnagrams("abab", "ab"));
    }
}
