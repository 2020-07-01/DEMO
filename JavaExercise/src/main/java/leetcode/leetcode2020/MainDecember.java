package leetcode.leetcode2020;


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

    public static void main(String[] args) {


    }

}
