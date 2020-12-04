package leetcode.leetcode2020;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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
        while (a > 0){
            list1 = list1.next;
            a--;
        }
        int c = b-a1;
        while (c > 0){
            list1.next = list1.next.next;
            c--;
        }
        ListNode temp = list1.next;

        while (list2.next != null){
            ListNode listNode = new ListNode(list2.val);
            list1.next = listNode;
            list1 = list1.next;
            list2 = list2.next;
        }

        list1.next = temp;
        return node;
    }


    public static void main(String[] args) {


    }

}
