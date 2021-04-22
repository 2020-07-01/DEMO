package leetcode.leetcode2021;

import callback_function.example2.Main;
import org.omg.CORBA.INTERNAL;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;

/**
 * @ClassName : MainApril
 * @Author : yq
 * @Date: 2021-04-01
 * @Description :
 */
public class MainApril {

    /**
     * 1006. 笨阶乘
     *
     * @param N
     * @return
     */
    public int clumsy(int N) {
        if (N == 1) {
            return 1;
        }
        if (N <= 2) {
            return N * (N - 1);
        }
        String str = "*";
        LinkedList<Integer> linkedList = new LinkedList<>();
        boolean p = false;
        if (N % 2 != 0) {
            p = true;
        }
        int res = N;
        N--;
        while (N > 0) {
            switch (str) {
                case "*":
                    res = res * N;
                    str = "/";
                    break;
                case "/":
                    res = res / N;
                    str = "+";
                    break;
                case "+":
                    str = "*";
                    linkedList.addLast(res);
                    linkedList.addLast(N);
                    res = --N;
                    break;
            }
            if (N == 1) {
                linkedList.addLast(res);
            }
            N--;
        }
        int sum = linkedList.removeFirst();
        p = true;
        for (int i = 0; i < linkedList.size(); i++) {
            if (p) {
                sum = sum + linkedList.get(i);
                p = false;
            } else {
                sum = sum - linkedList.get(i);
                p = true;
            }
        }
        return sum;
    }

    /**
     * 371. 两整数之和
     *
     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {

        return Math.addExact(a, b);
    }

    /**
     * 371. 两整数之和
     *
     * @param a
     * @param b
     * @return
     */
    public int getSum1(int a, int b) {
        /**
         * 二进制各位相加相当于做异或操作
         * 计算进位值相当于与操作左移一位
         */
        while (b != 0) {
            int temp = a ^ b;
            b = (a & b) << 1;
            a = temp;
        }
        return a;
    }

    /**
     * 328. 奇偶链表
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {

        ListNode node1 = new ListNode(0);
        ListNode n1 = node1.next;

        ListNode node2 = new ListNode(0);
        ListNode n2 = node2.next;

        boolean p = true;
        head = head.next;
        while (head != null) {
            ListNode temp = new ListNode(head.val);
            if (p) {
                n1.next = temp;
                n1 = temp;
                p = false;
            } else {
                n2.next = temp;
                n2 = temp;
                p = true;
            }
            head = head.next;
        }

        n1.next = node2.next.next;

        return node1.next.next;
    }

    /**
     * 334. 递增的三元子序列
     * 不应该是连续的吗
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {

        /*for (int i = 0; i < nums.length - 2) {
            if (nums[i] < nums[i + 1] && nums[i] + 1 < nums[i + 2]) {
                return true;
            }
        }
        return false;*/

        int min = Integer.MAX_VALUE;
        int max = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= min) {
                min = nums[i];
            } else if (nums[i] <= max) {
                max = nums[i];
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 326. 3的幂
     * 超时
     *
     * @param n
     * @return
     */
    public boolean isPowerOfThree(int n) {
        if (n == 0) {
            return false;
        }
        if (n == 1 || n == 3) {
            return true;
        }
        int temp = 3;
        do {
            temp = temp * 3;
        } while (temp < n);

        if (temp == n) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 326. 3的幂
     *
     * @param n
     * @return
     */
    public boolean isPowerOfThree1(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

    /**
     * 324. 摆动排序 II
     * 逆序 从后往前插入
     *
     * @param nums
     */
    public void wiggleSort(int[] nums) {

        Arrays.sort(nums);
        int[] res = new int[nums.length];

        int n = nums.length;
        for (int i = 1; i < nums.length; i += 2) {
            res[i] = nums[--n];
        }
        for (int i = 0; i < nums.length; i += 2) {
            res[i] = nums[--n];
        }

        System.arraycopy(res, 0, nums, 0, nums.length);
    }

    /**
     * 81. 搜索旋转排序数组 II
     *
     * @param nums
     * @param target
     * @return
     */
    public boolean search(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param nums
     * @param target
     * @return
     */
    public boolean search11(int[] nums, int target) {

        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            if (nums[start] == target || nums[end] == target) {
                return true;
            }
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[start] < target && nums[mid] > target) {
                end = mid - 1;
            } else if (nums[end] > target && nums[mid] < target) {
                start = mid + 1;
            } else {
                start++;
            }
        }
        return false;
    }

    /**
     * 153. 寻找旋转排序数组中的最小值
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] <= nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }


    /**
     * 丑数
     *
     * @param n
     * @return
     */
    public boolean isUgly(int n) {

        if (n < 1) {
            return false;
        }
        //只包含质因子 2 3 5
        while (n % 5 == 0) {
            n = n / 5;
        }
        while (n % 3 == 0) {
            n = n / 3;
        }
        while (n % 2 == 0) {
            n = n >> 1;
        }

        if (n == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 剑指 Offer 49. 丑数
     * 动态规划法
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {

        int[] dp = new int[n];
        int index2 = 0;
        int index3 = 0;
        int index5 = 0;
        dp[0] = 1;
        int i = 1;
        while (i < n) {
            dp[i] = Math.min(dp[index2] * 2, Math.min(dp[index3] * 3, dp[index5] * 5));
            while (dp[index2] * 2 <= dp[i]) {
                index2++;
            }
            while (dp[index3] * 3 <= dp[i]) {
                index3++;
            }
            while (dp[index5] * 5 <= dp[i]) {
                index5++;
            }
        }
        return dp[n - 1];
    }


    /**
     * 剑指 Offer 49. 丑数
     * 最小堆法
     *
     * @param n
     * @return
     */
    public int nthUglyNumber1(int n) {
        if (n < 1) {
            return -1;
        }

        int[] nums = new int[]{2, 3, 5};
        //去重
        Set<Long> set = new HashSet<>();
        PriorityQueue<Long> queue = new PriorityQueue();
        queue.add(1L);
        set.add(1L);
        int res = 1;
        while (n > 0) {
            long temp = queue.poll();
            res = (int) temp;
            for (int i = 0; i < nums.length; i++) {
                if (set.add(temp * nums[i])) {
                    queue.offer(temp * nums[i]);
                }
            }
            n--;
        }
        return res;
    }

    /**
     * 179. 最大数
     *
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {

        String string = "";
        String[] strings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strings[i] = nums[i] + "";
            string = string + nums[i];
        }

        if (string.replaceAll("0", "").equals("")) {
            return "0";
        }
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String string1 = o1 + o2;
                String string2 = o2 + o1;
                return string2.compareTo(string1);
            }
        });

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            stringBuilder.append(strings[i]);
        }
        return stringBuilder.toString();
    }


    /**
     * 152. 乘积最大子数组
     * 次解法有问题，应该考虑到负数
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {

        int res = Integer.MIN_VALUE;
        int left = 0;
        int right = 0;
        int sum = 1;
        while (right < nums.length) {
            int temp = sum * nums[right];
            if (res > temp) {
                left = right;
                right++;
                sum = nums[left];
            } else {
                sum = temp == 0 ? 1 : temp;
                res = temp;
                right++;
            }
        }
        return res;

        /**
         * int max = Integer.MIN_VALUE, imax = 1, imin = 1;
         *         for(int i=0; i<nums.length; i++){
         *             if(nums[i] < 0){
         *               int tmp = imax;
         *               imax = imin;
         *               imin = tmp;
         *             }
         *             imax = Math.max(imax*nums[i], nums[i]);
         *             imin = Math.min(imin*nums[i], nums[i]);
         *
         *             max = Math.max(max, imax);
         *         }
         *         return max;
         */
    }

    /**
     * 152. 乘积最大子数组
     * 当存在负数时，最大值会变成最小值，最小值会变成最大值
     *
     * @param nums
     * @return
     */
    public int maxProduct1(int[] nums) {

        int max = Integer.MIN_VALUE;
        int imax = 1;
        int imin = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                //交换
                int temp = imax;
                imax = imin;
                imin = temp;
            }
            //取最大值和最小值
            imax = Math.max(imax * nums[i], imax);
            imin = Math.min(imin * nums[i], imin);
            max = Math.max(max, imax);
        }
        return max;
    }

    /**
     * 打家劫舍2
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] dp = new int[nums.length - 1];
        dp[0] = nums[0];
        dp[1] = nums[1];
        dp[1] = Math.max(dp[0], dp[1]);
        for (int i = 2; i < nums.length - 1; i++) {
            //选择
            if (nums[i] + dp[i - 2] > dp[i - 1]) {
                dp[i] = dp[i - 2] + nums[i];
            } else {
                dp[i] = dp[i - 1];
            }
        }

        int[] dp1 = new int[nums.length];
        dp1[dp1.length - 1] = nums[nums.length - 1];
        dp1[dp1.length - 2] = nums[nums.length - 2];
        dp1[dp1.length - 2] = Math.max(dp1[dp1.length - 1], dp1[dp1.length - 2]);
        for (int i = nums.length - 3; i > 0; i--) {
            //选择
            if (nums[i] + dp1[i + 2] > dp1[i + 1]) {
                dp1[i] = dp1[i + 2] + nums[i];
            } else {
                dp1[i] = dp1[i + 1];
            }
        }
        return Math.max(dp[dp.length - 1], dp1[1]);
    }

    /**
     * 220. 存在重复元素 III
     * TreeSet的事件复杂度logN
     * 整体时间复杂度nlogmin(n,k)
     *
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        //滑动窗口
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {

            //返回大于或者等于给定元素的最小元素
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) {
                return true;
            }
            //不存在
            set.add((long) nums[i]);
            //删除最前面的元素
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }


    /**
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int index = 0;
        int right = 1;
        nums[index] = nums[0];
        while (right < nums.length) {
            if (nums[index] != nums[right]) {
                index++;
                nums[index] = nums[right];
            }
            right++;
        }
        return index + 1;
    }

    int columns, rows;
    //上下左右四个方向
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    /**
     * 329. 矩阵中的最长递增路径
     * 记忆优化深度优先遍历
     *
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        /**
         * 对于每个节点有四个方向
         * 举证来表示节点的最长子路径
         */

        columns = matrix[0].length;
        rows = matrix.length;
        //缓存矩阵
        int[][] memo = new int[rows][columns];
        int ans = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                ans = Math.max(ans, dfs(matrix, i, j, memo));
            }
        }
        return ans;
    }

    private int dfs(int[][] matrix, int row, int column, int[][] meno) {

        //不为0表示此节点已经计算过
        if (meno[row][column] != 0) {
            return meno[row][column];
        }
        ++meno[row][column];
        for (int[] dir : dirs) {
            int newRow = row + dir[0];
            int newColumn = column + dir[1];

            if (newRow >= 0 && newRow < rows && newColumn < columns && newColumn >= 0 && matrix[newRow][newColumn] > matrix[row][column]) {
                meno[row][column] = Math.max(meno[row][column], dfs(matrix, newRow, newColumn, meno) + 1);
            }
        }
        return meno[row][column];
    }

    /**
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {

        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                if (index != i) {
                    nums[index] = nums[i];
                }
                index++;
            }
        }
        return index;
    }


    /**
     * 300. 最长递增子序列
     * 超时
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = new LinkedList<>();
            list.add(nums[i]);
            int temp = dfs(list, nums, i);
            res = Math.max(res, temp);
        }
        return res;
    }

    private int dfs(List<Integer> list, int[] nums, int index) {

        //到头
        if (index == nums.length - 1) {
            return list.size();
        }
        int temp = list.size();

        for (int i = index + 1; i < nums.length; i++) {
            List<Integer> listTemp = new LinkedList<>(list);
            if (nums[i] > nums[index]) {
                listTemp.add(nums[i]);
                temp = Math.max(dfs(listTemp, nums, i), temp);
            }
        }
        return temp;
    }

    /**
     * 300. 最长递增子序列
     * <p>
     * 时间复杂度n2
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS1(int[] nums) {

        if (nums.length == 1) {
            return 1;
        }
        //第i个元素结尾的最长递增子数组的长度
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    //前面有值小于nums[i]
                    //当前值
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }

        return maxans;
    }

    /**
     * @param s
     * @return
     */
    public int numDecodings(String s) {

        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 0; i <= n; i++) {
            if (s.charAt(i - 1) != '0') {
                f[i] = f[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                f[i] = f[i - 2];
            }
        }
        return f[n];
    }

    /**
     * @param root
     * @return
     */
    public int sumEvenGrandparent(TreeNode root) {

        return dfs(root);
    }

    private int dfs(TreeNode node) {
        int res = 0;
        if (node == null) {
            return res;
        } else {
            if (node.val % 2 != 0) {
                return res;
            } else {
                if (node.left != null) {
                    if (node.left.left != null) {
                        res++;
                    }
                    if (node.left.right != null) {
                        res++;
                    }
                }

                if (node.right != null) {
                    if (node.right.left != null) {
                        res++;
                    }
                    if (node.right.right != null) {
                        res++;
                    }
                }
            }
        }

        return dfs(node.left) + dfs(node.right) + res;
    }

    /**
     * 面试题 04.02. 最小高度树
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length);
    }

    private TreeNode dfs(int[] nums, int left, int right) {
        if (left == right) {
            return null;
        }
        int mid = (left + right) >> 1;
        TreeNode treeNode = new TreeNode(nums[mid]);
        treeNode.left = dfs(nums, left, mid);
        treeNode.right = dfs(nums, mid + 1, right);
        return treeNode;
    }

    /**
     * 112. 路径总和
     *
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        return dfs1(root, stack, targetSum);
    }

    private boolean dfs1(TreeNode node, Stack<Integer> stack, int targetSum) {
        Stack<Integer> stackLeft = new Stack<>();
        Stack<Integer> stackRight = new Stack<>();
        if (node.left != null && node.right != null) {
            stackLeft.push(stack.peek() + node.val);
            stackRight.push(stack.peek() + node.val);
            return dfs1(node.left, stackLeft, targetSum) || dfs1(node.right, stackRight, targetSum);
        } else if (node.left == null && node.right != null) {
            stackRight.push(stack.peek() + node.val);
            return dfs1(node.right, stackRight, targetSum);
        } else if (node.right == null && node.left != null) {
            stackLeft.push(stack.peek() + node.val);
            return dfs1(node.left, stackLeft, targetSum);
        } else {
            return stack.pop() + node.val == targetSum;
        }
    }

    /**
     * 112. 路径总和
     * dfs
     *
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum1(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return targetSum == 0;
        }
        return hasPathSum1(root.left, targetSum - root.val) || hasPathSum1(root.right, targetSum - rows);
    }


    /**
     * 112. 路径总和
     * dfs
     *
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum2(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        LinkedList<Integer> q = new LinkedList<>();
        q.addLast(root.val);
        while (!queue.isEmpty()) {

            TreeNode node = queue.poll();
            int temp = q.removeFirst();
            if (node.left == null && node.right == null) {
                if (temp == targetSum) {
                    return true;
                }
                continue;
            }

            if (node.left != null) {
                q.addLast(node.left.val + temp);
                queue.addLast(node.left);
            }

            if (node.right != null) {
                q.addLast(node.right.val + temp);
                queue.addLast(node.right);
            }
        }
        return false;
    }

    public static void main(String[] args) {

        MainApril main = new MainApril();

        System.out.println(main.numDecodings("11106"));
    }

    /*class Solution {

        //节点深度
        public boolean canFinish(int numCourses,int[][] prerequististes){
            //课号以及对应的入度
            Map<Integer,Integer> inDegree = new HashMap<>();
            
            //将所有课号先放入
            for (int i = 0; i < numCourses; i++) {
                inDegree.put(i,0);
            }

            //依赖当前课程的后续课程
            Map<Integer,ArrayList<Integer>> adj = new HashMap<>();

            //初始化入度记和依赖关系
            for(int[] relate : prerequististes){
                int cur = relate[1];
                int next = relate[0];
                //更新入度
                inDegree.put(next,inDegree.get(next) + 1);
                //当前节点的临接表
                if(!adj.containsKey(cur)){
                    adj.put(cur,new ArrayList<>());
                }
                adj.get(cur).add(next);
            }

            Queue<Integer> queue = new LinkedList<>();
            for (int key : inDegree.keySet()) {
                if(inDegree.get(key) == 0){

                }
            }

        }
    }*/
}

