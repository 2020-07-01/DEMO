package leetcode.leetcode2021;

/**
 * @ClassName : TreeNode
 * @Author : yq
 * @Date: 2021-01-10
 * @Description :
 */
class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    TreeNode() {
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
