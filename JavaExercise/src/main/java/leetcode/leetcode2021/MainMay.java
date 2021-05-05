package leetcode.leetcode2021;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

        public Node() {
        }

        public Node(int _val) {
            val = _val;
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


    public static void main(String[] args) {


    }
}
