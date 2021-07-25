package leetcode.leetcode2021;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName : MainJuly
 * @Author : yq
 * @Date: 2021-07-03
 * @Description :
 */
public class MainJuly {


    /**
     * @param nums
     * @return
     */
    public boolean isStraight(int[] nums) {

        int min = 0;
        int max = 14;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            if (nums[i] == 0) {
                continue;
            }
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
            if (set.contains(nums[i])) {
                return false;
            }
        }
        return (max - min) < 5;
    }


    public int[] buildArray(int[] nums) {

        int[] ans = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[nums[i]];
        }
        return ans;
    }


    /**
     * 5801. 消灭怪物的最大数量
     *
     * @param dist
     * @param speed
     * @return
     */
    public int eliminateMaximum(int[] dist, int[] speed) {

        for (int i = 0; i < dist.length; i++) {
            dist[i] = (dist[i] + speed[i] - 1) / speed[i];
        }

        Arrays.sort(dist);
        for (int i = 0; i < dist.length; i++) {
            //到达了直接返回
            if (dist[i] < i + 1) {
                return i;
            }
        }
        return dist.length;
    }

    private boolean zhishu(int num) {
        if (num == 2 || num == 3) {
            return true;
        }
        for (int i = 2; i < num; i++) {
            if (num / i == 0) {
                return false;
            }
        }
        return true;
    }

    private boolean oushu(int num) {
        return num / 2 == 0;
    }

    /**
     * 剑指 Offer 31. 栈的压入、弹出序列
     *
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {

        Stack<Integer> stack = new Stack<>();

        int index = 0;
        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            while (!stack.empty() && stack.peek() == popped[index]) {
                stack.pop();
                index++;
            }
        }
        return stack.empty();
    }


    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        //创建根节点
        TreeNode node = new TreeNode(preorder[0]);
        //先序遍历第一个节点为根节点，此节点在中序遍历中的位置为k
        int k = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (preorder[0] != inorder[i]) {
                k++;
            } else {
                break;
            }
        }
        //中序遍历[0,k)属于左子树 [k+1,n)属于右子树
        //先序遍历[1,K+1)属于左子树  [k+1,n]属于右子树
        //中序遍历中根节点右边只有一个节点
        node.left = buildTree(Arrays.copyOfRange(preorder, 1, k + 1), Arrays.copyOfRange(inorder, 0, k));
        node.right = buildTree(Arrays.copyOfRange(preorder, k + 1, preorder.length), Arrays.copyOfRange(inorder, k + 1, preorder.length));
        return node;
    }

    List<Node> nodeList = new LinkedList<>();

    /**
     * 剑指 Offer 36. 二叉搜索树与双向链表
     *
     * @param root
     * @return
     */
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return root;
        }
        //中序遍历
        order(root);
        int size = nodeList.size();

        for (int i = 1; i < size - 1; i++) {
            nodeList.get(i).left = nodeList.get(i - 1);
            nodeList.get(i).right = nodeList.get(i + 1);
        }
        if (size == 1) {
            nodeList.get(0).left = nodeList.get(0);
            nodeList.get(1).right = nodeList.get(0);
        } else {
            nodeList.get(0).right = nodeList.get(1);
            nodeList.get(0).left = nodeList.get(size - 1);
            nodeList.get(size - 1).left = nodeList.get(size - 2);
            nodeList.get(size - 1).right = nodeList.get(0);
        }

        return nodeList.get(0);
    }

    private void order(Node node) {
        if (node == null) {
            return;
        }
        order(node.left);
        nodeList.add(node);
        order(node.right);
    }

    /**
     * 暴力解法
     *
     * @param a
     * @return
     */
    public int[] constructArr(int[] a) {
        int[] res = new int[a.length];

        int index = 0;
        while (index < a.length) {
            int count = 1;
            for (int i = 0; i < a.length; i++) {
                if (i != index) {
                    count = count * a[i];
                }
            }
            res[index] = count;
        }
        return res;
    }

    /**
     * 剑指 Offer 66. 构建乘积数组
     *
     * @param a
     * @return
     */
    public int[] constructArr1(int[] a) {
        int[] res = new int[a.length];
        if (a.length == 0) {
            return res;
        }
        int[] left = new int[a.length];
        int[] right = new int[a.length];

        left[0] = 1;
        right[a.length - 1] = 1;
        for (int i = 1; i < a.length; i++) {
            left[i] = left[i - 1] * a[i - 1];
        }
        for (int i = a.length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * a[i + 1];
        }
        for (int i = 0; i < a.length; i++) {
            res[i] = left[i] * right[i];
        }
        return res;
    }

    List<List<Integer>> res = new LinkedList<>();

    /**
     * 非根节点触发
     *
     * @param root
     * @param target
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int target) {

        if (root == null) {
            return res;
        }
        path(target, target, root, new LinkedList<>());
        return res;
    }

    private void path(int cur, int target, TreeNode node, List<Integer> list) {
        if (cur - node.val == 0) {
            list.add(node.val);
            res.add(list);
            path(target, target, node, new LinkedList<>());
        } else {
            list.add(node.val);
            cur = cur - node.val;
            List<Integer> leftList = new LinkedList<>(list);
            if (node.left != null) {
                path(cur, target, node.left, leftList);
            }

            List<Integer> rightList = new LinkedList<>(list);
            if (node.right != null) {
                path(cur, target, node.right, rightList);
            }
        }

        path(target, target, node, new LinkedList<>());
    }

    /**
     * 根节点出发
     *
     * @param root
     * @param target
     * @return
     */
    public List<List<Integer>> pathSum1(TreeNode root, int target) {
        if (root == null) {
            return res;
        }
        dfs(root, target, new LinkedList<>());
        return res;
    }

    private void dfs(TreeNode node, int target, List<Integer> list) {
        target = target - node.val;
        list.add(node.val);
        if (node.left == null && node.right == null) {
            if (target == 0) {
                res.add(list);
            }
        }

        if (node.left != null) {
            List<Integer> leftList = new LinkedList<>(list);
            dfs(node.left, target, leftList);
        }
        if (node.right != null) {
            List<Integer> rightList = new LinkedList<>();
            dfs(node.right, target, rightList);
        }
    }


    /**
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {

        Deque<TreeNode> deque = new LinkedList();
        List<List<Integer>> lists = new LinkedList<>();
        if (root == null) {
            return lists;
        }
        deque.addFirst(root);
        boolean flag = true;
        while (!deque.isEmpty()) {
            List<Integer> list = new LinkedList<>();
            int size = deque.size();
            if (flag) {
                //从左到右
                while (size > 0) {
                    TreeNode node = deque.removeFirst();
                    list.add(node.val);
                    if (node.left != null) {
                        deque.addLast(node.left);
                    }
                    if (node.right != null) {
                        deque.addLast(node.right);
                    }
                    size--;
                }
            } else {
                //从右到左
                while (size > 0) {
                    TreeNode node = deque.removeLast();
                    list.add(node.val);
                    if (node.right != null) {
                        deque.addFirst(node.right);
                    }
                    if (node.left != null) {
                        deque.addFirst(node.left);
                    }
                    size--;
                }
            }
            lists.add(list);
            flag = !flag;
        }
        return lists;
    }

    /**
     * 剑指 Offer 14- I. 剪绳子
     * dp套公式
     *
     * @param n
     * @return
     */
    public int cuttingRope(int n) {
        //dp[i] 长度为i的绳子 可获得的最大乘积
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i < n + 1; i++) {
            for (int j = 2; j < i; j++) {
                //剪去j,剩余不剪
                int sum1 = j * (i - j);

                //剪去j,剩余剪
                int sum2 = j * dp[i - j];

                dp[i] = Math.max(dp[i], Math.max(sum1, sum2));
            }
        }
        return dp[n];
    }


    /**
     * @param nums
     * @return
     */
    public String minNumber(int[] nums) {

//        List<String> list = new LinkedList<>();
//        for (Integer integer : nums) {
//            list.add(String.valueOf(integer));
//        }
//
//        Collections.sort(list, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                Integer a = Integer.valueOf(o1 + o2);
//                Integer b = Integer.valueOf(o2 + o1);
//                return a - b;
//            }
//        });
//
//        return String.join("", list);

        List<String> list = new ArrayList<>();
        for (int num : nums) {
            list.add(String.valueOf(num));
        }
        list.sort((o1, o2) -> (o1 + o2).compareTo(o2 + o1));
        return String.join("", list);
    }


    public int cuttingRope1(int n) {
        //长度为i的绳子，可获取的最大乘积
        int[] dp = new int[n + 1];
        dp[2] = 1;
        //i 表示当前绳子长度
        //j 表示减去j
        for (int i = 3; i < n + 1; i++) {
            for (int j = 2; j < i; j++) {
                //减去j,剩余不剪
                int sum1 = (i - j) * j;
                //剪去j ,剩余 剪
                int sum2 = j * dp[i - j];
                dp[i] = Math.max(dp[i], Math.max(sum1, sum2)) % 1000000007;
            }
        }
        return dp[n] % 1000000007;
    }

    public static void main(String[] args) {
        MainJuly main = new MainJuly();
        int[] nums = new int[]{1, 2, 3, 4, 5};
        int[] speed = new int[]{4, 5, 3, 2, 1};
        System.out.println(main.validateStackSequences(nums, speed));
    }

}
