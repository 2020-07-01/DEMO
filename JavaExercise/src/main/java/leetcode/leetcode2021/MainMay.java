
package leetcode.leetcode2021;

import callback_function.example2.Main;

import java.util.*;

/**
 * @ClassName : MainMay
 * @Author : yq
 * @Date: 2021-05-02
 * @Description :
 */
public class MainMay {

    private class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;
        public Node random;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    /**
     * 116. 填充每个节点的下一个右侧节点指针 完全二叉树 普通二叉树均使用
     * bfs
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {

        if (root == null) {
            return root;
        }

        Queue<Node> queue = new LinkedList();
        queue.add(root);

        while (!queue.isEmpty()) {
            Queue<Node> temp = new LinkedList<>();
            if (queue.isEmpty()) {
                Node node = queue.poll();
                if (node.left != null) {
                    temp.offer(node.left);
                }
                if (node.right != null) {
                    temp.offer(node.right);
                }
                node.next = queue.peek();
            } else {
                while (!queue.isEmpty()) {
                    Node node = queue.poll();
                    if (node.left != null) {
                        temp.offer(node.left);
                    }
                    if (node.right != null) {
                        temp.offer(node.right);
                    }
                    node.next = queue.peek();
                }
            }
            //待优化
            queue = new LinkedList<>(temp);
        }
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            node.next = queue.peek();
        }
        return root;
    }


    /**
     * 116. 填充每个节点的下一个右侧节点指针
     * dfs
     *
     * @param root
     * @return
     */
    public Node connect1(Node root) {
        if (root == null) {
            return root;
        }
        Node node = root;
        while (node.left != null) {
            Node head = node;
            while (head != null) {
                head.left.next = head.right;
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                head = head.next;
            }
            node = node.left;
        }
        return root;
    }

    /**
     * 117. 填充每个节点的下一个右侧节点指针 II 普通二叉树
     *
     * @param root
     * @return
     */
    public Node connect2(Node root) {
        if (root == null) {
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node last = null;
            for (int i = 1; i <= size; i++) {
                Node first = queue.poll();
                if (first.left != null) {
                    queue.offer(first.left);
                }
                if (first.right != null) {
                    queue.offer(first.right);
                }
                if (i != 1) {
                    //上个元素的next
                    last.next = first;
                }
                //上个元素
                last = first;
            }
        }
        return root;
    }


    /**
     * 199. 二叉树的右视图
     * bfs
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> resList = new LinkedList<>();
        if (root == null) {
            return resList;
        }

        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Deque<TreeNode> temp = new LinkedList<>();
            resList.add(queue.peekLast().val);
            while (!queue.isEmpty()) {
                TreeNode node = queue.removeLast();
                if (node.right != null) {
                    temp.addFirst(node.right);
                }
                if (node.left != null) {
                    temp.addFirst(node.left);
                }
            }
            queue = new LinkedList<>(temp);
        }

        return resList;
    }

    /**
     * 113. 路径总和 II
     * 待优化 记忆法
     *
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {

        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        dfs(res, targetSum, new LinkedList<>(), root);
        return res;
    }

    private void dfs(List<List<Integer>> res, int targetSum, List<Integer> list, TreeNode node) {

        targetSum = targetSum - node.val;
        list.add(node.val);

        if (node.left == null && node.right == null) {
            if (targetSum == 0) {
                res.add(list);
                return;
            }
        }

        if (node.left != null) {
            dfs(res, targetSum, new LinkedList<>(list), node.left);
        }

        if (node.right != null) {
            dfs(res, targetSum, new LinkedList<>(list), node.right);
        }
    }


    /**
     * 257. 二叉树的所有路径
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        dfs(res, root.val + "", root);
        return res;

    }

    private void dfs(List<String> res, String string, TreeNode node) {

        if (node.left == null && node.right == null) {
            res.add(string);
            return;
        }

        if (node.left != null) {
            dfs(res, string + "->" + node.left.val, node.left);
        }
        if (node.right != null) {
            dfs(res, string + "->" + node.right.val, node.right);
        }
    }

    /**
     * 993. 二叉树的堂兄弟节点
     *
     * @param root
     * @param x
     * @param y
     * @return
     */
    public boolean isCousins(TreeNode root, int x, int y) {

        if (root == null) {
            return false;
        }
        Deque<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode xp = null;
            TreeNode yp = null;
            TreeNode node;
            for (int i = 0; i < size; i++) {
                node = queue.poll();
                if (node.left != null) {
                    if (node.left.val == x) {
                        xp = node;
                    }
                    if (node.left.val == y) {
                        yp = node;
                    }
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    if (node.right.val == x) {
                        xp = node;
                    }
                    if (node.right.val == y) {
                        yp = node;
                    }
                    queue.offer(node.right);
                }
                if (xp != null && yp != null && xp.val != yp.val) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 103. 二叉树的锯齿形层序遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> res = new LinkedList<>();

        Deque<TreeNode> deque = new LinkedList();
        if (root == null) {
            return res;
        }
        deque.add(root);
        boolean p = true;
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> list = new LinkedList<>();
            if (p) {
                //从左往右
                for (int i = 0; i < size; i++) {
                    TreeNode treeNode = deque.pollFirst();
                    list.add(treeNode.val);
                    if (treeNode.left != null) {
                        deque.offerLast(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        deque.offerLast(treeNode.right);
                    }
                }
            } else {
                //从右往左
                for (int i = 0; i < size; i++) {
                    TreeNode treeNode = deque.pollLast();
                    list.add(treeNode.val);
                    if (treeNode.right != null) {
                        deque.offerFirst(treeNode.right);
                    }
                    if (treeNode.left != null) {
                        deque.offerFirst(treeNode.left);
                    }
                }
            }
            p = !p;
            res.add(list);
        }
        return res;
    }


    /**
     * 面试题 02.06. 回文链表
     * 优化：反转后半部分链表
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {

        List<Integer> list = new ArrayList<>();

        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        int left = 0;
        int right = list.size() - 1;

        while (left < right) {
            if (list.get(left).equals(list.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 1721. 交换链表中的节点
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode swapNodes(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        ListNode res = head;
        int count = k;
        while (count > 1) {
            head = head.next;
            count--;
        }
        int leftTemp = head.val;

        ListNode right = res;
        while (head.next != null) {
            right = right.next;
            head = head.next;
        }
        int rightTemp = right.val;
        right.val = leftTemp;

        head = res;
        count = k;
        while (count > 1) {
            head = head.next;
            count--;
        }
        head.val = rightTemp;
        return res;
    }


    /**
     * 面试题 02.05. 链表求和
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode head = new ListNode(0);
        ListNode node = head;
        boolean p = false;
        while (l1 != null && l2 != null) {
            int temp1 = l1.val;
            int temp2 = l2.val;
            int sum = temp1 + temp2;
            if (p) {
                //上次进1
                sum = sum + 1;
                if (sum / 10 == 1) {
                    //当前进1
                    p = true;
                } else {
                    p = false;
                }
                ListNode temp = new ListNode(sum % 10);
                node.next = temp;
            } else {
                //上次不进1
                if (sum / 10 == 1) {
                    //当前进1
                    p = true;
                } else {
                    p = false;
                }
                ListNode temp = new ListNode(sum % 10);
                node.next = temp;

            }
            node = node.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int val = l1.val;
            if (p) {
                //上次进1
                val = val + 1;
                if (val / 10 == 1) {
                    //当前进1
                    p = true;
                } else {
                    p = false;
                }
                ListNode temp = new ListNode(val % 10);
                node.next = temp;
            } else {
                //上次不进1
                if (val / 10 == 1) {
                    //当前进1
                    p = true;
                } else {
                    p = false;
                }
                ListNode temp = new ListNode(val % 10);
                node.next = temp;
            }
            node = node.next;
            l1 = l1.next;
        }

        while (l2 != null) {
            int val = l2.val;
            if (p) {
                //上次进1
                val = val + 1;
                if (val / 10 == 1) {
                    //当前进1
                    p = true;
                } else {
                    p = false;
                }
                ListNode temp = new ListNode(val % 10);
                node.next = temp;
            } else {
                //上次不进1
                if (val / 10 == 1) {
                    //当前进1
                    p = true;
                } else {
                    p = false;
                }
                ListNode temp = new ListNode(val % 10);
                node.next = temp;
            }
            node = node.next;
            l2 = l2.next;
        }

        if (p) {
            ListNode temp = new ListNode(1);
            node.next = temp;
        }
        return head.next;
    }

    /**
     * 725. 分隔链表
     *
     * @param root
     * @param k
     * @return
     */
    public ListNode[] splitListToParts(ListNode root, int k) {

        ListNode cur = root;
        int N = 0;
        while (cur != null) {
            cur = cur.next;
            N++;
        }

        int width = N / k, rem = N % k;

        ListNode[] ans = new ListNode[k];
        cur = root;
        for (int i = 0; i < k; ++i) {
            ListNode head = cur;
            for (int j = 0; j < width + (i < rem ? 1 : 0) - 1; ++j) {
                if (cur != null) cur = cur.next;
            }
            if (cur != null) {
                ListNode prev = cur;
                cur = cur.next;
                prev.next = null;
            }
            ans[i] = head;
        }
        return ans;
    }


    int res = 0;

    public int pathSum1(TreeNode root, int targetSum) {

        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        // 前缀和为0的一条路径
        prefixSumCount.put(0, 1);
        // 前缀和的递归回溯思路
        return rec(root, prefixSumCount, targetSum, 0);
    }


    private int dfs(TreeNode node, int targetSum) {
        if (node == null) {
            return 0;
        }
        int temp = 0;
        targetSum = targetSum - node.val;
        if (targetSum == 0) {
            temp++;
        }
        return temp + dfs(node.left, targetSum) + dfs(node.right, targetSum);
    }


    private int rec(TreeNode node, Map<Integer, Integer> prefixCount, int target, int currSum) {
        if (node == null) {
            return 0;
        }
        int res = 0;
        currSum = currSum + node.val;
        res = res + prefixCount.getOrDefault(currSum - target, 0);
        prefixCount.put(currSum, prefixCount.getOrDefault(currSum, 0) + 1);
        res = res + rec(node.left, prefixCount, target, currSum);
        res = res + rec(node.right, prefixCount, target, currSum);
        prefixCount.put(currSum, prefixCount.get(currSum) - 1);
        return res;
    }

    /**
     * 421. 数组中两个数的最大异或值
     *
     * @param nums
     * @return
     */
    public int findMaximumXOR(int[] nums) {

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                ans = Math.max(ans, nums[i] ^ nums[j]);
            }
        }
        return ans;
    }

    /**
     * 23. 合并K个升序链表
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {

        ListNode root = new ListNode(0);
        ListNode node = root;
        List<Integer> list = new LinkedList<>();
        for (ListNode listNode : lists) {
            while (listNode != null) {
                list.add(listNode.val);
                listNode = listNode.next;
            }
        }

        Collections.sort(list);

        for (Integer integer : list) {
            ListNode temp = new ListNode(integer);
            node.next = temp;
            node = node.next;
        }
        return root.next;
    }

    /**
     * 138. 复制带随机指针的链表
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node root = new Node(0);
        Node node = root;
        Map<Node, Node> nodeMap = new HashMap<>();

        Node tempHead = head;
        while (tempHead != null) {
            Node newNode = new Node(tempHead.val);
            nodeMap.put(tempHead, newNode);
            tempHead = tempHead.next;
        }


        while (head != null) {
            Node temp = nodeMap.get(head);
            Node random = nodeMap.get(head.random);
            temp.random = random;
            node.next = temp;
            node = node.next;
            head = head.next;
        }
        return root.next;
    }

    /**
     * 面试题 02.08. 环路检测
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
     * 面试题 02.08. 环路检测
     *
     * @param head
     * @return
     */
    public ListNode detectCycle1(ListNode head) {
        //公式推算
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                fast = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }


    /**
     * 1367. 二叉树中的列表
     * 逐层作为新的头节点开始比较
     *
     * @param head
     * @param root
     * @return
     */
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (root == null) {
            return false;
        }
        //每次都作为新的节点
        return dfs(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean dfs(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }

        if (root == null) {
            return false;
        }
        if (head.val != root.val) {
            return false;
        }
        return dfs(head.next, root.left) || dfs(head.next, root.right);
    }


    /**
     * 1833. 雪糕的最大数量
     * 刷新对贪心算法的理解
     *
     * @param costs
     * @param coins
     * @return
     */
    public int maxIceCream(int[] costs, int coins) {

        Arrays.sort(costs);
        int index = 0;
        int length = costs.length;
        while (index < length && coins >= costs[index]) {
            coins = coins - costs[index++];
        }
        return index;
    }

    /**
     * 209. 长度最小的子数组
     * 待优化
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {

        int left = 0;
        int right = 1;
        int res = Integer.MAX_VALUE;
        int currentSum = nums[left];
        while (right <= nums.length) {

            if (currentSum < target && right < nums.length) {
                currentSum = currentSum + nums[right];
                right++;
            } else if (currentSum == target) {
                int resTemp = right - left;
                if (resTemp < res) {
                    res = resTemp;
                }
                currentSum = currentSum - nums[left];
                if (right != nums.length) {
                    currentSum = currentSum + nums[right];
                    right++;
                }
                left++;

            } else {
                currentSum = currentSum - nums[left];
                left++;
            }
        }
        if (currentSum == 0) {
            res = 0;
        }
        return res;
    }

    /**
     * 209. 长度最小的子数组
     * 时间复杂度O(n)
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen1(int target, int[] nums) {

        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;

        int sum = 0;
        while (right < n) {
            sum = sum + nums[right];
            while (sum >= target) {
                ans = Math.min(ans, right - left + 1);
                sum = sum - nums[left];
                left++;
            }
            right++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }


    /**
     * 1748. 唯一元素的和
     *
     * @param nums
     * @return
     */
    public int sumOfUnique(int[] nums) {

        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == 1) {
                res = res + nums[i];
            }
        }
        return res;
    }

    /**
     * 15. 三数之和
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resList = new LinkedList<>();
        if (nums == null || nums.length < 3) {
            return resList;
        }

        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length; i++) {

            //如果nums[i] > 0 跳过
            if (nums[i] > 0) {
                continue;
            }

            //如果跟上个元素相同则跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = length - 1;
            while (left < right) {

                if (nums[i] + nums[left] + nums[right] > 0) {
                    right--;
                } else if (nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                } else {
                    List<Integer> list = new LinkedList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    resList.add(list);
                    //判断下一位是否相同 去重
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                }
            }
        }
        return resList;
    }


    /**
     * 62. 不同路径
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) {
            return 2;
        }

        int[][] right = new int[m][n];
        int[][] down = new int[m][n];

        for (int i = 0; i < m; i++) {
            right[i][0] = 1;
        }

        for (int i = 0; i < n; i++) {
            down[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                //上面的值 + 左边的值
                right[i][j] = down[i - 1][j] + right[i][j - 1];
                down[i][j] = down[i - 1][j] + right[i][j - 1];
            }
        }
        return right[m - 1][n - 2] + down[m - 2][n - 1];
    }


    public static void main(String[] args) {

        MainMay main = new MainMay();
        int[] nums = new int[]{1, 1, 1, 1, 1, 1, 1, 1};

        System.out.println(main.uniquePaths(3, 7));

    }
}
