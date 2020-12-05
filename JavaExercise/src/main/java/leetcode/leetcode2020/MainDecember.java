package leetcode.leetcode2020;


import java.util.*;

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


    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {

        ListNode node = list1;

        int a1 = a;
        while (a > 0) {
            list1 = list1.next;
            a--;
        }
        int c = b - a1;
        while (c > 0) {
            list1.next = list1.next.next;
            c--;
        }
        ListNode temp = list1.next;

        while (list2.next != null) {
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
}
