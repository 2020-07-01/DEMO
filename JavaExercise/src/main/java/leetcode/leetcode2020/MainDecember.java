package leetcode.leetcode2020;


import dataStructure.list.Link;
import javafx.scene.chart.StackedAreaChart;
import sun.reflect.generics.tree.Tree;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName : MainDecember
 * @Author : yq
 * @Date: 2020-12-01
 * @Description :
 */
public class MainDecember {

    /**
     * 100. 相同的树
     * 结构相同
     * 相同节点的值相同
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {

        /**
         * 广度优先遍历法
         * 需要判断本节点和本节点的左右子节点
         */
        //深度优先遍历
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        } else {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

    /**
     * 非递归方式实现先序遍历
     */
    public void preOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new LinkedList<>();

        stack.push(root);
        TreeNode node;
        while (!stack.isEmpty()) {
            node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    /**
     * 107. 二叉树的层次遍历 II
     * 时间复杂度O(n)
     * 广度优先遍历
     * 借用队列
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        LinkedList<List<Integer>> lists = new LinkedList<List<Integer>>();

        if (root == null) {
            return lists;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        TreeNode node;
        LinkedList<TreeNode> tempQueue;
        while (!queue.isEmpty()) {
            List<Integer> list = new LinkedList<>();
            tempQueue = new LinkedList<>();
            while (!queue.isEmpty()) {
                node = queue.removeFirst();
                list.add(node.val);
                if (node.left != null) {
                    tempQueue.addLast(node.left);
                }
                if (node.right != null) {
                    tempQueue.addLast(node.right);
                }
            }
            queue = tempQueue;
            lists.addFirst(list);
        }
        return lists;
    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * 应该使用二分法
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {

        int[] result = new int[2];
        if (nums == null || nums.length == 0) {
            result[0] = -1;
            result[1] = -1;
            return result;
        }

        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (target == nums[i]) {
                list.add(i);
            }
        }
        if (list.size() > 0) {
            result[0] = list.get(0);
            result[1] = list.get(list.size() - 1);
        } else {
            result[0] = -1;
            result[1] = -1;
        }
        return result;
    }


    /**
     * 111. 二叉树的最小深度
     * 广度优先遍历
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }
        int nodeCount = 1;
        LinkedList<TreeNode> queue = new LinkedList();
        queue.addFirst(root);
        while (!queue.isEmpty()) {
            LinkedList<TreeNode> list = new LinkedList<>();
            while (!queue.isEmpty()) {
                TreeNode node = queue.remove();
                if (node.left == null && node.right == null) {
                    return nodeCount;
                }
                if (node.left != null) {
                    list.addFirst(node.left);
                }
                if (node.right != null) {
                    list.addFirst(node.right);
                }
            }
            nodeCount++;
            queue = list;
        }
        return nodeCount;
    }

    /**
     * 111. 二叉树的最小深度
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     * 深度优先遍历
     *
     * @param root
     * @return
     */
    public int minDepth1(TreeNode root) {

        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int min_deep = Integer.MAX_VALUE;
        if (root.left != null) {
            min_deep = Math.min(minDepth1(root.left), min_deep);
        }
        if (root.right != null) {
            min_deep = Math.min(minDepth1(root.right), min_deep);
        }
        return min_deep + 1;
    }

    LinkedList<Integer> linkedList = new LinkedList<>();

    /**
     * 671. 二叉树中第二小的节点
     * 暴力解法
     *
     * @param root
     * @return
     */
    public int findSecondMinimumValue(TreeNode root) {
        order(root);
        Collections.sort(linkedList);
        if (linkedList.size() >= 2) {
            return linkedList.get(linkedList.size() - 2);
        }

        return -1;
    }

    private void order(TreeNode node) {
        if (node == null) {
            return;
        }

        order(node.left);
        if (!linkedList.contains(node.val)) {
            linkedList.add(node.val);
        }
        order(node.right);
    }


    /**
     * 204. 计数质数
     * 暴力解法会超时
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {

        if (n <= 2) {
            return 0;
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            int j = 2;
            for (j = 2; j < i; j++) {
                if (i % j == 0) {
                    break;
                }
            }
            if (j == i) {
                count++;
            }
        }

        return count;
    }


    /**
     * 700. 二叉搜索树中的搜索
     * 地规法
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {

        if (root == null) {
            return null;
        } else if (root != null && root.val == val) {
            return root;
        } else {
            TreeNode left = searchBST(root.left, val);
            TreeNode right = searchBST(root.right, val);
            return left == null ? right : left;
        }
    }


    /**
     * 700. 二叉搜索树中的搜索
     * 迭代法 比 递归法快
     * 时间复杂度O(n)
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST1(TreeNode root, int val) {

        while (root != null) {
            if (root.val == val) {
                return root;
            } else {
                root = root.val > val ? root.left : root.right;
            }
        }
        return null;
    }

    /**
     * 1669. 合并两个链表
     *
     * @param list1
     * @param a
     * @param b
     * @param list2
     * @return
     */
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {

        ListNode node = list1;

        int a1 = a;
        while (a > 1) {
            list1 = list1.next;
            a--;
        }
        int c = b - a1;
        while (c >= 0) {
            list1.next = list1.next.next;
            c--;
        }

        ListNode temp = list1.next;

        while (list2 != null) {
            ListNode listNode = new ListNode(list2.val);
            list1.next = listNode;
            list1 = list1.next;
            list2 = list2.next;
        }
        list1.next = temp;
        return node;
    }


    /**
     * 26. 删除排序数组中的重复项
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }
        int cur = nums[0];
        int index = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != cur) {
                cur = nums[i];
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }


    /**
     * 143. 重排链表
     *
     * @param head
     */
    public void reorderList(ListNode head) {

        if (head == null) {
            return;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = 0;
        ListNode next = head;
        while (next != null) {
            map.put(n, next.val);
            next = next.next;
            n++;
        }
        n = n - 1;
        int i = 1;
        boolean p = false;
        while (i <= n) {
            if (p) {
                ListNode temp = new ListNode(map.get(i));
                head.next = temp;
                head = head.next;
                p = false;
                i++;
            } else {
                ListNode temp = new ListNode(map.get(n));
                head.next = temp;
                head = head.next;
                p = true;
                n--;
            }
        }
    }

    /**
     * 559. N叉树的最大深度
     * 迭代法
     *
     * @param root
     * @return
     */
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int deep = 1;
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node node;
            Stack<Node> nodes = new Stack<>();
            while (!stack.isEmpty()) {
                node = stack.pop();
                List<Node> children = node.children;
                for (Node childrenNode : children) {
                    nodes.push(childrenNode);
                }
            }
            if (nodes.isEmpty()) {
                return deep;
            } else {
                stack = nodes;
                deep++;
            }
        }
        return deep;
    }

    /**
     * 783. 二叉搜索树节点最小距离
     * 空间复杂度O(n)
     * 时间复杂度O(nlogn)
     *
     * @param root
     * @return
     */
    public int minDiffInBST(TreeNode root) {

        if (root == null) {
            return 0;
        }
        List<Integer> list = new LinkedList<>();

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode node;
        Stack<TreeNode> temp;
        while (!stack.isEmpty()) {
            temp = new Stack<>();
            while (!stack.isEmpty()) {
                node = stack.pop();
                list.add(node.val);
                if (node.left != null) {
                    temp.push(node.left);
                }
                if (node.right != null) {
                    temp.push(node.right);
                }
            }
            stack = temp;
        }
        Collections.sort(list);
        if (list.size() == 1) {
            return list.get(0);
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i + 1) - list.get(i) < result) {
                result = list.get(i + 1) - list.get(i);
            }
        }
        return result;
    }

    /**
     * 783. 二叉搜索树节点最小距离
     *
     * @param root
     * @return
     */
    public int minDiffInBST1(TreeNode root) {

        /**
         * 中序遍历
         * 再遍历比较差值大小
         */
        return 0;

    }

    /**
     * 1346. 检查整数及其两倍数是否存在
     * 时间复杂度O(n2)
     * 空间复杂度O(1)
     *
     * @param arr
     * @return
     */
    public boolean checkIfExist(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] * 2 == arr[j] || arr[j] * 2 == arr[i]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 1200. 最小绝对差
     * 时间复杂度O(n)
     *
     * @param arr
     * @return
     */
    public List<List<Integer>> minimumAbsDifference(int[] arr) {

        List<List<Integer>> lists = new LinkedList<>();
        if (arr == null || arr.length < 0) {
            return lists;
        }

        int minValue = Integer.MAX_VALUE;

        Arrays.sort(arr);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] < minValue) {
                List<Integer> list = new ArrayList<>();
                lists.clear();
                list.add(arr[i - 1]);
                list.add(arr[i]);
                lists.add(list);
                minValue = arr[i] - arr[i - 1];
            } else if (arr[i] - arr[i - 1] == minValue) {
                List<Integer> list = new ArrayList<>();
                list.add(arr[i - 1]);
                list.add(arr[i]);
                lists.add(list);
            }
        }
        return lists;
    }

    /**
     * 面试题 16.01. 交换数字
     *
     * @param numbers
     * @return
     */
    public int[] swapNumbers(int[] numbers) {
        /**
         * a + b
         * ab - b
         */
        numbers[0] = numbers[0] + numbers[1];
        numbers[1] = numbers[0] - numbers[1];
        numbers[0] = numbers[0] - numbers[1];
        return numbers;
    }

    /**
     * 1281. 整数的各位积和之差
     *
     * @param n
     * @return
     */
    public int subtractProductAndSum(int n) {

        if (n == 0) {
            return 1;
        }
        int count = 0;
        int product = 1;
        while (n >= 10) {
            int temp = n % 10;
            n = n / 10;
            count = count + temp;
            product = product * temp;
        }
        count = count + n;
        product = product * n;
        return product - count;
    }

    /**
     * 1379. 找出克隆二叉树中的相同节点
     *
     * @param original
     * @param cloned
     * @param target
     * @return
     */
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (cloned == null || target == null) {
            return null;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(cloned);

        while (!stack.empty()) {
            Stack<TreeNode> tempStack = new Stack<>();
            while (!stack.empty()) {
                TreeNode node = stack.pop();
                if (node.val == target.val) {
                    return node;
                } else {
                    if (node.left != null) {
                        tempStack.push(node.left);
                    }
                    if (node.right != null) {
                        tempStack.push(node.right);
                    }
                }
            }
            stack = tempStack;
        }
        return target;
    }


    /**
     * 广度优先遍历，应该使用队列，避免切换
     */

    /**
     * 1302. 层数最深叶子节点的和
     * 空间复杂度O(n)
     * 时间复杂度O(n)
     * 获取最后一层的所有叶子节点
     * 遍历求和
     *
     * @param root
     * @return
     */
    public int deepestLeavesSum(TreeNode root) {
        int sum = 0;
        if (root == null) {
            return sum;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        List<Integer> list = new LinkedList<>();
        while (!stack.empty()) {
            list = new LinkedList<>();
            Stack<TreeNode> tempStack = new Stack<>();
            while (!stack.empty()) {
                TreeNode node = stack.pop();
                list.add(node.val);
                if (node.left != null) {
                    tempStack.push(node.left);
                }
                if (node.right != null) {
                    tempStack.push(node.right);
                }
            }
            stack = tempStack;
        }
        for (Integer integer : list) {
            sum = sum + integer;
        }
        return sum;
    }


    /**
     * 深度优先遍历使用栈
     *
     * @param root
     * @return
     */
    public int deepestLeavesSum1(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int maxDeep = 0;
        int sum = 0;

        int deep = 0;
        while (!stack.empty()) {
            deep = stack.size();
            TreeNode node = stack.pop();
            if (node.left == null && node.right == null) {
                if (maxDeep == deep) {
                    sum = sum + maxDeep;
                } else if (maxDeep < deep) {
                    sum = 0;
                    maxDeep = deep;
                } else {
                    continue;
                }
            } else if (node.right != null) {
                stack.push(node.right);
            } else if (node.left != null) {
                stack.push(node.left);
            }
        }
        return sum;

    }


    /**
     * @param root
     * @return
     */
    public int sumEvenGrandparent(TreeNode root) {
        int sum = 0;
        Deque<TreeNode> linkedList = new LinkedList();
        linkedList.add(root);
        while (!linkedList.isEmpty()) {
            int size = linkedList.size();
            while (size > 0) {
                TreeNode node = linkedList.removeLast();
                if (node.val % 2 == 0) {
                    if (node.left != null) {
                        sum = sum + (node.left.left == null ? 0 : node.left.left.val);
                        sum = sum + (node.left.right == null ? 0 : node.left.right.val);
                        linkedList.addFirst(node.left);
                    }
                    if (node.right != null) {
                        sum = sum + (node.right.left == null ? 0 : node.right.left.val);
                        sum = sum + (node.right.right == null ? 0 : node.right.right.val);
                        linkedList.addFirst(node.right);
                    }
                }
            }
        }
        return sum;
    }


    /**
     * 1672. 最富有客户的资产总量
     * 时间复杂度O(n * m)
     * 空间复杂度O(1)
     *
     * @param accounts
     * @return
     */
    public int maximumWealth(int[][] accounts) {

        int max = 0;

        int temp;
        for (int i = 0; i < accounts.length; i++) {
            temp = 0;
            for (int j = 0; j < accounts[i].length; j++) {
                temp = temp + accounts[i][j];
            }
            max = max > temp ? max : temp;
        }

        return max;
    }


    /**
     * 145. 二叉树的后序遍历
     * 迭代法
     * 与前序遍历类似
     * 每次迭代进行"根右左"操作最后反转list
     *
     * @param nums
     * @param index
     * @return
     */
    public int[] createTargetArray(int[] nums, int[] index) {

        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            linkedList.add(index[i], nums[i]);
        }

        for (int i = 0; i < linkedList.size(); i++) {
            nums[i] = linkedList.get(i);
        }
        return nums;
    }

    /**
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();

        List<Integer> list = new LinkedList<>();
        TreeNode cur = root;
        while (!stack.empty() || cur != null) {
            //如果当前节点为不为null，则下一个节点为当前节点的左节点
            if (cur != null) {
                list.add(cur.val);
                stack.push(cur);
                cur = cur.right;
            } else {
                //如果当前节点为空，获取父节点，进而访问父节点的右子节点
                TreeNode rightNode = stack.pop();
                cur = rightNode.left;
            }
        }

        Collections.reverse(list);
        return list;
    }

    /**
     * 144. 二叉树的前序遍历
     * 递归法
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> list = new LinkedList<>();

        pre(root, list);
        return list;
    }

    private void pre(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        } else {
            list.add(root.val);
            pre(root.left, list);
            pre(root.right, list);
        }

    }

    /**
     * 144. 二叉树的前序遍历
     * 迭代法
     * 迭代法时间比递归法慢
     * 每次迭代进行"根左右"操作
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal1(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();

        List<Integer> list = new LinkedList<>();
        TreeNode cur = root;
        while (!stack.empty() || cur != null) {
            //如果当前节点为不为null，则下一个节点为当前节点的左节点
            if (cur != null) {
                list.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            } else {
                //如果当前节点为空，获取父节点，进而访问父节点的右子节点
                TreeNode rightNode = stack.pop();
                cur = rightNode.right;
            }
        }

        return list;
    }

    /**
     * 剑指 Offer 37. 序列化二叉树
     *
     * @param root
     * @return
     */

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        Deque<TreeNode> deque = new LinkedList();
        deque.offerFirst(root);
        String string = "";
        while (!deque.isEmpty()) {
            TreeNode node = deque.pollLast();
            string = string + node.val + ",";
            if (node.left != null) {
                deque.offerFirst(node.left);
            }
            if (node.right != null) {
                deque.offerFirst(node.right);
            }
        }
        if (string.length() > 0) {
            return string.substring(0, string.length() - 1);
        }
        return "";
    }

    // Decodes your encoded data to tree.
  /*  public TreeNode deserialize(String data) {


    }*/

    /**
     * 1122. 数组的相对排序
     * 技术排序思想
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {

        List<Integer> list2 = new LinkedList<>();
        for (int i = 0; i < arr2.length; i++) {
            list2.add(arr2[i]);
        }

        int[] result = new int[arr1.length];
        List<Integer> noList = new LinkedList<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < arr1.length; i++) {
            if (list2.contains(arr1[i])) {
                hashMap.put(arr1[i], hashMap.getOrDefault(arr1[i], 0) + 1);
            } else {
                noList.add(arr1[i]);
            }
        }

        int index = 0;

        for (int i = 0; i < arr2.length; i++) {
            if (hashMap.containsKey(arr2[i])) {
                int count = hashMap.get(arr2[i]);
                while (count > 0) {
                    result[index] = arr2[i];
                    index++;
                    count--;
                }
            }
        }

        Collections.sort(noList);
        for (Integer item : noList) {
            result[index] = item;
            index++;
        }

        return result;
    }


    /**
     * 404. 左叶子之和
     * 广度优先遍历
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        int sum = 0;
        if (root == null) {
            return sum;
        }
        Deque<TreeNode> deque = new LinkedList();
        deque.offerFirst(root);
        while (!deque.isEmpty()) {
            TreeNode node = deque.pollLast();
            if (node.left != null) {
                deque.offerFirst(node.left);
                if (node.left.left == null && node.left.right == null) {
                    sum = sum + node.left.val;
                }
            }
            if (node.right != null) {
                deque.offerFirst(node.right);
            }
        }
        return sum;
    }

    /**
     * 面试题 01.02. 判定是否互为字符重排
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();

        Arrays.sort(chars1);
        Arrays.sort(chars2);
        int index = 0;
        while (index < chars1.length) {
            if (chars1[index] != chars2[index]) {
                return false;
            }
            index++;
        }
        return true;
    }


    /**
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {

        TreeSet<Integer> treeSet = new TreeSet<>();
        for (Integer item : nums) {
            if (treeSet.contains(item)) {
                return false;
            } else {
                treeSet.add(item);
            }
        }
        return true;
    }

    /**
     * 628. 三个数的最大乘积
     * 分情况题目
     * 贪心算法最合适
     *
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {

        Arrays.sort(nums);

        //如果全为负数 或者全为正数  1，2，3，4，5，6     -10，-9，-8，-7
        if (nums[nums.length - 1] < 0 || nums[0] > 0) {
            return nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3];
        }

        //有整数也有负数 -10，-9，1，2，3
        if (nums[0] < 0 && nums[1] < 0) {
            int length = nums.length - 1;
            return Math.max(nums[0] * nums[1] * nums[length], nums[length] * nums[length - 1] * nums[length - 2]);
        }

        return 0;

    }

    /**
     * 268. 丢失的数字
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {

        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return 0;
    }


    /**
     * 148. 排序链表
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        List<Integer> list = new LinkedList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        Collections.sort(list);
        head = new ListNode(0);
        ListNode cur = head;
        for (Integer integer : list) {

            ListNode node = new ListNode(integer);
            cur.next = node;
            cur = cur.next;
        }
        return head.next;
    }

    /**
     * 153. 寻找旋转排序数组中的最小值
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
        }
        return min;
    }


    /**
     * 面试题 04.03. 特定深度节点链表
     *
     * @param tree
     * @return
     */
    public ListNode[] listOfDepth(TreeNode tree) {

        if (tree == null) {
            return new ListNode[0];
        }
        //存储头节点
        List<ListNode> listNodes = new LinkedList<>();
        Deque<TreeNode> deque = new LinkedList();
        deque.offerFirst(tree);
        while (!deque.isEmpty()) {
            int i = deque.size();
            ListNode head = new ListNode(0);
            ListNode cur = head;
            while (i > 0) {
                TreeNode treeNode = deque.pollLast();
                ListNode temp = new ListNode(treeNode.val);
                //注意此处
                cur.next = temp;
                cur = cur.next;
                if (treeNode.left != null) {
                    deque.addFirst(treeNode.left);
                }

                if (treeNode.right != null) {
                    deque.addFirst(treeNode.right);
                }
                i--;
            }
            listNodes.add(head.next);
        }
        return listNodes.toArray(new ListNode[listNodes.size()]);
    }

    /**
     * 25. K 个一组翻转链表
     * 时间复杂度太高
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {

        if (head == null) {
            return null;
        }

        Deque<Integer> stack = new LinkedList<>();
        ListNode root = new ListNode(0);
        ListNode cur = root;
        while (head != null) {
            int i = 0;
            while (i < k && head != null) {
                stack.addFirst(head.val);
                head = head.next;
                i++;
            }
            if (i == k) {
                while (!stack.isEmpty()) {
                    ListNode temp = new ListNode(stack.pop().intValue());
                    cur.next = temp;
                    cur = cur.next;
                }
            }
        }

        while (!stack.isEmpty()) {
            ListNode temp = new ListNode(stack.pollLast().intValue());
            cur.next = temp;
            cur = cur.next;
        }

        return root.next;
    }

    /**
     * 待研究
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {

        if (head == null) {
            return null;
        }
        ListNode node = new ListNode(head.val);
        ListNode cur = node;
        head = head.next;
        int i = 1;
        while (i < m) {
            ListNode temp = new ListNode(head.val);
            cur.next = temp;
            cur = cur.next;
            head = head.next;
            i++;
        }

        ListNode listNode = new ListNode(head.val);
        head = head.next;
        m = m + 1;
        while (m < n) {

            ListNode temp = new ListNode(head.val);
            temp.next = head;
            listNode = temp;
            head = head.next;
            m++;
        }
        cur = listNode;
        cur.next = head;

        return node;
    }

    /**
     * 82. 删除排序链表中的重复元素 II
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {

        if (head == null) {
            return null;
        }

        ListNode node = new ListNode(0);
        int pre = head.val;
        boolean p = true;
        head = head.next;
        ListNode cur = node;

        while (head != null) {
            if (head.val == pre) {
                p = false;
            } else if (!p) {
                pre = head.val;
                p = true;
            } else {
                ListNode temp = new ListNode(pre);
                cur.next = temp;
                cur = cur.next;
                pre = head.val;
            }
            head = head.next;
        }

        if (p) {
            ListNode temp = new ListNode(pre);
            cur.next = temp;
            cur = cur.next;
        }

        return node.next;
    }

    /**
     * 剑指 Offer 32 - I. 从上到下打印二叉树
     *
     * @param root
     * @return
     */
    public int[] levelOrder(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        List<Integer> list = new LinkedList<>();
        Deque<TreeNode> deque = new LinkedList();
        deque.addFirst(root);

        while (!deque.isEmpty()) {
            TreeNode node = deque.pollLast();
            if (node.left != null) {
                deque.addFirst(node.left);
            }
            if (node.right != null) {
                deque.addFirst(node.right);
            }
            list.add(node.val);
        }
        int index = 0;
        int[] result = new int[list.size()];
        for (Integer item : list) {
            result[index++] = item;
        }
        return result;
    }

    /**
     * 面试题 01.01. 判定字符是否唯一
     *
     * @param astr
     * @return
     */
    public boolean isUnique(String astr) {

        int index = 0;
        while (index < astr.length() - 1) {
            if (-1 != astr.indexOf(astr.charAt(index), index + 1)) {
                return false;
            }
            index++;
        }
        return true;
    }

    /**
     * 1323. 6 和 9 组成的最大数字
     *
     * @param num
     * @return
     */
    public int maximum69Number(int num) {
        String s = String.valueOf(num);
        s = s.replaceFirst("6", "9");
        return Integer.valueOf(s);
    }

    /**
     * 58. 最后一个单词的长度
     *
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        if (s.trim() == null || s.trim().length() == 0) {
            return 0;
        }
        String[] strings = s.trim().split(" ");
        return strings[strings.length - 1].length();
    }


    /**
     * 520. 检测大写字母
     *
     * @param word
     * @return
     */
    public boolean detectCapitalUse(String word) {


        if (word == null || word.equals("")) {
            return true;
        }
        if (word.trim().equals("")) {
            return false;
        }
        if (word.length() > 1) {
            //如果第一个个字母大写，则其他全部要为小写 或者全部为大写
            if (word.charAt(0) > 'A' && word.charAt(0) < 'Z') {
                boolean p1 = false;//小写
                boolean p2 = false;//大写
                for (int i = 1; i < word.length(); i++) {
                    //存在大写
                    if (word.charAt(i) < 'a' || word.charAt(i) > 'z') {
                        if (p1) {
                            return false;
                        } else {
                            p2 = true;
                        }
                    }
                    //存在小写
                    if (word.charAt(i) < 'A' || word.charAt(i) > 'Z') {
                        if (p2) {
                            return false;
                        } else {
                            p1 = true;
                        }
                    }
                }
                return true;
            }
            //第一个字母小写,其他全部为小写
            else {
                for (int i = 1; i < word.length(); i++) {
                    //存在大写
                    if (word.charAt(i) < 'a' || word.charAt(i) > 'z') {
                        return false;
                    }
                }
                return true;
            }
        } else {
            //小写
            return true;
        }
    }


    public static void main(String[] args) {

        MainDecember mainDecember = new MainDecember();
        int[] arr2 = {2, 1, 4, 3, 9, 6};
        int[] arr1 = {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        List list2 = Arrays.asList(arr2);
        System.out.println(list2.toString());


        mainDecember.relativeSortArray(arr1, arr2);
    }

}
